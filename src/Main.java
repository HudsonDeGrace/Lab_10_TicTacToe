import java.util.Arrays;
import java.util.Scanner;
public class Main {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final String[][] board = new String[ROWS][COLS];

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
                System.out.println("\nO's turn");
                player = "O";
            }
            boolean done = false;
            do {
                int row = SafeInput.getRangedInt(in, "Enter a number 1-3 for the row you want", 1, 3) - 1;
                int column = SafeInput.getRangedInt(in, "Enter a number 1-3 for the column you want", 1, 3) - 1;
                if(isValidMove(row, column)){
                    board[row][column] = player;
                    done = true;
                } else{
                    System.out.println("Please choose a valid cell!");
                }
            }while(!done);
            turns++;
            if(isWin(player)){
                display();
                clearBoard();
                System.out.println("\n" + player + " Wins!");
                if(!SafeInput.getYNConfirm(in, "Would you like to play again")){
                    terminate = true;
                }
            } else if(isTie()){
                display();
                clearBoard();
                System.out.println("\nIt's a tie!");
                if(!SafeInput.getYNConfirm(in, "Would you like to play again")){
                    terminate = true;
                }
            }
        }while(!terminate);
    }

    private static void clearBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                board[i][j] = " ";
            }
        }
    }

    private static void display(){
        System.out.print("  1 | 2 | 3");
        System.out.printf("\n1 %1$S | %2$S | %3$S", board[0][0], board[0][1], board[0][2]);
        System.out.print("\n -----------");
        System.out.printf("\n2 %1$S | %2$S | %3$S", board[1][0], board[1][1], board[1][2]);
        System.out.print("\n -----------");
        System.out.printf("\n3 %1$S | %2$S | %3$S", board[2][0], board[2][1], board[2][2]);
    }

    private static boolean isValidMove(int row, int col){
        return (board[row][col] == " "); // A valid move if the element trying to be replaced is a space
    }

    private static boolean isWin(String player){
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player){
        int col1 = 0;
        int col2 = 0;
        int col3 = 0;
        for (String[] strings : board) { // Loop through first layer array
            for (int j = 0; j < board.length; j++) { // Loops through each second layer array looking for player matching columns
                switch (j) {
                    case 0:
                        if (strings[j].equals(player)) {
                            col1++;
                        }
                    case 1:
                        if (strings[j].equals(player)) {
                            col2++;
                        }
                    case 2:
                        if (strings[j].equals(player)) {
                            col3++;
                        }
                }
            }
        }
        return col1 == 3 || col2 == 3 || col3 == 3;
    }

    private static boolean isRowWin(String player) {
        int winSteps = 0;
        for (String[] i : board) {
            winSteps = 0;
            for (String j : i) { // Checks each second layer array to see if all the elements match the player
                if (j.equals(player)) {
                    winSteps++;
                }
            }
        }
        return winSteps == 3;
    }

    private static boolean isDiagonalWin(String player){
        int winSteps = 0;
        for(int i = 0; i < board.length; i++){ // Loops 3 times
            if(board[i][i].equals(player)){ // Checks each index of the same number and checks if it matches the player
                winSteps++;
            }
        }
        return winSteps == 3;
    }

    private static boolean isTie(){
        int steps = 0;
        for(String[] i : board){
            for(String j : i){ // A tie if all the elements are not empty/spaces
                if(!j.equals(" ")){
                    steps++;
                }
            }
        }
        return steps == 9;
    }
}