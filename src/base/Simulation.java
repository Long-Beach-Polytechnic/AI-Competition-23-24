package src.base;
public class Simulation {

  private int simCounter;
  private String mapName;
  private Team team1;
  private Team team2;
  private String winner;

  public Simulation(int simCounter, String mapName, Team team1, Team team2, String winner)
  {
    this.simCounter = simCounter;
    this.mapName = mapName;
    this.team1 = team1;
    this.team2 = team2;
    this.winner = winner;
  }

  public String toString()
  {
    return "#" + simCounter +" " + mapName + ":\t" + team1.getName() + " vs. " + team2.getName() + "\t\tRESULT:\t" + winner + "\n";
  }

  
}