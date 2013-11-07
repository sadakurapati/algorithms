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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * I find sub arrays where the sum of its elements is zero.
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class ZeroSumSubArray {

  public static void main(String args[]) {
    ZeroSumSubArray obj = new ZeroSumSubArray();
    int[] input = {4, 6, 3, -9, -5, 1, 3, 0, 2};
    //int[] input = {0, 1, -1, 0};
    //worst case - n=10
    //int[] input = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    List<SubArrayIndexes> sbArrayIndexes = obj.findZeroSumSubArrays(input);
    System.out.printf("Number of sub arrays which have sum as zero are %d. \n", sbArrayIndexes.size());
    obj.printSubArrays(input, sbArrayIndexes);
  }

  public List<SubArrayIndexes> findZeroSumSubArrays(int nums[]) {
    //Store the sum of elements. It stored sum as key and the position list as value.
    HashMap<Integer, List<Integer>> sumMap = new HashMap<Integer, List<Integer>>();
    //List to store the zero sub array indexes - our result ;)
    List<SubArrayIndexes> sbArraysList = new ArrayList<SubArrayIndexes>(1);
    int sum = 0;
    //Initial case where first element is zero
    if (nums[0] == 0) {
      List<Integer> currentIndex = new ArrayList<Integer>(1);
      currentIndex.add(-1);
      sumMap.put(0, currentIndex);
    }

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      List<Integer> preIndexes = sumMap.get(sum);
      if (null != preIndexes) {
        addSubArray(sbArraysList, preIndexes, i);
        preIndexes.add(i);
      } else {
        List<Integer> currentIndex = new ArrayList<Integer>(1);
        currentIndex.add(i);
        sumMap.put(sum, currentIndex);
      }
    }

    return sbArraysList;
  }

  /**
   * This prepares the zero sub arrays with all permutations. Assume that if
   * 1-5, 6-8 are zero sub arrays and and we found new one now at 9-11 then we
   * will have 1-11, 6-11 and 9-11 are new sub arrays.
   */
  private void addSubArray(List<SubArrayIndexes> sbArraysList, List<Integer> preSums,
          int currentIndex) {
    for (Integer left : preSums) {
      if (left < currentIndex) {
        SubArrayIndexes sbArray = new SubArrayIndexes(left + 1, currentIndex);
        sbArraysList.add(sbArray);
      }
    }
  }

  /**
   * I am here just to print the result in fancy way. So don't count me for time
   * complexity calculations. If you count me, then I will be sad/angry for sure
   * :D
   */
  private void printSubArrays(int inputArray[], List<SubArrayIndexes> sbArraysList) {
    if (null != sbArraysList) {
      for (SubArrayIndexes idx : sbArraysList) {
        System.out.printf("The sum of sub array from [%d-%d] is zero. Array = {", idx.left, idx.right);
        //printing elements of the sub array
        for (int i = idx.left; i < idx.right; i++) {
          System.out.printf("%d ", inputArray[i]);
        }
        //this is just for avoiding the space after last element 
        //which will be printed if we use <= in above for loop. 
        System.out.printf("%d", inputArray[idx.right]);
        System.out.println("}");
      }
    }
  }

  //I know you got me, yes I hold the starting and ending position of a (sub) array.
  class SubArrayIndexes {

    int left = -1;
    int right = -1;

    public SubArrayIndexes(int l, int r) {
      left = l;
      right = r;
    }
  }
}
