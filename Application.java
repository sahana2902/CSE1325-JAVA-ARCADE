package arcadeGame;

import java.util.Scanner;
import ticTacToe.*;
import bingo.*;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Application().run();
	}
	
	public static void displayArcadeMenu() {
		//This method will display the game menu and allow the user to choose which game they want to play
		
        //System.out.println("-----------------------------------------");
        System.out.println("=========================================");
        System.out.println("            Arcade Game Menu             ");
        System.out.println("=========================================");
        //System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("1. Tic Tac Toe");
        System.out.println("2. Bingo");
        System.out.println("3. Minesweeper");
        System.out.println("4. Exit");
        System.out.println();
        System.out.println("=========================================");
        //System.out.println("-----------------------------------------");
        System.out.println();
	}
	
	public void endProgramMenu() {
	    System.out.println();
	    System.out.println("Thank you for using Arcade Game.Good day!");
	    System.out.println();
	    System.out.println("=========================================");
	}
	
	public void run() {
		
		Scanner scanner = new Scanner(System.in);
		boolean end = false;
		
		//Error handling for invalid input 
	        while(true && end == false)	{
	        	displayArcadeMenu();
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
		            	//tic tac toe
		            	new ticTacToe.TicTacToeGUI().execute();
		                break;
		            case 2:
		                //bingo
				Bingo.main(new String[0]);
		                break;
		            case 3:
		            	//minesweeper
		                break;
		            case 4:
		            	endProgramMenu();
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

}
