package com.rdebokx.formica.execution;

import org.junit.Assert;
import org.junit.Test;

public class TimeConditionTest {

  @Test
  public void testShouldStop(){
    TimeCondition condition = new TimeCondition(100);
    Assert.assertFalse(condition.shouldStop(null));

    //Sleep for 100ms to make sure max execution time expired
    try {
      Thread.sleep(100);
    } catch (InterruptedException e){
      e.printStackTrace();
      Assert.fail(e.getMessage());
    }

    Assert.assertTrue(condition.shouldStop(null));
  }
}
