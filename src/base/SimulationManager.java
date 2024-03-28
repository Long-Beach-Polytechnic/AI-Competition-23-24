package src.base;
import java.util.ArrayList;

public class SimulationManager {

  private static ArrayList<Simulation> simulations = new ArrayList<Simulation>();
  private static int simCounter = 0;

  public static void add(String mapName, Team team1, Team team2, String result)
  {
    simCounter++;
    simulations.add(new Simulation(simCounter, mapName, team1, team2, result));
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
  
}