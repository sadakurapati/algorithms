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

/**
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class KPalindromeString {

  static int editDistance = 0;

  public static void main(String[] args) {
    //String str = "ghfeddcbabcdef";
    String str = "abcdsfsfsba";
    System.out.println(isPalindrome(str, 3, 0, str.length() - 1));
    System.out.println(distance(str.substring(0, str.length() / 2),
            str.substring((str.length() / 2) + 1, str.length())));
  }

  private static int isPalindrome(String str, int k, int i, int j) {
    if (editDistance > k) {
      return editDistance;
    }
    if (i >= j) {
      return 0;
    }
    if (i == j + 1) {
      return (str.charAt(i) == str.charAt(j)) ? 0 : 1;
    }

    int iMov = isPalindrome(str, k, i + 1, j) + 1;
    int ijMov = isPalindrome(str, k, i + 1, j - 1) + 1;
    int jMov = isPalindrome(str, k, i, j - 1) + 1;

    int deletions = Math.min(Math.min(iMov, ijMov), jMov);
    editDistance += deletions;
    return deletions;
  }
//Levenshtein Edit Distance Algorithm

  public static int distance(String s1, String s2) {
    int edits[][] = new int[s1.length() + 1][s2.length() + 1];
    for (int i = 0; i <= s1.length(); i++) {
      edits[i][0] = i;
    }
    for (int j = 1; j <= s2.length(); j++) {
      edits[0][j] = j;
    }
    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {
        int u = (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1);
        edits[i][j] = Math.min(
                edits[i - 1][j] + 1,
                Math.min(
                edits[i][j - 1] + 1,
                edits[i - 1][j - 1] + u));
      }
    }
    return edits[s1.length()][s2.length()];
  }
}
