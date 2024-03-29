package src.ai;

import src.base.Team;

public class TeamNothing extends Team
{
    public TeamNothing(String teamName)
  {
    super(teamName);
  }

  @Override
  public void initializeArena()
  {
    characters.add(new Nothing());
    characters.add(new Nothing());
    characters.add(new Nothing());
    characters.add(new Nothing());
    characters.add(new Nothing());
  }

  @Override
  public void initializeMaze()
  {
    characters.add(new Nothing());
    characters.add(new Nothing());
    characters.add(new Nothing());
    characters.add(new Nothing());
    characters.add(new Nothing());
  }
    
}
