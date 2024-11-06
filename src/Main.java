import java.util.Arrays;
import java.util.Scanner;
public class Main {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board [][] = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean terminate = false;
        String player = "";
        int turns = 0;

        clearBoard();
        do{
            display();
            if(turns % 2 == 0){
                System.out.println("\nX's turn");
                player = "X";
            }else{
                System.out.println("\nY's turn");
                player = "Y";
            }
            boolean done = false;
            do {
                int row = SafeInput.getRangedInt(in, "Enter a number 1-3 for the row you want", 1, 3) - 1;
                int column = SafeInput.getRangedInt(in, "Enter a number 1-3 for the column you want", 1, 3) - 1;
                if(isValidMove(row, column)){
                    board[row][column] = player;
                    done = true;
                }
            }while(!done);
            turns++;
            if(turns == 9){
                terminate = true;
            }
        }while(!terminate);
        display();
    }

    private static void clearBoard(){
        for (String[] strings : board) {
            Arrays.fill(strings, " ");
        }
    }

    private static void display(){
        System.out.print("  1 | 2 | 3");
        System.out.printf("\n1 %1$S | %2$S | %3$S", board[0][0], board[0][1], board[0][2]);
        System.out.printf("\n2 %1$S | %2$S | %3$S", board[1][0], board[1][1], board[1][2]);
        System.out.printf("\n3 %1$S | %2$S | %3$S", board[2][0], board[2][1], board[2][2]);
    }

    private static boolean isValidMove(int row, int col){
        return (board[row][col] == " ");
    }
}