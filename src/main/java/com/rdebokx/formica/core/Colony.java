package com.rdebokx.formica.core;

import com.rdebokx.formica.execution.Configuration;

public class Colony {

  private Ant[] ants;

  //Note: monitor that this property doesn't hog too much memory
  private Bucket[] buckets;

  public Colony(DataPoint ... initialData){
    initializeBuckets(initialData);
    initializeAnts();
  }

  private void initializeBuckets(DataPoint[] initialData){
    buckets = new Bucket[initialData.length];
    for(int i = 0; i < initialData.length; i++){
      buckets[i] = new Bucket(initialData[i]);
    }
  }

  private void initializeAnts(){
    ants = new Ant[Configuration.NR_OF_ANTS];
    for(int i = 0; i < Configuration.NR_OF_ANTS; i++){
      ants[i] = new Ant();
    }
  }

  public void start() {
    //TODO: start sorting.
  }

}
