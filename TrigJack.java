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
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.lang.NumberFormatException;
import java.util.Scanner;

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
			backgroundImage = ImageIO.read(new File("background.jpeg"));
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
            System.err.println("Instructions image not found: " + e.getMessage());
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
            "- Minimum bet: $5000.\n" +
            "- Start with $10000. \n" +
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
            System.err.println("Instructions image not found: " + e.getMessage());
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
	private JButton doubleDown;
	private JButton insure;
	private JButton hit;
	private JButton stand;
	private JMenuItem home;
	private JMenuItem newGame;
	
	private JLabel potValue;
	private JLabel playerBalance;
	private JLabel instructions;
	private JLabel insuranceLabel;
	
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    
    private ButtonStyler styler = new ButtonStyler();
    private Image backgroundImage2;
    private JLabel moneyTrack;
    private boolean insureCheck;
    
    private Game game;
    
    private String name = "";
    private Player player;
    private int betAmount;
    private int insureAmt;
    private boolean gameActive;

	private JPanel centerPanel;
	private JPanel buttonPanel;
	
	private boolean betButton = false;
	private boolean hide = true;
	private boolean problemSwitch = false;
	private int lastSliderPosition = 0;
	private boolean successfulBetThisRound = false;
	
    public PlayMenu(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
		trigHolder = trigHolderIn;
		cards = cardsIn;
           
        try
        {
            backgroundImage2 = ImageIO.read(new File("instructions.jpeg"));
        }
        catch (IOException e)
        {
            System.err.println("Instructions image not found: " + e.getMessage());
            setBackground(new Color(53, 0, 77));
        }
        /*Main Panels*/   
        setLayout(new BorderLayout());
        setBackground(new Color(53, 101, 77));
		
		CardsPanel cp = new CardsPanel();
		
			
		createBetSlider();
		createBettingLabels();
	    createMainMenu();   
		createButtons();
		createBettingLabels();
		
		cp.add(potValue);
		cp.add(playerBalance);
		cp.add(insuranceLabel);
		cp.add(instructions);
		
        gameActive = true;
		
		saveName(name);
		game = new Game(name);
						
		add(menuBar,BorderLayout.NORTH);
		add(betting,BorderLayout.SOUTH);
		add(cp, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.WEST);
		
		instructions.setText("Place your bets! Minimum Bet: $5000");
		
		repaint();
		
		
    }
    
    /* @TODO create Labels to reflect Pot Value and Players' current balance 
     * @TODO Position the labels right below the cards and above the slider */
    public void createBettingLabels()
    {
		potValue = new JLabel("Pot Value: 0");
		potValue.setFont(new Font("Serif", Font.BOLD, 20));
		potValue.setForeground(new Color(255, 215, 0));
		potValue.setHorizontalAlignment(SwingConstants.LEFT);
		potValue.setPreferredSize(new Dimension(0,25));
		potValue.setBounds(50, 600, 200, 30);
		
		playerBalance = new JLabel("Balance: 0");
		playerBalance.setFont(new Font("Serif", Font.BOLD, 20));
		playerBalance.setHorizontalAlignment(SwingConstants.LEFT);
		playerBalance.setForeground(new Color(255, 215, 0));
		playerBalance.setPreferredSize(new Dimension(0,25));
		playerBalance.setBounds(300, 600, 200, 30);	
		
		insuranceLabel = new JLabel("Insurance: 0");
		insuranceLabel.setFont(new Font("Serif", Font.BOLD, 20));
		insuranceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		insuranceLabel.setForeground(new Color(255, 215, 0));
		insuranceLabel.setPreferredSize(new Dimension(0,25));
		insuranceLabel.setBounds(550, 600, 200, 30);		
		
		instructions = new JLabel("");
		instructions.setFont(new Font("Serif", Font.BOLD, 20));
		instructions.setHorizontalAlignment(SwingConstants.CENTER);
		instructions.setForeground(new Color(255, 0, 0));
		instructions.setPreferredSize(new Dimension(0,25));
		instructions.setBounds(0, 650, 500, 30);
		
				

	} 
    
    /* @TODO Add another button called "Bet" to read from slider and commit the bet to Pot Value */
    public void createButtons()
    {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5,1));	
			
		hit = new JButton("Hit");
		hit.addActionListener(new ButtonControl());
		styler.styleButton(hit);
		buttonPanel.add(hit);
		
		stand = new JButton("Stand");
		stand.addActionListener(new ButtonControl());
		styler.styleButton(stand);		
		buttonPanel.add(stand);
		
		doubleDown = new JButton("Double Down");
		doubleDown.addActionListener(new ButtonControl());
		styler.styleButton(doubleDown);
		buttonPanel.add(doubleDown);
		
		JButton better = new JButton("Bet");	
		better.addActionListener(new ButtonControl());
		styler.styleButton(better);
		buttonPanel.add(better);
		
		JButton insure = new JButton("Insure");	
		insure.addActionListener(new ButtonControl());
		styler.styleButton(insure);
		buttonPanel.add(insure);
	}
    
    public void createMainMenu()
    {
		JMenuItem home = new JMenuItem("Home");
		JMenuItem newGame = new JMenuItem("New Game");
		
		home.addActionListener(new MenuControl());
		newGame.addActionListener(new MenuControl());
			
		menuOpt = new JMenu("Options");
		menuOpt.setForeground(new Color(255, 215, 0));
		
		menuOpt.add(home);
		menuOpt.add(newGame);
		
		menuBar = new JMenuBar();
		menuBar.add(menuOpt);
	}
	
    public void createBetSlider()
    {
		betting = new JSlider(0,10000,0);
        betting.setMajorTickSpacing(500);
	    betting.setPaintLabels(true);
	    betting.setFont(new Font("Serif", Font.PLAIN,15));
	    betting.setForeground(new Color(255, 215, 0));
	    betting.setBackground(new Color(255, 0, 0));
	    betting.setOrientation(JSlider.HORIZONTAL);
	    betting.setSnapToTicks(true);
	    	    
	    betting.addChangeListener(new SliderControl());
	}
	
	class MenuControl implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(evt.getActionCommand().equals("Home"))
			{
				cards.show(trigHolder, "Start");
			}
			else if(evt.getActionCommand().equals("New Game"))	
			{
				gameActive = true;
				hide = true;
				successfulBetThisRound = false;
				game.reset();
				boolean insurance = game.dealCards(false);
				if (insurance)
				{
					//JOptionPane.showMessageDialog(null,"The Dealer has an Ace! Would you like to pay insurance, if so click on options, then click on insurance!");
				}
				game.dealCards(false);
				instructions.setText("Place your bets! Minimum Bet: $5000");
				repaint();
				
			}	
			
		}
	}	
    
	class SliderControl implements ChangeListener
	{
		public void stateChanged(ChangeEvent evt)
		{	
			lastSliderPosition = betting.getValue();				
		}
	}
		
	class ButtonControl implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			
			if (!gameActive)
			{
				instructions.setText("Error! No Active Game. Start a new game!");
			}
			else if(evt.getActionCommand().equals("Hit"))
			{
				game.dealCards(true);
		
				repaint();
				
				boolean checkingBlackJack = game.playerBustCheck();
				
				if(checkingBlackJack == true)
				{
					System.out.println("Player busted");
					instructions.setText("Player busted Lost $"+(game.getPotValue()/2)+" this round.");
					hide = false; 
					gameActive = false;
					repaint();
				}
			}
			else if ((evt.getActionCommand().equals("Bet")) && (!successfulBetThisRound))
			{
				betAmount = lastSliderPosition;
				System.out.println("Amount Bet: "+betAmount);
				betAmount = game.placeBet(betAmount);
				
				if (betAmount > 0)
				{
					successfulBetThisRound = true;
					boolean insurance = game.dealCards(false);
					if (insurance)
					{
						instructions.setText("Use the slider to choose insurance amount ($0 to half of BetAmt) \n Press Insure button to place your insurance bet");
						repaint();
					} 
					else
					{
						game.dealCards(false); // This deals a second set of cards
						instructions.setText("Player to decide Hit or Stand!");
						repaint();
					}
				}
				else
				{
					instructions.setText("Error: Insufficient balance or bet. Minimum bet: $5000");
				}
				
				repaint();
			}
			else if(evt.getActionCommand().equals("Stand"))
			{
				hide = false;
				
				instructions.setText("Player is done playing! Dealer's Turn!");

				
				//dealer turn determines hit or stand
				if(game.dealerTurn())
				{
					//JOptionPane.showMessageDialog(null,"Dealer decided to Hit");
					System.out.println("Dealer decided to Hit");
				}
				else
				{
					//JOptionPane.showMessageDialog(null,"Dealer turn is over!");
					System.out.println("Dealer turn is over!");
				}
				
				repaint();
				
				/* Settle Game */
				String result = game.settle();
				
				if(result.equals("dealer won"))
				{
					JOptionPane.showMessageDialog(null,"You Lost $"+(game.getPotValue()/2)+" this round.\nTime to solve a problem!");
					problemSwitch = true;
					cards.show(trigHolder, "Problem");
				}
				else if(result.equals("player won"))
				{
					JOptionPane.showMessageDialog(null,"You won $"+(game.getPotValue()/2)+" this round!");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"It's a push! You lost nothing!"); 
				}
				repaint();
				gameActive = false;
			}
			
			else if(evt.getActionCommand().equals("Insure"))
			{
				insureAmt = lastSliderPosition;
				
				if(game.insure(insureAmt))
				{
					game.dealCards(false);
					instructions.setText("Player to decide Hit or Stand!");
					
				}
				else
				{
					instructions.setText("Error: Insufficient balance or bet.");
				}
				
				repaint();
			
			}
			
			else if(evt.getActionCommand().equals("double down"));
			{
				game.playerDoubleDown();
				repaint();
			}
				
		} /* actionPerformed */
	} /* Class Button Control */
    
    public void saveName(String naming)
    {
		name = naming;
		System.out.println(name);
	}
    
	
	class CardsPanel extends JPanel
	{
		public CardsPanel()
		{
			setLayout(null);
			setBackground(new Color(53,101,77));
			repaint();
		}
		
		public void paintComponent(Graphics g)
		{
			
			super.paintComponent(g);
			
			playerBalance.setText("Balance: $"+game.getPlayerAccountBalance());
			potValue.setText("Pot Value: $"+game.getPotValue());

			game.render(g, this, hide);
			
		}
	}
}

