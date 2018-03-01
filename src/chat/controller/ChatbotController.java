package chat.controller;

import chat.view.*;
import chat.model.*;

/**
 * This is the controller of chatbot.
 * @author Sean Kuo
 * @version 21/11/17
 *
 */



public class ChatbotController
{
	private Chatbot chatbot;
	private PopupDisplay display;
	private ChatFrame appFrame;
	private CTECTwitter myTwitter;
	
	public ChatbotController()
	{
		//Initialize the Models
		chatbot = new Chatbot("Sean Kuo");
		myTwitter = new CTECTwitter(this);
		//Then Initialize the Views After the Model
		display = new PopupDisplay();
		appFrame = new ChatFrame(this);
	}
	
	public void start()
	{
		IOController.loadFromFile(this, "commonWords.txt");
//		display.displayText("Welcome to Beardbot!");
		
//		while (chatbot.lengthChecker(response) && !chatbot.quitChecker(response))
//		{
//			response = popupChat(response);
//			response = display.collectResponse(response);
//		}
	}
	
	public String interactWithChatbot(String input)
	{
		String chatbotSays = "";
		
		if(chatbot.quitChecker(input))
		{
			close();
		}
		
		chatbotSays += chatbot.processConversation(input);
		
		return chatbotSays;
	}
	
	public String useCheckers(String text) 
	{
		String response = "";
		
		if(chatbot.contentChecker(text))
		{
			response += "This text matches the special content\n";
		}
		if(chatbot.cuteAnimalMemeChecker(text))
		{
			response += "This text has a cute animal meme!\n ";
		}
		if(chatbot.htmlTagChecker(text))
		{
			response += "This text has some sort of HTML Tag\n";
		}
		if(chatbot.userNameChecker(text))
		{
			response += "This has a valid username in it\n";
		}
		if(chatbot.shoppingListChecker(text))
		{
			response += "This text has an item off the shopping list\n";
		}
		if(chatbot.movieTitleChecker(text))
		{
			response += "This text is a title from our Movie List\n";
		}
		if(chatbot.movieGenreChecker(text))
		{
			response += "This text is a genre of a movie\n";
		}
		if(chatbot.keyboardMashChecker(text))
		{
			response += "We detected a keyboard mash\n";
		}
		// continue with all checkers except length and quit checker
		
		return response;
	}
	
	private void close()
	{
		display.displayText("Goodbye");
		System.exit(0);
	}
	
	private String popupChat(String chat)
	{
		String chatbotSays = ""; //Assigns a valid value to a variable that will be returned for the method
		
		chatbotSays += chatbot.processConversation(chat);
		
		return chatbotSays;
	}
	
	public void handleErrors(Exception error)
	{
		display.displayText(error.getMessage());
	}
	
	public void tweet (String text)
	{
		myTwitter.sendTweet(text);
	}
	
	public String search(String text)
	{
		return myTwitter.getMostCommonWord();
	}
}
