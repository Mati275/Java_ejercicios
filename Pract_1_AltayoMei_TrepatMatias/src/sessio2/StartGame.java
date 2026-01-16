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
		int num;
		
		// Clears the text before
		userInput.clear();
		
		// Show both options
		userInput.resetColor();
		userInput.println ("Select option");
		userInput.println ("1. New game");
		userInput.println ("2. Exit");
		
		// Ask the user one number 
		userInput.setForegroundColor(Color.GREEN);
		num = userInput.readInt();
		userInput.println();

		
		// The number isn't between 1 and 2, so ask again the number
		while (num < 1 || num > 2) {
			
			// Show the error message
			userInput.setForegroundColor(Color.RED);
			userInput.println("Menu opion must be betwwn 1 (New Game) and 2 (Exit). Reenter your selection:");
			
			// Ask the user one number 
			userInput.setForegroundColor(Color.GREEN);
			num = userInput.readInt();
			userInput.println();
		}
		
		
		userInput.resetColor();
		
		return num; // The number must be between 1 and 2
	}
	
	
	private static int getAndValidateColumn(JConsole console, int nCols) {
		//TODO: Complete
		int col;
		
		console.resetColor();
		console.print("Insert column number [0," + (nCols - 1) + "]: ");
		
		console.setForegroundColor(Color.GREEN);
		col = console.readInt();
		
		//console.resetColor();

		console.println();
		
		while (col < 0 || col > nCols - 1) {
				
			console.setForegroundColor(Color.RED);
			console.print ("Value must be between [0," + (nCols - 1) + "]. Insert a new value: ");
			
			console.setForegroundColor(Color.GREEN);
			col = console.readInt();
			console.resetColor();
			
			console.println();
		}
		
		return col;
	}

}