class Problem extends JPanel implements ActionListener
{
    public JButton option1, option2, option3, option4;
    public ButtonStyler styler = new ButtonStyler();
    public JLabel questionLabel;
    public JButton homeButton;
    public Scanner input;
    public int correctOption;
    public TrigJackHolder trigHolder;
    public CardLayout cards;
    public Image backgroundImage;

    public Problem(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        trigHolder = trigHolderIn;
        cards = cardsIn;

        setLayout(null);

        try
        {
            backgroundImage = ImageIO.read(new File("background.jpeg"));
        }
        catch (IOException e)
        {
            System.err.println("Background image not found: " + e.getMessage());
            setBackground(new Color(53, 101, 77));
        }

        questionLabel = new JLabel("");
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("DialogInput", Font.BOLD, 24));
        questionLabel.setOpaque(true);
        questionLabel.setBackground(new Color(0, 0, 0, 180));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setBounds(25, 30, 950, 120);
        add(questionLabel);

        option1 = new JButton();
        option2 = new JButton();
        option3 = new JButton();
        option4 = new JButton();

        Color[] buttonColors = {new Color(219, 68, 55), new Color(66, 133, 244), new Color(244, 180, 0), new Color(15, 157, 88)};

        int buttonWidth = 300;
        int buttonHeight = 80;
        int gridWidth = buttonWidth * 2 + 20;
        int gridHeight = buttonHeight * 2 + 20;
        int gridX = (1000 - gridWidth) / 2;
        int gridY = (800 - gridHeight - 120 - 80 - 50) / 2 + 120;

