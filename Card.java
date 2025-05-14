import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileWriter;
public class Card
{
	private int value;
	private char type;
	private Image cardImage;
 
	
	public Card (int inVal, char inType)
	{
		value = inVal;
		type = inType;
		cardImage = readImg(getCardImgFileName());
		
	}
	
	private String getCardImgFileName()
	{
		String imgFileName = value+""+type+".jpg";
		return imgFileName;
	}
	
	private Image readImg(String cardName)
	{
		try
		{
			cardImage = ImageIO.read(new File(cardName));
		}
		catch(IOException e)
		{
			System.err.println("Error printing "+cardName);
			e.printStackTrace();
		}
		
		return cardImage;
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
