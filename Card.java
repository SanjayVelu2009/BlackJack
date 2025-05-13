public class Card
{
	private int value;
	private char type;
	
	public Card (int inVal, char inType)
	{
		value = inVal;
		type = inType;
	}
	
	public int getValue()
	{
		if(value == 11 || value == 12 || value == 13)
		{
			value = 10;
		}
		return value;
	}
	
	public char getType()
	{
		return type;
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
