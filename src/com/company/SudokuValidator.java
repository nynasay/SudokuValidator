package com.company;
import java.text.DecimalFormat;
import java.util.Arrays;

public class SudokuValidator {

    public static void main(String[] args) {

        int correctSudoku[][]= {{6, 2, 4, 5, 3, 9, 1, 8, 7},
                {5, 1, 9, 7, 2, 8, 6, 3, 4},
                {8, 3, 7, 6, 1, 4, 2, 9, 5},
                {1, 4, 3, 8, 6, 5, 7, 2, 9},
                {9, 5, 8, 2, 4, 7, 3, 6, 1},
                {7, 6, 2, 3, 9, 1, 4, 5, 8},
                {3, 7, 1, 9, 5, 6, 8, 4, 2},
                {4, 9, 6, 1, 8, 2, 5, 7, 3},
                {2, 8, 5, 4, 7, 3, 9, 1, 6}};


        long start = System.nanoTime();
        long end = System.nanoTime();

        DecimalFormat format = new DecimalFormat("#.##");
        //boolean valid = true;
        boolean c = threadCreator(correctSudoku);

    }

    public static boolean threadCreator(int sudoku[][]) {

        int [] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int [] eval = new int[9];

        boolean valid = true;


        //Looping through all of the columns
        for(int row = 0; row < sudoku.length; row++) {
            for(int col = 0; col < sudoku[0].length; col++) {
                eval[col] = sudoku[row][col];
            }
            Arrays.sort(eval);
            if (!Arrays.equals(values, eval)) {
                valid = false;
            }
        }
        Arrays.fill(eval, 0);

        //Looping through all the rows
        for(int col = 0; col < sudoku[0].length; col++) {
            for(int row = 0; row < sudoku.length; row++) {
                eval[row] = sudoku[row][col];
            }
            Arrays.sort(eval);
            if (!Arrays.equals(values, eval)) {
                valid = false;
            }
        }
        Arrays.fill(eval, 0);


        //Looping through all the boxes
        for (int i =0; i<3; i++) {
            for(int j = 0; j <3;j++) {
                int k =0;
                eval = new int [9];
                for( int row = i*3; row <(i*3)+3; row++) {
                    for( int column = j *3; column< (i*3)+3; column++) {
                        eval[k] = sudoku[row][column];
                        System.out.println("k: " + k);
                        k++;

                    }

                }
            }
        }
        Arrays.sort(eval);
        if (!Arrays.equals(values, eval)) {
            valid = false;
        }


        return valid;
    }
}
