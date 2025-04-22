import java.util.Scanner;
public class Player
{
	
	private String name;
	private Card[] drawnCards;
	private int numCards;
	private int currentBalance;
	private int amountBet;
	private int jackChecker;
	
	
	public Player(String inName)
	{
		drawnCards = new Card[10];
		currentBalance = 1000;
		amountBet = -1;
		numCards = 0;
		name = inName;
		
	}
	
	public void initPlayerHand(Card card1, Card card2)
	{
		drawnCards[0] = card1;
		numCards++;
		
		
		drawnCards[1] = card2;
		numCards++;
	}
	
	public void showHand()
	{
		for(int i = 0; i<numCards; i++)
		{
			drawnCards[i].show();
		}
	}
	public void hit(Card c)
	{
		drawnCards[numCards] = c;
		numCards++;	
	}
	
	public void stand()
	{
		
	}
	
	public void split()
	{
		
		if((drawnCards[0].getType() == drawnCards[1].getType()) && (drawnCards[0].getValue() == drawnCards[1].getValue()))
		{
			Card[] splitHand = new Card[2];
			splitHand[0] = drawnCards[1];
		}
		else
			System.out.println("Cards are not the same!");
		
	}
	
	public boolean placeBet(int amt)
	{
		boolean betSuccess = false;
		
		if (currentBalance >= amt)
		{
			amountBet += amt;
			currentBalance -= amountBet;	
			betSuccess = true;
		}
		
		return betSuccess;
	}
	
	public boolean checkBlackJack()
	{
		jackChecker = 0;
		for(int i = 0; i<numCards; i++)
		{
			jackChecker+=drawnCards[i].getValue();
		}
		
		if(jackChecker <= 21)
		{
			System.out.println("You didn't Bust!");
			return true;
		}
		else
		{
			System.out.println("You Busted!");
			return false;
		}
		
		
	}
	
	public int getPlayerAmount()
	{
		return jackChecker;
	}
}

