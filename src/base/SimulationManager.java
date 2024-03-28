package src.base;
import java.util.ArrayList;

public class SimulationManager {

  private static ArrayList<Simulation> simulations = new ArrayList<Simulation>();
  private static int simCounter = 0;

  public static void add(Team team1, Team team2, String result)
  {
    simCounter++;
    simulations.add(new Simulation(simCounter, team1, team2, result));
  }

  public static void printResults()
  {
    printAllSimulations();
    //printAverageRun();
  }

  public static void printAllSimulations()
  {
    System.out.println(simulations);
  }

  /*
  public static void printAverageRun()
  {
    int totalTurns = 0;
    for (Simulation sim:simulations)
    {
      totalTurns += sim.getNumTurns();
    }
    System.out.println("Average Turns:\t" + ((double) totalTurns / simulations.size()));
  }
  */
  
}