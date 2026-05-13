package sessio2;

import java.awt.Color;
import java.awt.Toolkit;

import sessio2.Game;


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
		
		
		//TODO: Declare more variables here if you need them
		int option, col;
		
		//Console positioning, AVOID CHANGING
		gameConsole.setLocation(halfWidthGame,10);
		userInput.setLocation(halfWidthGame,halfHeightPlayer);
		
		gameConsole.setCursorVisible(false);

		//TODO: Show main menu
		option = showMenuAndSelectOption(userInput);
		
		// Set the boolean variable "exit" depending on the user's response
		if (option == 1) {
			exit = false;
		} else {
			exit = true;
		}
				
		while(!exit) {
			
			gameEnded = false; // At every loop, reset the variable on false

			///Game begins
			
			//TODO: Create the game
			currentGame = new Game(); // Tablero
			
			//TODO: Show current board 
			gameConsole.clear(); // Clear the previous version of the board (if it isn't the first game)
			gameConsole.println(currentGame.boardToString()); // Print the current board
			
			while(!gameEnded) {
				
				//TODO: Interact with user and make move
				col = getAndValidateColumn(userInput, currentGame.getBoardCol());
				
				// The "move" method inside the condition in the while it's always executed before executing the code inside the loop
				// The code inside the loop it's only executed if the player can't make a move (because the column is filled) and show an error message
				while (!currentGame.move(col)) {
					userInput.setForegroundColor(Color.red);
					userInput.println("Column already full. You need to enter another column");
					userInput.println();
					col = getAndValidateColumn(userInput, currentGame.getBoardCol());

				}
				
				gameConsole.clear(); // Clear the previous version of the board
				gameConsole.println(currentGame.boardToString()); // Print the current board
				
				// Check if the game has already ended				
				gameEnded = currentGame.hasGameEnded();
			}
					
			//TODO: Whatever needs to be done when a game has finished
			gameConsole.print(currentGame.getEndMessage());
			
			//TODO: A user should be able to play a new game when the previous has ended
			option = showMenuAndSelectOption(userInput);
			
			// only change the "exit" variable if the option of the player is exit, because in this point "exit == false"
			if (option == 2) {
				exit = true;
			} 

		}
		
		// Exit the game
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
	
	
	// OTHER FUNCTIONS
	
	/*private static void showBoard (JConsole gameConsole, Game game) {
		for (int i = 0; i < game.getBoardRow(); i++){
			for (int j = 0; j < game.getBoardCol(); j++) {
				gameConsole.print ("|");

				// charAt devuelve un caracter, devuelve el indice del parentesis (i+1)
				// PREGUNTAR
				gameConsole.print (game.boardToString().charAt( (i * 7) + j ) ) ;
			}
			
			gameConsole.println ("|");
		}
		
	}*/

}
