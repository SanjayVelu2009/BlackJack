// Sanjay Velu & Neel Murthy
// Main class with all the panels
// Game logic class and text file class will be called in

// Imports for the JFrame/Java Elements, etc.
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
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileWriter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



public class TrigJack
{
    public TrigJack() 
    {
	}

	public static void main(String[] args)
    {
		TrigJack tj = new TrigJack();
		tj.runIt();
	}
    public void runIt()
    {
        JFrame frame = new JFrame("TrigJack");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 50);
        frame.setResizable(true);

        TrigJackHolder tjh = new TrigJackHolder();
        frame.getContentPane().add(tjh);
        frame.setVisible(true);
    }
}

class ButtonStyler
{
    public void styleButton(JButton button)
    {
        button.setBackground(new Color(255, 215, 0));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("DialogInput", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setOpaque(true);
    }
}

class TrigJackHolder extends JPanel
{
    public TrigJackHolder()
    {
        CardLayout card = new CardLayout();
        setLayout(card);

        StartMenu sm = new StartMenu(this, card);
        Instructions ins = new Instructions(this, card);
        Highscores hs = new Highscores(this, card);
        PlayMenu pm = new PlayMenu(this, card);
        Problem prob = new Problem(this, card);
        Solution sol = new Solution(this, card);
        Names nam = new Names(this, card);

        add(sm, "Start");
        add(ins, "Instructions");
        add(hs, "Highscores");
        add(pm, "Playing");
        add(prob, "Problem");
        add(sol, "Solution");
        add(nam, "Name");

        card.show(this, "Start");
    }
    
   
}

class StartMenu extends JPanel implements ActionListener
{
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    private JButton playButton, instructionsButton, highScoresButton, quitButton;
    private JLabel titleLabel;
    private Image backgroundImage;
    private ButtonStyler styler = new ButtonStyler();
    

    public StartMenu(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        trigHolder = trigHolderIn;
        cards = cardsIn;

        setLayout(null);

        try
        {
			//for loop
				backgroundImage = ImageIO.read(new File("background.jpeg"));
            //array.append(backgroundImage)
        }
        catch (IOException e)
        {
            System.err.println("Background image not found: " + e.getMessage());
            setBackground(new Color(53, 101, 77));
        }

        titleLabel = new JLabel("TRIGJACK");
        titleLabel.setFont(new Font("DialogInput", Font.BOLD, 90));
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setBounds(260, 50, 800, 100);
        add(titleLabel);

        playButton = new JButton("Play");
        playButton.setBounds(390, 300, 200, 60);
        styler.styleButton(playButton);
        playButton.addActionListener(this);
        add(playButton);

        instructionsButton = new JButton("Instructions");
        instructionsButton.setBounds(390, 380, 200, 60);
        styler.styleButton(instructionsButton);
        instructionsButton.addActionListener(this);
        add(instructionsButton);

        highScoresButton = new JButton("High Scores");
        highScoresButton.setBounds(390, 460, 200, 60);
        styler.styleButton(highScoresButton);
        highScoresButton.addActionListener(this);
        add(highScoresButton);

        quitButton = new JButton("Quit");
        quitButton.setBounds(390, 540, 200, 60);
        styler.styleButton(quitButton);
        quitButton.addActionListener(this);
        add(quitButton);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (backgroundImage != null)
        {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        Object src = evt.getSource();

        if (src == playButton)
        {
            cards.show(trigHolder, "Name");
        }
        else if (src == instructionsButton)
        {
            cards.show(trigHolder, "Instructions");
        }
        else if (src == highScoresButton)
        {
            cards.show(trigHolder, "Highscores");
        }
        else if (src == quitButton)
        {
            System.exit(1);
        }
    }
}

class Instructions extends JPanel implements ActionListener
{
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    private JButton home;
    private JScrollPane instructions;
    private JTextArea text;
    private Image backgroundImage1;
    private ButtonStyler styler = new ButtonStyler();

