package chat.model;

import chat.controller.ChatbotController;
import chat.controller.IOController;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.Status;
import twitter4j.*;

import java.util.*;
import java.text.*;

public class CTECTwitter
{
	private ChatbotController appController;
	private Twitter chatbotTwitter;
	
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private long totalWordCount;
	private HashMap<String, Integer> wordsAndCount;
	
	
	public CTECTwitter(ChatbotController appController)
	{
		this.appController = appController;
		this.chatbotTwitter = TwitterFactory.getSingleton();
		this.tweetedWords = new ArrayList<String>();
		this.searchedTweets = new ArrayList<Status>();
		this.wordsAndCount = new HashMap<String, Integer>();
		this.totalWordCount = 0;
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			chatbotTwitter.updateStatus(textToTweet + " @ChatbotCTEC");
		}
		catch (TwitterException tweetError)
		{
			appController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			appController.handleErrors(otherError);
		}
	}
	
	public String getMostCommonWord(String username)
	{
		String mostCommon = "";
		
		collectTweets(username);
		turnStatusesToWords();
		totalWordCount = tweetedWords.size();
		String [] boring = createIgnoredWordArray();
		removeBlanks();
//		trimTheBoringWords(boring);
//		generateWordCount();
		
		ArrayList<Map.Entry<String, Integer>> sorted = sortHashMap();
		
		String mostCommonWord = sorted.get(0).getKey();
		int maxWord = 0;
		
		maxWord = sorted.get(0).getValue();
		
		mostCommon = "The most common word in " + username  + "'s " + searchedTweets.size() + " tweet is " +
					mostCommonWord + ", and it was used " + maxWord + " times. \nThis is " +
					(DecimalFormat.getPercentInstance().format(((double) maxWord)/totalWordCount));
		
		
		return mostCommon;
	}
	
	private String sortedWords()
	{
		String allWords = "";
		String [] words = (String []) wordsAndCount.keySet().toArray();
		for(int index = 0; index < words.length - 1; index++)
		{
			int maxIndex = index;
			
			for(int inner = index + 1; inner < words.length; inner++)
			{
				if (words[inner].compareTo(words[maxIndex]) > 0)
				{
					maxIndex = inner;
				}
			}
			
			String tempMax = words[maxIndex];
			words[maxIndex] = words[index];
			words[index] = tempMax;
		}
		
		for (String word : words)
		{
			allWords += word + ", ";
		}
		
		return allWords;
	}
	
	private void collectTweets(String username) 
	{
		searchedTweets.clear();
		tweetedWords.clear();
		
		Paging statusPage = new Paging(1,100);
		int page = 1;
		long lastID = Long.MAX_VALUE;
		
		while(page <= 10)
		{
			statusPage.setPage(page);
			try
			{
				ResponseList<Status> listedTweets = chatbotTwitter.getUserTimeline(username, statusPage);
				for(Status current : listedTweets)
				{
					if(current.getId() < lastID)
					{
						searchedTweets.add(current);
						lastID = current.getId();
					}
				}
			}
			catch(TwitterException searchTweetError)
			{
				appController.handleErrors(searchTweetError);
			}
			page++;
		}
	}
	
	private void turnStatusesToWords()
	{
		for(Status currentStatus : searchedTweets)
		{
			String tweetText = currentStatus.getText();
			String [] tweetWords = tweetText.split(" ");
			for(int index = 0; index < tweetWords.length; index++)
			{
				tweetedWords.add(removePunctuation(tweetWords[index]).trim());
			}
		}
	}
	
	private String removePunctuation(String currentString)
	{
		String punctuation = ".,'?!:;\"() {}^[]<>-";
		
		String scrubbedString = "";
		for(int i = 0; i < currentString.length(); i++)
		{
			if (punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		return scrubbedString;
	}
	
	private String [] createIgnoredWordArray()
	{
		String [] boringWords;
		String fileText = IOController.loadFromFile(appController, "commonWords.txt");
		int wordCount = 0;
		
		Scanner wordScanner = new Scanner(fileText);
		
		while(wordScanner.hasNextLine())
		{
			wordScanner.nextLine();
			wordCount++;
		}
		
		boringWords = new String [wordCount];
		wordScanner.close();
		
		wordScanner = new Scanner(this.getClass().getResourceAsStream("data/commonWords.txt"));
		for(int index = 0; index < boringWords.length; index++)
		{
			boringWords[index] = wordScanner.nextLine();
		}
		
		wordScanner.close();
		return boringWords;
	}
	
	private void removeBlanks()
	{
		for (int index = tweetedWords.size() - 1; index >= 0 ; index --)
		{
			if (tweetedWords.get(index).trim().length() == 0)
			{
				tweetedWords.remove(index);
			}
		}
	}
	
	private ArrayList<Map.Entry<String, Integer>> sortHashMap()
	{
		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>();
		return entries;
	}
	
	public String analyzeTwitterForTopic(String topic)
	{
		return null;
	}
	
	twitterQuery.setGeoCode(new GeoLocation(latitude, longitude), radius, Query.KILOMETERS);
	ArrayList<Status> matchingTweets = new ArrayList<Status>();
	

}