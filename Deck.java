import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Deck
{
	public Deck()
	{
	}
	
	public static void main(String[] args)
	{
		Deck df = new Deck();
		df.runIt();
	}
	
	public void runIt()
	{
		JFrame frame = new JFrame("Deck");
		frame.setSize(800, 800);				
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); 
		frame.setLocation(0,50);
		frame.setResizable(true);
		DeckPanel dp = new DeckPanel(); 		
		frame.getContentPane().add(dp);		
		frame.setVisible(true);		
	}
}
class DeckPanel extends JPanel
{
	
	private Card[] cardStack = new Card[52];
		
	private int topCard;
	
	private Image cardImage;
	
	private String cardName;
	
	
	public DeckPanel()
	{
		topCard = 0;
		
	}
	
	public static void main(String[] args)
	{
		DeckPanel testDeck = new DeckPanel();
		testDeck.runIt();
	}
		
	public void runIt()
	{
		initializeDeck();		
	}
	
	public void initializeDeck()
	{
		int cardIndex = 0;
		
		/* Create all cards in Heart Suite */
		for (int i=1; i<=13; i++)
		{	
			Card heartCard = new Card(i,'H');
			cardStack[cardIndex++] = heartCard;	
		}
		
		/* Create all cards in Clubs Suite */
		for (int x=1; x<=13; x++)
		{
			Card clubsCard = new Card(x,'C');
			cardStack[cardIndex++] = clubsCard;	
		}
		
		/* Create all cards in Diamond Suite */
		for (int k=1; k<=13; k++)
		{	
			Card diceCard = new Card(k,'D');	
			cardStack[cardIndex++] = diceCard;
		}
		
		/* Create all cards in Spade Suite */
		for (int j=1; j<=13; j++)
		{
			Card spadeCard = new Card(j,'S');
			cardStack[cardIndex++] = spadeCard;
		}
		
		String[] nameStack = {"1H.jpg","2H.jpg","3H.jpg","4H.jpg","5H.jpg","6H.jpg","7H.jpg","8H.jpg","9H.jpg","10H.jpg","11H.jpg","12H.jpg","13H.jpg","1C.jpg","2C.jpg","3C.jpg","4C.jpg","5C.jpg","6C.jpg","7C.jpg","8C.jpg","9C.jpg","10C.jpg","11C.jpg","12C.jpg","13C.jpg"
							+"1D.jpg","2D.jpg","3D.jpg","4D.jpg","5D.jpg","6D.jpg","7D.jpg","8D.jpg","9D.jpg","10D.jpg","11D.jpg","12D.jpg","13D.jpg","1S.jpg","2S.jpg","3S.jpg","4S.jpg","5S.jpg","6S.jpg","7S.jpg","8S.jpg","9S.jpg","10S.jpg","11S.jpg","12S.jpg","13S.jpg"};
		shuffleDeckAndImage(nameStack);
	}
	
	public  void showDeck()
	{
		for(int i = 0; i<=51; i++)
		{
			cardStack[i].show();
		}
	}
	
	public void shuffleDeckAndImage(String[] nameStack)
	{
		int b;
		String swapImage = "";
		
		
		for(int a = 0; a<nameStack.length; a++)
		{
			b = (int)(Math.random()*(51));
			
			if(b != a)
			{
				Card swapCard = cardStack[a];
				cardStack[a] = cardStack[b];
				cardStack[b] = swapCard;
				//shuffling Card deck
				
				swapImage = nameStack[a];
				nameStack[a] = nameStack[b];
				nameStack[b] = swapImage;
				
				//shuffling names of images
			}
			
			else
				a--;
			
			System.out.println(nameStack[a]);
		}
		
		generateImage(nameStack);
		
	}
	
	public void generateImage(String[] nameStack)
	{
		cardName = nameStack[0];
		
		try
		{
			cardImage = ImageIO.read(new File(cardName));
		}
		catch(IOException e)
		{
			System.err.println(" Error printing "+cardName);
			e.printStackTrace();
		}
	}
			
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (cardImage != null)
		{
			g.drawImage(cardImage, 0, 0, 200, 300, this);
		}
		
	}
	
	public Card dealCard()
	{
		Card deal = cardStack[topCard];
		topCard++;
		return deal;
	}
	

}
