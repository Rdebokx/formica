package com.rdebokx.formica.testhelpers;

import com.rdebokx.formica.core.Ant;
import com.rdebokx.formica.core.Colony;
import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.execution.Configuration;

import java.util.List;

public class TestColony extends Colony {

  public final static long RANDOMIZER_SEED = 1561570379050615836l;

  public TestColony(Configuration config, List<DataPoint> initialData){
    super(config, initialData);
    randomizer.setSeed(RANDOMIZER_SEED);
  }

  public Ant[] getAnts() { return ants; }
}
