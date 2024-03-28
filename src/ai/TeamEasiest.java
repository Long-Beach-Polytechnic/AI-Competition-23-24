package src.ai;
import src.base.*;
import src.base.Character;
import java.util.ArrayList;

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