package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MineSweeper extends JFrame {
    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final int MINES = 10;
    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] revealed;
    private int remainingCells;
    private int score;

    private JPanel gamePanel;
    private JButton tryAgainButton;

    private boolean gameOver;

    public MineSweeper() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        setupGamePanel();
        setupTryAgainButton();

        add(gamePanel, BorderLayout.CENTER);
        add(tryAgainButton, BorderLayout.SOUTH);

        initializeGame();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(ROWS, COLS));

        buttons = new JButton[ROWS][COLS];
        revealed = new boolean[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(30, 30));
                button.setFocusPainted(false);
                gamePanel.add(button);
                buttons[i][j] = button;

                final int row = i;
                final int col = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!gameOver) {
                            handleButtonClick(row, col);
                        }
                    }
                });
            }
        }
    }


    private void setupTryAgainButton() {
        tryAgainButton = new JButton("Try Again");
        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        tryAgainButton.setVisible(false); // Initially invisible
    }

    private void initializeGame() {
        mines = new boolean[ROWS][COLS];
        revealed = new boolean[ROWS][COLS];
        remainingCells = ROWS * COLS - MINES;
        score = 0;
        gameOver = false;

        Random random = new Random();
        int count = 0;
        while (count < MINES) {
            int row = random.nextInt(ROWS);
            int col = random.nextInt(COLS);
            if (!mines[row][col]) {
                mines[row][col] = true;
                count++;
            }
        }
    }

    private void handleButtonClick(int row, int col) {
        if (revealed[row][col]) {
            return;
        }

        revealed[row][col] = true;
        buttons[row][col].setEnabled(false);

        if (mines[row][col]) {
            revealAllMines();
            gameOver = true;
            showTryAgainButton();
            gameOver();
        } else {
            int adjacentMines = getAdjacentMineCount(row, col);
            if (adjacentMines > 0) {
                buttons[row][col].setText(Integer.toString(adjacentMines));
            } else {
                // Recursive reveal for adjacent cells
                revealCell(row - 1, col - 1);
                revealCell(row - 1, col);
                revealCell(row - 1, col + 1);
                revealCell(row, col - 1);
                revealCell(row, col + 1);
                revealCell(row + 1, col - 1);
                revealCell(row + 1, col);
                revealCell(row + 1, col + 1);
            }
            score++;
            checkWin();
        }
    }

    private void revealCell(int row, int col) {
        if (row < 0 || col < 0 || row >= ROWS || col >= COLS || revealed[row][col]) {
            return;
        }

        revealed[row][col] = true;
        buttons[row][col].setEnabled(false);
        remainingCells--;

        int adjacentMines = getAdjacentMineCount(row, col);
        if (adjacentMines > 0) {
            buttons[row][col].setText(Integer.toString(adjacentMines));
        } else {
            // Recursive reveal for adjacent cells
            revealCell(row - 1, col - 1);
            revealCell(row - 1, col);
            revealCell(row - 1, col + 1);
            revealCell(row, col - 1);
            revealCell(row, col + 1);
            revealCell(row + 1, col - 1);
            revealCell(row + 1, col);
            revealCell(row + 1, col + 1);
        }
    }

    private int getAdjacentMineCount(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < ROWS && j >= 0 && j < COLS && mines[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void revealAllMines() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (mines[i][j]) {
                    buttons[i][j].setText("*");
                }
            }
        }
    }

    private void gameOver() {
        disableAllButtons();
        JOptionPane.showMessageDialog(this, "Game Over! Score: " + score);
    }

    private void checkWin() {
        if (remainingCells == 0) {
            disableAllButtons();
            JOptionPane.showMessageDialog(this, "You win! Score: " + score);
        }
    }

    private void disableAllButtons() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

private void resetGame() {
    initializeGame();
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            buttons[i][j].setText(""); // Clear cell texts
            buttons[i][j].setEnabled(true); // Enable buttons
        }
    }
    tryAgainButton.setVisible(false); // Hide the button again
    gameOver = false;
}


    private void showTryAgainButton() {
        tryAgainButton.setVisible(true);
    }

    public static void main(String[] args) {
        runMineSweeper();
    }

     public static void runMineSweeper() {
        SwingUtilities.invokeLater(() -> {
            MineSweeper game = new MineSweeper();
            game.setVisible(true);
        });
    }
}
//SwingUtilities.invokeLater(() -> new MineSweeper());

