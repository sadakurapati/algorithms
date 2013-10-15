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
package com.sada.algorithms;

/**
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class SelectionSort {

  public static void main(String args[]) {
    int input[];
    input = new int[]{55, 32, 8, 44, 23, 64, 25, 24, 63};
    System.out.print("Before sorting: ");
    print(input);
    sort(input);
    System.out.print("After sorting: ");
    print(input);
  }

  //Selection sorting method.
  private static void sort(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
      int smallElementAt = i;
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[j] < nums[smallElementAt]) {
          smallElementAt = j;
        }
      }
      if (smallElementAt != i) {
        int temp = nums[i];
        nums[i] = nums[smallElementAt];
        nums[smallElementAt] = temp;
      }
    }
  }

  //to pring elemtns in array.
  private static void print(int[] nums) {
    for (int n : nums) {
      System.out.printf("%d ", n);
    }
    System.out.println("");
  }
}
