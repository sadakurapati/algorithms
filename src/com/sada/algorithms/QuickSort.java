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
public class QuickSort {

  private static int QSortSwaps = 0;

  public static void main(String args[]) {
    int input[];
    input = new int[]{1, 3, 9, 8, 2, 7, 5};
    System.out.print("Before sorting: ");
    print(input);
    quickSort(input, 0, input.length - 1);
    System.out.print("\nAfter sorting: ");
    print(input);
    System.out.println(QSortSwaps);
  }

  public static void quickSort(int[] a, int p, int r) {
    if (p < r) {
      int q = partition(a, p, r);
      quickSort(a, p, q - 1);
      quickSort(a, q + 1, r);
    }
  }

  public static int partition(int[] a, int p, int r) {
    int pivot = a[r];
    int i = p - 1;
    int temp = 0;
    for (int j = p; j < r; j++) {
      if (a[j] <= pivot) {
        i++;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        ++QSortSwaps;
      }
    }
    temp = a[i + 1];
    a[i + 1] = a[r];
    a[r] = temp;
    ++QSortSwaps;
    return (i + 1);
  }

  //to pring elemtns in array.
  private static void print(int[] nums) {
    for (int n : nums) {
      System.out.printf("%d ", n);
    }
    System.out.println("");
  }
}
