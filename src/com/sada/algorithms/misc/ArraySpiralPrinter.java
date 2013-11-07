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
package com.sada.algorithmsmiscp;

import java.util.Scanner;

/**
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class ArraySpiralPrinter {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); //marrix size
    //read array
    int[][] ar = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        ar[i][j] = sc.nextInt();
      }
    }
    printTopRight(0, 0, n - 1, n - 1, ar);
  }
    //prints top and right layers.
  //(x1,y1) to (x1, y2) - top layer & (x1,y2) to (x2, y2)
  private static void printTopRight(int x1, int y1, int x2, int y2, int[][] ar) {
    //print row values - top
    for (int y = y1; y <= y2; y++) {
      System.out.printf("%d ", ar[x1][y]);
    }
    //print column value - right
    for (int x = x1 + 1; x <= x2; x++) {
      System.out.printf("%d ", ar[x][y2]);
    }

    //are there any remaining layers
    if (x2 - x1 > 0) {
      //call printBottemLeft
      printBottomLeft(x1 + 1, y1, x2, y2 - 1, ar);
    }
  }

    //prints bottom and left layers in reverse order
  //(x2,y2) to (x2, y1) - bottom layer & (x2,y1) to (x1, y1)
  private static void printBottomLeft(int x1, int y1, int x2, int y2, int[][] ar) {
    //print row values in reverse order - bottom
    for (int y = y2; y >= y1; y--) {
      System.out.printf("%d ", ar[x2][y]);
    }

    //print column value in reverse order - left
    for (int x = x2-1; x >= x1; x--) {
      System.out.printf("%d ", ar[x][y1]);
    }

    //are there any remaining layers
    if (x2 - x1 > 0) {
      printTopRight(x1, y1 + 1, x2 - 1, y2, ar);
    }
  }
}
