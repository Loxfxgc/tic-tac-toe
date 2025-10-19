import java.util.*;

public class Main {

    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer = 'X';
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Tic Tac Toe ===");
        System.out.println("Instructions:");
        System.out.println("- Player 1: X");
        System.out.println("- Player 2: O");
        System.out.println("- Enter row and column numbers (0, 1, or 2) separated by space.");
        System.out.println("Example: '1 2' means Row 1, Column 2.\n");

        boolean playAgain;
        do {
            initializeBoard();
            playGame();
            playAgain = askToPlayAgain();
        } while (playAgain);

        System.out.println("Thanks for playing! Goodbye 👋");
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = ' ';
    }

    private static void playGame() {
        currentPlayer = 'X';
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();
            playerMove();

            if (hasWon(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (isDraw()) {
                printBoard();
                System.out.println("It's a draw!");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private static void printBoard() {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(" ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < SIZE - 1) System.out.println("---+---+---");
        }
        System.out.println();
    }

    private static void playerMove() {
        int row, col;
        while (true) {
            System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
                if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
                    System.out.println("Invalid input! Row and column must be between 0 and 2.");
                } else if (board[row][col] != ' ') {
                    System.out.println("Cell already taken! Choose another.");
                } else {
                    board[row][col] = currentPlayer;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter two integers (e.g., 1 2).");
                scanner.nextLine();
            }
        }
    }

    private static boolean hasWon(char player) {
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private static boolean isDraw() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    private static boolean askToPlayAgain() {
        System.out.print("Do you want to play again? (y/n): ");
        char choice = scanner.next().toLowerCase().charAt(0);
        return choice == 'y';
    }
}
