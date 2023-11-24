import java.util.Random;
import java.util.Scanner;

public class Bingo {
    static Scanner input = new Scanner(System.in);
    static int[][] board = new int[5][5];
    static Random ran = new Random();

    public static void Finished() {
        // Implementation of the Finished method
        System.out.println("Game Over!");
    }

    public static void Bingo1() {
        int num = 0;
        int RanNum = 1;
        boolean uniqueNumber;
        for (int index = 0; index < 5; index++) {
            for (int row = 0; row < 5; row++) {
                do {
                    num = (int) (Math.random() * 15) + RanNum;
                    uniqueNumber = true;

                    for (int rows = 0; rows < 5; rows++) {
                        for (int columns = 0; columns < 5; columns++) {
                            if (board[rows][columns] == num) {
                                uniqueNumber = false;
                            }
                        }
                    }
                } while (!uniqueNumber);
                board[row][index] = num;
            }
            RanNum += 15;
        }
        board[2][2] = 100;
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

        while (true) {
            String answer;
            int randomizer = 0;
            System.out.println("Do you want to roll? \ny or n?");
            answer = input.next();
            if (answer.equalsIgnoreCase("Y")) {
                randomizer = ran.nextInt(76);
                if (randomizer == 0) {
                    randomizer++;
                }
            } else if (answer.equalsIgnoreCase("N")) {
                System.out.println("If you press 'no' you will lose");
                break;
            } else {
                System.out.println("Not in range");
            }
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
            System.out.println();
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
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (horizontal2 == 500) {
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (horizontal3 == 500) {
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (horizontal4 == 500) {
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (horizontal5 == 500) {
                System.out.println("Completed Pattern: Horizontal");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical2 == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical3 == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical4 == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            } else if (vertical5 == 500) {
                System.out.println("Completed Pattern: Vertical");
                System.out.println("B I N G O!");
                Finished();
                break;
            }
        }
    }

    public static void main(String[] args) {
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

        while (true) {
            System.out.print("Enter the number here: ");
            int in = input.nextInt();

            if (in == 1) {
                System.out.println("    ::::::::::::::::::::::::::::::::::");
                Bingo1();
                String answer;

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

            else if (in == 2) {
                System.out.println("GoodBye");
                break;
            } else {
                System.out.println("Invalid Input: Please choose 1 or 2");
            }
        }

    }
}
