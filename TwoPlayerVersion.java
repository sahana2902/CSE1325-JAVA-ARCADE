package ticTacToe;


public class TwoPlayerVersion extends TicTacToe {
	
	public  void execute() {
		gameNumber++;
		super.displayRules();
		System.out.println("Game Number " + gameNumber);
		System.out.println();
		System.out.println("You are Player 1. Your game piece is X.");
		System.out.println("Other player is Player 2. Their game piece is O.");		
		super.printGameBoard(board);
		
		//TwoPlayerVersion
		int version = 2;
		//two player version means that the user will be player 1 and the other user will be player 2
		//player 1 = user 1;
		//player 2 = user 2;
		String player1 = "Player 1";
		String player2 = "Player 2";

		char gamePiece;
		boolean end = false;
		
		
		while(true && end == false)	{
			//player 1 (user 1) actions
			gamePiece = 'X';
			int userCellNumber = super.getCellNumber(player1);
			
			/*
			 this condition makes sure that player 1 (user 1) doesn't override the cells he has already 
			 marked with 'X' or player 2 (user 2) cells that have already been marked with 'O'
		    */
			
			while(player1Positions.contains(userCellNumber) || player2Positions.contains(userCellNumber)) {
				System.out.println("Cell already taken.");
				System.out.println();
				userCellNumber = super.getCellNumber(player1);
			}
			
			
			
			//place player 1 (user 1) game piece 'X' in the right cell as per player 1 (user 1) wish
			super.placeGamePiece(userCellNumber,board,gamePiece);
			
			//after player 1 (user 1) plays their turns check to see if player 1 won
			super.printGameBoard(board);
			int result = super.checkWinner();
			end = super.printWinner(result,version);
			
			//if player 1 already won terminate game
			if(end == true) {
				break;
			}
			
			//player 2 (user 2) actions
			gamePiece = 'O';
			int user2CellNumber = super.getCellNumber(player2);
			
			//only allows the player 2 (user 2) to mark a cell when there is an empty cell in the board
			if(player1Positions.size()+ player2Positions.size() < 9) {
				while(player2Positions.contains(user2CellNumber) || player1Positions.contains(user2CellNumber)) {
					System.out.println("Cell already taken.");
					System.out.println();
					user2CellNumber = super.getCellNumber(player2);
					
				}
			}
			//place player 2 (user 2) game piece 'O' in random cell 
			super.placeGamePiece(user2CellNumber, board, gamePiece);
			
			
			//after player 2 (user 2) plays their turns check to see if player 2 won
			super.printGameBoard(board);
			result = super.checkWinner();
			end = super.printWinner(result,version);
			
		    
		       
		        
		
		
		}
		super.emptyPositions();
	}

}
