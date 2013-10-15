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
package com.sada.main;

import java.util.HashMap;

/**
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class MainClass {

  public static int c = 0;

  public static void main(String args[]) {
//    
//    int input[] = {3, 5, 4, 2, 7, 6, 5, 8, 1};
//    int dup = findDuplicate(input);
//    System.out.printf("first duplicate element is %d \n", dup);
//    System.out.println("Execution count: " + c);

    int ii = 1000000;
    System.out.println("i = " + ii);

    HashMap<String, IntWrapper> m = new HashMap<String, IntWrapper>();
    m.put("one", new IntWrapper());
    changeIntegerValue(m);
    IntWrapper i = m.get("one");
    i.value++;
    System.out.println("one = " + m.get("one").value);
    long l = 4999950000l;
    System.out.println(Integer.MAX_VALUE);
    System.out.println(Long.MAX_VALUE);
  }

  private static void changeIntegerValue(HashMap<String, IntWrapper> map) {
    IntWrapper i = map.get("one");
    i.value++;
    System.out.println("one = " + map.get("one").value);
    i.value++;
  }

  private static int findDuplicate(int nums[]) {
    int low = 0, high = nums.length;
    while (high > low) {
      ++c;
      int mid = (high + low) / 2;
      // go through the list and count # of items between 'low' and 'mid', inclusive.
      int count = getCount(nums, low, mid);
      if (count > mid - low + 1) {
        high = mid; // we know there's a duplicate between low and mid
      } else {
        low = mid + 1; // we know there's a duplicate between mid+1 and high
      }
    }
    return low;
  }

  private static int getCount(int nums[], int low, int mid) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      ++c;
      //compare the element in array with the index low & mid and if it is between them, 
      //increment the count. 
      if (low <= nums[i] && nums[i] <= mid) {
        count++;
      }
    }
    return count;
  }

  static class IntWrapper {

    int value = 0;
  }
}
