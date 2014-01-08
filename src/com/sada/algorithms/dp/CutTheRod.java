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
 *
 * @author Sada Kurapati <sadakurapati@gmail.com>
 */
public class CutTheRod {

  public static void main(String[] args) {
      int[] price = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
      int n = 10;
      int[] priceSum = new int[n + 1];
      System.out.printf("byTopDownDP = %d \n", byTopDownDP(price, n, priceSum));
      System.out.printf("byBottonUpDP = %d", byBottonUpDP(price, n));
  }

    private static int byBottonUpDP(int[] price, int n){
        int[] priceSum = new int[n+1];
        for(int i=1; i<=n; i++){
            int totalPrice = Integer.MIN_VALUE;
            for(int j = 1; j <= i; j++){
                totalPrice = Math.max(totalPrice, price[j-1] + priceSum[i-j]);
            }
            priceSum[i] = totalPrice;
        }
        return priceSum[n];
    }

  private static int byTopDownDP(int[] price, int n, int[] priceSum) {

    //If already calculated the price, then return.
    if (priceSum[n] > 0) {
      return priceSum[n];
    }
      //base case
      if(n == 0){
          return 0;
      }else{
          int totalPrice = Integer.MIN_VALUE;
          for(int i=1; i<= n; i++){
              totalPrice = Math.max(totalPrice, price[i - 1] + byTopDownDP(price, n - i, priceSum));
          }
          priceSum[n] = totalPrice;
          return totalPrice;
      }
  }
}
