package ticTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
	protected static ArrayList<Integer> player1Positions = new ArrayList<>();
	protected static ArrayList<Integer> player2Positions = new ArrayList<>();
	protected static char [][] board = {{' ', '|', ' ', '|', ' '},
								 {'-', '+', '-', '+', '-'},
								 {' ', '|', ' ', '|', ' '},
								 {'-', '+', '-', '+', '-'},
								 {' ', '|', ' ', '|', ' '}};

	private static HashMap<String,String> totalScores = new HashMap<>();
	public static int gameNumber = 0;
	
	
	private static void updateTotalScore( String winner) {
		String key = "Game Number " + Integer.toString(gameNumber);
		String value = "";
		if(winner.compareTo("CPU") == 0) {
			value = "CPU system won";
		}
		else if(winner.compareTo("Player 1") == 0) {
			value = "You won";
		}
		else if (winner.compareTo("Player 2") == 0) {
			value = "Player 2 won";
		}
		else if(winner.compareTo("tie") == 0)
		{
			value = "Tie";
		}
		
		totalScores.put(key, value);
	}
	public static void displayTotalScore() {
		
		ArrayList<String> keys = new ArrayList<>();
		keys.addAll(totalScores.keySet());
		Collections.sort(keys);
		
		System.out.println("=========================================");
		System.out.println("         Tic Tac Toe Score Board         ");
		System.out.println("=========================================");
		System.out.println();
		
		
		if(totalScores.isEmpty()) {
			System.out.println("         No scores to be shown       ");
		}
		
		for(String key : keys) {
			System.out.printf("For %s: %s\n",key, totalScores.get(key));
		}
		System.out.println();
		
	}
	
	public static void displayRules() {
		System.out.println("========== Rules of Tic Tac Toe =========");
		System.out.println();
		System.out.println("Players take turns putting their marks in empty squares.");
		System.out.println();
		System.out.println("The first player to get 3 of their marks in a row ");
		System.out.println();
		System.out.println("(up, down, across, or diagonally) is the winner.");
		System.out.println();
		System.out.println("When all 9 squares are full, the game is over.");
		System.out.println();
		System.out.println("If no player has 3 marks in at row, the game ends in a tie.");
		System.out.println();
		System.out.println("When it is your turn, enter the cell number as shown below");
		System.out.println();
		System.out.println("where you want your gamepiece to be placed at.");
		System.out.println();
		System.out.println("1|2|3\n"
				+ "-+-+-\n"
				+ "4|5|6\n"
				+ "-+-+-\n"
				+ "7|8|9");
		System.out.println();
		System.out.println("Good luck! Let's start.");
		System.out.println();
		System.out.println("=========================================");
		
	}
	
	public static void printGameBoard(char [][] board) {
		System.out.println();
		for(char[] row : board) {
			for(char item : row) {
				System.out.print(item);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int getCellNumber (String player) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			
			System.out.printf(player + ": Enter which cell you want to place you game piece (1-9): ");
			//error handling for invalid input
			if(scanner.hasNextInt() == false) {
				System.out.println("Invalid input format for cell number. Please try again.");
				scanner.nextLine();
				System.out.println();
				continue;
			}
			
			
			break;
		}
		int cellNumber = scanner.nextInt();
		if(cellNumber < 1 || cellNumber> 9) {
			System.out.println("Cell number out of range. Please try again.");
			System.out.println();
			return getCellNumber(player);
		}
		return cellNumber;
		
	}
	
	public static void placeGamePiece(int cellNumber, char[][]board, char gamePiece) {
		
		if(gamePiece == 'X') { //user = player 1
			player1Positions.add(cellNumber);
		}
		else if(gamePiece == 'O') { //either cpu or user 2 = player 2
			player2Positions.add(cellNumber);
		}
		if(player1Positions.size()+ player2Positions.size() > 9) {
			return;
		}
		switch(cellNumber) {
			case 1:
				board[0][0] = gamePiece;
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
		
		
		
	}
	
	public static int checkWinner() {
		int result = 0;
		//the numbers given are the cellNumbers 
		
		//winning conditions for 3 in a row
		List<Integer> filledFirstRow = Arrays.asList(1,2,3);
		List<Integer> filledSecondRow = Arrays.asList(4,5,6);
		List<Integer> filledThirdRow = Arrays.asList(7,8,9);
		
		//winning conditions for 3 in a column
		List<Integer> filledFirstCol = Arrays.asList(1,4,7);
		List<Integer> filledSecondCol = Arrays.asList(2,5,8);
		List<Integer> filledThirdCol = Arrays.asList(3,6,9);
		
		//winning conditions for 3 in a diagonal
		List<Integer> leftDiagonal = Arrays.asList(1,5,9);
		List<Integer> rightDiagonal = Arrays.asList(3,5,7);
		
		ArrayList<List<Integer>> winningConditions = new ArrayList<>();
		winningConditions.add(filledFirstRow);
		winningConditions.add(filledSecondRow);
		winningConditions.add(filledThirdRow);
		winningConditions.add(filledFirstCol);
		winningConditions.add(filledSecondCol);
		winningConditions.add(filledThirdCol);
		winningConditions.add(leftDiagonal);
		winningConditions.add(rightDiagonal);
		
		for(List<Integer> winningCriteria : winningConditions) {
			if(player1Positions.containsAll(winningCriteria)) { //player 1 (user) won
				result = 1;
				
				
			}
			else if(player2Positions.containsAll(winningCriteria)) { //player 2 (CPU or user 2) won
				result = 2;
				
			}
			
		}
		//if no one won and board is full then declare a tie
		if(result == 0 && player1Positions.size() + player2Positions.size() == 9) { 
			result = 3;
			
		}
		return result;
		
		
		
	}
	
	public static boolean printWinner(int result, int version) {
		//version 1 = SinglePlayer: player 1 (user) against CPU system
		//version 2 = TwoPlayer: player 1 (user 1) against player 2 (user 2)
		
		if(result == 1) { //player 1 (user) won
			if(version == 1) { 
				System.out.printf("Congratulations, you won :)\n");
				
			}
			else if (version ==2) {
				System.out.printf("Player 1, congratulations you won :)\n");
				System.out.printf("Player 2, better luck next time!\n");
			}
			System.out.println();
			updateTotalScore("Player 1");
			return true;
		}
		else if (result == 2) { //player 2 (CPU or user 2) won
			if(version == 1) {
				System.out.println("Sorry the system won :(");
				updateTotalScore("CPU");
			}
			else if (version == 2){
				System.out.printf("Player 2, congratulations you won :)\n");
				System.out.printf("Player 1, better luck next time!\n");
				updateTotalScore("Player 2");
			}
			System.out.println();
			return true;
		}
		else if (result ==3) { //tie
			System.out.println("It's a tie! Good game!!");
			System.out.println();
			updateTotalScore("tie");
			return true;
		}
		return false;
		
		
	}
	
	public static void emptyPositions() {
		player1Positions.clear();
		player2Positions.clear();
	}
	
	public static void emptyTotalScores() {
		totalScores.clear();
	}
	
	public static void emptyBoard() {
		for(int i = 1; i <= 9; i++) {
			placeGamePiece(i, board, ' ');
		}
		
	}
	
	
}