        option1.setBounds(gridX, gridY, buttonWidth, buttonHeight);
        option2.setBounds(gridX + buttonWidth + 20, gridY, buttonWidth, buttonHeight);
        option3.setBounds(gridX, gridY + buttonHeight + 20, buttonWidth, buttonHeight);
        option4.setBounds(gridX + buttonWidth + 20, gridY + buttonHeight + 20, buttonWidth, buttonHeight);

        int i;
        for (i = 0; i < 4; i++)
        {
            JButton btn;
            if (i == 0) btn = option1;
            else if (i == 1) btn = option2;
            else if (i == 2) btn = option3;
            else btn = option4;
            btn.setBackground(buttonColors[i]);
            btn.setForeground(Color.BLACK);
            btn.setFont(new Font("DialogInput", Font.BOLD, 16));
            btn.setFocusPainted(false);
            btn.setOpaque(true);
            btn.setBorderPainted(true);
            btn.addActionListener(this);
            add(btn);
        }

        homeButton = new JButton("Home");
        styler.styleButton(homeButton);
        homeButton.setBounds(425, 700, 150, 50);
        homeButton.addActionListener(this);
        add(homeButton);

        try
        {
            input = new Scanner(new File("TrigQuestions.txt"));
        }
        catch (Exception e)
        {
            questionLabel.setText("Could not load questions.");
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);
            return;
        }

        loadNextQuestion();
    }

    public void loadNextQuestion()
    {
        if (input.hasNextLine())
        {
            String q = input.nextLine();
            String a1 = input.nextLine();
            String a2 = input.nextLine();
            String a3 = input.nextLine();
            String a4 = input.nextLine();
            String correctStr = input.nextLine();

            questionLabel.setText("<html><body style='width: 900px; text-align: center; padding: 10px;'>" + q + "</body></html>");
            option1.setText("<html>A. " + a1 + "</html>");
            option2.setText("<html>B. " + a2 + "</html>");
            option3.setText("<html>C. " + a3 + "</html>");
            option4.setText("<html>D. " + a4 + "</html>");

            option1.setBackground(new Color(219, 68, 55));
            option2.setBackground(new Color(66, 133, 244));
            option3.setBackground(new Color(244, 180, 0));
            option4.setBackground(new Color(15, 157, 88));
            option1.setForeground(Color.BLACK);
            option2.setForeground(Color.BLACK);
            option3.setForeground(Color.BLACK);
            option4.setForeground(Color.BLACK);

            option1.setEnabled(true);
            option2.setEnabled(true);
            option3.setEnabled(true);
            option4.setEnabled(true);

            correctOption = Integer.parseInt(correctStr.trim());
        }
        else
        {
            questionLabel.setText("No more questions!");
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == homeButton)
        {
            cards.show(trigHolder, "Start");
            return;
        }

        int selected = 0;
        JButton selectedButton = null;

        if (e.getSource() == option1)
        {
            selected = 1;
            selectedButton = option1;
        }
        else if (e.getSource() == option2)
        {
            selected = 2;
            selectedButton = option2;
        }
        else if (e.getSource() == option3)
        {
            selected = 3;
            selectedButton = option3;
        }
        else if (e.getSource() == option4)
        {
            selected = 4;
            selectedButton = option4;
        }

        if (selected > 0)
        {
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);

            if (selected == correctOption)
            {
                selectedButton.setBackground(new Color(0, 255, 0));
                JOptionPane.showMessageDialog(this, "Correct! Returning to game.");
                cards.show(trigHolder, "Playing");
            }
            else
            {
                selectedButton.setBackground(new Color(255, 0, 0));
                JOptionPane.showMessageDialog(this, "Incorrect. Correct answer was option " + correctOption + ". Try another question.");
                if (correctOption == 1) option1.setBackground(new Color(0, 255, 0));
                else if (correctOption == 2) option2.setBackground(new Color(0, 255, 0));
                else if (correctOption == 3) option3.setBackground(new Color(0, 255, 0));
                else if (correctOption == 4) option4.setBackground(new Color(0, 255, 0));

                Timer timer = new Timer();
                timer.schedule(new TimerTask()
                {
                    public void run()
                    {
                        loadNextQuestion();
                    }
                }, 1000);
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (backgroundImage != null)
        {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
