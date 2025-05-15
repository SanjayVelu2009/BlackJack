import javax.swing.JPanel;
import java.awt.Graphics;

public class Player
{
	
	private String name;
	private Card[] playerHand;
	private int numCards;
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
		numCards++;
	}
	
	public String showHand(int i)
	{
		String card = "";
		card = playerHand[i].show();
		return card;
	}
	
	public void hit(Card c)
	{
		playerHand[numCards] = c;
		numCards++;	
	}
	
	public void stand()
	{
		System.out.println("\nYour turn is over!\n");
	}
	
	public void split()
	{
		
		if((playerHand[0].getType() == playerHand[1].getType()) && 
		   (playerHand[0].getValue() == playerHand[1].getValue()))
		{
			Card[] splitHand = new Card[2];
			splitHand[0] = playerHand[1];
		}
		else
			System.out.println("Cards are not the same!");
		
	}
	
	/* @TODO Add checks to enforce amt>minimumBet */
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
	
	/* @TODO need to understand how checkBlackJack is used */
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
	
	/* @TODO Render Player Hand image */
	public void renderHand(Graphics g, JPanel panel)
	{
		
	}
	
	public int getPlayerHandValue()
	{
		int totalPlayerValue = 0;
		
		for(int i = 0; i<numCards-1; i++)
		{
			totalPlayerValue += playerHand[i].getValue();
		}
		
		return totalPlayerValue;
	}
}
