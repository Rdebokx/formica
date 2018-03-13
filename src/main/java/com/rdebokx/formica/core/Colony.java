package com.rdebokx.formica.core;

import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.distance.DistanceMetric;
import com.rdebokx.formica.metrics.distance.EuclideanMetric;
import com.rdebokx.formica.metrics.distance.ManhattanMetric;
import org.pmw.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Colony<T extends DataPoint<?>> {

  /**
   * List of all the ants in this Colony.
   */
  protected Ant[] ants;

  /**
   * List of all buckets.
   * TODO: monitor that this list is not claiming too much memory.
   */
  private List<Bucket<T>> buckets;

  protected final Configuration config;

  /**
   * The DistanceMetric used by the ants in this colony.
   */
  protected final DistanceMetric<T> distanceCalculator;

  /**
   * Logger for keeping track of the Colony stats.
   */
  protected final StatsLogger statsLogger;

  /**
   * The Random object used by the ants in this colony.
   */
  protected Random randomizer;

  /**
   * List with all Ants that are still carrying a payload, while the algorithm was ordered to stop.
   */
  protected List<Ant> terminatingAnts;

  /**
   * Constructor, initializing this colony with a set of Ants and one bucket per provided DataPoint.
   * @param config The Configuration object to be used by this Colony.
   * @param initialData The data that needs to be sorted by this Colony.
   */
  public Colony(Configuration config, List<T> initialData){
    this.config = config;
    this.distanceCalculator = createDistanceMetric(initialData);
    this.randomizer = new Random();
    this.statsLogger = new StatsLogger(config.getNrOfAnts());
    initializeBuckets(initialData);
    initializeAnts();
  }

  /**
   * Helper function for creating a new DistanceMetric based off the configuration of this Colony.
   * @param initialData The list of initial DataPoints in this colony, passed on to metric constructor if needed.
   * @return The created DistanceMetric.
   */
  private DistanceMetric createDistanceMetric(List<T> initialData){
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
  private void initializeBuckets(List<T> initialData){
    buckets = initialData.stream().map(dataPoint -> new Bucket<T>(this, dataPoint)).collect(Collectors.toList());
  }

  /**
   * Helper method for initializing the Ants in this colony. A number of ants will be initialized that matches Configuration.NR_OF_ANTS.
   */
  private void initializeAnts(){
    ants = new Ant[config.getNrOfAnts()];
    for(int i = 0; i < config.getNrOfAnts(); i++){
      ants[i] = new Ant(this, i);
    }
  }

  /**
   * @return The StatsLogger of this Colony.
   */
  public StatsLogger getStatsLogger() {
    return statsLogger;
  }

  /**
   * Function for executing the next step in the sorting process.
   * This method will select a random ant from this Colony and move it to a randomly picked bucket where it may or may not pick up or drop a DataPoint,
   * depending on the contents of the new bucket.
   */
  public boolean nextStep() {
    if(terminatingAnts == null){
      return doNextStep();
    } else if(terminatingAnts.size() > 0){
      windDown();
      return true;
    } else {
      Logger.info("Colony has terminated.");
      return false;
    }
  }

  /**
   * Method for making this colony perform the next step, iff the stopping condition in the config of this colony was not met.
   * Iff the stopping condition was met, the stop() function is called, follwed by another call to the nextStep() function which should be directed to the windDown() function.
   * Otherwise, a rand om Ant in this colony will be picked and moved across the buckets.
   */
  private boolean doNextStep() {
    if(config.getStopCondition().shouldStop(this)){
      Logger.info("Stopping Condition was met.");
      stop();
      return nextStep();
    } else {
      Ant nextAnt = ants[randomizer.nextInt(ants.length)];
      Bucket nextBucket = buckets.get(randomizer.nextInt(buckets.size()));
      nextAnt.move(nextBucket);
      return true;
    }
  }

  /**
   * This method performs a next step in winding down this colony, which is done by picking a random Ant of this colony that is still carrying a payload
   * and moving it to a random bucket to stochastically drop its payload. Note that this is stochastic, meaning that the Ant might not drop its payload, eg.
   * when the contents of the bucket are not similar enough to this ant.
   * Iff the randomly picked ant actually dropped its payload, it is being removed from the terminatingAnts list and ignored in future calls to this function.
   */
  private void windDown(){
    int antIndex = randomizer.nextInt(terminatingAnts.size());
    Ant antToTerminate = terminatingAnts.get(antIndex);
    Bucket nextBucket = buckets.get(randomizer.nextInt(buckets.size()));
    antToTerminate.drop(nextBucket);

    if(antToTerminate.getPayload() == null){
      terminatingAnts.remove(antIndex);
    }
  }

  /**
   * This function will return the buckets in this colony. To safeguard integrity of the Colony, this function will
   * construct a list of copies instead of returning the actual list of buckets used by this Colony.
   * @return List of copies of all buckets.
   */
  public List<Bucket> getBucketsCopy() {
    return this.buckets.stream().map(bucket -> bucket.copy()).collect(Collectors.toList());
  }

  /**
   * This method will set this colony in shut down mode. This means that every consecutive step will only be focussed on
   * moving Ants that are still carrying a payload across the buckets until no more Ants are carrying a payload.
   * As part of this function, a list of Ants that are still carrying payloads is constructed which will be used is the consecutive steps.
   */
  public void stop(){
    Logger.info("Colony was requested to stop.");
    terminatingAnts = new ArrayList<>();
    for(Ant ant : ants){
      if(ant.getPayload() != null){
        terminatingAnts.add(ant);
      }
    }
  }

  /**
   * @return Whether this colony is in the process of stopping, meaning that consecutive steps will be focussed on reducing the number Ants that are carrying a payload.
   */
  public boolean isStopping() {
    return terminatingAnts != null && !terminatingAnts.isEmpty();
  }

  /**
   * @return Whether this colony has stopped, meaning that the stop() function has been called an no more Ants are carrying a payload.
   */
  public boolean hasStopped() {
    return terminatingAnts != null && terminatingAnts.isEmpty();
  }

}
