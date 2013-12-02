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
package com.sada.algorithms.dp;

/**
 * I find maximum contiguous sub array in given array of integers.
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class MaxSubArray {

  public static void main(String args[]) {
    //int[] intArr={3, -1, -1, -1, -1, -1, 2, 0, 0, 0 };
    //int[] intArr={-1, 3, -5, 4, 6, -1, 2, -7, 13, -3};
    //int[] intArr={-6,-2,-3,-4,-1,-5,-5};
    //int[] intArr={-1, 3};
    //int[] intArr={-8, -2, -5, -1, -2, -9};
    /**
     * //int[] intArr = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; //Answer 4, -1, 2, 1,
     * with sum 6 MaxSubArray maxSubArray = new MaxSubArray(); SubArray sbArray
     * = maxSubArray.byKadane(intArr); sbArray.print(); sbArray =
     * maxSubArray.byDivideAndConquer(intArr, 0, intArr.length - 1);
     * sbArray.print();
     *
     */
    //int[] intArr = {-3, -2, 4, 1, -5};
    //int maxSum = maxWithoutAdjcentElementsDP(intArr);
    //System.out.printf("Max sum is %d.\n", maxSum);
    int[] intArr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    MaxSubArray maxSubArray = new MaxSubArray(); 
    SubArray sbArray = maxSubArray.byKadane(intArr); sbArray.print(); 
  }

  /**
   * Finds the maximum sub array using Kadane's implementation. Time complexity:
   * O(n) - Linear.
   *
   * @param nums
   */
  public SubArray byKadane(int nums[]) {

    int finalStart = 0, finalEnd = 0, tempStart = 0;
    int finalMax = nums[0], tempMax = nums[0];

    for (int i = 1; i < nums.length; i++) {

      if (tempMax < 0) {
        //Reset the max sub array to start from here.
        tempMax = nums[i];
        tempStart = i;
      } else {
        tempMax += nums[i];
      }

      //change it to maxSoFar if the maxEndingHere is maximum.
      if (tempMax > finalMax) {
        finalMax = tempMax;
        finalStart = tempStart;
        finalEnd = i;
      }

    }
    return new SubArray(finalStart, finalEnd, finalMax);
  }

  /**
   * suppose you are given an array of int. for example A[ ] = {1, - 1, 3, 8 ,4
   * } maximize the sum of subset such that if you include the number in sum,
   * you may not use adjutant numbers to count in sum. so in the example above
   * the max sum is 1+ 8 = 9; in A[ ] = { 1, 5, 3, 9, 4 } the max sum is 5 + 9 =
   * 14. and in A[ ] = { 1,2,3,4, 5} max sum i s 1 + 3 + 5 = 9
   *
   * @param nums
   * @return
   */
  public static int maxWithoutAdjcentElementsDP(int nums[]) {
    int sumAtminus2 = 0;
    int sumAtminus1 = nums[0];
    int sumToHere = Integer.MIN_VALUE;
    for (int i = 1; i < nums.length; i++) {
      sumToHere = (sumAtminus1 > sumAtminus2 + nums[i]) ? sumAtminus1 : sumAtminus2 + nums[i];

      sumAtminus2 = sumAtminus1;
      sumAtminus1 = sumToHere;
    }
    return sumToHere;
  }

  /**
   * Finds the max sub array using Divide and conquer technique. Time
   * complexity: O(n log n)
   *
   * @param nums
   * @param left
   * @param right
   * @return
   */
  public SubArray byDivideAndConquer(int nums[], int left, int right) {

    if (left == right) {
      return new SubArray(left, right, nums[left]);
    } else {
      int mid = (left + right) / 2;
      SubArray leftMax = byDivideAndConquer(nums, left, mid);
      SubArray rightMax = byDivideAndConquer(nums, mid + 1, right);

      //Find max array crossing mid
      int leftCrossMaxSum = nums[mid];
      int sum = 0;
      int crossLeft = mid;
      int crossRight = mid + 1;
      for (int i = mid; i >= left; i--) {
        sum += nums[i];
        if (sum > leftCrossMaxSum) {
          leftCrossMaxSum = sum;
          crossLeft = i;
        }
      }
      int rightCrossMaxSum = nums[mid + 1];
      sum = 0;
      for (int j = mid + 1; j <= right; j++) {
        sum += nums[j];
        if (sum > rightCrossMaxSum) {
          rightCrossMaxSum = sum;
          crossRight = j;
        }
      }
      SubArray crossMax = new SubArray(crossLeft, crossRight, leftCrossMaxSum + rightCrossMaxSum);

      if (leftMax.getMax() > rightMax.getMax() && leftMax.getMax() > crossMax.getMax()) {
        return leftMax;
      } else if (rightMax.getMax() > leftMax.getMax() && rightMax.getMax() > crossMax.getMax()) {
        return rightMax;
      } else {
        return crossMax;
      }

    }
  }

  class SubArray {

    public int _left = 0;
    public int _right = 0;
    public int _max = Integer.MIN_VALUE;

    public SubArray(int left, int right, int max) {
      _left = left;
      _right = right;
      _max = max;
    }

    public int getMax() {
      return _max;
    }

    public void print() {
      System.out.printf("Array from position %d to %d holds the maximum value %d.\n",
              _left, _right, _max);
    }
  }
}
