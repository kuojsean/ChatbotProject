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
	private JButton chatButton;
	private JButton searchButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton tweetButton;
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
		chatButton = new JButton("chat", new ImageIcon(getClass().getResource("/chat/view/images/chat.png")));
		searchButton = new JButton("search", new ImageIcon(getClass().getResource("/chat/view/images/search.png")));
		saveButton = new JButton("save", new ImageIcon(getClass().getResource("/chat/view/images/save.png")));
		loadButton = new JButton("load", new ImageIcon(getClass().getResource("/chat/view/images/load.png")));
		tweetButton = new JButton("tweet", new ImageIcon(getClass().getResource("/chat/view/images/tweet.png")));
		responseButton = new JButton ("Say");
		baseLayout = new SpringLayout();
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 0, SpringLayout.NORTH, loadButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatButton, 0, SpringLayout.SOUTH, loadButton);
		baseLayout.putConstraint(SpringLayout.NORTH, searchButton, 0, SpringLayout.NORTH, loadButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, searchButton, 0, SpringLayout.SOUTH, loadButton);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, loadButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, saveButton, 0, SpringLayout.SOUTH, loadButton);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 5, SpringLayout.SOUTH, tweetButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, loadButton, 55, SpringLayout.SOUTH, tweetButton);
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
//		this.add(responseButton);
//		this.add(checkerButton);
		this.add(chatButton);
		this.add(searchButton);
		this.add(saveButton);
		this.add(loadButton);
		this.add(tweetButton);
		this.add(chatScrollPane);
		this.add(userInput);
		this.add(infoLabel);
		chatTexts.setEnabled(false);
		chatTexts.setEditable(false);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, chatScrollPane, 20, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatScrollPane, 25, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatScrollPane, -25, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.EAST, searchButton, -6, SpringLayout.WEST, saveButton);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, -6, SpringLayout.WEST, loadButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 0, SpringLayout.WEST, tweetButton);
		baseLayout.putConstraint(SpringLayout.WEST, responseButton, 15, SpringLayout.EAST, userInput);
		baseLayout.putConstraint(SpringLayout.NORTH, infoLabel, 0, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, infoLabel, 130, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, checkerButton, 6, SpringLayout.SOUTH, responseButton);
		baseLayout.putConstraint(SpringLayout.WEST, checkerButton, 350, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, checkerButton, 0, SpringLayout.EAST, responseButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 0, SpringLayout.WEST, chatScrollPane);
		baseLayout.putConstraint(SpringLayout.NORTH, tweetButton, 6, SpringLayout.SOUTH, chatScrollPane);
		baseLayout.putConstraint(SpringLayout.EAST, tweetButton, 0, SpringLayout.EAST, chatScrollPane);
		baseLayout.putConstraint(SpringLayout.NORTH, userInput, 6, SpringLayout.SOUTH, chatScrollPane);
		baseLayout.putConstraint(SpringLayout.WEST, userInput, 0, SpringLayout.WEST, chatScrollPane);
		baseLayout.putConstraint(SpringLayout.NORTH, responseButton, 6, SpringLayout.SOUTH, chatScrollPane);



	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
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
		
		tweetButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent click)
					{
						appController.tweet(userInput.getText());
					}
				});
		
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String usernameToSearch = userInput.getText();
				
				chatTexts.setText(appController.search(usernameToSearch));
			}
		});
		
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
	}
	
}
