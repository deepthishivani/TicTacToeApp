import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static char[][] board = new char[3][3];

    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initializeBoard();
        tossAndAssignSymbols();
        displayTossResult();
        printBoard();

        while (true) {
            char currentSymbol;

            if (isHumanTurn) {
                makeHumanMove();
                currentSymbol = humanSymbol;
            } else {
                makeComputerMove();
                currentSymbol = computerSymbol;
            }

            printBoard();

            if (checkWinner(currentSymbol)) {
                if (currentSymbol == humanSymbol) {
                    System.out.println("Human wins!");
                } else {
                    System.out.println("Computer wins!");
                }
                break;
            }

            if (isBoardFull()) {
                System.out.println("Game is a draw!");
                break;
            }

            isHumanTurn = !isHumanTurn;
        }
    }

    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    static void tossAndAssignSymbols() {
        int toss = random.nextInt(2);

        if (toss == 0) {
            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';
        } else {
            isHumanTurn = false;
            computerSymbol = 'X';
            humanSymbol = 'O';
        }
    }

    static void displayTossResult() {
        System.out.println("Toss completed.");

        if (isHumanTurn) {
            System.out.println("Human starts first.");
        } else {
            System.out.println("Computer starts first.");
        }

        System.out.println("Human Symbol: " + humanSymbol);
        System.out.println("Computer Symbol: " + computerSymbol);
    }

    static void makeHumanMove() {
        int slot;

        do {
            slot = getUserSlot();

            if (!isValidMove(slot)) {
                System.out.println("Invalid move. Try again.");
            }
        } while (!isValidMove(slot));

        placeMove(slot, humanSymbol);
        System.out.println("Human placed move at slot: " + slot);
    }

    static int getUserSlot() {
        System.out.print("Enter slot number (1-9): ");
        return scanner.nextInt();
    }

    static boolean isValidMove(int slot) {
        if (slot < 1 || slot > 9) {
            return false;
        }

        int row = getRowFromSlot(slot);
        int col = getColFromSlot(slot);

        return board[row][col] == '-';
    }

    static void placeMove(int slot, char symbol) {
        int row = getRowFromSlot(slot);
        int col = getColFromSlot(slot);

        board[row][col] = symbol;
    }

    static void makeComputerMove() {
        int slot;

        do {
            slot = random.nextInt(9) + 1;
        } while (!isValidMove(slot));

        placeMove(slot, computerSymbol);
        System.out.println("Computer chose slot: " + slot);
    }

    static boolean checkWinner(char symbol) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == symbol && board[row][1] == symbol && board[row][2] == symbol) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == symbol && board[1][col] == symbol && board[2][col] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }

        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }

        return false;
    }

    static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }

        return true;
    }

    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3;
    }

    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }

    static void printBoard() {
        System.out.println("\nTic-Tac-Toe Board:");

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
