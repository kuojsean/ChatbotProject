package chat.view;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
/**
 * This is the JOptionPane subclass of chatbot.
 * @author Sean Kuo
 * @version 21/11/17
 *
 */
public class PopupDisplay
{
	private ImageIcon icon;
	private String windowTitle;
	
	public PopupDisplay() 
	{
		icon = new ImageIcon(getClass().getResource("images/chatbot.png"));
		windowTitle = "Beardbot Says";
				
	}
	
	public void displayText(String message)
	{
		JOptionPane.showMessageDialog(null, message, windowTitle, JOptionPane.INFORMATION_MESSAGE, icon);
	}
	
	public String collectResponse(String question)
	{
		String answer = "";
		
		answer += JOptionPane.showInputDialog(null, question, windowTitle, JOptionPane.PLAIN_MESSAGE, icon, null, "");
		
		return answer;
	}

}
