public class Simulation {

  private int simNumber;
  private int numTurns;

  public Simulation(int simNumber, int numTurns)
  {
    this.simNumber = simNumber;
    this.numTurns = numTurns;
  }

  public int getSimNumber()
  {
    return simNumber;
  }

  public int getNumTurns()
  {
    return numTurns;
  }

  public String toString()
  {
    return "Run: " + simNumber + "\tturns: " + numTurns + "\n";
  }

  
}