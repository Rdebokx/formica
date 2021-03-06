package com.rdebokx.formica.example;

import com.rdebokx.formica.core.Bucket;
import com.rdebokx.formica.core.Colony;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.execution.ResultPrinter;
import com.rdebokx.formica.execution.TimeCondition;
import com.rdebokx.formica.metrics.distance.EuclideanMetric;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

public class ExampleRunner {

  public static void main(String ... args){
    Logger.info("Hello world! Let's build a colony filled with fruit...");
    Colony colony = buildColony();

    long startTime = System.currentTimeMillis();
    //Run for 10s
    Logger.info("Running the colony for 10s.");
    while(!colony.hasStopped()){
      colony.nextStep();
    }

    Logger.info("Colony terminated after 10s. " + colony.getStatsLogger().getTotalAntMoves() + " ant moves were done.");

    StringWriter stringWriter = new StringWriter();
    try{
      ResultPrinter.printBucketResults(stringWriter, colony);
      Logger.info("Colony Result: \n" + stringWriter.toString());
    } catch(IOException e){
      Logger.error(e);
    }
  }

  public static Colony buildColony() {
    Configuration config = new Configuration(EuclideanMetric.METRIC_NAME, 5, 0.05, 0.25, new TimeCondition(1000*10));

    return new Colony(config, Arrays.asList(
        new Fruit("Apple", 0.214, 0.180, 2.5, 60),
        new Fruit("Apple", 0.350, 0.231, 2.5, 60),
        new Fruit("Apple", 0.221, 0.186, 2.5, 60),
        new Fruit("Apple", 0.264, 0.199, 2.5, 60),
        new Fruit("Apple", 0.182, 0.151, 2.5, 60),
        new Fruit("Pear", 0.190, 0.150, 3.5, 62),
        new Fruit("Pear", 0.182, 0.145, 3.5, 62),
        new Fruit("Pear", 0.199, 0.189, 3.5, 62),
        new Fruit("Pear", 0.160, 0.110, 3.5, 62),
        new Fruit("Pineapple", 0.814, 1.040, 6.5, 30),
        new Fruit("Pineapple", 0.999, 1.152, 6.5, 30),
        new Fruit("Pineapple", 1.005, 1.200, 6.5, 30),
        new Fruit("Pineapple", 0.701, 0.991, 6.5, 30),
        new Fruit("Pineapple", 0.592, 0.880, 6.5, 30),
        new Fruit("WaterMelon", 5.624, 5.150, 0.9, 90),
        new Fruit("WaterMelon", 3.814, 3.456, 0.9, 90),
        new Fruit("WaterMelon", 4.854, 4.515, 0.9, 90),
        new Fruit("WaterMelon", 5.356, 5.020, 0.9, 90),
        new Fruit("WaterMelon", 6.785, 6.650, 0.9, 90)
    ));
  }
}
