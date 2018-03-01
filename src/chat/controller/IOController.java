package chat.controller;

import java.io.*;
import java.util.*;

public class IOController
{

	public static void saceToFile(ChatbotController app, String textToSave, String path)
	{
		
	}
	
	public static String loadFromFile(ChatbotController app, String filename)
	{
		String results = "";
		
		try
		{
			File openFile = new File(filename);
			Scanner fileScanner = new Scanner(openFile);
			
			String currentLine = fileScanner.nextLine();
			while(fileScanner.hasNextLine())
				{
					results += currentLine + "\n";
					currentLine = fileScanner.nextLine();
				}
			results += currentLine + "\n";
			fileScanner.close();
		}
		catch (IOException error)
		{
			app.handleErrors(error);
		}
		return results;
	}
}
