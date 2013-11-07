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
package com.sada.algorithms.misc;

import java.util.Arrays;

/*
 * You are given an array of 1's 2's and 3's. Sort this list so the 1's are first, 
 * the 2's come second, and the 3's come third. 
 * 
 * Ex: Input [1, 3, 3, 2, 1] 
 * Output [1, 1, 2, 3, 3] 
 * 
 * But there is a catch!! The algorithm must be one pass, which means no merge/quick sort. 
 * Also no extra list allocations are allowed, which means no bucket/radix/counting sorts. 
 * You are only permitted to swap elements
 *
 * @author @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class DutchNationalFlagProblem {

  public static void main(String args[]) {
    int intArray[] = {2, 1, 3, 2, 2, 3, 2, 1};
    System.out.printf("Before: %s \n", Arrays.toString(intArray));
    solve(intArray, 1, 3);
    System.out.printf("After: %s \n", Arrays.toString(intArray));
  }

  private static void solve(int nums[], int low, int high) {
    assert nums != null : "Array is empty";
    int length = nums.length;
    int lowIndex = 0;
    int highIndex = length - 1;
    int index = 0;
    while (index < highIndex) {
      if (nums[index] == low) {
        swap(nums, index, lowIndex);
        ++lowIndex;
        ++index;
      } else if (nums[index] >= high) {
        swap(nums, index, highIndex);
        --highIndex;
      } else {
        ++index;
      }
    }
  }

  private static void swap(int nums[], int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;

  }
}
