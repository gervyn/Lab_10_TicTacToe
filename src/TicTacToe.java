import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean playAgain;

        do {
            clearBoard();
            String player = "X";
            int moveCount = 0;
            boolean gameOver = false;

            while (!gameOver) {
                display();

                int row = SafeInput.getRangedInt(in, "Player " + player + " - choose row", 1, 3) - 1;
                int col = SafeInput.getRangedInt(in, "Player " + player + " - choose column", 1, 3) - 1;

                while (!isValidMove(row, col)) {
                    System.out.println("That cell is already taken. Please pick an empty cell.");
                    row = SafeInput.getRangedInt(in, "Player " + player + " - choose row", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in, "Player " + player + " - choose column", 1, 3) - 1;
                }

                board[row][col] = player;
                moveCount++;

                if (moveCount >= 5 && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (moveCount >= 7 && isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else {
                    player = player.equals("X") ? "O" : "X";
                }
            }

            playAgain = SafeInput.getYNConfirm(in, "Play again");
        } while (playAgain);

        System.out.println("Thanks for playing!");
        in.close();
    }

    private static void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }
    }

    private static void display() {
        System.out.println();
        System.out.println("    1   2   3");
        for (int r = 0; r < ROWS; r++) {
            System.out.println("  +---+---+---+");
            System.out.print((r + 1) + " |");
            for (int c = 0; c < COLS; c++) {
                System.out.print(" " + board[r][c] + " |");
            }
            System.out.println();
        }
        System.out.println("  +---+---+---+");
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return false;
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int r = 0; r < ROWS; r++) {
            if (player.equals(board[r][0]) &&
                    player.equals(board[r][1]) &&
                    player.equals(board[r][2])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int c = 0; c < COLS; c++) {
            if (player.equals(board[0][c]) &&
                    player.equals(board[1][c]) &&
                    player.equals(board[2][c])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        boolean mainDiag = player.equals(board[0][0]) &&
                player.equals(board[1][1]) &&
                player.equals(board[2][2]);

        boolean antiDiag = player.equals(board[0][2]) &&
                player.equals(board[1][1]) &&
                player.equals(board[2][0]);

        return mainDiag || antiDiag;
    }

    private static boolean isTie() {
        // Tie when board is full and nobody has won
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c].equals(" ")) {
                    return false;
                }
            }
        }
        return !isWin("X") && !isWin("O");
    }

}