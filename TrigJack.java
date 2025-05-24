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
import java.util.Random;

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
		//creating the frame
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
		//styling the buttons
        button.setBackground(new Color(255, 215, 0));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("DialogInput", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setOpaque(true);
    }
}

class TrigJackHolder extends JPanel
{
    private CardLayout card;
    private Highscores highscoresPanel;

    public TrigJackHolder()
    {
        card = new CardLayout();
        setLayout(card);

        StartMenu sm = new StartMenu(this, card);
        Instructions ins = new Instructions(this, card);
        highscoresPanel = new Highscores(this, card);
        Problem prob = new Problem(this, card);
        PlayMenu pm = new PlayMenu(this, card, prob);
        Names nam = new Names(this, card, pm);
		Finish fin = new Finish(this, card, pm);

		//adding all the panels to card layout
        add(sm, "Start");
        add(ins, "Instructions");		
		add(highscoresPanel, "Highscores");

        add(pm, "Playing");
        add(prob, "Problem");
        add(nam, "Name");
		add(fin, "Finish");

        card.show(this, "Start");
    }
    
    public Highscores getHighscoresPanel()
    {
        return highscoresPanel;
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
		//try catch file, loading image
        try
        {
			backgroundImage = ImageIO.read(new File("assets/background.jpeg"));		//background image in assets file
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

        playButton = new JButton("Play");			//play button
        playButton.setBounds(390, 300, 200, 60);
        styler.styleButton(playButton);
        playButton.addActionListener(this);
        add(playButton);

        instructionsButton = new JButton("Instructions");	//instructions button
        instructionsButton.setBounds(390, 380, 200, 60);
        styler.styleButton(instructionsButton);
        instructionsButton.addActionListener(this);
        add(instructionsButton);

        highScoresButton = new JButton("High Scores");	//highscores button
        highScoresButton.setBounds(390, 460, 200, 60);
        styler.styleButton(highScoresButton);
        highScoresButton.addActionListener(this);
        add(highScoresButton);

        quitButton = new JButton("Quit");			//quit button
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

    public void actionPerformed(ActionEvent evt)	//action performed, performs the function of each button
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
		    trigHolder.getHighscoresPanel().refresh();
            cards.show(trigHolder, "Highscores");
        }
        else if (src == quitButton)
        {
            System.exit(0);
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
            setBackground(new Color(53, 101, 77));
        }

        home = new JButton("Home");
        home.setBounds(390, 700, 200, 60);
        styler.styleButton(home);
        home.addActionListener(this);
        add(home);

        text = new JTextArea(30, 50);		//instructions in JTextArea with JScrollPane
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
            "Trig Challenge on Bust or Loss:\n" +
            "- Correct answer: Lose half bet.\n" +
            "- Incorrect answer: Lose full bet.\n" +
            "- Wrong? Step-by-step solution shown.\n\n" +
            "Winning & Losing:\n" +
            "- Start: $1000.\n" +
            "- Goal: Reach $20,000 to win.\n" +
            "- Lose: Bankrupt.\n" +
            "- Final Challenge if bankrupt.\n\n" +
            "High Scores:\n" +
            "- The lowest number of rounds to reach $20,000 is on the leaderboard!\n\n");
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);

        instructions = new JScrollPane(text);
        instructions.setBounds(50, 50, 900, 600);
        add(instructions);
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getActionCommand().equals("Home"))		//home button function
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
    private JTextArea text;
    private JScrollPane highscoresPane;
    private ButtonStyler styler = new ButtonStyler();
    private Image backgroundImage;

    public Highscores(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        cards = cardsIn;
        trigHolder = trigHolderIn;

        setLayout(null);

        try
        {
            backgroundImage = ImageIO.read(new File("instructions.jpeg"));
        }
        catch (IOException e)
        {
            System.err.println("Instructions image not found: " + e.getMessage());
            setBackground(new Color(53, 101, 77));
        }

        home = new JButton("Home");
        home.setBounds(390, 700, 200, 60);
        styler.styleButton(home);
        home.addActionListener(this);
        add(home);

        text = new JTextArea(30, 50);
        text.setFont(new Font("DialogInput", Font.PLAIN, 16));
        text.setForeground(new Color(255, 215, 0));
        text.setBackground(new Color(53, 101, 77));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);

        highscoresPane = new JScrollPane(text);
        highscoresPane.setBounds(50, 50, 900, 600);
        add(highscoresPane);

        loadHighScores();
    }

    public void refresh()
    {
        System.out.println("Refreshing Highscores panel");
        loadHighScores();
    }

    public void loadHighScores()
    {
        int maxEntries = 100;
        String[] names = new String[maxEntries];
        int[] rounds = new int[maxEntries];
        int validEntries = 0;

        Scanner scanner = null;
        try
        {
            File file = new File("highscores.txt");
            System.out.println("Attempting to read highscores.txt at: " + file.getAbsolutePath());
            System.out.println("File exists: " + file.exists());
            scanner = new Scanner(file);
            int index = 0;
            while (scanner.hasNextLine() && index < maxEntries)
            {
                String line = scanner.nextLine();
                System.out.println("Reading line: " + line);
                if (line.length() == 0)
                {
                    System.out.println("Skipping empty line");
                }
                else
                {
                    int commaIndex = line.indexOf(",");
                    if (commaIndex > 0 && commaIndex < line.length() - 1)
                    {
                        String namePart = line.substring(0, commaIndex).trim();
                        String roundsPart = line.substring(commaIndex + 1).trim();
                        System.out.println("Name: " + namePart + ", Rounds: " + roundsPart);
                        boolean isNumber = true;
                        int i = 0;
                        while (i < roundsPart.length())
                        {
                            char c = roundsPart.charAt(i);
                            if (c < '0' || c > '9')
                            {
                                isNumber = false;
                            }
                            i = i + 1;
                        }
                        if (isNumber && roundsPart.length() > 0 && namePart.length() > 0)
                        {
                            int roundCount = Integer.parseInt(roundsPart);
                            if (roundCount >= 0)
                            {
                                names[index] = namePart;
                                rounds[index] = roundCount;
                                validEntries = validEntries + 1;
                                index = index + 1;
                                System.out.println("Valid entry added: " + namePart + ", " + roundCount);
                            }
                            else
                            {
                                System.out.println("Skipping negative rounds: " + roundCount);
                            }
                        }
                        else
                        {
                            System.out.println("Skipping invalid rounds or empty name: " + line);
                        }
                    }
                    else
                    {
                        System.out.println("Skipping invalid line format: " + line);
                    }
                }
            }
            scanner.close();
            System.out.println("Total valid entries: " + validEntries);
        }
        catch (Exception e)
        {
            if (scanner != null)
            {
                scanner.close();
            }
            System.err.println("Error reading highscores.txt: " + e.getMessage());
            text.setText("No high scores available.");
            return;
        }

        if (validEntries == 0)
        {
            text.setText("No high scores available.");
            System.out.println("No valid high scores found");
            return;
        }

        int i = 0;
        while (i < validEntries - 1)
        {
            int j = 0;
            while (j < validEntries - i - 1)
            {
                if (rounds[j] > rounds[j + 1])
                {
                    int tempRounds = rounds[j];
                    rounds[j] = rounds[j + 1];
                    rounds[j + 1] = tempRounds;
                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                    System.out.println("Swapped: " + names[j] + " (" + rounds[j] + ") with " + names[j + 1] + " (" + rounds[j + 1] + ")");
                }
                j = j + 1;
            }
            i = i + 1;
        }

        text.setText("High Scores - Players Who Reached $20,000\n\n");
        i = 0;
        while (i < validEntries)
        {
            String scoreLine = (i + 1) + ". " + names[i] + " - " + rounds[i] + " rounds\n";
            text.append(scoreLine);
            System.out.println("Displaying: " + scoreLine.trim());
            i = i + 1;
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getActionCommand().equals("Home"))
        {
            refresh(); // Refresh before leaving to ensure next visit has fresh data
            cards.show(trigHolder, "Start");
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (backgroundImage != null)
        {
            g.drawImage(backgroundImage, 0,0, getWidth(), getHeight(), this);
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
    private PlayMenu pm;

    public Names(TrigJackHolder trigHolderIn, CardLayout cardsIn, PlayMenu inPm)
    {
        try
        {
            backgroundImage1 = ImageIO.read(new File("instructions.jpeg"));
        }
        catch (IOException e)
        {
            System.err.println("Error loading instructions image: " + e.getMessage());
            setBackground(new Color(53, 101, 77));
        }

        cards = cardsIn;
        trigHolder = trigHolderIn;
        pm = inPm;

        setLayout(null);
        setBackground(new Color(53, 101, 77));

        nameLabel = new JLabel("Enter Your Name:");			
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(340, 300, 300, 30);
        nameLabel.setFont(new Font("DialogInput", Font.BOLD, 18));
        add(nameLabel);

        name = new JTextField();					//JTextField used for user input
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
        
        //buttons navigating to different panels
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
                pm.saveName(playerName);
                cards.show(trigHolder, "Playing");
            }
        }
        else if (evt.getActionCommand().equals("Home"))
        {
            cards.show(trigHolder, "Start");
        }
        
        playerName = name.getText().trim();
        pm.saveName(playerName);
    }

    public void saveNameToFile(String name)
    {
        try
        {
            FileWriter writer = new FileWriter("players.txt", true);
            writer.write(name + "\n");
            writer.close();
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
	//components
	private JSlider betting;
	private JMenu menuOpt;
	private JMenuBar menuBar;
	private JButton doubleDown;
	private JButton insure;
	private JButton hit;
	private JButton stand;
	private JButton better;
	
	private JMenuItem home;
	private JMenuItem newGame;
	
	private JLabel potValue;
	private JLabel playerBalance;
	private JLabel instructions;
	private JLabel insuranceLabel;
	private JLabel dealerLabel;
	private JLabel playerLabel;
	
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    
    private ButtonStyler styler = new ButtonStyler();
    private Image backgroundImage2;
    private JLabel moneyTrack;
    private boolean allowToInsure = false;
    
    private Game game;		//game class, game functionality and logic 
    
    private String name = "";
    private Player player;
    private int betAmount;
    private int insureAmt;
    private boolean gameActive;

    private int roundCount; // Track rounds

	private JPanel centerPanel;
	private JPanel buttonPanel;
	
	private boolean betButton = false;
	private boolean hide = true;
	private boolean problemSwitch = false;
	private int lastSliderPosition = 0;
	private boolean successfulBetThisRound = false;
	
	private boolean initialDealDone = false;
	
	private boolean successfulDoubleDownThisRound = false;
	private Problem problems;
	
    public PlayMenu(TrigJackHolder trigHolderIn, CardLayout cardsIn, Problem inProb)
    {
		trigHolder = trigHolderIn;
		cards = cardsIn;
		problems = inProb;
           
        try
        {
            backgroundImage2 = ImageIO.read(new File("instructions.jpeg"));
        }
        catch (IOException e)
        {
            System.err.println("Error loading instructions image: " + e.getMessage());
            setBackground(new Color(53, 101, 77));
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
		cp.add(dealerLabel);
		cp.add(playerLabel);
		
        gameActive = true;
		roundCount = 0; // Initialize round count
		game = new Game(name);
		problems.setGame(game);
						
		add(menuBar,BorderLayout.NORTH);
		add(betting,BorderLayout.SOUTH);
		add(cp, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.WEST);
		
		instructions.setText("Place your bets! Minimum Bet: $5000");
		
		repaint();
		
		
    }
    
    /* @TODO create Labels to reflect Pot Value and Players' current balance 
     * @TODO Position the labels right below the cards and above the slider */
     
    public void createBettingLabels()			//creates labels to track the betting amounts
    {
		dealerLabel = new JLabel("Dealer");
		dealerLabel.setFont(new Font("Serif", Font.BOLD, 20));
		dealerLabel.setForeground(new Color(0, 0, 255));
		dealerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dealerLabel.setPreferredSize(new Dimension(0,25));
		dealerLabel.setBounds(0, 10, 100, 30);
		dealerLabel.setVisible(false);
		
		playerLabel = new JLabel("Player: "+name);
		playerLabel.setFont(new Font("Serif", Font.BOLD, 20));
		playerLabel.setForeground(new Color(0, 0, 255));
		playerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		playerLabel.setPreferredSize(new Dimension(0,25));
		playerLabel.setBounds(0, 270, 100, 30);
		playerLabel.setVisible(false);
		
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
		instructions.setBounds(0, 650, 700, 30);

	} 
    
    /* @TODO Add another button called "Bet" to read from slider and commit the bet to Pot Value */
    public void createButtons()									//buttons for game functionality
    {
		buttonPanel = new JPanel();			//added to main game panel
		buttonPanel.setLayout(new GridLayout(5,1));	
			
		hit = new JButton("Hit");			//hit button
		hit.addActionListener(new ButtonControl());	//adds functionality by adding action listener
		styler.styleButton(hit);		//styles button
		buttonPanel.add(hit);			//adds to button panel
		
		stand = new JButton("Stand");		//stand button
		stand.addActionListener(new ButtonControl());
		styler.styleButton(stand);		
		buttonPanel.add(stand);
		
		doubleDown = new JButton("Double Down");		//double down button
		doubleDown.addActionListener(new ButtonControl());
		styler.styleButton(doubleDown);
		buttonPanel.add(doubleDown);
		
		better = new JButton("Bet");					//bet button
		better.addActionListener(new ButtonControl());
		styler.styleButton(better);
		buttonPanel.add(better);
		
		insure = new JButton("Insure");				//insure button	
		insure.addActionListener(new ButtonControl());
		styler.styleButton(insure);
		buttonPanel.add(insure);
	}
    
    public void createMainMenu()
    {
		//menu items being created
		home = new JMenuItem("Home");
		newGame = new JMenuItem("New Game");
		
		//action listeners being added for functionality
		home.addActionListener(new MenuControl());
		newGame.addActionListener(new MenuControl());
		
		//JMenu holding menu items	
		menuOpt = new JMenu("Options");
		menuOpt.setForeground(new Color(255, 215, 0));
		
		menuOpt.add(home);
		menuOpt.add(newGame);
		
		//Menu Bar holding JMenu and the menu items 
		menuBar = new JMenuBar();
		menuBar.add(menuOpt);
	}
	
    public void createBetSlider()
    {
		//Slider used for betting and insurance
		betting = new JSlider(0,10000,0);		//goes up to 10000 and starts at 0
        betting.setMajorTickSpacing(500);
	    betting.setPaintLabels(true);
	    betting.setFont(new Font("Serif", Font.PLAIN,15));
	    betting.setForeground(new Color(255, 215, 0));
	    betting.setBackground(new Color(255, 0, 0));
	    betting.setOrientation(JSlider.HORIZONTAL);
	    betting.setSnapToTicks(true);
	    	    
	    betting.addChangeListener(new SliderControl());			//adding change listener for functionality of slider
	}
	
	class MenuControl implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(evt.getActionCommand().equals("Home"))
			{
				cards.show(trigHolder, "Start");			//goes to start page
			}
			else if(evt.getActionCommand().equals("New Game"))	
			{
				gameActive = true;
				hide = true;							//hides the dealer card
				successfulBetThisRound = false;
				initialDealDone = false;
                roundCount++; // Increment Round Count
 
				game.reset();							//resets everything
				instructions.setText("Place your bets! Minimum Bet: $5000");
				repaint();
				
			}	
			
		}
	}	
    
	class SliderControl implements ChangeListener
	{
		public void stateChanged(ChangeEvent evt)
		{	
			lastSliderPosition = betting.getValue();			//getting the last position			
		}
	}
	
		
	class ButtonControl implements ActionListener
	{
		
		public void handleHit()		//handles hit logic
		{
			game.dealCards(true);	//deals the cards boolean passed in to see if cards are dealt to player only or to everyone
	
			repaint();
			
			if (game.playerBustCheck())		//checks if the player bust
				closeCurrentRound(true);	//finishes the round boolean passed to see if player busted or not
			
		} /* handleHit */
		
		public void handleBet()
		{
			betAmount = lastSliderPosition;
			//System.out.println("Amount Bet: "+betAmount);
			betAmount = game.placeBet(betAmount);				//places bet with the amount saved with the slider and button
			
			if (betAmount > 0)
			{
				successfulBetThisRound = true;
				System.out.println("Dealing Cards");
				initialDealDone = true;
				System.out.println("Round " + roundCount + " started");
				dealerLabel.setVisible(true);
				playerLabel.setVisible(true);
				boolean insurance = game.dealCards(false);		//checks if insurance is possible
				
				if (insurance)
				{
					/* TODO Only allow user to "Insure" in this state */
					instructions.setText("Use the slider to choose insurance amount ($0 to half of BetAmt) \n Press Insure button to place your insurance bet");
					allowToInsure = true;
					repaint();
				} 
				else
				{
					System.out.println("Dealing 2nd set of cards");
					game.dealCards(false); 
					allowToInsure = false;
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
		
		public void handleStand()
		{	
			hide = false;
				
			instructions.setText("Player is done playing! Dealer's Turn!");

			//dealer turn determines hit or stand
			if(game.dealerTurn())
			{
				//JOptionPane.showMessageDialog(null,"Dealer decided to Hit");
				instructions.setText("Dealer decided to Hit");
				System.out.println("Dealer decided to Hit");
			}
			else
			{
				//JOptionPane.showMessageDialog(null,"Dealer turn is over!");
				instructions.setText("Dealer turn is over");
				System.out.println("Dealer turn is over!");
			}
			
			repaint();
			
			closeCurrentRound(false);
		}
		
		public void closeCurrentRound(boolean playerWentBust)
		{
			/* Update Instructions to start a new game */
			
			if (playerWentBust)
			{
				instructions.setText("Player busted Lost $"+(game.getPotValue()/2)+" this round.");
				hide = false; 
				repaint();
				JOptionPane.showMessageDialog(null, "Player busted Lost $"+(game.getPotValue()/2)+" this round.");
				problems.loadNextQuestion();
				cards.show(trigHolder, "Problem");
			}
			else
			{
				/* Settle Game */
				String result = game.settle();
			
				/* TODO */
				if(result.equals("dealer won"))
				{
					System.out.println("dealer won");
					JOptionPane.showMessageDialog(null,"You Lost $"+(game.getPotValue()/2)+" this round.\nTime to solve a problem!");
					problemSwitch = true;		//goes to problem panel and loads a question
					repaint();
					problems.loadNextQuestion();
					cards.show(trigHolder, "Problem");
				}
				else if(result.equals("player won"))
				{
					System.out.println("player won");
					repaint();
					JOptionPane.showMessageDialog(null,"You won $"+(game.getPotValue()/2)+" this round!");
				}
				else
				{
					System.out.println("Its a push");
					repaint();
					JOptionPane.showMessageDialog(null,"It's a push! You lost nothing!"); 
				}
			}
			
			instructions.setText("Round Ended! Start a new game to continue playing.");
			
			repaint();
			game.reset();
			dealerLabel.setVisible(false);
			playerLabel.setVisible(false);
			gameActive = false;
			successfulBetThisRound = false;
			successfulDoubleDownThisRound = false;
			allowToInsure = false;
	
			if (game.getPlayerAccountBalance() >= 20000)
            {
				System.out.println("Switching to Finish panel with name=" + name + ", rounds=" + roundCount);
                cards.show(trigHolder, "Finish");
            }


		} /* closeCurrentRound */
		
		/* TODO Update Insurance Label to r to insure amount */
		public void handleInsure()		
		{
			
			insureAmt = lastSliderPosition;			//gets the last slider position and sets it as 
			
			if(game.insure(insureAmt))
			{
				game.dealCards(false);				//deals cards 
				allowToInsure = false;
				instructions.setText("Player to decide Hit or Stand!");
			}
			else
			{
				instructions.setText("Error: Insufficient balance or bet.");
			}
							
			repaint();
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			//calling methods for each button action command
			if (!gameActive)
			{
				instructions.setText("Error! No Active Game. Start a new game!");
			}
			else if ((evt.getActionCommand().equals("Hit")) && successfulBetThisRound && !allowToInsure && !successfulDoubleDownThisRound)
			{
				handleHit();
			}
			else if ((evt.getActionCommand().equals("Bet")) && (!successfulBetThisRound) && !allowToInsure && !successfulDoubleDownThisRound)
			{
				handleBet();
			}
			else if ((evt.getActionCommand().equals("Stand")) && successfulBetThisRound && !allowToInsure && !successfulDoubleDownThisRound)
			{
				handleStand();
			}
			else if ((evt.getActionCommand().equals("Insure")) && successfulBetThisRound && allowToInsure && !successfulDoubleDownThisRound)
			{
				handleInsure();
			}
			else if ((evt.getActionCommand().equals("Double Down")) && successfulBetThisRound && !allowToInsure && !successfulDoubleDownThisRound)
			{
				successfulDoubleDownThisRound = game.playerDoubleDown();
				handleStand();
				repaint();
			}
		} /* actionPerformed */
	} /* Class Button Control */
    
    
    public void saveName(String naming)
    {
		name = naming;
		System.out.println(name);
    }
    
    public String getPlayerName()
    {
        return name;
    }
    
    public int getRoundCount()
    {
        return roundCount;
    }

	class CardsPanel extends JPanel		//center panel where the cards show up
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
			//making the labels show up
			playerBalance.setText("Balance: $"+game.getPlayerAccountBalance());
			potValue.setText("Pot Value: $"+game.getPotValue());
			insuranceLabel.setText("Insurance: "+game.getAmtInsured());

			game.render(g, this, hide);
			
		}
	}
}

/* class Finish extends JPanel implements ActionListener
{
		JLabel congratulate;
		JButton back;
		CardLayout cards;
		TrigJackHolder trigHolder;
		
		public Finish(TrigJackHolder trigHolderIn, CardLayout cardsIn)
		{
			
			trigHolder = trigHolderIn;
			cards = cardsIn;
			
			setLayout(null);
			setBackground(Color.YELLOW);
			
			String congrats = "Congrats! You reached $20000 in our game! \n You have became a profecient gambler! \n Would you like to play again?";
			
			congratulate = new JLabel("<html><body style='width: 900px; text-align: center; padding: 10px;'>" + congrats + "</body></html>");
			congratulate.setFont(new Font("Serif", Font.BOLD, 20));
			congratulate.setHorizontalAlignment(SwingConstants.CENTER);
			congratulate.setForeground(new Color(0, 0, 0));
			congratulate.setPreferredSize(new Dimension(0,25));
			congratulate.setBounds(0, 300, 700, 30);
			
			add(congratulate);
			
			back = new JButton("Home");
			back.setBounds(270, 700, 200, 60);
			back.addActionListener(this);
			add(back);
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
			
			
		}
	

} */

class Finish extends JPanel implements ActionListener
{
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    private JButton home, newGame;
    private JLabel messageLabel;
    private ButtonStyler styler = new ButtonStyler();
    private Image backgroundImage;
    private boolean scoreSaved = false; // Prevent multiple saves

    public Finish(TrigJackHolder trigHolderIn, CardLayout cardsIn, PlayMenu playMenu)
    {
        cards = cardsIn;
        trigHolder = trigHolderIn;

        setLayout(null);

        try
        {
            backgroundImage = ImageIO.read(new File("instructions.jpeg"));
        }
        catch (IOException e)
        {
            System.err.println("Instructions image not found: " + e.getMessage());
            setBackground(new Color(53, 101, 77));
        }

        String playerName = playMenu.getPlayerName();
        if (playerName == null || playerName.trim().isEmpty())
        {
            playerName = "Anonymous";
        }
        int rounds = playMenu.getRoundCount();
        System.out.println("Finish panel: playerName=" + playerName + ", rounds=" + rounds);

        messageLabel = new JLabel("Congratulations, " + playerName + "! You reached $20,000 in " + rounds + " rounds!");
        messageLabel.setFont(new Font("DialogInput", Font.BOLD, 24));
        messageLabel.setForeground(new Color(255, 215, 0));
        messageLabel.setBounds(50, 50, 900, 100);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel);

        home = new JButton("Home");
        home.setBounds(270, 700, 200, 60);
        styler.styleButton(home);
        home.addActionListener(this);
        add(home);

        newGame = new JButton("New Game");
        newGame.setBounds(520, 700, 200, 60);
        styler.styleButton(newGame);
        newGame.addActionListener(this);
        add(newGame);

        saveHighScore(playerName, rounds);
    }

    private void saveHighScore(String name, int rounds)
    {
        if (scoreSaved)
        {
            System.out.println("Score already saved for this session");
            return;
        }

        if (name == null || name.trim().isEmpty())
        {
            name = "Anonymous";
        }
        if (rounds < 0)
        {
            rounds = 0;
        }
        System.out.println("Attempting to save high score: " + name + "," + rounds);

        try
        {
            File file = new File("highscores.txt");
            System.out.println("Writing to: " + file.getAbsolutePath());
            System.out.println("File exists: " + file.exists());
            FileWriter writer = new FileWriter(file, true);
            writer.write(name + "," + rounds + "\n");
            writer.close();
            System.out.println("Saved high score: " + name + "," + rounds);
            scoreSaved = true;
        }
        catch (IOException e)
        {
            System.err.println("Error saving high score: " + e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getActionCommand().equals("Home"))
        {
            cards.show(trigHolder, "Start");
        }
        else if (evt.getActionCommand().equals("New Game"))
        {
            cards.show(trigHolder, "Name");
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
