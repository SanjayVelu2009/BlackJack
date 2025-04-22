public class Dealer
{
	Card[] dealHand;
	int numCards;
	//dealer must hit if both cards revealed are 16 or less and must stand if the total is 17 or more
	public Dealer()
	{
		dealHand = new Card[10];
		numCards = 0;
		
		
	}
	
	public void initDealerCards(Card card1, Card card2)
	{
		dealHand[0] = card1;
		numCards++;
		
		dealHand[1] = card2; //needs to be face down will be done in the game class
		numCards++;
	}
	
	public int hit(Card dealerHit)
	{
		dealHand[numCards-1] = dealerHit;
		numCards++;
		
		int totalValue = 0;
		
		for(int i = 0; i<numCards-1; i++)
		{
			totalValue += dealHand[i].getValue();
		}
		
		return totalValue;
	}
	
	public void stand()
	{
		System.out.println("The dealer's turn is over!");
	}
	
	public void showHand(boolean showing)
	{
		if(showing == true)
		{
			dealHand[0].show();
			dealHand[1].show();
		}
		
		else
		{
			dealHand[0].show();
		}
	}
	
	public boolean checkDealerBlackJack()
	{
		if((dealHand[0].getValue() + dealHand[1].getValue()) <= 21)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public boolean checkHitOrStand()
	{
		if((dealHand[0].getValue() + dealHand[1].getValue()) <= 16)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

