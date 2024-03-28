package src.base;
import java.util.ArrayList;

public class Team {

  private String teamName;
  protected ArrayList<Character> characters;
  private int numKills;
  private int numWins;
  private int numLosses;
  private int numTies;

  private static final int STARTING_HEALTH = 500;

  public Team(String teamName)
  {
    this.teamName = teamName;
    this.characters = new ArrayList<Character>();
    this.numKills = 0;
  }

  public void initializeArena()
  {

  }

  public void initializeMaze()
  {
    
  }

  public void setupForArena(int teamNumber)
  {
    initializeArena();
    trimTeam();

    int startingRow = -1;
    Cell teamToken;
    if (teamNumber == 1)
    {
      startingRow = 4;
      teamToken = new Cell("@",TextColor.WHITE_BOLD);
    }
    else
    {
      startingRow = 15;
      teamToken = new Cell("#",TextColor.WHITE_BOLD);
    }
    int startingCol = 4;
    for (int characterIndex = 0; characterIndex < characters.size(); characterIndex++)
    {
        Character currenCharacter = characters.get(characterIndex);
        currenCharacter.setRow(startingRow);
        currenCharacter.setCol(startingCol);
        currenCharacter.setToken(teamToken);

        startingCol += 3;
    }
  }

  public void setupForMaze(int teamNumber)
  {
    initializeMaze();
    trimTeam();
    
    int startingRow = -1;
    Cell teamToken;
    if (teamNumber == 1)
    {
      startingRow = 4;
      teamToken = new Cell("*",TextColor.WHITE);
    }
    else
    {
      startingRow = 15;
      teamToken = new Cell("#",TextColor.WHITE);
    }
    int startingCol = 4;
    for (int characterIndex = 0; characterIndex < characters.size(); characterIndex++)
    {
        Character currenCharacter = characters.get(characterIndex);
        currenCharacter.setRow(startingRow);
        currenCharacter.setCol(startingCol);
        currenCharacter.setToken(teamToken);

        startingCol += 3;
    }
  }

  public ArrayList<Character> getCharacters()
  {
    return characters;
  }

  public String getName()
  {
    return teamName;
  }

  public int getWins()
  {
    return numWins;
  }

  public int getLosses()
  {
    return numLosses;
  }

  public int getTies()
  {
    return numTies;
  }

  public int getNumKills()
  {
    return numKills;
  }

  public void addWin()
  {
    numWins++;
  }

  public void addLoss()
  {
    numLosses++;
  }

  public void addTie()
  {
    numTies++;
  }

  public void trimTeam()
  {
    while (characters.size() > 5)
    {
      characters.remove(0);
    }
  }


}