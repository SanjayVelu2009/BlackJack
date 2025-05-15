import javax.swing.JPanel;
import java.awt.Graphics;
public class Dealer
{
	Card[] dealerHand;
	int numCards;
	
	//dealer must hit if both cards revealed are 16 or less and must stand if the total is 17 or more
	public Dealer()
	{
		dealerHand = new Card[10];
		numCards = 0;
	}
	
	public void dealCard(Card c)
	{
		dealerHand[numCards] = c;
		numCards++;
	}
	
	public void hit(Card dealerHit)
	{
		dealerHand[numCards-1] = dealerHit;
		numCards++;	
	}
	
	public String showHand(int i)
	{
		String card = dealerHand[i].show();
		
		return card;
	}
	
	/* @TODO need to understand how checkDealerBlackJack is used */
	public boolean checkDealerBlackJack()
	{
		if((getHandValue()) <= 21)
		{
			return true;
		}
		
		else
		{
			return false;
		}
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
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/* Return total value of the hand */
	public int getHandValue()
	{
		int totalValue = 0;
		
		for(int i = 0; i<numCards-1; i++)
		{
			totalValue += dealerHand[i].getValue();
		}
		
		return totalValue;
	}
		
		
	/* @TODO Render Dealer Hand image */
	public void renderHand(Graphics g, JPanel panel, boolean flip)
	{
		Card cardImage;
		
		for(int i = 0; i<dealerHand.length; i++)
		{
			cardImage = dealerHand[i]; 
			cardImage.render(g,panel);
		}
				
	}
}
