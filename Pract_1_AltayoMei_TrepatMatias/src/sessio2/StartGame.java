package sessio2;

import java.awt.Color;
import java.awt.Toolkit;

import jconsole.JConsole;

public class StartGame {
	
	public static void main (String []args) {
		
		//Two consoles, AVOID CHANGING
		JConsole gameConsole = new JConsole(30,10);
		JConsole userInput = new JConsole(60,15);
		
		//Screen size, AVOID CHANGING
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int halfWidthGame = (screenWidth)/4;
		int halfHeightPlayer = (screenHeight)/2;
		
		//Variables
		boolean gameEnded;
		boolean exit;
		Game currentGame;
		
		//Additional vars
		//TODO: Declare more variables here if you need them
		
		//Console positioning, AVOID CHANGING
		gameConsole.setLocation(halfWidthGame,10);
		userInput.setLocation(halfWidthGame,halfHeightPlayer);
		
		gameConsole.setCursorVisible(false);

		//TODO: Show main menu
		//TODO: We initialize the variables with some dummy values, feel free to change them if you need to
		exit = false;
		gameEnded = false;
		
		while(!exit) {
			
			///Game begins
			
			//TODO: Create the game
			
			//TODO: Show current board 
			
			while(!gameEnded) {
				//TODO: Interact with user and make move
				
			}
			
			//TODO: Whatever needs to be done when a game has finished
		
			//TODO: A user should be able to play a new game when the previous has ended
			
		}
		
		userInput.setCursorPosition(0, userInput.getRows()-1);
		userInput.print("Press any key to exit...");
		userInput.readKey();
		System.exit(0);
		
	}
	
	private static int showMenuAndSelectOption(JConsole userInput) {
		//TODO: Complete
		return -1;
	}
	
	
	private static int getAndValidateColumn(JConsole console, int nCols) {
		//TODO: Complete
		return -1;
	}

}
