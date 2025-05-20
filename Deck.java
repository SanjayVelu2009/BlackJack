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
	private Card[] cardStack = new Card[52];
	private int topCard;
	
	
	public Deck()
	{
		topCard = 0;
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
		
	}
	
	public  void showDeck()
	{
		for(int i = 0; i<=51; i++)
		{
			cardStack[i].show();
		}
	}
	
	public void shuffleDeck()
	{
		int b;		
		
		for(int a = 0; a<cardStack.length; a++)
		{
			b = (int)(Math.random()*(cardStack.length-1));
			
			if(b != a)
			{
				// Shuffling deck
				Card swapCard = cardStack[a];
				cardStack[a] = cardStack[b];
				cardStack[b] = swapCard;
			}
			else
				a--;
		}	
		
		topCard = 0;	
	}
			

	public Card dealCard()
	{
		Card deal = cardStack[topCard];
		topCard++;
		return deal;
	}
	

}
