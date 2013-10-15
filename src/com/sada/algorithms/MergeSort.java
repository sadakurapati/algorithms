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
 * I implement the Merge sort algorithm - Divide and Concur. Time complexity:
 * O(n log n)
 *
 * @author
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class MergeSort {

  public static void main(String args[]) {
    int input[];
    input = new int[]{6, 3, 2, 5, 7, 4, 8, 1};
    System.out.print("Before sorting: ");
    print(input);
    sort(input, 0, input.length - 1);
    System.out.print("\nAfter sorting: ");
    print(input);
    System.out.println();
  }

  //I sort the array using merge sort technique.
  public static void sort(int[] nums, int left, int right) {
    if (left < right) {
      //Split in half
      int mid = (left + right) / 2;
      //Sort recursively.
      sort(nums, left, mid);
      sort(nums, mid + 1, right);

      //Merge the two sorted sub arrays.
      merge(nums, left, mid, right);
    }
  }

  private static void merge(int[] nums, int left, int mid, int right) {
    // nums[left…mid] and A[mid+1…right] are the two sorted sub arrays to be merged.
    int size = right - left + 1;
    int temp[] = new int[size];
    //Copy the array into temp so that we can replace merged result into nums[left...right]
    //This is an extra space and time overhead in this merge sorting.
    for (int i = 0; i < size; i++) {
      temp[i] = nums[left + i];
    }
    //Changed positions in temp array.
    mid = mid - left;
    right = right - left;
    int k = left;
    int i = 0; // new left is (left - left) which is 0;
    int j = mid + 1;
    while (i <= mid && j <= right) {
      if (temp[i] <= temp[j]) {
        nums[k] = temp[i];
        i++;
      } else {
        nums[k] = temp[j];
        j++;
      }
      k++;
    }

    if (i > mid) {
      //copy remaining elements from right
      for (; j <= right; j++, k++) {
        nums[k] = temp[j];
      }
    } else {
      //copy remaining elements from left
      for (; i <= mid; i++, k++) {
        nums[k] = temp[i];
      }
    }
  }

  private static void print(int[] nums) {
    for (int n : nums) {
      System.out.printf("%d ", n);
    }
  }
}
