package src.ai;
import src.base.*;

public class TeamCodinator extends Team {

  public TeamCodinator(String Codinator)
  {
    super(Codinator);
  }

  @Override
  public void initializeArena()
  {
    characters.add(new Codinator());
    characters.add(new Codinator());
    characters.add(new Codinator());
    characters.add(new Codinator2());
    characters.add(new Codinator2());
  }

  @Override
  public void initializeMaze()
  {
    characters.add(new Codinator());
    characters.add(new Codinator());
    characters.add(new Codinator());
    characters.add(new Codinator2());
    characters.add(new Codinator2());
  }

  

}