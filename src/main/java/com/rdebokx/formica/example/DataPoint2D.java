package com.rdebokx.formica.example;

import com.rdebokx.formica.core.DataPoint;

/**
 * Example of a two-dimensional DataPoint, representing a value X and Y.
 */
public class DataPoint2D extends DataPoint {

  /**
   * Costructor, constructing an empty DataPoint2D with X and Y set to 0.0.
   */
  public DataPoint2D(){
    super(2);
  }

  /**
   * Set X.
   * @param x new X value.
   */
  public void setX(double x){
    values[0] = x;
  }

  /**
   * @return value of X.
   */
  public double getX() {
    return values[0];
  }

  /**
   * Set Y.
   * @param y new Y value.
   */
  public void setY(double y){
    values[1] = y;
  }

  /**
   * @return value of Y.
   */
  public double getY() {
    return values[1];
  }
}
