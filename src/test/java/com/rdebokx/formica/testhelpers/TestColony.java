package com.rdebokx.formica.testhelpers;

import com.rdebokx.formica.core.Ant;
import com.rdebokx.formica.core.Bucket;
import com.rdebokx.formica.core.Colony;
import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.distance.DistanceMetric;

import java.util.List;

public class TestColony<T extends DataPoint<?>> extends Colony<T> {



  /**
   * Constructor, constructing a TestColony which is essentially a non-stochastic Colony.
   * @param config The configuration to be used by this colony.
   * @param randomizer The TestRandom object to be used as randomizer. Allows you to define the stochastic behavior of the Colony.
   * @param initialData The list of initial DataPoints.
   */
  public TestColony(Configuration config, TestRandom randomizer, List<T> initialData){
    super(config, initialData);
    this.randomizer = randomizer;
  }

  /**
   * @return The ants in this colony
   */
  public Ant[] getAnts() { return ants; }

  public DistanceMetric<T> getDistanceCalculator(){
    return this.distanceCalculator;
  }

}
