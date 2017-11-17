package chat.model;

import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;

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
	
	public Chatbot(String username)
	{
		this.movieList = new ArrayList<Movie>();
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = new ArrayList<String>();
		this.currentTime = null;
		this.questions = new String[10];
		this.username = username;
		this.content = null;
		this.intro = null;
//		this.currentTime = currentTime;
		this.topics = new String [7];
		this.verbs = new String [4];
		this.followUps = new String [5];
		
		buildVerbs();
		buildShoppingList();
		buildTopics();
		buildQuestions();
		buildFollowups();
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
		Movie inception = new Movie("Inception");
		Movie theLegoMovie = new Movie("The Lego Movie");
		Movie interstellar = new Movie("Interstellar");
		Movie kingsman = new Movie("Kingsman");
		Movie tropicThunder = new Movie("Tropic Thunder");
		movieList.add(inception);
		movieList.add(theLegoMovie);
		movieList.add(interstellar);
		movieList.add(kingsman);
		movieList.add(tropicThunder);
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
		
	}
	
	private void buildQuestions()
	{
		questions [0] = "What do you like to do?";
		questions [1] = "What's your favorite food?";
		questions [2] = "Who is your favorite actor?";
		questions [3] = "What's our favorite book?";
		questions [4] = "What's your favorite sport?";
		questions [5] = "What's your favorite color?";
		questions [6] = "What's your favorite song?";
		questions [7] = "Who is your favorite singer";
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
//		boolean answer = false;//valid answer
//		
//		for(String htmlTagCheck : testedChatbot);
//		if(htmlTag !=)
		return false;
	}
	
	public boolean userNameChecker(String input)
	{
		return false;
	}
	
	public boolean contentChecker(String contentCheck)
	{
		return false;
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
		}
		return false;
	}
	
	public boolean movieTitleChecker(String title)
	{
		return false;
	}
	
	public boolean movieGenreChecker(String genre)
	{
		return false;
	}

	public boolean quitChecker(String exitString)
	{
		if (exitString.equalsIgnoreCase("quit"))
		{
			return true;
		}
		return false;
	}

	public boolean keyboardMashChecker(String sample)
	{
		return false;
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
