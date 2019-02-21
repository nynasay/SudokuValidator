package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;

public class SudokuValidator {

    public static long startTime = System.nanoTime();
    public static DecimalFormat df = new DecimalFormat("#.##");
    public static boolean[] threadsResults = new boolean[3];

    public static void main(String[] args) {

        int[][] correctSudoku = new int[][]{
                {6, 2, 4, 5, 3, 9, 1, 8, 7},
                {5, 1, 9, 7, 2, 8, 6, 3, 4},
                {8, 3, 7, 6, 1, 4, 2, 9, 5},
                {1, 4, 3, 8, 6, 5, 7, 2, 9},
                {9, 5, 8, 2, 4, 7, 3, 6, 1},
                {7, 6, 2, 3, 9, 1, 4, 5, 8},
                {3, 7, 1, 9, 5, 6, 8, 4, 2},
                {4, 9, 6, 1, 8, 2, 5, 7, 3},
                {2, 8, 5, 4, 7, 3, 9, 1, 6}};


        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        //CREATE TASKS
        Runnable checkCols = new ValidateColumn(correctSudoku, values);
        Runnable checkRows = new ValidateRow(correctSudoku, values);
        Runnable checkGrids = new ValidateGrid(correctSudoku, values);

        //CREATE THREADS
        Thread thread1 = new Thread(checkCols);
        Thread thread2 = new Thread(checkRows);
        Thread thread3 = new Thread(checkGrids);

        //START THREADS
        try {
            thread1.start();
            thread2.start();
            thread3.start();
            thread3.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        //THE FOLLOWING CODE PRINTS TO THE CONSOLE WHETHER THE ENTIRE SUDOKU PUZZLE IS VALID
        boolean valid = true;
        for(int i = 0; i < threadsResults.length; i++){
            if(threadsResults[i] == false){
                System.out.println("Invalid Sudoku Puzzle");
                valid = false;
            }
        }

        if(valid){
            System.out.println("Valid Sudoku Puzzle");
        }
    }
}

//THE TASK FOR CHECKING IF THE COLUMNS ARE VALID
class ValidateColumn implements Runnable {
    private int[][] sudokuPuzzle; //THE SUDOKU TO VALIDATE THE COLUMNS OF
    private int[] validNums; //THE POTENTIAL VALUES THAT CAN BE FOUND IN A COLUMN
    private boolean result; //THE VALUES FOUND IN A COLUMN

    /**
     * CONSTRUCTING A TASK WITH THE SPECIFIED 2D ARRAY OF THE SUDOKU PUZZLE, POTENTIAL
     * VALUES THAT CAN BE FOUND IN A COLUMN, AND VALUES FOUND IN A COLUMN
     */
    public ValidateColumn(int[][] sudoku, int[] valid) {
        sudokuPuzzle = sudoku;
        validNums = valid;
        result = true;
    }

    @Override
    //OVERRIDE THE run() METHOD TO TELL THE SYSTEM WHAT TASK TO PERFORM
    public void run() {
        int[] found = new int[9];
        //Looping through all of the columns
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                found[row] = sudokuPuzzle[row][col];
            }
            Arrays.sort(found);
            if (!Arrays.equals(validNums, found)) {
                result = false;
                break;
            }
        }

        if (!result) {
            System.out.println("Columns are not valid");
        } else {
            System.out.println("Columns are valid");
        }
        System.out.println("Column Time: " + SudokuValidator.df.format((double)(System.nanoTime() - SudokuValidator.startTime)/1000000) + "ms");
        SudokuValidator.threadsResults[0] = result;
    }
}

//THE TASK FOR CHECKING IF THE ROWS ARE VALID
class ValidateRow implements Runnable {
    private int[][] sudokuPuzzle; //THE SUDOKU TO VALIDATE THE ROWS OF
    private int[] validNums; //THE POTENTIAL VALUES THAT CAN BE FOUND IN A ROW
    private boolean result; //THE VALUES FOUND IN A ROW

    /**
     * CONSTRUCTING A TASK WITH THE SPECIFIED 2D ARRAY OF THE SUDOKU PUZZLE, POTENTIAL
     * VALUES THAT CAN BE FOUND IN A ROW, AND VALUES FOUND IN A ROW
     */
    public ValidateRow(int[][] sudoku, int[] valid) {
        sudokuPuzzle = sudoku;
        validNums = valid;
        result = true;
    }

    @Override
    //OVERRIDE THE run() METHOD TO TELL THE SYSTEM WHAT TASK TO PERFORM
    public void run() {
        int[] found = new int[9];
        //Looping through all of the columns
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                found[col] = sudokuPuzzle[row][col];
            }
            Arrays.sort(found);
            if (!Arrays.equals(validNums, found)) {
                result = false;
                break;
            }
        }

        if (!result) {
            System.out.println("Rows are not valid");
        } else {
            System.out.println("Rows are valid");
        }
        System.out.println("Row Time: " + SudokuValidator.df.format((double)(System.nanoTime() - SudokuValidator.startTime)/1000000) + "ms");
        SudokuValidator.threadsResults[1] = result;
    }
}

//THE TASK FOR CHECKING IF THE GRIDS ARE VALID
class ValidateGrid implements Runnable {
    private int[][] sudokuPuzzle; //THE SUDOKU TO VALIDATE THE ROWS OF
    private int[] validNums; //THE POTENTIAL VALUES THAT CAN BE FOUND IN A ROW
    private boolean result; //THE VALUES FOUND IN A ROW

    /**
     * CONSTRUCTING A TASK WITH THE SPECIFIED 2D ARRAY OF THE SUDOKU PUZZLE, POTENTIAL
     * VALUES THAT CAN BE FOUND IN A ROW, AND VALUES FOUND IN A ROW
     */
    public ValidateGrid(int[][] sudoku, int[] valid) {
        sudokuPuzzle = sudoku;
        validNums = valid;
        result = true;
    }

    @Override
    //OVERRIDE THE run() METHOD TO TELL THE SYSTEM WHAT TASK TO PERFORM
    public void run() {
        int[] found = new int[9];
        for (int col = 0; col < 9; col = col + 3) {
            for (int row = 0; row < 9; row = row + 3) {
                int k = 0;
                for (int i = row; i < (row + 3); i++) {
                    for (int j = col; j < (col + 3); j++) {
                        found[k] = sudokuPuzzle[j][i];
                        k++;
                    }

                }
                Arrays.sort(found);
                if (!Arrays.equals(validNums, found)) {
                    result = false;
                    break;
                }
            }
        }
        if (!result) {
            System.out.println("Grids are not valid");
        } else {
            System.out.println("Grids are valid");
        }
        System.out.println("Grid Time: " + SudokuValidator.df.format((double)(System.nanoTime() - SudokuValidator.startTime)/1000000) + "ms");
        SudokuValidator.threadsResults[2] = result;
    }
}



