
public class Game
{
	private int roundNum;
	//private String playerName;
	private int amtBet;
	private Player p;
	private Deck d;
	private Dealer de;
	private int totalDealerValue;
	private int totalPlayerValue;
	private boolean gameState;
	
	public Game(String name)
	{
		//create deck and shuffle
		d = new Deck();
		d.shuffleDeck();
		
		// create dealer and player
		de = new Dealer();
		p = new Player(name);
		
		roundNum = 0;
		amtBet = 0;
		totalDealerValue = 0;
		totalPlayerValue = 0;
		gameState = true;
	}
	

	public int placeBet()
	{
		return 0;
	}

	public boolean insureCheck()
	{
		boolean insureChecker = false;
		Card playerCard1 = d.dealCard();
		Card dealerCard1 = d.dealCard();
		if(dealerCard1.show().contains("1"))
		{
			insureChecker = true;
			
		}
		//~ else
			//~ return insureChecker;
	
		initHands(playerCard1, dealerCard1);
		return insureChecker;
	}
	
	public void initHands(Card playerCard1, Card dealerCard1)
	{
		
		Card playerCard2 = d.dealCard();
		Card dealerCard2 = d.dealCard();
		p.initPlayerHand(playerCard1,playerCard2);
		de.initDealerCards(dealerCard1,dealerCard2);
		
		//after this method was called in the PlayMenu show the image using image(animation & timer)
	}
	
	
		
	/*public void showCards()
	{
		System.out.println("Player Cards");
		p.showHand();
		System.out.println();	
		
		System.out.println("Dealer Cards");
		de.showHand(false);
		System.out.println();
	}*/
	
	public void playerTurn(boolean decision)
	{
		//The current options are to hit and stand
		if(decision == true)
		{
			Card hitting = d.dealCard();
			p.hit(hitting);
		}
		else
			p.stand();
		
		//after this method was called in the PlayMenu show the image using image(animation & timer)	 
	}
	
	public void playerDoubleDown()
	{
		Card doubler = d.dealCard();
		p.hit(doubler);
		//make sure to double the bet amount in the TrigJack class
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
		if(de.checkHitOrStand() == true)
		{
			//print message the dealer decided to hit!
			Card dealHit = d.dealCard();
			totalDealerValue = de.hit(dealHit);
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
		totalPlayerValue = p.getPlayerAmount();
		
		if(totalDealerValue <= 21 && totalPlayerValue <=21)
		{
			if(totalDealerValue > totalPlayerValue)
			{
				//print message the player lost and that he will be directed to the problem page
				greater = "true";
			
			}
			else if(totalDealerValue < totalPlayerValue)
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
	
}




