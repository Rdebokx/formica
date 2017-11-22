package com.rdebokx.formica.example;

import com.rdebokx.formica.core.Colony;

public class TestRunner {

  public static void main(String ... args){
    System.out.println("Hello world! Let's build a colony filled with fruit...");
    Colony colony = buildColony();

    //TODO: initialize ants in colony
    colony.start();


  }

  public static Colony buildColony() {
    return new Colony(
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
    );
  }
}
