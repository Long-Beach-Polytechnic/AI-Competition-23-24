package src.base;
import java.util.ArrayList;

public class Team {

  private String teamName;
  protected ArrayList<Character> characters;
  private int numKills;
  private int numWins;
  private int numLosses;
  private int numTies;

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
        currenCharacter.setLocation(startingRow, startingCol);
        currenCharacter.setToken(teamToken);

        startingCol += 3;
    }
  }

  public void setupForMaze(int teamNumber)
  {
    initializeMaze();
    trimTeam();
    
    Cell teamToken;
    if (teamNumber == 1)
    {
      teamToken = new Cell("*",TextColor.WHITE);
      characters.get(0).setLocation(1, 19);
      characters.get(1).setLocation(1, 17);
      characters.get(2).setLocation(1, 15);
      characters.get(3).setLocation(3, 18);
      characters.get(4).setLocation(3, 16);
    }
    else
    {
      teamToken = new Cell("#",TextColor.WHITE);
      characters.get(0).setLocation(17,2);
      characters.get(1).setLocation(17, 4);
      characters.get(2).setLocation(19, 5);
      characters.get(3).setLocation(19, 3);
      characters.get(4).setLocation(19, 1);
    }
    for (int characterIndex = 0; characterIndex < characters.size(); characterIndex++)
    {
        Character currenCharacter = characters.get(characterIndex);
        currenCharacter.setToken(teamToken);
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

  public int getPoints()
  {
    return numWins * 10 + numTies * 5 + numKills;
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

  public void addKill()
  {
    numKills++;
  }

  public void trimTeam()
  {
    while (characters.size() > 5)
    {
      characters.remove(0);
    }
  }


}