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
		displayMode();
		System.out.print("Please enter the option number: ");
		Scanner scanner = new Scanner(System.in);
		int mode = scanner.nextInt();
		scanner.nextLine();
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
			System.out.println(userCellNumber);
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
			if(result == 1) {
				super.printGameBoard(board);
				end = super.printWinner(result,version);
				break;
			}
	
			
			//player 2 (CPU) actions
			gamePiece = 'O';
			int cpuCellNumber = 10;
			
			switch(mode) {
				case 1 -> cpuCellNumber = easyMode();
				case 2 -> cpuCellNumber = hardMode(gamePiece);
				default -> System.out.println("wrong value");
			}
			System.out.println("cpu cell" + cpuCellNumber);
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
	
	private static void displayMode() {
		System.out.println("=========================================");
        System.out.println("            Tic Tac Toe Mode             ");
        System.out.println("=========================================");
        System.out.println();
        System.out.println("1. Easy Mode");
        System.out.println("2. Hard Mode");
        System.out.println("3. Exit");
        System.out.println();
        System.out.println("=========================================");
       
        System.out.println();
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
	private static int bestCPUMove(char gamePiece) {
		int bestScore = -999;
		int bestCell = 10;
		int cellNumber = 0;
		int depth = 0;
		//CPU is the Maximizing player, human is the Minimizing player
		boolean isMaximizing = false;

		//check if there is an available spot
		for(int i =0; i < board.length; i+=2) {
			for(int j = 0; j < board[i].length; j+=2) {
				cellNumber++;
				if(board[i][j] == ' ') {
					//place CPU gamePiece
					board[i][j] = 'O';
					/*to find the scores after CPU plays 
					* Now it is Human's turn which means it is minimizing
					*/
					int score = (int) miniMax(board,depth,isMaximizing);
					//undo placing the CPU gamepiece as it was used only to find the scores with miniMax Algorithm
					board[i][j] = ' ';
					if(score > bestScore) {
						bestScore = score;
						bestCell = cellNumber;
					}
				}
			}
			
		}
		cellNumber = 0;
		return bestCell;
		
		

	}
	
	private static double miniMax(char [][] board, int depth, boolean isMaximizing) {
		double score;
		int result = checkWinner();
		HashMap<Integer, Integer> scoreTable = new HashMap<>();
		/*
		 * result = 1 means player 1 (user) won
		 * result = 2 means player 2(CPU) won
		 * result = 3 means tie
		 * result = 0 means no winner yet
		 */
		switch(result) {
			//key is the result and value is the score
			case 1 -> scoreTable.put(1, -1);
			case 2 -> scoreTable.put(2, 1);
			case 3 -> scoreTable.put(3, 0);
			//default -> scoreTable.put(0, -999);
		}
		
		//if it is a terminal state
		if(result != 0) {
			return scoreTable.get(result);
		}
		
		if(isMaximizing == true) { //next turn is CPU turn
			double bestScore = -999;
			for(int i =0; i < board.length; i+=2) {
				for(int j = 0; j < board[i].length; j+=2) {
					if(board[i][j] == ' ') {
						board[i][j] = 'O'; //CPU game piece
						score = miniMax(board, depth+1, false);
						board[i][j] = ' ';
						bestScore = Math.max(score, bestScore);
					}
				}
			}
			return bestScore;
		}
		
		else { //inMinimizing which means next turn is human turn
			double bestScore = 999;
			for(int i =0; i < board.length; i+=2) {
				for(int j = 0; j < board[i].length; j+=2) {
					if(board[i][j] == ' ') {
						board[i][j] = 'X'; //Human game piece
						//following turn is CPU's so isMaximizing is true
						score = miniMax(board, depth+1, true);
						board[i][j] = ' ';
						bestScore = Math.min(score, bestScore);
					}
				}
			}
			return bestScore;
		}
	}
	/*
	public static int convertToCellNumber(int row,int col) {
		int cellNumber;
		switch (row) {
			case 0 -> {switch (col) {
							case 0 -> cellNumber = 1;
							case 1 -> cellNumber = 2;
							case 2 -> cellNumber = 3;
						}	
					  }
			case 1 -> {switch (col) {
							case 0 -> cellNumber = 1;
							case 1 -> cellNumber = 2;
							case 2 -> cellNumber = 3;
									}	
					  }
			case 1 -> {switch (col) {
			case 0 -> cellNumber = 1;
			case 1 -> cellNumber = 2;
			case 2 -> cellNumber = 3;
		}	
	  }
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		if(row == 0 && col == 0) {
			cellNumber = 1;
		}
		break;
	case 2:
		board[0][2] = gamePiece;
		break;
	case 3:
		board[0][4] = gamePiece;
		break;
	case 4:
		board[2][0] = gamePiece;
		break;
	case 5:
		board[2][2] = gamePiece;
		break;
	case 6:
		board[2][4] = gamePiece;
		break;
	case 7:
		board[4][0] = gamePiece;
		break;
	case 8:
		board[4][2] = gamePiece;
		break;
	case 9:
		board[4][4] = gamePiece;
		break;
	default:
		System.out.println("Invalid inputs.");
		break;
	}
	*/
	private static int hardMode(char gamePiece) {
		//using miniMax algorithm
		
		int bestCellNumber = bestCPUMove(gamePiece);

		return bestCellNumber;

	}

}
