import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.*;

public class Game
{

	private Player p;
	private Dealer de;
	private Deck d;	
	
	private boolean gameState;
	private int roundNum;
	private int potValue;
	private int amountBet;
	private int amountInsured;
	private int lastAmountBet;
	
	public Game(String name)
	{
		//create deck and shuffle
		d = new Deck();
		d.shuffleDeck();
		
		// create dealer and player
		de = new Dealer();
		p = new Player(name);
		
		roundNum = 0;
		potValue = 0;
		gameState = true;
		amountBet = 0;
		amountInsured = 0;
	}
	
	public void rewardPlayer()
	{
		p.returnWinnings(lastAmountBet/2);
	}
	
	/* Allow betting only once */
	public int placeBet(int amt)
	{
		if (amt >= 5000)
		{
			amountBet += p.placeBet(amt);
			if (amountBet > 0)
				potValue = amountBet * 2;
		}
		return amountBet;
	}
	
	/* Deal one card each to dealer and player 
	 * Returns: TRUE if Dealer has an ACE and if this is the first card in the hand and FALSE otherwise */
	public boolean dealCards(boolean playerOnly)
	{
		boolean checkForInsurance = false;
		
		if (!playerOnly)
		{
			Card dealerCard = d.dealCard();
			if( de.isHandEmpty() && (dealerCard.getValue() == 1) && (p.getPlayerAccountBalance() > 0))
			{
				checkForInsurance = true;
			}
			de.dealCard(dealerCard);
			//System.out.println(dealerCard.show() + " Dealer Card");
		}

		Card playerCard = d.dealCard();
		p.dealCard(playerCard);		//deals the card to the player

		return checkForInsurance;		//returns the insurance check
	}
	
	public boolean insure(int insuranceAmount)
	{
		
		if((insuranceAmount > 0) && (amountBet > 0) && (amountBet/2 >= insuranceAmount))	//seeing if the insurance amount is probable in the game
		{
			if (p.insure(insuranceAmount))
			{
				amountInsured = insuranceAmount;
				return true;
			}
		}
	
		return false;
	}
	
	public boolean playerDoubleDown()
	{
		//double down, where the bet gets doubled and the player automatically hits, ending their turn
		boolean doubleDownSuccess = false;
		
		
		if (placeBet(amountBet) > 0)
		{
			Card doubleCard = d.dealCard();
			p.dealCard(doubleCard);
			doubleDownSuccess = true;
		}
		
		return doubleDownSuccess;
		
	}
	
	public boolean determineGameState()		//determines game state and returns it
	{
		if(p.playerWinOrLose())
			return true;
		else
			return false;
	}
	
	public boolean playerBustCheck()		//checks if the player busted or went over 21
	{
		int playerValue = p.getPlayerHandValue();
		
		if(playerValue > 21)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public int getPlayerAccountBalance()
	{
		return p.getPlayerAccountBalance();		//returns the current balance in the players account
	}
	
	public int getPotValue()
	{
		return potValue;		//returns pot value which is both how much the dealer bet and the player (which are both the same)
	}
	
	public boolean dealerReveal()
	{	
		//dealer reveals the flipped down card
		if(p.checkBlackJack() == true)
		{
			//de.showHand(true);	
			dealerTurn();		
			return true;
		}
		else
		{
			return false;
		}
		
		//if it returns true send this message ("Let's see the dealer's cards!") or if it is fale return ("You lost this round!")		
	}
	
	
	public boolean dealerTurn()
	{
		boolean hitOnce = false;
									 	//checks if dealer must hit, if so they get a card dealed to them
		while (de.mustHit())
		{
			//print message the dealer decided to hit!
			Card dealHit = d.dealCard();
			de.dealCard(dealHit);
			hitOnce = true;
		}
		
		return hitOnce;
		
	}
	
	/* @TODO If Player wins, add amtBet to player balance. Reset AmtBet to zero for next round */
	public String settle()
	{
		String result = "";	
		int dVal = de.getHandValue();
		int pVal = p.getPlayerHandValue();
	
		System.out.println("D Value: "+dVal+" P Value: "+pVal+" Pot Value: "+potValue);
		
		if(dVal <= 21)
		{
			if(pVal > dVal)		//player has higher than dealer
			{
				//player won
				result = "player won";
				p.returnWinnings(potValue);
				amountInsured = 0;
			}
			
			else if(pVal < dVal)		//player has lower than dealer 
			{
				//player lost
				result = "dealer won";
				if (amountInsured != 0)
				{
					p.returnWinnings(amountInsured*2);
					amountInsured = 0;
				}
			}
			else
			{
				result = "push";
				p.returnWinnings(amountBet);
			}
		}
		else 				//dealer has more than 21, causing them to bust, the player automatically winning
		{
			result = "player won";
			p.returnWinnings(potValue);
			amountInsured = 0;
		}
		
		
		
		return result;
		
	}
	
	public int getAmtInsured()
	{
		return amountInsured;
	}
	
	public boolean finalProblem()
	{
		if(p.getPlayerAccountBalance() < 5000)		//checks if the account has less than 5000 which is the minimum betting amount causing the player to lose and do their punishment problem
		{
			return true;
		}
		
		return false;
	}
		
	public void render(Graphics g, JPanel panel, boolean hideIn)
	{
		boolean hide = hideIn;
		//renders hands by calling the render hands in dealer and player 
		de.renderHand(g,panel,hide);
		p.renderHand(g,panel);
		
	}
	
	/* Reset Game back to initial state */
	public void reset()
	{
		lastAmountBet = amountBet;
		amountBet = 0;
		potValue = 0;
		amountInsured = 0;
		d.initializeDeck();
		d.shuffleDeck();	
		p.resetHand();
		de.resetHand();
	}
	
	public void showCards()			//prints cards (used for testing)
	{
		System.out.println("Player Cards");
		//p.showHand();
		System.out.println();	
		
		System.out.println("Dealer Cards");
		//de.showHand();
		System.out.println();
	}
}
