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
   * @param initialData The list of initial DataPoints.
   */
  public TestColony(Configuration config, List<T> initialData){
    super(config, initialData);
    this.randomizer = new TestRandom();
  }

  /**
   * @return The ants in this colony
   */
  public Ant[] getAnts() { return ants; }

  /**
   * @return The DistanceMetric used by this TestColony.
   */
  public DistanceMetric<T> getDistanceCalculator(){
    return this.distanceCalculator;
  }

  /**
   * @return The TestRandomizer used by this TestColony.
   */
  public TestRandom getRandomizer() {
    return (TestRandom) this.randomizer;
  }

}
