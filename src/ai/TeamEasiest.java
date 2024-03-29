package src.ai;
import src.base.*;

public class TeamEasiest extends Team {

  public TeamEasiest(String teamName)
  {
    super(teamName);
  }

  @Override
  public void initializeArena()
  {
    characters.add(new Easiest());
    characters.add(new Easiest());
    characters.add(new Easiest());
    characters.add(new Easiest());
    characters.add(new Easiest());
  }

  @Override
  public void initializeMaze()
  {
    characters.add(new Easiest());
    characters.add(new Easiest());
    characters.add(new Easiest());
    characters.add(new Easiest());
    characters.add(new Easiest());
  }

}