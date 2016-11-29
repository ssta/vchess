package com.ssta;

/**
 * Created by ssta on 11/28/16.
 */
public class Foo {


  public static double s(int n, int k, double p) {
    double result;
    double[] x = new double[10000000];

    x[0] = Math.pow(p, k);
    int i = 1;

    while (n > k) {
      double sum = 0;
      int l;
      if (i - k < 0) l = 0;
      else l = i - k;
      for (int j = i; j > l; j--) {
        sum += (1 - p) * Math.pow(p, 0 + i - j) * x[j - 1];
      }
      x[i] = Math.pow(p, k) + sum;

      i++;
      n--;
    }
    result = x[i - 1];
    return (result);
  }
}
