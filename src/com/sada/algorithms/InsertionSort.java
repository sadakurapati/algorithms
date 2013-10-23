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

import java.util.Arrays;

/**
 * I implement the Insertion sorting algorithm. Time complexity: O(n^2)
 *
 * @author
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class InsertionSort {

  public static void main(String args[]) {
    int input[];
    input = new int[]{5, 3, 8, 44, 23, 64, 225, 24, 63};
    System.out.printf("Before sorting: %s \n", Arrays.toString(input));
    sort(input);
    System.out.printf("After sorting: %s \n", Arrays.toString(input));
  }

  //I sort the given numbers in ascending order using Insertion sort algorithm.
  public static void sort(int nums[]) {
    //Start with the 2nd position as the partition with 1 elements is already sorted.
    for (int i = 1; i < nums.length; i++) {
      //Take the card in hand
      int key = nums[i];
      //Move the elements to right till we find the correct position for the key
      //in already sorted array (loop invariant) nums[1.. j-1]
      int j = i - 1;
      //We can also right this in single line for (; j >= 0 && nums[j] > key; nums[j + 1] = nums[j],j--);
      for (; j >= 0 && nums[j] > key; j--) {
        nums[j + 1] = nums[j];
      }
      /**
       * Insert the key[card in hand] at the position. You might be wondering
       * why we are inserting at nums[j+1] rather than nums[j]. Think what will
       * be the j value when we wanted to insert smallest element. it will be -1
       * and hope now you got me why we are inserting it at nums[j+1].
       */
      nums[j + 1] = key;
    }
  }
}
