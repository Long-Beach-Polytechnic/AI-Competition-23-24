package src.base;
public class Simulation {

  private int simCounter;
  private Team team1;
  private Team team2;
  private String winner;

  public Simulation(int simCounter, Team team1, Team team2, String winner)
  {
    this.simCounter = simCounter;
    this.team1 = team1;
    this.team2 = team2;
    this.winner = winner;
  }

  public String toString()
  {
    return "#" + simCounter + "\tTeam 1: " + team1.getName() + "\tTeam2: " + team2.getName() + "\tRESULT:\t" + winner + "\n";
  }

  
}