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

/**
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class StringUtils {

  public static void main(String args[]) {
    String strInput = "SadA Kurapati";
    int index = containsDuplicateChar(strInput);
    if (index >= 0) {
      System.out.printf("In the input string '%s', the duplicate character '%c' found at %d \n",
              strInput, strInput.charAt(index), index + 1);
    } else {
      System.out.printf("The input string %s does not contain any duplicate characters.\n",
              strInput);
    }
  }

  public static int containsDuplicateChar(String str) {
    boolean ch[] = new boolean[256];
    if (null != str) {
      for (int i = 0; i < str.length(); i++) {
        if (ch[str.charAt(i)]) {
          return i;
        } else {
          ch[str.charAt(i)] = true;
        }
      }
    }
    return -1;
  }
}
