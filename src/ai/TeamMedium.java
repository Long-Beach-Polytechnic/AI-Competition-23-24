package src.ai;
import src.base.*;

public class TeamMedium extends Team {

  public TeamMedium(String teamName)
  {
    super(teamName);
  }

  @Override
  public void initializeArena()
  {
    characters.add(new Medium());
    characters.add(new Medium());
    characters.add(new Medium());
    characters.add(new Medium());
    characters.add(new Medium());
  }

  @Override
  public void initializeMaze()
  {
    characters.add(new Medium());
    characters.add(new Medium());
    characters.add(new Medium());
    characters.add(new Medium());
    characters.add(new Medium());
  }

}