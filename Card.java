
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileWriter;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.*;


public class Card
{
	private int value;
	private char type;
	private Image cardImage;
	private Image cardBackImage;
 
	
	public Card (int inVal, char inType)
	{
		value = inVal;
		type = inType;
		cardImage = readImg(getCardImgFileName());
		cardBackImage = readImg("images/card_back.jpg");
		
	}
	
	private String getCardImgFileName()
	{
		String imgFileName = "images/"+value+""+type+".jpg";
		return imgFileName;
	}
	
	private Image readImg(String cardName)
	{
		Image img = null;
		try
		{
			img = ImageIO.read(new File(cardName));
		}
		catch(IOException e)
		{
			System.err.println("Error printing "+cardName);
			e.printStackTrace();
		}
		
		return img;
	}
	
	public int getValue()
	{
		int retVal = value;
		
		if(value == 11 || value == 12 || value == 13)
		{
			retVal = 10;
		}
		
		return retVal;
	}
	
	public char getType()
	{
		return type;
	}
	
	public Image getImage()
	{
		return cardImage;
	}
	
	public void render(Graphics g, JPanel panel, int xpos, int ypos, boolean hide)
	{
		if (!hide)
		{
			g.drawImage(cardImage,xpos,ypos,cardImage.getWidth(panel)/4,cardImage.getHeight(panel)/4, panel);
		}
		else
		{	
			g.drawImage(cardBackImage,xpos,ypos,cardBackImage.getWidth(panel)/4,cardBackImage.getHeight(panel)/4, panel);
		}
	}
	
	public String show()
	{
		String card = "";
		String suite = "";
		
		if(type == 'S')		//if it is Spade
		{
			suite = "Spade";
		}
		
		else if(type == 'D')	//if it is Diamonds
		{
			suite = "Diamond";
		}
		
		else if(type == 'H') //If it is Heart
		{
			suite = "Heart";
		}
		
		else if(type == 'C') //if it is clubs
		{
			suite = "Clubs";
		}
		
		//Defining Face Cards
		if (value < 11)
		{	
			card = value+" "+suite;
			return card;
		}
		
		else if (value == 11)
		{
			card = "Jack "+suite;
			return card;
		}
		
		else if (value == 12)
		{
			card = "Queen "+suite;
			return card;
		}
		else if (value == 13)
		{
			card = "King " + suite;
			return card;
		}
		
		return card;
	}
	
}
