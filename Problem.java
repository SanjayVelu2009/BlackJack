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
	private Random random;
	private Game gm;
	

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

		random = new Random();

		// Initial question load
		loadNextQuestion();
	}
	
	public void setGame(Game inGame)
	{
		gm = inGame;
	}
	
	/* TODO Problems are repeating */
	public void loadNextQuestion()
	{
		// Reset button states
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
		option1.setText("");
		option2.setText("");
		option3.setText("");
		option4.setText("");

		// Count total lines to determine number of question blocks
		int lineCount = 0;
		try
		{
			input = new Scanner(new File("assets/TrigQuestions.txt"));
			while (input.hasNextLine())
			{
				input.nextLine();
				lineCount++;
			}
			input.close();
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

		if (lineCount < 6)
		{
			questionLabel.setText("No questions available.");
			option1.setEnabled(false);
			option2.setEnabled(false);
			option3.setEnabled(false);
			option4.setEnabled(false);
			return;
		}

		int numQuestions = lineCount / 6; // Each question has 6 lines
		if (numQuestions == 0)
		{
			questionLabel.setText("No questions available.");
			option1.setEnabled(false);
			option2.setEnabled(false);
			option3.setEnabled(false);
			option4.setEnabled(false);
			return;
		}

		// Select random question block
		int index = random.nextInt(numQuestions);
		String[] questionBlock = new String[6];

		// Read the selected question block
		try
		{
			input = new Scanner(new File("assets/TrigQuestions.txt"));
			// Skip to the start of the selected question block
			for (int i = 0; i < index * 6 && input.hasNextLine(); i++)
			{
				input.nextLine();
			}
			// Read 6 lines for the question block
			for (int i = 0; i < 6 && input.hasNextLine(); i++)
			{
				questionBlock[i] = input.nextLine();
			}
			input.close();
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

		// Verify question block is complete
		for (int i = 0; i < 6; i++)
		{
			if (questionBlock[i] == null)
			{
				questionLabel.setText("Incomplete question data.");
				option1.setEnabled(false);
				option2.setEnabled(false);
				option3.setEnabled(false);
				option4.setEnabled(false);
				return;
			}
		}

		// Set question and options
		questionLabel.setText("<html><body style='width: 900px; text-align: center; padding: 10px;'>" + questionBlock[0] + "</body></html>");
		option1.setText("<html>A. " + questionBlock[1] + "</html>");
		option2.setText("<html>B. " + questionBlock[2] + "</html>");
		option3.setText("<html>C. " + questionBlock[3] + "</html>");
		option4.setText("<html>D. " + questionBlock[4] + "</html>");

		try
		{
			correctOption = Integer.parseInt(questionBlock[5].trim());
		}
		catch (NumberFormatException e)
		{
			questionLabel.setText("Invalid question format.");
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
			//PlayMenu.this.problemSwitch = false;
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
				gm.rewardPlayer();
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
	
} /* Problem */
