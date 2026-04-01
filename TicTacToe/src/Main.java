import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }

        char player = 'X';
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printBoard(board);
            System.out.print("Player " + player + " enter: ");
            int row, col;
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine();
                System.out.println();
                continue;
            }
            System.out.println();
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid input. Rows and columns must be 0, 1, or 2. Try again!");
                continue;
            }
            if (board[row][col] == ' ') {
                board[row][col] = player; // place the element
                gameOver = haveWon(board, player);
                if (gameOver) {
                    System.out.println("Player " + player + " has won: ");
                } else if (isBoardFull(board)) {
                    System.out.println("The game is a draw!");
                    gameOver = true;
                } else {
                    // if (player == 'X') {
                    // player = 'O';
                    // } else {
                    // player = 'X';
                    // }
                    player = (player == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move. Try again!");
            }
        }
        printBoard(board);
    }

    public static boolean haveWon(char[][] board, char player) {
        // check the rows
        for (char[] chars : board) {
            if (chars[0] == player && chars[1] == player && chars[2] == player) {
                return true;
            }
        }

        // check for col
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // diagonal
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }
    public static boolean isBoardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println();
        }
    }
}