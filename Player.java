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
		playerHand = new Card[10];		//player hand array of cards
		currentBalance = 10000;			//starting amount $10,000
		numCards = 0;					//number of cards
		name = inName;					//name (being passed in)
		
	}
	
	public void dealCard(Card c)
	{
		playerHand[numCards] = c;		//deals card saves into the array
		System.out.println(playerHand[numCards].show() + " Player card");
		numCards++;		//increments number of cards
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
		
		if (currentBalance >= amt)		//checks if bet is not greater than the current money at hand
		{
			amountBet = amt;
			currentBalance -= amountBet;	//subtracts from the total amount
		}
		
		return amountBet;
	}
	
	public void returnWinnings(int amt)
	{
		currentBalance += amt;
		System.out.println(currentBalance + "Current Balance");		//adds the amount bet to current balance 
	}
	
	public boolean checkBlackJack()
	{
		if(getPlayerHandValue() <= 21)		//checking if the player busted
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
		//rendering all cards in the array, calls each on using the render method inside of the card class
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
		//gets the hand value of the player 
		for(int i = 0; i<numCards; i++)
		{
			totalPlayerValue += playerHand[i].getValue();
			//System.out.println(totalPlayerValue);
			
			if(playerHand[i].getValue() == 1)		//if there is an ACE it can be 1 or 11 depending on the current hand value
				handHasAce = true;
		}
		
		if (handHasAce && totalPlayerValue <= 11)
			totalPlayerValue += 10;
		
		return totalPlayerValue;
	}
	
	public int getPlayerAccountBalance()
	{
		return currentBalance;		//returns the current balance 
	}
	
	public String getPlayerName()
	{
		return name;		//returns name of the player 
	}
}
