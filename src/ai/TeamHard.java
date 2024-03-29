package src.ai;
import src.base.*;

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