import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.*;

public class Dealer
{
	Card[] dealerHand;		//dealer hand, array of Cards
	public int numCards;	//number of cards 
	
	//dealer must hit if both cards revealed are 16 or less and must stand if the total is 17 or more
	public Dealer()
	{
		dealerHand = new Card[10];
		numCards = 0;
	}
	
	public void dealCard(Card c)
	{
		dealerHand[numCards] = c;		//deals a card and adds it to the array
		System.out.println(dealerHand[numCards].show() + " Dealer cards");
		numCards++;		//increments the number of cards
	}
	
	/* Discard all cards from hand */
	public void resetHand()
	{
		numCards = 0;
	}
	
	public String showHand(int i)
	{
		String card = dealerHand[i].show();				
		
		return card;
	}
	
	
	/* Returns True if the Hand is empty and false otherwise */
	public boolean isHandEmpty()
	{
		return (numCards == 0);		
	}
	
	/* Check if the dealer must hit */
	public boolean mustHit()
	{
		if((getHandValue()) <= 16)
		{
			System.out.println("Dealer must hit "+getHandValue());
			return true;
		}
		else
		{
			System.out.println("Dealer need not hit "+getHandValue());
			return false;
		}
	}
	
	/* Return total value of the hand */
	public int getHandValue()
	{
		int totalValue = 0;
		boolean handHasAce = false;
		
		for(int i = 0; i<numCards; i++)
		{
			totalValue += dealerHand[i].getValue();
			
			if(dealerHand[i].getValue() == 1)
				handHasAce = true;
			System.out.println(totalValue+ "Value at each iteration");
		}
		
		/* BUG: Fixed for the check to be <=11 instead of <=10 */
		if (handHasAce && totalValue <= 11)
			totalValue += 10;
		
		System.out.println(totalValue+ "End Value of each hand");
		
		return totalValue;
	}
	
	/* Render Card images to the panel */
	public void renderHand(Graphics g, JPanel panel, boolean hideSecond)
	{
		Card dealerCard;
		boolean hideThisCard = false;
		
		for(int i = 0; i<numCards; i++)
		{		
			hideThisCard = ((hideSecond) && (i==1));
			dealerCard = dealerHand[i]; 
			dealerCard.render(g,panel, (i*200)+50, 50, hideThisCard);
		}			
	}
}
