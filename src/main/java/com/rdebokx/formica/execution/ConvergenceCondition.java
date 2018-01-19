package com.rdebokx.formica.execution;

import com.rdebokx.formica.core.Colony;
import com.rdebokx.formica.metrics.distance.DistanceMetric;
import com.rdebokx.formica.metrics.quality.QualityMetric;

import java.util.Arrays;

public class ConvergenceCondition implements StopCondition {

  private final double minDeviation;

  private final int maxIterations;

  private final QualityMetric qualityMetric;

  private int iteration = 0;

  private final double[] fitnessValues;

  /**
   * Constructor, constructing a ConvergenceCondition of which the shouldStop function will return false iff the colony has converged.
   * A colony will be considered as converged if the maximum and minimum fitness value found in the last {maxIterations} iterations
   * differ no more than {minDeviation}, according to the provided QualityMetric.
   * @param minDeviation The minimum deviation required in the last {maxIterations} iterations, inclusive.
   * @param maxIterations The maximum number of iterations that the Colony's found solution quality is allowed to differ not more than {minDeviation}, inclusive.
   * @param qualityMetric The QualityMetric used to calculate the quality of the solution found by the Colony.
   */
  public ConvergenceCondition(double minDeviation, int maxIterations, QualityMetric qualityMetric){
    this.minDeviation = minDeviation;
    this.maxIterations = maxIterations;
    fitnessValues = new double[maxIterations];
    this.qualityMetric = qualityMetric;
  }

  @Override
  public boolean shouldStop(Colony colony){
    boolean result = true;
    if(maxIterations > 1) {
      double currentFitness = qualityMetric.getScore(colony.getBucketsCopy());
      fitnessValues[iteration++ % maxIterations] = currentFitness;

      if(iteration < maxIterations){
        result = false;
      } else {
        //Get min and max
        result = checkConvergence();
      }
    }
    return result;
  }

  /**
   * Helper function for checking whether the colony has converged, based on the stored fitness values.
   * @return true iff the minimum and maximum of the stored fitness values differ less than {minDeviation}
   */
  private boolean checkConvergence(){
    double min = fitnessValues[0];
    double max = fitnessValues[0];

    for (int i = 0; i < Math.min(iteration, maxIterations); i++) {
      min = Math.min(min, fitnessValues[i]);
      max = Math.max(max, fitnessValues[i]);
    }
    return (max - min) < minDeviation;
  }
}
