package com.rdebokx.formica.example;

import com.rdebokx.formica.core.DataPoint;

public class DataPoint2D extends DataPoint {

  public DataPoint2D(){
    super(2);
  }

  public void setX(double x){
    values[0] = x;
  }

  public double getX() {
    return values[0];
  }

  public void setY(double y){
    values[1] = y;
  }

  public double getY() {
    return values[1];
  }
}
