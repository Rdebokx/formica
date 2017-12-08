package com.rdebokx.formica.core;

import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.DistanceMetric;
import com.rdebokx.formica.metrics.EuclideanMetric;
import com.rdebokx.formica.metrics.ManhattanMetric;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Colony {

  /**
   * List of all the ants in this Colony.
   */
  protected Ant[] ants;

  /**
   * List of all buckets.
   * TODO: monitor that this list is not claiming too much memory.
   */
  private List<List<DataPoint>> buckets;

  protected final Configuration config;

  /**
   * The DistanceMetric used by the ants in this colony.
   */
  protected final DistanceMetric distanceCalculator;

  /**
   * The Random object used by the ants in this colony.
   */
  protected final Random randomizer;

  /**
   * Constructor, initializing this colony with a set of Ants and one bucket per provided DataPoint.
   * @param initialData The data that needs to be sorted by this Colony.
   */
  public Colony(Configuration config, List<DataPoint> initialData){
    this.config = config;
    this.distanceCalculator = createDistanceMetric(initialData);
    this.randomizer = new Random();
    initializeBuckets(initialData);
    initializeAnts();
  }

  /**
   * Helper function for creating a new DistanceMetric based off the configuration of this Colony.
   * @param initialData The list of initial DataPoints in this colony, passed on to metric constructor if needed.
   * @return The created DistanceMetric.
   */
  private DistanceMetric createDistanceMetric(List<DataPoint> initialData){
    DistanceMetric result = null;
    switch(config.getMetric()){
      case EuclideanMetric.METRIC_NAME:
        result = new EuclideanMetric(initialData);
        break;
      case ManhattanMetric.METRIC_NAME:
        result = new ManhattanMetric(initialData);
    }
    return result;
  }

  /**
   * Helper method for initializing the list of buckets with one bucket per DataPoint provided.
   * @param initialData List of DataPoints that needs sorting by this Colony.
   */
  private void initializeBuckets(List<DataPoint> initialData){
    buckets = initialData.stream().map(dataPoint -> Arrays.asList(dataPoint)).collect(Collectors.toList());
  }

  /**
   * Helper method for initializing the Ants in this colony. A number of ants will be initialized that matches Configuration.NR_OF_ANTS.
   */
  private void initializeAnts(){
    ants = new Ant[config.getNrOfAnts()];
    for(int i = 0; i < config.getNrOfAnts(); i++){
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
