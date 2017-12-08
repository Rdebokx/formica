package com.rdebokx.formica.testhelpers;

import java.util.Random;

/**
 * Mocked Random class that can be used in tests instead of java.util.Random by TestColonies.
 */
public class TestRandom extends Random {

  /**
   * The integer values to be returned by nextInt()
   */
  private int[] intSeq;

  /**
   * Pointer to the next int to be returned from the intSeq by nextInt()
   */
  private int nextIntIndex;

  /**
   * The double values to be returned by nextDouble()
   */
  private double[] doubleSeq;

  /**
   * pointer to the next double to be returned from the doubleSeq by nextDouble()
   */
  private int nextDoubleIndex;

  /**
   * Set the sequence of integers that should be returned by the nextInt() function.
   * @param intSeq The new list of integers to be returned by nextInt()
   */
  public void setIntSeq(int ... intSeq){
    this.intSeq = intSeq;
    nextIntIndex = 0;
  }

  /**
   * The sequence of doubles that should be returned by the nextDouble function.
   * @param doubleSeq The new list of doubles to be returned by nextDouble
   */
  public void setDoubleSeq(double ... doubleSeq){
    this.doubleSeq = doubleSeq;
    nextDoubleIndex = 0;
  }

  @Override
  public int nextInt() {
    return intSeq[nextIntIndex++];
  }

  @Override
  public int nextInt(int bound){
    return nextInt();
  }

  @Override
  public double nextDouble() {
    return doubleSeq[nextDoubleIndex++];
  }
}
