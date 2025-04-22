// Sanjay Velu & Neel Murthy
// Main class with all the panels
// Game logic class and text file class will be called in

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
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 50);
        frame.setResizable(true);

        TrigJackHolder tjh = new TrigJackHolder();
        frame.getContentPane().add(tjh);
        frame.setVisible(true);
    }
}

// This class holds all the panels in a CardLayout
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

        add(sm, "Start");
        add(ins, "Instructions");
        add(hs, "Highscores");
        add(pm, "Playing");
        add(prob, "Problem");
        add(sol, "Solution");

        card.show(this, "Start");
    }
}

class StartMenu extends JPanel implements ActionListener
{
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    private JButton playButton;
    private JButton instructionsButton;
    private JButton highScoresButton;
    private JLabel titleLabel;

    public StartMenu(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        trigHolder = trigHolderIn;
        cards = cardsIn;

        setLayout(null);
        setBackground(new Color(30, 93, 82));

        titleLabel = new JLabel("TRIG JACK");
        titleLabel.setLocation(200, 50);
        titleLabel.setSize(450, 80);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 80));
        add(titleLabel);

        playButton = new JButton("Play");
        playButton.setLocation(300, 180);
        playButton.setSize(200, 60);
        playButton.addActionListener(this);
        add(playButton);

        instructionsButton = new JButton("Instructions");
        instructionsButton.setLocation(300, 260);
        instructionsButton.setSize(200, 60);
        instructionsButton.addActionListener(this);
        add(instructionsButton);

        highScoresButton = new JButton("High Scores");
        highScoresButton.setLocation(300, 340);
        highScoresButton.setSize(200, 60);
        highScoresButton.addActionListener(this);
        add(highScoresButton);
    }

    public void actionPerformed(ActionEvent evt)
    {

        if (evt.getActionCommand().equals("Play"))
        {
            cards.show(trigHolder, "Playing");
        }
        else if (evt.getActionCommand().equals("Instructions"))
        {
            cards.show(trigHolder, "Instructions");
        }
        else if (evt.getActionCommand().equals("High Scores"))
        {
            cards.show(trigHolder, "Highscores");
        }
    }
}

class Instructions extends JPanel implements ActionListener
{
    private TrigJackHolder trigHolder;
    private CardLayout cards;
    private JButton home;
    private JScrollPane instructions;

    public Instructions(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        cards = cardsIn;
        trigHolder = trigHolderIn;

        setLayout(null);
        setBackground(Color.CYAN);
		
		home = new JButton("Home");
		home.setLocation(300, 700);
		home.setSize(200, 60);
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

class Highscores extends JPanel implements ActionListener
{	
	private TrigJackHolder trigHolder;
    private CardLayout cards;
    private JButton home;
    
    public Highscores(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        cards = cardsIn;
        trigHolder = trigHolderIn;

        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
		
		home = new JButton("Home");
		home.setLocation(300, 700);
		home.setSize(200, 60);
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

class PlayMenu extends JPanel
{
    public PlayMenu(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        setBackground(Color.GREEN);
        add(new JLabel("Play Menu Page"));
    }
}

class Problem extends JPanel
{
    public Problem(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        setBackground(Color.MAGENTA);
        add(new JLabel("Problem Page"));
    }
}

class Solution extends JPanel
{
    public Solution(TrigJackHolder trigHolderIn, CardLayout cardsIn)
    {
        setBackground(Color.ORANGE);
        add(new JLabel("Solution Page"));
    }
}
