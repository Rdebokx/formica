package com.rdebokx.formica.core;

import com.rdebokx.formica.core.Ant;

/**
 * Class for logging the stats related to a Colony, eg. the nr. of moves done by the ants in the colony.
 */
public class StatsLogger {

  /**
   * Array containing the number of moves done per ant.
   */
  private final int[] antMoves;

  /**
   * Constructor, constructing a new StatsLogger.
   * @param numberOfAnts The number of ants in the colony to log.
   */
  protected StatsLogger(int numberOfAnts){
    antMoves = new int[numberOfAnts];
  }

  /**
   * Log a move of an ant. This will increment the nr. of moves recorded for the given ant.
   * @param ant The ant to log a move for.
   */
  protected void logMove(Ant ant){
    antMoves[ant.getId()]++;
  }

  /**
   * The number of moves done by the given ant.
   * @param ant The ant of which the number of moves is requested.
   * @return The number of moves done by the given ant.
   */
  public int getAntMoves(Ant ant){
    return getAntMoves(ant.getId());
  }

  /**
   * The number of moves done by the given ant.
   * @param antId The ID of the ant of which the number of moves is requested.
   * @return The number of moves done by the given ant.
   */
  public int getAntMoves(int antId) {
    return antMoves[antId];
  }

  /**
   * @return The total number of moves logged for all the ants.
   */
  public int getTotalAntMoves(){
    int total = 0;
    for(int moves : antMoves){
      total += moves;
    }
    return total;
  }
}
