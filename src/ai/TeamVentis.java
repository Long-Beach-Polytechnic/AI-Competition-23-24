package src.ai;
import src.base.*;

public class TeamVentis extends Team {

  public TeamVentis(String teamName)
  {
    super(teamName);
  }

  @Override
  public void initializeArena()
  {
    characters.add(new Ventis());
    characters.add(new Ventis());
    characters.add(new Ventis());
    characters.add(new Ventis());
    characters.add(new Ventis());
  }

  @Override
  public void initializeMaze()
  {
    characters.add(new Ventis());
    characters.add(new Ventis());
    characters.add(new Ventis());
    characters.add(new Ventis()); //hi no :(  :(  whyyyyy
    characters.add(new Ventis());
  }

}