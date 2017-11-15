package chat.view;

import javax.swing.*;
import chat.controller.*;
import java.awt.*;


public class ChatPanel extends JPanel
{
	private ChatbotController appController;
	private JButton responseButton;
	private SpringLayout baseLayout;
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
		
	}
	
	private void setupLayout()
	{
		
	}
	
	private void setupListeners()
	{
		
	}
	
}
