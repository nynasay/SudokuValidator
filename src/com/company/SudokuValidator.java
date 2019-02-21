package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;

public class SudokuValidator {

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


        if (!ValidateColumn(correctSudoku)) {
            System.out.println("Columns are not valid");
        } else {
            System.out.println("Columns are valid");
        }

        if (!ValidateRow(correctSudoku)) {
            System.out.println("Rows are not valid");
        } else {
            System.out.println("Rows are valid");
        }


        for (int col = 0; col < 9; col =col+3) {
            for(int row =0; row < 9; row = row+3) {
                if (!ValidateGrid(correctSudoku, col, row)) {
                    System.out.println("Box is not valid");
                } else {
                    System.out.println("Box is valid");
                }
            }

        }

        //ValidateColumn columns = new ValidateColumn(correctSudoku);
        //ValidateRow rows = new ValidateRow(correctSudoku);
        //ValidateGrid three_by_three = new ValidateGrid(correctSudoku);

        //long start = System.nanoTime();
        //long end = System.nanoTime();

        //DecimalFormat format = new DecimalFormat("#.##");
        //boolean valid = true;


    }


    public static boolean ValidateColumn(int[][] sudoku) {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] eval = new int[9];
        boolean valid = true;
        //Looping through all of the columns
        for (int col = 0; col < 9; col++) {
            eval = new int[9];
            for (int row = 0; row < 9; row++) {
                eval[row] = sudoku[row][col];
            }
            System.out.println();
            Arrays.sort(eval);
            if (!Arrays.equals(values, eval)) {
                valid = false;
                break;
            }
        }
        return valid;
    }


    public static boolean ValidateRow(int[][] sudoku) {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] eval = new int[9];
        boolean valid = true;
        //Looping through all of the columns
        for (int row = 0; row < 9; row++) {
            eval = new int[9];
            for (int col = 0; col < 9; col++) {
                eval[col] = sudoku[row][col];
            }
            Arrays.sort(eval);
            if (!Arrays.equals(values, eval)) {
                valid = false;
                break;

            }
        }
        return valid;
    }


    public static boolean ValidateGrid(int[][] sudoku, int row, int column) {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] eval = new int[9];
        boolean valid = true;
        int k = 0;
        for (int i = row; i < (row+3); i++) {
            for (int j = column; j < (column+3); j++) {
                eval[k] = sudoku[j][i];
                k++;
            }

        }
        Arrays.sort(eval);
        if (!Arrays.equals(values, eval)) {
            valid = false;

        }
        return valid;
    }

}



