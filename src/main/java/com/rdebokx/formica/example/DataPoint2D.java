package com.rdebokx.formica.example;

import com.rdebokx.formica.core.DataPoint;

/**
 * Example of a two-dimensional DataPoint, representing a value X and Y.
 */
public class DataPoint2D extends DataPoint<Double> {

  /**
   * Constructor, constructing an empty DataPoint2D with X and Y set to 0.0.
   */
  public DataPoint2D(){
    super(null, null);
  }

  /**
   * Constructor, constructing a DataPoint2D object with the given X and Y value.
   * @param x The value of X.
   * @param y The value of Y.
   */
  public DataPoint2D(double x, double y){ super(new Double(x), new Double(y)); }

  /**
   * Set X.
   * @param x new X value.
   */
  public void setX(Double x){
    values[0] = x;
  }

  /**
   * @return value of X.
   */
  public Double getX() {
    return values[0];
  }

  /**
   * Set Y.
   * @param y new Y value.
   */
  public void setY(Double y){
    values[1] = y;
  }

  /**
   * @return value of Y.
   */
  public Double getY() {
    return values[1];
  }
}
