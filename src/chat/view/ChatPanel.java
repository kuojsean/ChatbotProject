package chat.view;

import javax.swing.*;
import chat.controller.*;
import java.awt.event.*;
import java.awt.*;


public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JButton responseButton;
	private SpringLayout baseLayout;
	private JButton checkerButton;
	private JTextArea chatTexts;
	private JTextField userInput;
	
	public ChatPanel(ChatbotController appController)
	{
		super();
		this.appController = appController;
		
		//Initialize GUI Data Members
		
		responseButton = new JButton ("Say");
		baseLayout = new SpringLayout();
		chatTexts = new JTextArea(10,25);
		userInput = new JTextField(25);
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setBackground(Color.RED);
		this.setLayout(baseLayout);
		this.add(responseButton);
		this.add(chatTexts);
		this.add(userInput);
		chatTexts.setEnabled(false);
		chatTexts.setEditable(false);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.SOUTH, responseButton, -33, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, responseButton, 0, SpringLayout.EAST, chatTexts);
		baseLayout.putConstraint(SpringLayout.NORTH, chatTexts, 20, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatTexts, 25, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatTexts, -25, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, userInput, 0, SpringLayout.NORTH, responseButton);
		baseLayout.putConstraint(SpringLayout.WEST, userInput, 0, SpringLayout.WEST, chatTexts);
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
	}
	
}
