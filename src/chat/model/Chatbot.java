package chat.model;

import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This is the builder of chatbot.
 * @author Sean Kuo
 * @version 21/11/17
 *
 */
public class Chatbot
{
	private List<Movie> movieList;
	private List<String> shoppingList;
	private List<String> cuteAnimalMemes;
	private String [] verbs;
	private String [] topics;
	private String [] followUps;
	private String [] questions;
	private String username;
	private String content;
	private String intro;
	private LocalTime currentTime;
	private String toString;
	
	public Chatbot(String username)
	{
		this.movieList = new ArrayList<Movie>();
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = new ArrayList<String>();
		this.questions = new String[10];
		this.username = username;
		this.content = "Chatbot";
		this.intro = "";
		this.currentTime = LocalTime.now();
		this.topics = new String [7];
		this.verbs = new String [4];
		this.followUps = new String [5];
		this.toString = new String();
		
		buildVerbs();
		buildShoppingList();
		buildCuteAnimals();
		buildTopics();
		buildQuestions();
		buildFollowups();
		buildMovieList();
	}

	private void buildVerbs()
	{
		verbs [0] = "like";
		verbs [1] = "dislike";
		verbs [2] = "am ambivalent about";
		verbs [3] = "am thinking about";
	}
	
	private void buildFollowups()
	{
		followUps [0] = "Boi if you don't";
		followUps [1] = "And the ting goes BRRRRAAAA";
		followUps [2] = "Take of your jacket... mans not hot";
		followUps [3] = "2 + 2 = 4 minus 1 that's three quick math";
		followUps [4] = "Sauce... no ketchup... just sauce";
				
	}
	
	private void buildMovieList()
	{
		movieList.add(new Movie("Inception"));
		movieList.add(new Movie("The Lego Movie"));
		movieList.add(new Movie("Interstellar"));
		movieList.add(new Movie("Kingsman"));
		movieList.add(new Movie("Tropic Thunder"));
//		Movie inception = new Movie("Inception");
//		Movie theLegoMovie = new Movie("The Lego Movie");
//		Movie interstellar = new Movie("Interstellar");
//		Movie kingsman = new Movie("Kingsman");
//		Movie tropicThunder = new Movie("Tropic Thunder");
//		movieList.add(inception);
//		movieList.add(theLegoMovie);
//		movieList.add(interstellar);
//		movieList.add(kingsman);
//		movieList.add(tropicThunder);
	}
	
	private void buildShoppingList()
	{
		shoppingList.add("snacks");
		shoppingList.add("veggies");
		shoppingList.add("protein");
		shoppingList.add("slug bait");
		shoppingList.add("gross things");
	}
	
	private void buildCuteAnimals()
	{
		cuteAnimalMemes.add("otter");
		cuteAnimalMemes.add("FLOOFER");
		cuteAnimalMemes.add("kittie");
	}
	
	private void buildQuestions()
	{
		questions [0] = "What is your name?";
		questions [1] = "What's your favorite food?";
		questions [2] = "Who is your favorite actor?";
		questions [3] = "What's our favorite book?";
		questions [4] = "What's your favorite sport?";
		questions [5] = "What's your favorite color?";
		questions [6] = "What's your favorite song?";
		questions [7] = "Who is your favorite singer?";
		questions [8] = "What are you doing this weekend?";
		questions [9] = "Who am I?";
	}
	
	private void buildTopics()
	{
		topics [0] = "school";
		topics [1] = "basketball";
		topics [2] = "fashion";
		topics [3] = "food";
		topics [4] = "business";
		topics [5] = "music";
		topics [6] = "politics";
	}
	
	public String processConversation(String input)
	{
		String chatbotResponse = "";
		chatbotResponse += currentTime.getHour() + ":" + currentTime.getMinute() + " ";
		chatbotResponse += "You said:" + "\n" + input + "\n";
		chatbotResponse += buildChatbotResponse();
		return chatbotResponse;
	}
	
	private String buildChatbotResponse()
	{
		String response = "I ";
		int random = (int) (Math.random() * verbs.length);
		
		response += verbs[random];
		
		random = (int) (Math.random() * topics.length);
		response += " " + topics[random] + ".\n";
		
		random = (int) (Math.random() * questions.length);
		response += questions[random];
		
		random = (int) (Math.random() *2);
		if(random % 2 == 0)
		{
			random = (int) (Math.random() *movieList.size());
			response += "\n" + movieList.get(random).getTitle() + " is a great movie!";
		}
		
		int followup = (int) (Math.random() * 5);
		
		switch(followup)
		{
		case 0:
			response += followUps[0] + "\n";
			break;
		case 3:
			response += followUps[1] + "\n";
		case 1:
			response += followUps[2] + "\n";
			break;
		default:
			response += followUps[4] + "\n";
			response += followUps[3] + "\n";
			break;
		}
		
		
		return response;
	}
	