    public Instructions(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        cards = cardsIn;
        trigHolder = trigHolderIn;

        setLayout(null);

        try
        {
            backgroundImage1 = ImageIO.read(new File("instructions.jpeg"));
        }
        catch (IOException e)
        {
            System.err.println("Background image not found: " + e.getMessage());
            setBackground(new Color(53, 0, 77));
        }

        home = new JButton("Home");
        home.setBounds(390, 700, 200, 60);
        styler.styleButton(home);
        home.addActionListener(this);
        add(home);

        text = new JTextArea(30, 50);
        text.setText("The game aims to bet against the dealer to see whose hand is closest to 21.\n\n" +
            "Cards:\n" +
            "- 2–10: Worth face value.\n" +
            "- J, Q, K: Worth 10.\n" +
            "- A: Worth 1 or 11.\n\n" +
            "Gameplay:\n" +
            "- Minimum bet: $10.\n" +
            "- Start with $1000. \n" +
            "- Players and dealer are each dealt two cards (one of dealer’s face-down).\n\n" +
            "Blackjack:\n" +
            "- If player gets 21 and dealer doesn't, player wins 1.5x their bet.\n\n" +
            "Insurance:\n" +
            "- Available if dealer shows an Ace.\n" +
            "- Win 2x insurance bet if dealer has Blackjack.\n" +
            "- Max insurance bet: 50% of original bet.\n\n" +
            "Example:\n" +
            "- Bet $10, Insurance $5. Dealer Blackjack → break even.\n\n" +
            "Player Options:\n" +
            "- Hit: Draw another card.\n" +
            "- Stand: Keep current hand.\n" +
            "- Split: Divide two matching cards into two hands.\n" +
            "- Double Down: Double bet, draw one card, and stand.\n\n" +
            "Dealer Rules:\n" +
            "- Dealer reveals second card after player.\n" +
            "- Dealer hits or stands according to standard rules.\n\n" +
            "Trig Challenge on Bust:\n" +
            "- Correct answer: Lose half bet.\n" +
            "- Incorrect answer: Lose full bet.\n" +
            "- Wrong? Step-by-step solution shown.\n\n" +
            "Winning & Losing:\n" +
            "- Start: $1000.\n" +
            "- Goal: Reach $10,000 to win.\n" +
            "- Lose: Bankrupt.\n" +
            "- Final Challenge if bankrupt.\n\n" +
            "High Scores:\n" +
            "- The lowest number of rounds to reach $10000 is on the leaderboard!\n\n");
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);

