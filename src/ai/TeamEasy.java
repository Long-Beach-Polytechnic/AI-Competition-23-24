package src.ai;
import src.base.*;
import src.base.Character;
import java.util.ArrayList;

public class TeamEasy extends Team {

  public TeamEasy(String teamName)
  {
    super(teamName);
  }

  @Override
  public void initializeArena()
  {
    characters.add(new Easy());
    characters.add(new Easy());
    characters.add(new Easy());
    characters.add(new Easy());
    characters.add(new Easy());
  }

  @Override
  public void initializeMaze()
  {
    characters.add(new Easy());
    characters.add(new Easy());
    characters.add(new Easy());
    characters.add(new Easy());
    characters.add(new Easy());
  }

}