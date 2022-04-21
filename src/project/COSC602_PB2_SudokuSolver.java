package project;

import java.io.*;
import java.util.Scanner;

/**
 * @author Steven Hogenson on 4/19/2022
 * @project StevenHogenson_Java
 */

public class COSC602_PB2_SudokuSolver {
    static String fileName;

    /**
     * test method to call/run from Main class
     */
    public static void test() {
        fileName = "../COSC602_PB2_SuDokuSheet.txt";
        long startTime = System.currentTimeMillis(); //used to check the computation time
        int[][] board = new int[0][0];
        try {
            board = boardFile();
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
        }

        if (!solveBoard(board)) {
            System.err.println("Not Solvable");
        } else {
            try {
                writeBoardToFile(board, System.currentTimeMillis() - startTime);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.err.println("File Not Found");
            }

        }
    }

    /**
     * Checks is a given number is currently in a given row
     *
     * @param board 2d int array
     * @param row   the row's index
     * @param num   the number to check if is already in row
     * @return boolean if a given number already exists in a given row
     */
    static boolean isNumberInRow(int[][] board, int row, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks is a given number is currently in a given column
     *
     * @param board  2d int array
     * @param column the column's index
     * @param num    the number to check if is already in column
     * @return boolean if a given number already exists in a given column
     */
    static boolean isNumberInColumn(int[][] board, int column, int num) {
        for (int[] ints : board) {
            if (ints[column] == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given number is currently in a given 3x3 square on the sudoku board
     *
     * @param board  the 2d int array as input
     * @param row    the row's index
     * @param column the column's index
     * @param num    the number to check if is already in 3x3 square
     * @return boolean if a given number already exists in a given 3x3 square
     */
    static boolean isNumberIn3x3(int[][] board, int row, int column, int num) {
        int localRow = row - (row % 3);
        int localColumn = column - (column % 3);
        for (int i = localRow; i < localRow + 3; i++) {
            for (int j = localColumn; j < localColumn + 3; j++) {
                if (board[i][j] == num) {
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Determines if a space on the board is valid for a given number (combines all 3 test cases)
     *
     * @param board  the 2d int array as input
     * @param row    the row's index
     * @param column the column's index
     * @param num    the number to check if it is valid for a spot
     * @return boolean if num can be placed in this spot of the 2d array
     */
    static boolean isValid(int[][] board, int row, int column, int num) {
        return !isNumberInRow(board, row, num) && !isNumberInColumn(board, column, num) && !isNumberIn3x3(board, row, column, num);
    }

    /**
     * Populates the board with numbers in valid positions until no further valid placements can be made, or the board is solved
     *
     * @param board the 2d int array as input
     * @return boolean if the board contains all valid spaces for numbers
     */
    public static boolean solveBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                if (board[row][column] == 0) {
                    for (int i = 1; i <= board.length; i++) {
                        if (isValid(board, row, column, i)) {
                            board[row][column] = i;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Converts the text file to a string from the designated input file and then generates a 2d int array
     *
     * @return a 2d int array
     * @throws FileNotFoundException if file is not found
     */
    public static int[][] boardFile() throws FileNotFoundException {

        Scanner sc = new Scanner(new File(fileName));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
        }
        //replaces all instances of 3 spaces ("   ") with a 0 and removes all characters that are not numbers in the text file
        String numString = sb.toString().replaceAll(" {3}", " 0 ").replaceAll("[^0-9]", "");

        //converts the String s2 into a 2d array of integers and returns that array
        char[] numStringArr = numString.toCharArray();
        int[][] boardArr = new int[9][9];
        for (int i = 0; i < boardArr.length; i++) {
            for (int j = 0; j < boardArr.length; j++) {
                boardArr[i][j] = Character.getNumericValue(numStringArr[(i * 9) + j]);
            }
        }
        return boardArr;
    }

    /**
     * Prints the solved sudoku board to the file in the method
     *
     * @param board           the 2d integer input array
     * @param computationTime the amount of time it takes for the program to complete;
     *                        This value is determined in the test() method and passed
     *                        here in order to write the value to the text file
     */
    public static void writeBoardToFile(int[][] board, long computationTime) throws FileNotFoundException {
        File output = new File("../COSC602_PB2_SuDokuSolution.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        //write the original file back to the solution file
        try (BufferedWriter writer = new BufferedWriter((new FileWriter(output)))) {
            while ((line = br.readLine()) != null) {
                writer.write(line + "\n");
            }
            //write the solution to the solution file
            writer.write("\n\n\nSolution:\n\n|---------------------------------------|\n");
            for (int i = 0; i < board.length; i++) {
                if (i % 3 == 0) {
                    writer.write("||-----------||-----------||-----------||\n");
                }
                for (int j = 0; j < board.length; j++) {
                    if (j % 3 == 0) {
                        writer.write("|| ");
                    } else {
                        writer.write("| ");
                    }
                    writer.write(board[i][j] + " ");
                }
                writer.write("||\n||-----------||-----------||-----------||\n");
            }
            writer.write("|---------------------------------------|");
            writer.newLine();
            writer.write("\nComputation Time: " + computationTime + " milliseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
