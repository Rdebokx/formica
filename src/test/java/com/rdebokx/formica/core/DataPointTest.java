package com.rdebokx.formica.core;

import com.rdebokx.formica.example.DataPoint2D;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.ManhattanMetric;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DataPointTest {

  @Test
  public void testAvgDistanceToBucket(){
    //TODO: create couple of data points
    ArrayList<DataPoint> bucket = new ArrayList<>();
    bucket.add(new DataPoint2D(1, 1));
    bucket.add(new DataPoint2D(5, 5));
    bucket.add(new DataPoint2D(9, 9));
    bucket.add(new DataPoint2D(9, 9));

    //TODO
    // Test distance of p4 to rest

    // Test distance of p2 to rest

    // Test of new dp to rest
    DataPoint dp5 = new DataPoint2D(5, 5);

    DataPoint dp6 = new DataPoint2D(6, 6);

    Assert.fail("TODO");
  }
}
