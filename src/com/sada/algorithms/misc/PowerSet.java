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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class PowerSet {

  public static void main(String args[]) {
    String input[] = new String[]{"1", "2", "2"};
    Collection<Set<String>> colSet = calculateSubsets(input);

    //printing
    for (Set<String> s : colSet) {
      System.out.println(toString(s));
    }

  }

  private static String toString(Set<String> s) {
    StringBuffer sb = new StringBuffer("{");
    for (String v : s) {
      sb.append(v);
      sb.append(" ");
    }
    sb.append("}");
    return sb.toString();
  }

  private static Collection<Set<String>> calculateSubsets(String[] values) {
    int num = values.length;
    int max = 1 << num;
    Collection<Set<String>> subsets = new ArrayList<Set<String>>(max);

    for (int subset = 1; subset < max; ++subset) {
      subsets.add(calculateSubset(subset, values, num));
    }
    return subsets;
  }

  private static Set<String> calculateSubset(int subset, String x[], int num) {
    Set<String> set = new HashSet<String>();

    for (int j = 0; j < num; ++j) {
      if (((subset >> j) & 1) == 1) {
        set.add(x[j]);
      }
    }
    return set;
  }
}
