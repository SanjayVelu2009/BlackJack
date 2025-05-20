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
	}
	
	
	public int placeBet(int amt)
	{
		amountBet += p.placeBet(amt);
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
	
	/* @TODO implement playerDoubleDown */
	public void playerDoubleDown()
	{
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
	public String determine()
	{
		String greater = "false";	//for the dealer
		int dVal = de.getHandValue();
		int pVal = p.getPlayerHandValue();
	
		if(dVal <= 21)
		{
			if(pVal > dVal)
			{
				//player won
				greater = "false";
			}
			
			else if(dVal > pVal)
			{
				//player lost
				greater = "true";
			}
			else if(dVal == pVal)
			{
				greater = "push";
			}
		}
		else
		{
			greater = "false";
		}
		
		return greater;
		
	}
		
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

