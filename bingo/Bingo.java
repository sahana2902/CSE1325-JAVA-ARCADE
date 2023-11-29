package bingo;

import java.util.Random;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Bingo {
    // Scanner for user input
    static Scanner input = new Scanner(System.in);
    // 2D array to represent the Bingo board
    static int[][] board = new int[5][5];
    // Random number generator for rolling the Bingo numbers
    static Random ran = new Random();

    // Method to display a message when the game is finished
    public static void Finished() {
        System.out.println("Game Over!");
    }

    // Method to initialize the Bingo board
    public static void Bingo1() {
        int num = 0;
        int RanNum = 1;
        boolean uniqueNumber;
        Set<Integer> drawnNumbers = new HashSet<>();

        // Loop to fill the Bingo board with random numbers
        for (int index = 0; index < 5; index++) {
            for (int row = 0; row < 5; row++) {
                // Generate unique random numbers for each cell
                do {
                    num = (int) (Math.random() * 15) + RanNum;
                    uniqueNumber = true;

                    // Check if the generated number is unique on the board
                    for (int rows = 0; rows < 5; rows++) {
                        for (int columns = 0; columns < 5; columns++) {
                            if (board[rows][columns] == num) {
                                uniqueNumber = false;
                            }
                        }
                    }
                } while (!uniqueNumber);
                // Assign the unique number to the board
                board[row][index] = num;
            }
            RanNum += 15;
        }

        // Set the center cell as 'Free'
        board[2][2] = 100;

        // Display the initial state of the Bingo board
        String title[] = { "    B" + "    I" + "    N" + "    G" + "    O" };
        for (String title1 : title) {
            System.out.print(title1 + "\t");
        }
        System.out.println();
        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                if (rowIndex == 2 && columnIndex == 2) {
                    System.out.print("  Free ");
                } else {
                    System.out.print("  " + board[rowIndex][columnIndex] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("    ::::::::::::::::::::::::::::::::::");

        // Main game loop
        while (true) {
            String answer;
            int randomizer = 0;

            // Ask the user if they want to roll a number
            System.out.println("Do you want to roll? \ny or n?");
            answer = input.next();

            // Generate a random Bingo number if the user chooses 'y'
            if (answer.equalsIgnoreCase("Y")) {
                do {
                    randomizer = ran.nextInt(76);
                } while (drawnNumbers.contains(randomizer)); // Ensure the number is unique
                drawnNumbers.add(randomizer); // Add the drawn number to the set
            }
            // End the game if the user chooses 'n'
            else if (answer.equalsIgnoreCase("N")) {
                System.out.println("If you press 'no' you will lose");
                break;
            }
            // Display an error message for invalid input
            else {
                System.out.println("Not in range");
            }

            // Display the rolled Bingo number and update the board
            System.out.println("The numbers are: " + randomizer);
            System.out.println("    ::::::::::::::::::::::::::::::::::");
            for (String title1 : title) {
                System.out.print(title1 + "\t");
            }
            System.out.println();

            for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
                for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                    if (rowIndex == 2 && columnIndex == 2) {
                        System.out.print("  Free" + "\t");
                    } else if (board[rowIndex][columnIndex] == randomizer) {
                        System.out.print("  X\t");
                        board[rowIndex][columnIndex] = 100;
                    } else if (board[rowIndex][columnIndex] == 100) {
                        System.out.print("  X\t");
                    } else {
                        System.out.print("  " + board[rowIndex][columnIndex] + "\t");
                    }
                }
                System.out.println();
            }
            System.out.println(" ::::::::::::::::::::::::::::::::::::::");

            // Check for winning patterns (Diagonal, Horizontal, Vertical)
            int diagonal = 0, diagonal1 = 4;
            int sum = 0, sum1 = 0, horizontal = 0, horizontal2 = 0, horizontal3 = 0, horizontal4 = 0, horizontal5 = 0;
            int vertical = 0, vertical2 = 0, vertical3 = 0, vertical4 = 0, vertical5 = 0;

            for (int row = 0; row < 5; row++) {
                for (int column = 0; column < 5; column++) {
                    if (column == diagonal) {
                        sum = sum + board[row][column];
                    }
                    if (column == diagonal1) {
                        sum1 = sum1 + board[row][column];
                    }
                    if (column == 0) {
                        horizontal = horizontal + board[row][column];
                    }
                    if (column == 1) {
                        horizontal2 = horizontal2 + board[row][column];
                    }
                    if (column == 2) {
                        horizontal3 = horizontal3 + board[row][column];
                    }
                    if (column == 3) {
                        horizontal4 = horizontal4 + board[row][column];
                    }
                    if (column == 4) {
                        horizontal5 = horizontal5 + board[row][column];
                    }
                    if (row == 0) {
                        vertical = vertical + board[row][column];
                    }
                    if (row == 1) {
                        vertical2 = vertical2 + board[row][column];
                    }
                    if (row == 2) {
                        vertical3 = vertical3 + board[row][column];
                    }
                    if (row == 3) {
                        vertical4 = vertical4 + board[row][column];
                    }
                    if (row == 4) {
                        vertical5 = vertical5 + board[row][column];
                    }
                }
                diagonal1--;
                diagonal++;
            }

            // Check if any winning pattern is achieved
            if (sum == 500) {
                System.out.println("Completed Pattern: Diagonal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (sum1 == 500) {
                System.out.println("Completed Pattern: Diagonal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (horizontal == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (horizontal2 == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (horizontal3 == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (horizontal4 == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (horizontal5 == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical == 500) {
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical2 == 500) {
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical3 == 500) {
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical4 == 500) {
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical5 == 500) {
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            }
        }
    }

    public static void displayRules() {
        System.out.println("     \033[31;43;5mWelcome to the Bingo Game!\033[0m");
        System.out.println("========== Rules of Bingo ==========");
        System.out.println();
        System.out.println("1. Each player is given a Bingo board with randomly placed numbers.");
        System.out.println();
        System.out.println("2. The center cell is marked as 'Free'.");
        System.out.println();
        System.out.println("3. Players take turns rolling Bingo numbers.");
        System.out.println();
        System.out.println("4. If a rolled number matches a number on your board, mark it with an 'X'.");
        System.out.println();
        System.out.println("5. The game continues until a player completes a winning pattern:");
        System.out.println("   - Diagonal, Horizontal, or Vertical.");
        System.out.println();
        System.out.println("7. The game also ends if a player chooses to stop rolling numbers.");
        System.out.println();
        System.out.println("8. Enjoy the game of Bingo!");
        System.out.println();
        System.out.println("==================================");
    }

    // Main method to start the game
    public static void main(String[] args) {

        // Display the rules when the game starts
        displayRules();

        System.out.println("    ::::::::::::::::::::::::::::::::::");
        System.out.println("    ::      XXX B I N G O XXX       ::");
        System.out.println("    ::                              ::");
        System.out.println("    ::                              ::");
        System.out.println("    :: 1. Start Game                ::");
        System.out.println("    :: 2. Exit                      ::");
        System.out.println("    ::                              ::");
        System.out.println("    ::                              ::");
        System.out.println("    ::      XXX B I N G O XXX       ::");
        System.out.println("    ::::::::::::::::::::::::::::::::::");
        System.out.println("    NOTE: ALL YOUR INPUTS IN THIS GAME ARE CASE 'IN'SENSITIVE");

        // Initial menu loop
        while (true) {
            System.out.print("Enter the number here: ");

            // Check if the input is an integer
            if (input.hasNextInt()) {
                int choice = input.nextInt();

                // Start the game if the user chooses '1'
                if (choice == 1) {
                    System.out.println("    ::::::::::::::::::::::::::::::::::");
                    Bingo1();
                    String answer;

                    // Ask the user if they want to play again
                    while (true) {
                        System.out.println("Do you want to play again? \nYes or No?");
                        answer = input.next();
                        if (answer.equalsIgnoreCase("Yes")) {
                            System.out.println();
                            System.out.println();
                            System.out.println("    ::::::::::::::::::::::::::::::::::");
                            Bingo1();
                        } else if (answer.equalsIgnoreCase("No")) {
                            System.out.println("Thank You");
                            break;
                        } else {
                            System.out.println("Invalid Input");
                        }
                    }
                    break;
                }
                // Exit the game if the user chooses '2'
                else if (choice == 2) {
                    System.out.println("Goodbye");
                    break;
                } else {
                    // Display an error message for invalid input
                    System.out.println("Invalid Input: Please choose 1 or 2");
                }
            } else {
                // Display an error message for non-integer input
                System.out.println("Invalid Input: Please enter a number");
                input.next(); // consume the invalid input
            }
        }
    }
}
