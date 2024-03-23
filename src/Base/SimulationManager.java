import java.util.ArrayList;

public class SimulationManager {

  private static ArrayList<Simulation> simulations = new ArrayList<Simulation>();

  public static void add(int simNumber, int numTurns)
  {
    simulations.add(new Simulation(simNumber, numTurns));
  }

  public static void printResults()
  {
    printAllSimulations();
    printAverageRun();
  }

  public static void printAllSimulations()
  {
    System.out.println(simulations);
  }

  public static void printAverageRun()
  {
    int totalTurns = 0;
    for (Simulation sim:simulations)
    {
      totalTurns += sim.getNumTurns();
    }
    System.out.println("Average Turns:\t" + ((double) totalTurns / simulations.size()));
  }
  
}