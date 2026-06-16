import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static char[][] board = new char[3][3];

    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        tossAndAssignSymbols();
        displayTossResult();
        printBoard();

        int slot = getUserSlot();
        System.out.println("Slot entered: " + slot);
    }

    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    static void tossAndAssignSymbols() {
        Random random = new Random();
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

    static int getUserSlot() {
        System.out.print("Enter slot number (1-9): ");
        return scanner.nextInt();
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
