import java.util.Scanner;
public class Game
{
	private int roundNum;
	private String playerName;
	private int amtBet;
	private Player p;
	private Deck d;
	private Dealer de;
	private int totalDealerValue;
	private int totalPlayerValue;
	
	public Game()
	{
		d = new Deck();
		de = new Dealer();
		roundNum = 0;
		amtBet = 0;
		playerName = " ";
		totalDealerValue = 0;
		totalPlayerValue = 0;
	}
	
	public static void main(String[] args)
	{
		Game g = new Game();
		
		
		
		g.getInfo();
		
		g.determine();
	
		
	}
	
	public void getInfo()
	{
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Hello Player! Welcome to Blackjack, please enter your name!");
		playerName = keyboard.nextLine();
		
		
		p = new Player(playerName);
		
		betMoney();
		
		
	}
	
	public void dealCards()
	{
		
		
		initHands();
		showCards();
		
		
	}
	
	public void initHands()
	{
		Card playerCard1 = d.dealCard();
		Card dealerCard1 = d.dealCard();
		Card playerCard2 = d.dealCard();
		Card dealerCard2 = d.dealCard();
		
		p.initPlayerHand(playerCard1,playerCard2);
		
		de.initDealerCards(dealerCard1,dealerCard2);
		
		
		
	}
	
	public void showCards()
	{
		System.out.println("Player Cards");
		p.showHand();
		System.out.println();	
		
		System.out.println("Dealer Cards");
		de.showHand(false);
		System.out.println();
		
		
	}
	
	
	public void betMoney()
	{
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Please enter how much you would like to bet for this round!");
		
		amtBet = keyboard.nextInt();
		
		//Player p = new Player();
		
		boolean checker = p.placeBet(amtBet);
		
		if(checker == true)	//checking if amount bet is valid
		{
			dealCards();
			playerTurn();
			dealerTurn();
		}
		else
			System.out.println("Please enter a valid betting amount");
		
		
	}
	
	
	public void playerTurn()
	{
		String decision = "";
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Would you like to hit or stand?");
		decision = keyboard.nextLine();
		
		if(decision.equalsIgnoreCase("hit"))
		{
			Card hitting = d.dealCard();
			hitting.show();
			p.hit(hitting);
			
		}
		else if(decision.equalsIgnoreCase("stand"))
		{
			p.stand();
		}
		else
			System.out.println("Please enter a valid statement!");
		
		dealerReveal();
	}
	
	public void dealerReveal()
	{	
		if(p.checkBlackJack() == true)
		{
			System.out.println("Let's see the dealer's cards!");
			de.showHand(true);
			dealerTurn();
		}
		else
		{
			System.out.println("You lost this round!");
		}
		
			
	}
	
	public void dealerTurn()
	{
		
		if(de.checkHitOrStand() == true)
		{
			System.out.println("The dealer decided to hit!");
			Card dealHit = d.dealCard();
			dealHit.show();
			totalDealerValue = de.hit(dealHit);
		}
		else
		{
			System.out.println("The dealer decided to stand!");
			de.stand();
		}
		
		determine();
	}
	
	public void determine()
	{
		totalPlayerValue = p.getPlayerAmount();
		
		if(totalDealerValue <= 21 && totalPlayerValue <=21)
		{
			if(totalDealerValue > totalPlayerValue)
			{
				System.out.println("You Lost!");
			}
			
			else if(totalDealerValue < totalPlayerValue)
			{
				System.out.println("You won!");
			}
			
			else if(totalDealerValue == totalPlayerValue)
				System.out.println("Its a push!!!");
		}
		
	}
}





