//Sanjay Velu & Neel Murthy
//Main class with all the panels
//Game logic class and text file class will be called in

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
		TrigJack tj = new TrigJack()
		tj.runIt();
	}
	
	public void runIt()
	{
		JFrame frame = new JFrame("TrigJack");
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocation(0,50);
		frame.setResizable(true);
		TrigJackHolder tjh = new TrigJackHolder(); 		
		frame.getContentPane().add( tjh );		
		frame.setVisible(true);	
	}
}

//This class holds all the classes in a card layout
class TrigJackHolder
{

	public TrigJackHolder()
	{
		CardLayout card = new CardLayout();
		setLayout(card);
		
		StartMenu sm = new StartMenu(card,this);
		Instructions ins = new Instructions(card,this);
		Highscores hs = new Highscores(card,this);
		PlayMenu pm = new PlayMenu(card,this);
		Problem prob = new Problem(card,this);
		Solution sol = new Solution(card,this);
		
		add(sm,"Start");
		add(ins,"Instructions");
		add(hs,"Highscores");
		add(pm,"Playing");
		add(prob,"Problem");
		add(sol,"Solution");
	}
}

class StartMenu extends JPanel
{
	JButton Play;
	JButton Instructions
		
		
		
		
	
