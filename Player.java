import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.*;


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
	
	public int placeBet(int amt)
	{
		int amountBet = 0;
		
		if (currentBalance >= amt && amt>= 5000)
		{
			amountBet = amt;
			currentBalance -= amountBet;	
		}
		
		return amountBet;
	}
	
	public void addBet(int amt)
	{
		amt = 2*(amt);
		currentBalance += amt;
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
	
	public void renderHand(Graphics g, JPanel panel)
	{
		Card playerCard;
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
		
		if (handHasAce && totalPlayerValue <= 10)
			totalPlayerValue += 10;
		
		return totalPlayerValue;
	}
	
	public int getPlayerAccountBalance()
	{
		return currentBalance;
	}
}
