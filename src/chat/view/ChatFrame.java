package chat.view;

import chat.controller.ChatbotController;
import javax.swing.JFrame;
/**
 * This is the JFrame subclass of chatbot.
 * @author Sean Kuo
 * @version 21/11/17
 *
 */
public class ChatFrame extends JFrame
{
	private ChatbotController appController;
	private ChatPanel appPanel;
	
	public ChatFrame(ChatbotController appController)
	{
		super();
		this.appController = appController;
		appPanel = new ChatPanel(appController);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setSize(500, 500);
		this.setTitle("Chatbot 2017");
		this.setContentPane(appPanel);
		this.setResizable(false);
		this.setVisible(true);
	}
}
