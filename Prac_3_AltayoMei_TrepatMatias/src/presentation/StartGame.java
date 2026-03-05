package presentation;

import java.awt.Color;
import java.awt.Toolkit;

import domain.Game;
import exeptions.InvalidCoordinatesException;
import exeptions.NoCityException;
import jconsole.JConsole;

public class StartGame {
	
	
	private Game game;
	private JConsole console;
	private JConsole savedCitiesInfo;
	
	private StartGame() {
		
		//Do not delete existing code
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int halfWidthGame = (screenWidth)/4;
		
		try {
			game = new Game();
		} catch (NoCityException err) {
			System.out.print(err.getMessage());
			System.exit(-1);
		}
		
		console = new JConsole(80,20);
		savedCitiesInfo = new JConsole(40,10);
		
		//Console positioning
		savedCitiesInfo.setLocation((int) (2*halfWidthGame),0);		
		savedCitiesInfo.setCursorVisible(false);
	}
	
	private void run() {
		int []coordinates = new int[2];
		String userInput;
		int totalRow, totalCols;
		String cityTypeName;
		
		savedCitiesInfo.println("SAVED CITIES INFO");
		savedCitiesInfo.println("*****************");
		//Setup
		
		while(!game.hasEnded()) {
			//Show board
			console.resetColor();
			console.print( game.boardToString() );
			
			
			//Show remaining actions info
			console.setForegroundColor(Color.yellow);
			console.println("Remaining actions: " + game.getRemainingActions());
			console.println();
			
			//Get user input
			console.resetColor();
			console.print("Please enter a row (0 - 9) and column (0 - 9) separated by a comma: ");
			userInput = console.readString();
			
			//Parse and validate input
			coordinates = null;
			while( coordinates == null ) {
				
				try {
					coordinates = parseAndValidateCoordinates(userInput);
				}
				
				catch(InvalidCoordinatesException err) {
					console.clear(); // Clear the console

					console.setForegroundColor(Color.red);
					console.println(err.getMessage());
					console.println();
					
					
					// Print the board
					console.resetColor();
					console.print( game.boardToString() );
					
					// Print the remaining actions
					console.setForegroundColor(Color.yellow);
					console.println("Remaining actions: " + game.getRemainingActions());
					console.println();
					
					
					// Print the message to ask the value of the position
					console.resetColor();
					console.print("Please enter a row (0 - 9) and column (0 - 9) separated by a comma: ");
					userInput = console.readString();
					

				}
			
			}
			
			
				
			//Make move && show result
			console.clear();
			
			// Try/catch because in method "investigateArea()" && "getCityTypeName()" can throw "NoCityException"
			try {
				if( game.investigateArea(coordinates[0], coordinates[1]) ) {
					console.setForegroundColor(Color.green);
					console.println("You FOUND a city");
					console.println();
					if( game.hasCityBeenSaved(coordinates[0], coordinates[1]) ) {
						console.println( game.getCityTypeName( coordinates[0], coordinates[1] ) +  " SAVED!");
						console.println();
					}
				}
				
				else {
					console.setForegroundColor(Color.red);
					console.println("NOTHING FOUND");
					console.println();
				}
			}
			catch( NoCityException err ) {
				
			}

			

			
			//If city saved, show info
			savedCitiesInfo.clear();
			savedCitiesInfo.println("SAVED CITIES INFO");
			savedCitiesInfo.println("*****************");
			
			savedCitiesInfo.print( game.savedCitiesInfo() );
			

		}
		//Do whatever should be done when out of the while loop
		console.resetColor();
		console.println( game.boardToString() );

		
	}
	
	private int[] parseAndValidateCoordinates(String input) { 

		String[] strCoordinates;
		int[] coordinates;
		
		strCoordinates = input.split(",");
		coordinates = new int[2];
		
		// The user inputs less than 2 coordinates or more than 2
		if(strCoordinates.length != coordinates.length) {
			throw new InvalidCoordinatesException("Coordinates aren't valid, because you put more or less than two values separated by a ','");		
		} // There are two elements of input

		
		// Fill the array of coordinates
		for(int i = 0; i < strCoordinates.length; i++) {
			
			// If the string is void (possible if the user inputs a "," + number )
			// or the string has a "." (isn't an int number)

			try {
				coordinates[i] = Integer.parseInt(strCoordinates[i]);
			}
			catch( NumberFormatException err ) {
				throw new InvalidCoordinatesException("Coordinates aren't valid, because the value that you've introduced is not an int");
			}
			
		}
		
		// The index isn't valid (of rows or cols)
		if(coordinates[0] >= game.getNumRows() || 
				coordinates[0] < 0 ||
				coordinates[1] >= game.getNumCols() ||
				coordinates[1] < 0) {
			throw new InvalidCoordinatesException("Coordinates aren't valid, because the coordinates are out of bounds");		}
		
		// The coordinates are valid
		return coordinates;
	}
	
	
	
	private void end() {

		//These are the end game messages. Add the required ifs to ensure only the right message is shown when game ends.


		if( game.getRemainingActions() <= 0) {
			console.println("Sorry, no remaining actions... You lose the game");
		}
		
		else if( game.allCitiesInfected() ) {
			console.println("All cities have been infected... You lose!");
		} 
		else {
			console.println("Congratulations! You saved all cities!");
		}
		
		console.println("Press any key to close...");
		console.readKey();
		System.exit(0);	
	}

	
	public static void main(String [] args) throws Exception {
		//Do not modify this code
		StartGame runningGame = new StartGame();
		runningGame.run();
		runningGame.end();
	}

	
	
	
}
