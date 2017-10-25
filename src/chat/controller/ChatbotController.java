package chat.controller;

import chat.view.*;
import chat.model.*;

public class ChatbotController
{
	private PopupDisplay display;
	
	public ChatbotController()
	{
		display = new PopupDisplay();
	}
	
	public void start()
	{
		display.displayText("asd");
		
	}
}
