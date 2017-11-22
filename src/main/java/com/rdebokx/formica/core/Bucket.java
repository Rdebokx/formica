package com.rdebokx.formica.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bucket {

  private final List<DataPoint> contents;

  public Bucket(){
    contents = new ArrayList<>();
  }

  public boolean isEmpty() {
    return contents.isEmpty();
  }

  public void drop(DataPoint dataPoint){
    contents.add(dataPoint);
  }

  public DataPoint pick() {
    int resultIndex = -1;

    Random picker = new Random();
    while(resultIndex == -1) {
      int candidateIndex = picker.nextInt(contents.size());
      DataPoint candidate = contents.get(candidateIndex);
      double chance = 1; //TODO: apply some probabilistic logic here to calculate the chance of picking this element
      if(picker.nextDouble() < chance){
        resultIndex = candidateIndex;
      }
    }
    return contents.remove(resultIndex);
  }
}
