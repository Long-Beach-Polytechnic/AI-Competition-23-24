package src.ai;
import src.base.*;
import src.base.Character;
import java.util.ArrayList;

public class TeamHard extends Team {

  public TeamHard(String teamName)
  {
    super(teamName);
  }

  @Override
  public void initializeArena()
  {
    characters.add(new Hard());
    characters.add(new Hard());
    characters.add(new Hard());
    characters.add(new Hard());
    characters.add(new Hard());
  }

  @Override
  public void initializeMaze()
  {
    characters.add(new Hard());
    characters.add(new Hard());
    characters.add(new Hard());
    characters.add(new Hard());
    characters.add(new Hard());
  }

}