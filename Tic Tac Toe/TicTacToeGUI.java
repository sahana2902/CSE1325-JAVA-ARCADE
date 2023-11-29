package tictactoe;

import java.util.Scanner;

public class TicTacToeGUI {

	
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		boolean end = false;
		 while(true && end == false)	{
			 displayTicTacToeMenu();
			 System.out.print("Please enter the option number: ");
			 if(scanner.hasNextInt() == false) {
				 System.out.println("Invalid input format for option number. Please try again.");
				 scanner.nextLine();
				 System.out.println();
				 continue;
			 }
		
	        int userChoice = scanner.nextInt();
	        scanner.nextLine();
	        System.out.println();
	       // System.out.println("=========================================");
	        
	       
	        switch (userChoice) {
	            case 1:
	            	//tic tac toe single player
	            	new SinglePlayerVersion().execute();
	                break;
	            case 2:
	                //tic tac toe two player
	            	new TwoPlayerVersion().execute();
	                break;
	            case 3:
	            	//exit
	            	endTicTacToeMenu();
	            	//reset the games played so far back to zero
	            	TicTacToe.gameNumber = 0;
	            	//empties the score board every time the user exits tic tac toe
	            	TicTacToe.emptyTotalScores();
	            	end = true;
	                break;
	            default:
	            	//error handling to check that the integer user entered is in the valid range
	                System.out.println();
	                System.out.println("Option number out of range. Please try again.");
	                break;
	        }
		 }
	}
	
	private static void displayTicTacToeMenu() {
		System.out.println("=========================================");
        System.out.println("            Tic Tac Toe Menu             ");
        System.out.println("=========================================");
        System.out.println();
        System.out.println("1. Single player version");
        System.out.println("2. Two player version");
        System.out.println("3. Exit");
        System.out.println();
        System.out.println("=========================================");
       
        System.out.println();
	}

	private static void endTicTacToeMenu() {
	    System.out.println("Hope you enjoyed the Tic Tac Toe games!");
	    System.out.println();
	    System.out.println("=========================================");
	}
	
	

}
