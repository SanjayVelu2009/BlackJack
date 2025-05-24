import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.*;


public class Player
{
	
	private String name;
	private Card[] playerHand;
	public int numCards;
	private int currentBalance;
	
	
	public Player(String inName)
	{
		playerHand = new Card[10];
		currentBalance = 10000;
		numCards = 0;
		name = inName;
		
	}
	
	public void dealCard(Card c)
	{
		playerHand[numCards] = c;
		System.out.println(playerHand[numCards].show() + " Player card");
		numCards++;
	}
	
	public boolean insure(int insuranceAmount)
	{
		boolean success = false;
		
		if (insuranceAmount <= currentBalance)
		{
			currentBalance -= insuranceAmount;
			success = true;
		}
		return success;
	}
	
	/* Discard all cards from hand */
	public void resetHand()
	{
		numCards = 0;
	}
	
	public String showHand(int i)
	{
		String card = "";
		card = playerHand[i].show();
		return card;
	}
	
	public void stand()
	{
		System.out.println("\nYour turn is over!\n");
	}
	
	
	public int placeBet(int amt)
	{
		int amountBet = 0;
		
		if (currentBalance >= amt)
		{
			amountBet = amt;
			currentBalance -= amountBet;	
		}
		
		return amountBet;
	}
	
	public void returnWinnings(int amt)
	{
		currentBalance += amt;
		System.out.println(currentBalance + "Current Balance");
	}
	
	public boolean checkBlackJack()
	{
		if(getPlayerHandValue() <= 21)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean playerWinOrLose()
	{
		if(currentBalance <= 0)
		{
			return true;
		}
		else
			return false;
	}
	/*TODO renderHand for iterations should numCards be subtracted by one? */
	public void renderHand(Graphics g, JPanel panel)
	{
		Card playerCard;
		System.out.println("Num Cards "+numCards);
		for(int i = 0; i<numCards; i++)
		{
			playerCard = playerHand[i];
			playerCard.render(g, panel, (i*200)+50, 300, false);
		}
	}
	
	public int getPlayerHandValue()
	{
		int totalPlayerValue = 0;
		boolean handHasAce = false;
		
		for(int i = 0; i<numCards; i++)
		{
			totalPlayerValue += playerHand[i].getValue();
			//System.out.println(totalPlayerValue);
			
			if(playerHand[i].getValue() == 1)
				handHasAce = true;
		}
		
		if (handHasAce && totalPlayerValue <= 11)
			totalPlayerValue += 10;
		
		return totalPlayerValue;
	}
	
	public int getPlayerAccountBalance()
	{
		return currentBalance;
	}
	
	public String getPlayerName()
	{
		return name;
	}
}
