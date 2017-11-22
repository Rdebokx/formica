package com.rdebokx.formica.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public abstract class DataPoint {

  protected final double[] values;

  public DataPoint(int ... indices){
    HashSet<Integer> indexSet = new HashSet<>(indices.length);
    for(int index : indices){
      if(indexSet.contains(index)){
        throw new RuntimeException("Cannot construct DataPoint with duplicate indices.");
      }
      indexSet.add(index);
    }
    values = new double[indices.length];
  }

  public DataPoint(int numProperties) {
    this(new double[numProperties]);
  }

  public DataPoint(double ... values){
    this.values = values;
  }

  public double[] getValues() {
    return values;
  }

}

