package com.rdebokx.formica.testhelpers;

import com.rdebokx.formica.core.Colony;
import com.rdebokx.formica.core.DataPoint;

import java.util.Random;

public class TestColony extends Colony {

  public final static long RANDOMIZER_SEED = 1561570379050615836l;

  public TestColony(DataPoint ... initialData){
    super(initialData);
    randomizer.setSeed(RANDOMIZER_SEED);
  }
}
