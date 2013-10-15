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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that
 * string can generate. Give a count as well as print the strings. *
 *
 * For example: Input: "1123". You need to general all valid alphabet codes from
 * this string. *
 *
 * Output List:
 * <ol>
 * <li>aabc //a = 1, a = 1, b = 2, c = 3 </li>
 * <li>kbc // since k is 11, b = 2, c=3 </li>
 * <li>alc // a = 1, l = 12, c = 3 </li>
 * <li>aaw // a= 1, a =1, w= 23 </li>
 * <li>kw // k = 11, w = 23</li>
 * </ol>
 *
 * @author
 * @author Sada Kurapati <sadakurapati@gmail.com>
 *
 * do this using DFS & DP and Recursion.
 */
public class DecodeToString {

  public static void main(String args[]) {

    //String input = "1123";// good test case. Output: [aabc, kbc, alc, aaw, kw]
    String input = "110203";
    //String input = "2010";// test 2 - Output: [tj]
    //String input = "20010"; // bad test case. 
    try {
      decodeByRecurssion("", input, 0);
      List<String> result = decode(input);
      System.out.println(result);
    } catch (Exception ex) {
      Logger.getLogger(DecodeToString.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static void decodeByRecurssion(String left, String input,
          int position) throws Exception {
    int length = input.length();
    if (position >= length) {
      System.out.println(left);
    } else {
      //with 1 char
      String str = getDecodedCharacter(input.charAt(position) + "");
      if (null != str) {
        decodeByRecurssion(left + str, input, position + 1);
      }

      //with 2 char
      if (position + 1 < length) {
        str = getDecodedCharacter(input.charAt(position) + "" + input.charAt(position + 1));
        if (null != str) {
          decodeByRecurssion(left + str, input, position + 2);
        }
      }
    }
  }

  private static List<String> decode(String input) {
    List<String> withCurrentLetter = new ArrayList<String>();
    List<String> withNextLetter = new ArrayList<String>();
    //This is essential so that it enters to the loop first time.
    withCurrentLetter.add("");
    int length = input.length();
    for (int i = 0; i < length; i++) {
      //Iterate throug all strings to this position list and add ith letter
      List<String> tempWithNextLetter = new ArrayList<String>();
      List<String> tempWithCurrentLetter = new ArrayList<String>();
      for (String preStr : withCurrentLetter) {
        int oneNumber = Integer.valueOf(input.charAt(i) + "");
        if (oneNumber > 0) {
          char oneChar = input.charAt(i);
          oneChar += 48;
          tempWithCurrentLetter.add(preStr + oneChar);
        }

        if (i + 1 < length) {
          int twoNumbers = Integer.valueOf(input.charAt(i) + "" + input.charAt(i + 1));
          if (twoNumbers <= 0) {
            System.out.printf("The provided %s can't be converted because it contains "
                    + "%s characters "
                    + "at position %d and %d. \n",
                    input, input.charAt(i) + "" + input.charAt(i + 1), i, i + 1);
            return null;
          }
          if (twoNumbers >= 10 && twoNumbers <= 26) {
            char twoChar = (char) (twoNumbers + 96);
            tempWithNextLetter.add(preStr + twoChar);
          }
        }
      }
      /**
       * Add previously prepared string with next characters as those are now
       * string with current string.
       */
      tempWithCurrentLetter.addAll(withNextLetter);

      //Keep the strings with next letter for next iteration
      withNextLetter = tempWithNextLetter;
      //Keep the strings with current letter for next iteration
      withCurrentLetter = tempWithCurrentLetter;
    }
    return withCurrentLetter;
  }

  private static String getDecodedCharacter(String str) throws Exception {
    String result = null;
    switch (str.length()) {
      case 1:
        int oneNumber = Integer.valueOf(str.charAt(0) + "");
        if (oneNumber > 0) {
          char oneChar = str.charAt(0);
          oneChar += 48;
          result = String.valueOf(oneChar);
        }
        break;
      case 2:
        int twoNumbers = Integer.valueOf(str.charAt(0) + "" + str.charAt(1));
        if (twoNumbers == 0) {
          throw new Exception("Invalid input, it contains 00");
        }
        if (twoNumbers >= 10 && twoNumbers <= 26) {
          char twoChar = (char) (twoNumbers + 96);
          result = String.valueOf(twoChar);
        }
        break;
    }
    return result;
  }
}
