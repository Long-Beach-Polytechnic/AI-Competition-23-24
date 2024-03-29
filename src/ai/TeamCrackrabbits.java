package src.ai;
import src.base.*;

public class TeamCrackrabbits extends Team {

  public TeamCrackrabbits(String teamName)
  {
    super(teamName);
  }

  @Override
  public void initializeArena()
  {
    characters.add(new Crackrabbit());
    characters.add(new Crackrabbit());
    characters.add(new Crackrabbit());
    characters.add(new Crackrabbit());
    characters.add(new Crackrabbit());
  }

  @Override
  public void initializeMaze()
  {
    characters.add(new Crackrabbit());
    characters.add(new Crackrabbit());
    characters.add(new Crackrabbit());
    characters.add(new Crackrabbit());
    characters.add(new Crackrabbit());
  }

}