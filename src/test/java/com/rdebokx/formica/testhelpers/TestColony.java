package com.rdebokx.formica.testhelpers;

import com.rdebokx.formica.core.Colony;

import java.util.Random;

public class TestColony extends Colony {

  public final static long RANDOMIZER_SEED = 1561570379050615836l;

  @Override
  protected void initializeRandomizer() {
    //TODO: use fixed seed
    this.randomizer = new Random(RANDOMIZER_SEED);
  }
}
