package com.rdebokx.formica.core;

import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.DistanceCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Colony {

  /**
   * List of all the ants in this Colony.
   */
  private Ant[] ants;

  /**
   * List of all buckets.
   * TODO: monitor that this list is not claiming too much memory.
   */
  private List<List<DataPoint>> buckets;

  /**
   * The DistanceCalculator used by the ants in this colony.
   */
  protected final DistanceCalculator distanceCalculator;

  /**
   * The Random object used by the ants in this colony.
   */
  protected final Random randomizer;

  /**
   * Constructor, initializing this colony with a set of Ants and one bucket per provided DataPoint.
   * @param initialData The data that needs to be sorted by this Colony.
   */
  public Colony(DataPoint ... initialData){
    this.distanceCalculator = new DistanceCalculator(initialData);
    this.randomizer = new Random();
    initializeBuckets(initialData);
    initializeAnts();
  }

  /**
   * Helper method for initializing the list of buckets with one bucket per DataPoint provided.
   * @param initialData List of DataPoints that needs sorting by this Colony.
   */
  private void initializeBuckets(DataPoint[] initialData){
    buckets = new ArrayList<>(initialData.length);
    for(DataPoint dataPoint : initialData){
      List<DataPoint> newBucket = new ArrayList<>();
      newBucket.add(dataPoint);
      buckets.add(newBucket);
    }
  }

  /**
   * Helper method for initializing the Ants in this colony. A number of ants will be initialized that matches Configuration.NR_OF_ANTS.
   */
  private void initializeAnts(){
    ants = new Ant[Configuration.NR_OF_ANTS];
    for(int i = 0; i < Configuration.NR_OF_ANTS; i++){
      ants[i] = new Ant(this);
    }
  }

  /**
   * Function for executing the next step in the sorting process.
   * This method will select a random ant from this Colony and move it to a randomly picked bucket where it may or may not pick up or drop a DataPoint,
   * depending on the contents of the new bucket.
   */
  public void nextStep() {
    Ant nextAnt = ants[randomizer.nextInt(ants.length)];
    List<DataPoint> nextBucket = buckets.get(randomizer.nextInt(buckets.size()));
    nextAnt.move(nextBucket);
  }

  /**
   * @return List of all buckets.
   */
  public List<List<DataPoint>> getBuckets() {
    return this.buckets;
  }

}
