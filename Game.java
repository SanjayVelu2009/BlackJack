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
	}
	

	public int placeBet(int amt)
	{
		int amountBet = 0;
		amountBet = p.placeBet(amt);
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
	
	public void playerDoubleDown()
	{
		/* @TODO make sure to double the bet amount in the TrigJack class */
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
	
	/* @DONE do something about the dealtCard */
	public boolean dealerTurn()
	{
		if(de.mustHit() == true)
		{
			//print message the dealer decided to hit!
			Card dealHit = d.dealCard();
			de.hit(dealHit);
			return true;
		}
		
		else 
		{
			//print message The dealer decided to stand!
			return false;
		}
		
	}
	
	public String determine()
	{
		String greater = "false";	//for the dealer
	
		if(de.getHandValue() <= 21 && p.getPlayerHandValue() <=21)
		{
			if(de.getHandValue() > p.getPlayerHandValue())
			{
				//print message the player lost and that he will be directed to the problem page
				greater = "true";
			
			}
			else if(de.getHandValue() < p.getPlayerHandValue())
			{
				//print message the player won and how much money he won
				greater = "false";	
			}
			else
			{
				greater = "push";
			}
		}
		return greater;
		
	}
		
	/* @TODO Render Player and Dealer Hands */
	public void render(Graphics g, JPanel panel, boolean hideIn)
	{
		boolean hide = hideIn;
		
		de.renderHand(g,panel,hide);
		p.renderHand(g,panel);
		
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


