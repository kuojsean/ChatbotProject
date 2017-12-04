package chat.view;

import javax.swing.*;
import chat.controller.*;
import java.awt.event.*;
import java.awt.*;

/**
 * The JPanel subclass for the chatbot project.
 * @author Sean Kuo
 * @version 21/11/17
 * 
 */
public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JButton responseButton;
	private SpringLayout baseLayout;
	private JButton checkerButton;
	private JTextArea chatTexts;
	private JTextField userInput;
	private JLabel infoLabel;
	//Need a data member  the scrollpane
	private JScrollPane chatScrollPane;
	
	public ChatPanel(ChatbotController appController)
	{
		super();
		this.appController = appController;
		
		//Initialize GUI Data Members
		
		responseButton = new JButton ("Say");
		baseLayout = new SpringLayout();
		chatTexts = new JTextArea(10,25);
		userInput = new JTextField(25);
		infoLabel = new JLabel("Text to chat with the chatbot");
		checkerButton = new JButton("Check");
		//init the scrollpane
		chatScrollPane = new JScrollPane();

		//call new helper method
		setupScrollPane();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupScrollPane()
	{
		chatScrollPane.setViewportView(chatTexts);
		chatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void setupPanel()
	{
		this.setBackground(Color.RED);
		this.setLayout(baseLayout);
		this.add(responseButton);
		this.add(checkerButton);
		this.add(chatScrollPane);
		this.add(userInput);
		this.add(infoLabel);
		chatTexts.setEnabled(false);
		chatTexts.setEditable(false);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.SOUTH, responseButton, -33, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, responseButton, 0, SpringLayout.EAST, chatScrollPane);
		baseLayout.putConstraint(SpringLayout.NORTH, chatScrollPane, 20, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatScrollPane, 25, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatScrollPane, -25, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, userInput, 0, SpringLayout.NORTH, responseButton);
		baseLayout.putConstraint(SpringLayout.WEST, userInput, 0, SpringLayout.WEST, chatScrollPane);
		baseLayout.putConstraint(SpringLayout.WEST, checkerButton, 0, SpringLayout.WEST, responseButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, checkerButton, 20, SpringLayout.SOUTH, responseButton);
		baseLayout.putConstraint(SpringLayout.EAST, checkerButton, 0, SpringLayout.EAST, responseButton);
		baseLayout.putConstraint(SpringLayout.WEST, infoLabel, 0, SpringLayout.WEST, chatScrollPane);
		baseLayout.putConstraint(SpringLayout.SOUTH, infoLabel, -6, SpringLayout.NORTH, userInput); 
	}
	
	private void setupListeners()
	{
		responseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String userText = userInput.getText();
				String displayText = appController.interactWithChatbot(userText);
				chatTexts.append(displayText);
				userInput.setText("");
			}
		});
		
		checkerButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent click)
					{
						String userText = userInput.getText();
						String displayText = appController.useCheckers(userText);
						chatTexts.append(displayText);
						userInput.setText("");
					}
				});
	}
	
}
