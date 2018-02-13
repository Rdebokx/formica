package com.rdebokx.formica.testhelpers;

import com.rdebokx.formica.core.Colony;
import com.rdebokx.formica.execution.StopCondition;

/**
 * Dummy StopCondition, of which the shouldStop function will always return false,
 * allowing tests not to be influenced by StopConditions if they don't need to.
 */
public class DummyCondition implements StopCondition {

  @Override
  public boolean shouldStop(Colony colony) { return false; }
}
