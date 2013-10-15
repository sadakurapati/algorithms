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
 * @author @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class HeapSort {

  public static void main(String args[]) {
    int input[];
    input = new int[]{6, 3, 2, 5, 7, 4, 8, 1};
    System.out.print("Before sorting: ");
    print(input);
    sort(input);
    System.out.print("\nAfter sorting: ");
    print(input);
    System.out.println();
  }

  public static void sort(int nums[]) {
    buildMaxHeap(nums);
    int heapSize = nums.length - 1;
    while (heapSize > 0) {
      swap(nums, heapSize, 0);
      --heapSize;
      maxHeapify(nums, 0, heapSize);
    }
  }

  private static void buildMaxHeap(int nums[]) {
    int parentAt = parentAt(nums.length);
    for (int i = parentAt; i >= 0; i--) {
      maxHeapify(nums, i, nums.length - 1);
    }
  }

  private static void maxHeapify(int nums[], int i, int heapSize) {
    int left = leftAt(i);
    int right = rightAt(i);
    int maxElementAt = i;
    if (left <= heapSize && nums[left] > nums[maxElementAt]) {
      maxElementAt = left;
    }
    if (right <= heapSize && nums[right] > nums[maxElementAt]) {
      maxElementAt = right;
    }
    if (maxElementAt != i) {
      swap(nums, i, maxElementAt);
      maxHeapify(nums, maxElementAt, heapSize);
    }
  }

  private static int leftAt(int i) {
    return 2 * i;
  }

  private static int rightAt(int i) {
    return (2 * i) + 1;
  }

  private static int parentAt(int i) {
    return i / 2;
  }

  private static void swap(int nums[], int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private static void print(int[] nums) {
    for (int n : nums) {
      System.out.printf("%d ", n);
    }
  }
}