	public boolean lengthChecker(String input)
	{
		boolean validLength = false;
		
		if (input != null && input.length() > 2)
		{
				validLength = true;
		}
		
		return validLength;
	}
	
	public boolean htmlTagChecker(String input)
	{
		boolean containsHTML = false;
		if(input == null || !input.contains("<"))
		{
			return containsHTML;
		}
		int firstOpen = input.indexOf("<");
		int firstClose = input.indexOf(">",firstOpen);
		int secondOpen = -9;
		int secondClose = -9;
		String tagText = "";
		
		//Check bad tags
		if (input.contains("<>") || input.indexOf("< >") > -1)
		{
			containsHTML = false;
		}
		//Check singleton
		if(input.toUpperCase().contains("<P>") || input.toLowerCase().contains("<br>"))
		{
			containsHTML = true;
		}
		//check others
		else if (firstClose > firstOpen)
		{
			
			//Others
			tagText = input.substring(firstOpen + 1, firstClose).toLowerCase();
			secondOpen = input.toLowerCase().indexOf("</" + tagText, firstClose);
		}
		return containsHTML;
	}
	
	public boolean userNameChecker(String input)
	{
		boolean validUserName = false;
		int atCount = 0;
		if(input != null)
		{
			if (input.startsWith("@"))
			{
			validUserName = true;
				for(int i = 0; i < input.length(); i++)
				{
					if (input.charAt(i) == '@')
					{
						atCount++;
					}
					if (atCount >= 2)
					{
						validUserName = false;
					}
				}
			}	
		}		
		
		return validUserName;
	}
	
	public boolean contentChecker(String contentCheck)
	{
		return true;
	}
	
	public boolean cuteAnimalMemeChecker(String cuteAnimalMemeItem)
	{
		for(String cuteAnimalCheck : cuteAnimalMemes)
		{
			if (cuteAnimalMemeItem.contains(cuteAnimalCheck))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean shoppingListChecker(String shoppingItem)
	{
		for(String shoppingCheck: shoppingList)
		{
			
			if (shoppingItem.contains(shoppingCheck))
			{
				return true;
				
			}
			if (shoppingItem.equalsIgnoreCase("slug bait"))
			{
				return false;
			}
		}
		return false;
	}
	
	public boolean movieTitleChecker(String title)
	{
		boolean titleCheck = true;
		if (title == "")
		{
			titleCheck = false;
		}
		return titleCheck;
	}
	
	public boolean movieGenreChecker(String genre)
	{
		boolean genreCheck = true;
		if (genre == "")
		{
			genreCheck = false;
		}
		return genreCheck;
	}

	public boolean quitChecker(String exitString)
	{
		if (exitString != null)
		{
			if (exitString.equalsIgnoreCase("exit"))
			{
				return false;
			}
			if (exitString.equalsIgnoreCase("quit"))
				{
				return true;
				}
		}
		return false;

	}
	
	public String toString()
	{
		String toStringMessage = "What up!";
		toStringMessage.replace("@","");
				
		
		return toStringMessage;
	}

	public boolean keyboardMashChecker(String sample)
	{
		boolean keyTest = true;
		if (sample.contains("a") ||
				sample.contains("e")||
				sample.contains("i") ||
				sample.contains("o") ||
				sample.contains("u") ||
				sample.contains("y"))
			{
			keyTest = false;
			}
		else if (sample.contains("."))
		{
			int periodCount = 0;
			for(int i = 0; i < sample.length(); i++)
			{
				if (sample.charAt(i) == '.')
				{
					periodCount ++;
				}		
			}
			if (periodCount > 1)
			{
			keyTest = false;
			}
		}
		return keyTest;
	}
	
	public List<Movie> getMovieList()
	{
		return movieList;
	}
	
	public List<String> getShoppingList()
	{
		return shoppingList;
	}
	
	public List<String> getCuteAnimalMemes()
	{
		return cuteAnimalMemes;
	}

	public String [] getQuestions()
	{
		return questions;
	}
	
	public String[] getVerbs()
	{
		return verbs;
	}

	public String[] getTopics()
	{
		return topics;
	}

	public String[] getFollowUps()
	{
		return followUps;
	}

	public String getUsername()
	{
		return username;
	}
	
	public String getContent()
	{
		return content;
	}

	public String getIntro()
	{
		return intro;
	}
	
	public LocalTime getCurrentTime()
	{
			return currentTime;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
}
