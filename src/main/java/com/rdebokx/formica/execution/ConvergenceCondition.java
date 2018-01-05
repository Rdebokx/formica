package com.rdebokx.formica.execution;

import com.rdebokx.formica.core.Colony;
import com.rdebokx.formica.metrics.distance.DistanceMetric;
import com.rdebokx.formica.metrics.quality.QualityMetric;

public class ConvergenceCondition implements StopCondition {

  private final double minDeviation;

  private final int maxIterations;

  private final QualityMetric qualityMetric;

  private final DistanceMetric<?> distanceMetric;

  private int iteration = -1;

  private final double[] fitnessValues;

  //TODO: javadoc
  //minDeviation: inclusive
  //maxIterations: inclusive
  public ConvergenceCondition(double minDeviation, int maxIterations, QualityMetric qualityMetric, DistanceMetric<?> distanceMetric){
    this.minDeviation = minDeviation;
    this.maxIterations = maxIterations;
    fitnessValues = new double[maxIterations];
    this.qualityMetric = qualityMetric;
    this.distanceMetric = distanceMetric;
  }

  @Override
  public boolean shouldStop(Colony colony){
    boolean enoughDeviation = false;
    double currentFitness = qualityMetric.getScore(colony.getBucketsCopy());
    for(int i = 0; i < Math.min(iteration, maxIterations); i++){
      enoughDeviation |= Math.abs(fitnessValues[i] - currentFitness) >= minDeviation;
    }

    if(enoughDeviation){
      iteration++;
      fitnessValues[iteration % maxIterations] = currentFitness;
    }

    return !enoughDeviation;
  }
}
