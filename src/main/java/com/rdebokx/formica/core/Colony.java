package com.rdebokx.formica.core;

import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.DistanceCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Colony {

  private Ant[] ants;

  //Note: monitor that this property doesn't hog too much memory
  private List<List<DataPoint>> buckets;

  protected DistanceCalculator distanceCalculator;

  protected Random randomizer;

  public Colony(DataPoint ... initialData){
    distanceCalculator = new DistanceCalculator(initialData);
    initializeBuckets(initialData);
    initializeAnts();
    this.randomizer = new Random();
  }

  private void initializeBuckets(DataPoint[] initialData){
    buckets = new ArrayList<>(initialData.length);
    for(DataPoint dataPoint : initialData){
      List<DataPoint> newBucket = new ArrayList<>();
      newBucket.add(dataPoint);
      buckets.add(newBucket);
    }
  }

  private void initializeAnts(){
    ants = new Ant[Configuration.NR_OF_ANTS];
    for(int i = 0; i < Configuration.NR_OF_ANTS; i++){
      ants[i] = new Ant(this);
    }
  }

  public void nextStep() {
    Ant nextAnt = ants[randomizer.nextInt(ants.length)];
    List<DataPoint> nextBucket = buckets.get(randomizer.nextInt(buckets.size()));
    nextAnt.move(nextBucket);
  }

  public List<List<DataPoint>> getBuckets() {
    return this.buckets;
  }

}
