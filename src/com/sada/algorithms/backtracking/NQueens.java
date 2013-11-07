/*
 * The MIT License
 *
 * Copyright 2013 Sada Kurapati <sadakurapati@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sada.algorithms.backtracking;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class NQueens {

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] board = new int[n]; //hold the column position of n queens
    placeQueenOnBoard(0, board);

  }

  private static void placeQueenOnBoard(int Qi, int[] board) {
    int n = board.length;
    //base case
    if (Qi == n) {// a valid configuration found.
      System.out.println(Arrays.toString(board));
    } else {
      //try to put the ith Queen (Qi) in all of the columns
      for (int column = 0; column < n; column++) {
        if (isSafePlace(column, Qi, board)) {
          board[Qi] = column;
          //then place remaining queens.
          placeQueenOnBoard(Qi + 1, board);
        }
      }
    }
  }
  //check if the column is safe place to put Qi (ith Queen)
  private static boolean isSafePlace(int column, int Qi, int[] board) {

    //check for all previously placed queens
    for (int i = 0; i < Qi; i++) {
      if (board[i] == column) { // the ith Queen(previous) is in same column
        return false;
      }
      //the ith Queen is in diagonal
      //(r1, c1) - (r2, c1). if |r1-r2| == |c1-c2| then they are in diagonal
      if (Math.abs(board[i] - column) == Math.abs(i - Qi)) {
        return false;
      }
    }
    return true;
  }
}