        instructions = new JScrollPane(text);
        instructions.setBounds(50, 50, 900, 600);
        add(instructions);
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getActionCommand().equals("Home"))
        {
            cards.show(trigHolder, "Start");
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (backgroundImage1 != null)
        {
            g.drawImage(backgroundImage1, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class Highscores extends JPanel implements ActionListener
{
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    private JButton home;
    private ButtonStyler styler = new ButtonStyler();

    public Highscores(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        cards = cardsIn;
        trigHolder = trigHolderIn;

        setLayout(null);
        setBackground(new Color(53, 101, 77));

        home = new JButton("Home");
        home.setBounds(390, 700, 200, 60);
        styler.styleButton(home);
        home.addActionListener(this);
        add(home);
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getActionCommand().equals("Home"))
        {
            cards.show(trigHolder, "Start");
        }
    }
}

class Names extends JPanel implements ActionListener
{
    private JTextField name;
    private JLabel nameLabel;
    private JButton play;
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    private ButtonStyler styler = new ButtonStyler();
    private Image backgroundImage1;
    private JCheckBox understand;
    private JButton back;
    private boolean insure;
    private String playerName;
    private PlayMenu pm = new PlayMenu(trigHolder, cards);

    public Names(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        try
        {
            backgroundImage1 = ImageIO.read(new File("instructions.jpeg"));
        }
        catch (IOException e)
        {
            System.err.println("Background image not found: " + e.getMessage());
            setBackground(new Color(53, 0, 77));
        }

        cards = cardsIn;
        trigHolder = trigHolderIn;

        setLayout(null);
        setBackground(new Color(53, 101, 77));

        nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(340, 300, 300, 30);
        nameLabel.setFont(new Font("DialogInput", Font.BOLD, 18));
        add(nameLabel);

        name = new JTextField();
        name.setBounds(340, 340, 300, 30);
        add(name);
        
        understand = new JCheckBox("I have read and understood the instructions");
        understand.setForeground(Color.WHITE);
        understand.setBounds(340, 400, 400, 30);
        understand.setFont(new Font("DialogInput", Font.BOLD, 12));
        add(understand);

		back = new JButton("Home");
		back.setBounds(270, 700, 200, 60);
		styler.styleButton(back);
		back.addActionListener(this);
		add(back);

        play = new JButton("Play Game");
        play.setBounds(520, 700, 200, 60);
        styler.styleButton(play);
        play.addActionListener(this);
        add(play);
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getActionCommand().equals("Play Game"))
        {
            String playerName = name.getText().trim();
            if (playerName.isEmpty() && understand.isSelected() == false)
            {
                nameLabel.setText("Name required to play!");
                nameLabel.setForeground(Color.RED);
            }
            else if (understand.isSelected() == false)
            {
				nameLabel.setText("Please review the instructions!");
				nameLabel.setForeground(Color.RED);
			}
            else
            {
                saveNameToFile(playerName);
                nameLabel.setText("Enter Your Name:");
                nameLabel.setForeground(Color.WHITE);
                cards.show(trigHolder, "Playing");
            }
        }
        else if (evt.getActionCommand().equals("Home"))
        {
			cards.show(trigHolder, "Start");
		}
		
		playerName = name.getText();
		pm.saveName(playerName);
    }


    public void saveNameToFile(String name)
    {
        try (FileWriter writer = new FileWriter("players.txt", true))
        {
            writer.write(name + "\n");
        }
        catch (IOException e)
        {
            System.err.println("Error saving name: " + e.getMessage());
        }
        
        
       
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (backgroundImage1 != null)
        {
            g.drawImage(backgroundImage1, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class PlayMenu extends JPanel
{
	private JSlider betting;
	private JMenu menuOpt;
	private JMenuBar menuBar;
	private JMenuItem split;
	private JMenuItem doubleDown;
	private JMenuItem insure;
	private JButton allIn;
	private JButton hit;
	private JButton stand;
	
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    
    private ButtonStyler styler = new ButtonStyler();
    private Image backgroundImage2;
    private JLabel moneyTrack;
    private boolean insureCheck;
    
    private Game game = new Game();
    private String name = "";
    private Player player;
    private int moneyAmt;
    private int totalAmt;
    private boolean gameState;
    private Deck deck = new Deck();
    private Dealer dealer = new Dealer();
    
    private String[] playerCards = new String[3];
    private String[] dealerCards = new String[3];
    private int insureAmount = 0;

    public PlayMenu(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
		trigHolder = trigHolderIn;
		cards = cardsIn;
		try
        {
            backgroundImage2 = ImageIO.read(new File("playMenu.jpeg"));
        }
        catch (IOException e)
        {
            System.err.println("Background image not found: " + e.getMessage());
            setBackground(new Color(53, 101, 77));
        }
           
        
        moneyTrack = new JLabel("Amount Bet $0");
		moneyTrack.setForeground(Color.WHITE);
           
        setLayout(new BorderLayout());
        setBackground(new Color(53, 101, 77));
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		
		
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BorderLayout());
		add(westPanel, BorderLayout.WEST);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1));
		add(buttonPanel, BorderLayout.WEST);
		
        betting = new JSlider(0,2000,0);
        betting.setMajorTickSpacing(200);
	    betting.setPaintLabels(true);
	    betting.setFont(new Font("Serif", Font.PLAIN,15));
	    betting.setForeground(new Color(255, 215, 0));
	    betting.setOrientation(JSlider.HORIZONTAL);
	    
	    class SliderControl implements ChangeListener
	    {
			public void stateChanged(ChangeEvent evt)
			{
				moneyAmt = betting.getValue();
				
				if (moneyAmt > 0)
				{
					moneyTrack.setText(" Amount Bet $" + moneyAmt);
					repaint();
				}
				else if (moneyAmt == 0)
				{
					moneyTrack.setText("Amount Bet $0");
					repaint();
				}
			}
		}
	    
	    betting.addChangeListener(new SliderControl());
	    
	    JPanel labelPanel = new JPanel();
		labelPanel.setLayout(null);
		add(labelPanel, BorderLayout.EAST);
		labelPanel.add(moneyTrack);
		moneyTrack.setBounds(700,500,100,50);		
	   
		JMenuItem insure = new JMenuItem("insurance");
		JMenuItem doubleDown = new JMenuItem("double down");
		//JMenuItem split = new JMenuItem("split");
		
		menuOpt = new JMenu("Options");
		menuOpt.setForeground(new Color(255, 215, 0));
		
		menuOpt.add(insure);
		menuOpt.add(doubleDown);
		//menuOpt.add(split);
		
		menuBar = new JMenuBar();
		menuBar.add(menuOpt);
		
		
		hit = new JButton("HIT");
		styler.styleButton(hit);
		stand = new JButton("STAND");
		styler.styleButton(stand);
		
        
        //int currentMoney = game.totalMoney() - betting.getValue();
        
        //tArea.setText(" Current Balance " + (currentMoney) +"\n"
        
        gameState = false;
		
		buttonPanel.add(hit);
		buttonPanel.add(stand);
		
		add(betting,BorderLayout.SOUTH);
		add(menuBar,BorderLayout.NORTH);
		
		saveName(name);
		//create a really small JTextArea to show amount bet and total amount of money 
		//betMoney method does that
		//initAndShuffleDeck
		//initHands
		//insuranceOption
		//playerTurn
		//dealerTurn
		//determine who won
		//settleAmount
		//problem if needed
		//solution if needed
		//playAgain
		//when implementing double down don't forget to double the bet amount
		
    }
    
    public void saveName(String naming)
    {
		name = naming;
		player = new Player(name);
		System.out.println(name);
	}
    
    public void betMoney()
    {
		
		totalAmt = player.placeBet(moneyAmt, gameState);
		
		
		//pass this into a method and if they won add amtBet to current balance, update, and do vice versa
	}
	
	public void initAndShuffleDeck()
	{
		deck.initializeDeck();	//initializes decks
		deck.shuffleDeckAndImage(); //shuffles deck array and image array
		insureCheck = game.insureCheck();	//initializes hands
		
		if(insureCheck = true)
		{
			JOptionPane.showMessageDialog(PlayMenu.this, "The dealer has an Ace, would you like to pay insureance? If yes go to the menu and click on the insureance button.");
		}
		
		//do the message stuff using the JDropDownMenu and get input using JOPtionPane again
		
		
		for(int i = 0; i<4; i++)
		{
			playerCards[i] = player.showHand(i);	//saves the names (file names) of the cards to the string arrays
			dealerCards[i] = dealer.showHand(i);
		}
		
		deck.generateImage(playerCards[0]);
		deck.generateImage(playerCards[1]);
		deck.generateImage(dealerCards[1]);
		
		
		
	}
	

	public void playerTurn()
	{
		
	}
	
	public void dealerTurn()
	{
	
	}
	
	public void determineWhoWon()
	{
		
	}
	
	public void settleAmount()
	{
	
	}
	
	public void problemIfNeeded()
	{
	
	}
	
	public void solutionIfNeeded()
	{
	
	}
	
	public void playAgain()
	{
	
	}
    
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (backgroundImage2 != null)
		{
			g.drawImage(backgroundImage2, 0, 0, getWidth(), getHeight(), this);
		}
	}
    
}

class Problem extends JPanel
{
    public Problem(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        setLayout(null);
        setBackground(new Color(53, 101, 77));

        JLabel label = new JLabel("Problem Page");
        label.setBounds(350, 350, 200, 30);
        label.setForeground(Color.WHITE);
        add(label);
    }
}

class Solution extends JPanel
{
    public Solution(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        setLayout(null);
        setBackground(new Color(53, 101, 77));

        JLabel label = new JLabel("Solution Page");
        label.setBounds(350, 350, 200, 30);
        label.setForeground(Color.WHITE);
        add(label);
    }
}
