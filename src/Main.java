package src;

import src.base.*;
import src.ai.*;
;

class Main {
  public static void main(String[] args) {

    /*
    TO DO
    * 1. Create three maps in MapFactory.java
    *   - game cycles through each map 5 times
    *   - best of 5 wins
    * 2. Learn recursive logic
    */
    
    SimulationRunner sim = new SimulationRunner();

    sim.run();
  }
}