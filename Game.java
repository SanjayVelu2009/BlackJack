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
	
	/* Allow betting only once */
	public int placeBet(int amt)
	{
		if (amt >= 5000)
		{
			amountBet = p.placeBet(amt);
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
			if( de.isHandEmpty() && dealerCard.show().contains("1"))
			{
				checkForInsurance = true;
			}
			de.dealCard(dealerCard);
		}

		Card playerCard = d.dealCard();
		p.dealCard(playerCard);

		return checkForInsurance;
	}
	
	public boolean insure(int insuranceAmount)
	{
		
		if((insuranceAmount > 0) && (amountBet > 0) && (amountBet/2 >= insuranceAmount))
		{
			amountInsured = insuranceAmount;
			return true;
		}
	
		return false;
	}
	
	/* @TODO implement playerDoubleDown */
	public void playerDoubleDown()
	{
		amountBet = 2*amountBet;
		Card doubleCard = d.dealCard();
		p.dealCard(doubleCard);
		
	}
	
	public boolean determineGameState()
	{
		if(p.playerWinOrLose())
			return true;
		else
			return false;
	}
	
	public boolean playerBustCheck()
	{
		int playerValue = p.getPlayerHandValue();
		//System.out.println(playerValue);
		
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
		return p.getPlayerAccountBalance();
	}
	
	public int getPotValue()
	{
		return potValue;
	}
	
	public boolean dealerReveal()
	{	
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
		String result = "false";	//for the dealer
		int dVal = de.getHandValue();
		int pVal = p.getPlayerHandValue();
	
		if(dVal <= 21)
		{
			if(pVal > dVal)
			{
				//player won
				result = "player won";
				p.returnWinnings(potValue);
			}
			
			else if(pVal < dVal)
			{
				//player lost
				result = "dealer won";
			}
			else
			{
				result = "push";
				p.returnWinnings(amountBet);
			}
		}
		else
		{
			result = "player won";
		}
		
		/* TODO Resetting potValue here messes up the message that says player has Won. */
		potValue = 0;
		
		return result;
		
	}
		
	public void render(Graphics g, JPanel panel, boolean hideIn)
	{
		boolean hide = hideIn;
		
		de.renderHand(g,panel,hide);
		p.renderHand(g,panel);
		
	}
	
	/* Reset Game back to initial state */
	public void reset()
	{
		amountBet = 0;
		potValue = 0;
		d.initializeDeck();
		d.shuffleDeck();	
		p.resetHand();
		de.resetHand();
	}
	
	public void showCards()
	{
		System.out.println("Player Cards");
		//p.showHand();
		System.out.println();	
		
		System.out.println("Dealer Cards");
		//de.showHand();
		System.out.println();
	}
}
