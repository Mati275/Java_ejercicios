package presentation;

import java.awt.Color;
import java.awt.Toolkit;

import domain.Game;
import jconsole.JConsole;

public class StartGame {
	
	
	//Atributos
	private Game game;
	private JConsole console;
	private JConsole savedCitiesInfo;
	
	private StartGame() {
		
		//Do not delete existing code
		
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int halfWidthGame = (screenWidth)/4;
		
		game = new Game();
		
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
		
		//Setup
		
		while(!game.hasEnded()) {
			//Show board
			
			//Show remaining actions info

			//Get user input
			
			//Parse and validate input
			
			//Make move
			
			//Show result
			
			//If city saved, show info

		}
		
		//Do whatever should be done when out of the while loop
		
	}
	
	private int[] parseAndValidateCoordinates(String input) { 
		//TODO: Complete
		return null;
	}
	
	private void end() {

		//These are the end game messages. Add the required ifs to ensure only the right message is shown when game ends.
		
		console.println("Sorry, no remaining actions... You lose the game");
		
		console.println("All cities have been infected... You lose!");

		console.println("Congratulations! You saved all cities!");
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
