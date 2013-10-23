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
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class CountingSort {

  public static void main(String args[]) {
    int input[] = new int[]{6, 0, 2, 1, 2, 3, 5, 6, 4, 8, 1, 2, 3};
    System.out.printf("Before sorting: %s \n", Arrays.toString(input));
    int result[] = sort(input);
    System.out.printf("After sorting: %s \n", Arrays.toString(result));
  }

  private static int[] sort(int nums[]) {
    //to hold the result.
    int result[] = new int[nums.length];
    //to hold the counts in sorted of values
    TreeMap<Integer, Integer> countsMap = new TreeMap<Integer, Integer>();
    //prepare the count of each elements map.
    for (int i = 0; i < nums.length; i++) {
      Integer elementCount = countsMap.get(nums[i]);
      elementCount = (null == elementCount) ? 1 : ++elementCount;
      countsMap.put(nums[i], elementCount);
    }
    //prepare the positions
    int sum = 0;
    for (Entry<Integer, Integer> entry : countsMap.entrySet()) {
      sum += entry.getValue();
      entry.setValue(sum);
    }

    for (int i = nums.length - 1; i >= 0; i--) {
      //get the entry set for nums[i]
      Integer position = countsMap.get(nums[i]);
      result[--position] = nums[i];
      countsMap.put(nums[i], position);
    }
    return result;
  }
}
