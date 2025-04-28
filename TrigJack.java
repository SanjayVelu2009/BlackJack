// Sanjay Velu & Neel Murthy
// Main class with all the panels
// Game logic class and text file class will be called in


//Imports for the JFrame/Java Elements, etc.
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


public class TrigJack //Class header
{
    public TrigJack() //Constructor
    {
    }

    public static void main(String[] args) //main
    {
        TrigJack tj = new TrigJack(); // create instance of class
        tj.runIt(); //call run method
    }

    public void runIt() //run method
    {
        JFrame frame = new JFrame("TrigJack"); //create JFrame
        frame.setSize(1000, 800); // Frame size is 1000 x 800
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 50);
        frame.setResizable(true);

        TrigJackHolder tjh = new TrigJackHolder(); //Create Panel Holder class
        frame.getContentPane().add(tjh); //Add Panel holder to the JFrame
        frame.setVisible(true);
    }
}

class ButtonStyler // Class to get colored buttons
{
    public void styleButton(JButton button) //Method (accepts a JButton as a parameter)
    {
        button.setBackground(new Color(255, 215, 0)); //Set Background of the accepted Button
        button.setForeground(new Color(0, 0, 0)); //Set foreground of the accepted Button
        button.setFont(new Font("DialogInput", Font.BOLD, 18)); //Set fond
        button.setFocusPainted(false);
        button.setOpaque(true);
    }
}

class TrigJackHolder extends JPanel //Panel Holder Class
{
    public TrigJackHolder()
    {
        CardLayout card = new CardLayout(); //Create Card Layout
        setLayout(card); //Set Layout to be card layout

		//Create the cards
        StartMenu sm = new StartMenu(this, card); 
        Instructions ins = new Instructions(this, card);
        Highscores hs = new Highscores(this, card);
        PlayMenu pm = new PlayMenu(this, card);
        Problem prob = new Problem(this, card);
        Solution sol = new Solution(this, card);

		//Add the cards to the Card Layout
        add(sm, "Start");
        add(ins, "Instructions");
        add(hs, "Highscores");
        add(pm, "Playing");
        add(prob, "Problem");
        add(sol, "Solution");
        card.show(this, "Start"); //Show the Start Panel
    }
}

class StartMenu extends JPanel implements ActionListener //Start Menu Class
{
	
	//FV for the UI elements & the classes like the button styler and the panel holder
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    private JButton playButton;
    private JButton instructionsButton;
    private JButton highScoresButton;
    private JButton quitButton;
    private JLabel titleLabel;
    private Image backgroundImage;
    private ButtonStyler styler = new ButtonStyler();

    public StartMenu(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        trigHolder = trigHolderIn;
        cards = cardsIn;

        setLayout(null); //Start Menu layout is null

		//load image for background using try-catch
        try
        {
            backgroundImage = ImageIO.read(new File("background.jpeg"));
        }
        catch (IOException e) //If image isn't found on user's system, set background to green
        {
            System.err.println("Background image not found: " + e.getMessage());
            setBackground(new Color(53, 101, 77));
        }

		
		//JLabel for the title
		titleLabel = new JLabel("TRIGJACK");
		titleLabel.setFont(new Font("DialogInput", Font.BOLD, 90));
		titleLabel.setForeground(new Color(255, 215, 0));
		titleLabel.setBounds(260, 50, 800, 100);
		add(titleLabel);

		//Buttons for Play, Instructions, High Scores, & Quit
        playButton = new JButton("Play");
        playButton.setBounds(390, 300, 200, 60);
        styler.styleButton(playButton); //Call the styler class to give the button the gold outline
        playButton.addActionListener(this); //Add action Listener
        add(playButton); //Add the button to the panel

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

    public void paintComponent(Graphics g) //Draw the image using paintComponent
    {
        super.paintComponent(g);
        if (backgroundImage != null) //Checks if there is a background image
        {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); //Crop the iamge for the pane
        }
    }

    public void actionPerformed(ActionEvent evt) // Checks if the user clicks the button using Action Performed
    {
		Object src = evt.getSource(); //Checks what button the user clicks on using getSource()

		//If the user clicks on a button the program goes to that corresponding card
		if (src == playButton)
		{
			cards.show(trigHolder, "Playing");
		}
		else if (src == instructionsButton)
		{
			cards.show(trigHolder, "Instructions");
		}
		else if (src == highScoresButton)
		{
			cards.show(trigHolder, "Highscores");
		}
		else if (src == quitButton) // If user clicks the "Quit" button then the program exits
		{
			System.exit(1);
		}
	}
}

class Instructions extends JPanel implements ActionListener // Intructions Panel
{
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    private JButton home;
    private JScrollPane instructions;
    private JTextArea text;
    private ButtonStyler styler = new ButtonStyler();

    public Instructions(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        cards = cardsIn;
        trigHolder = trigHolderIn;

        setLayout(null);
        setBackground(new Color(53, 101, 77));

		//Home button that returns the user to the Start panel
        home = new JButton("Home");
        home.setBounds(390, 700, 200, 60);
        styler.styleButton(home);
        home.addActionListener(this);
        add(home);

		//JTextArea that stores the instructions for the game
        text = new JTextArea(60, 50);
        text.setText("");
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);

        instructions = new JScrollPane(text);
        instructions.setBounds(50, 50, 900, 600);
        add(instructions);
    }

    public void actionPerformed(ActionEvent evt) //Checks if user clicks the Home button and returns them home
    {
        if (evt.getActionCommand().equals("Home"))
        {
            cards.show(trigHolder, "Start");
        }
    }
}

class Highscores extends JPanel implements ActionListener //High Scores panel
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

class PlayMenu extends JPanel //Game Panel where the game is actually played
{
    public PlayMenu(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        setLayout(null);
        setBackground(new Color(53, 101, 77));

        JLabel label = new JLabel("Play Menu Page");
        label.setBounds(350, 350, 200, 30);
        label.setForeground(Color.WHITE);
        add(label);
    }
}

class Problem extends JPanel //Problem Panel that will have the problems for the user to solve
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

class Solution extends JPanel //Solution Panel that will show up if the user chooses the wrong answer
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
