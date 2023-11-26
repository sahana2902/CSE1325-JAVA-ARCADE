package ticTacToe;


import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SinglePlayerVersion extends TicTacToe {
	
	public void execute() {
		gameNumber++;
		super.displayRules();
		System.out.println("Game Number " + gameNumber);
		System.out.println();
		System.out.println("You are Player 1. Your game piece is X.");
		System.out.println("CPU is Player 2. It's game piece is O.");
		System.out.println();

		super.printGameBoard(board);
		
		//SinglePlayerVersion
		int version = 1;
		//single player version means that the user will be player 1 and the CPU will be player 2
		String player1 = "Player 1";
		
		
		char gamePiece;
		boolean end = false;
		
		
		while(true && end == false)	{
			//player 1 (user) actions
			
			
			gamePiece = 'X';
			int userCellNumber = super.getCellNumber(player1);
			
			/*
			 this condition makes sure that player 1 (user) doesn't override the cells he has already 
			 marked with 'X' or player 2's (CPU) cells that have already been marked with 'O'
		    */
			
			while(player1Positions.contains(userCellNumber) || player2Positions.contains(userCellNumber)) {
				System.out.println("Cell already taken.");
				System.out.println();
				userCellNumber = super.getCellNumber(player1);
			}
			
			
			
			//place player 1 (user) game piece 'X' in the right cell as per player 1 (user) wish
			super.placeGamePiece(userCellNumber,board,gamePiece);
			
			int result = super.checkWinner();
			
			//if player 1 already won terminate game
			if(result == 1 || result == 3) {
				super.printGameBoard(board);
				end = super.printWinner(result,version);
				break;
			}
	
			
			
			//player 2 (CPU) actions
			gamePiece = 'O';
			int cpuCellNumber = 10;
			cpuCellNumber = easyMode();
		
			
			//place player 2 (CPU) game piece 'O' in random cell 
			super.placeGamePiece(cpuCellNumber, board, gamePiece);
			
			
			//after both player 1 (user) and player 2 (CPU) plays their turns
			super.printGameBoard(board);
			result = super.checkWinner();
			end = super.printWinner(result,version);
			
		    
		       
		     
		
		
		}
		super.emptyPositions();
		super.emptyBoard();
	}
	

	/*This method makes the CPU choose a random cell without strategy.
	 *So it gives the user a higher chance of winning.
	 */
	
	private static int easyMode() {
		Random random = new Random();
		
		//random.nextInt(upper bound) generates random numbers in the range 0 to upper bound-1 so need 
		//to add 1 to get cpuCellNumber in range 1-9
		int cpuCellNumber = random.nextInt(9) + 1;
		
		//only allows the player 2 (CPU) to mark a cell when there is an empty cell in the board
		if(player1Positions.size()+ player2Positions.size() < 9) {
			while(player2Positions.contains(cpuCellNumber) || player1Positions.contains(cpuCellNumber)) {
				cpuCellNumber = random.nextInt(9) + 1;
				
			}
		}
		return cpuCellNumber;
		
	}
	
}
