package src.base;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {

  private Map map;
  private String currentMapName;
  private Team team1;
  private Team team2;
  private ArrayList<Character> team1characters;
  private ArrayList<Character> team2characters;
  private ArrayList<Item> itemsOnMap;
  private String result;
  private int turnCounter;
  private boolean keepPlaying;
  private int simulationCounter;
  
  private static final int NUMBER_OF_ROWS = 21;
  private static final int NUMBER_OF_COLS = 21;
  private static final int NUMBER_OF_STARTING_MINES = 50;

  private static final boolean DOES_DISPLAY = false
  ;
  private static final int SIMULATION_DELAY_MILLISECONDS = 100;
  private static final int PAUSE_TIME_MILLISECONDS = 2000;

  public Game(Team team1, Team team2)
  {
    this.team1 = team1;
    this.team2 = team2;
    this.simulationCounter = 0;
  }


  public void run()
  {
    simulationCounter++;
    turnCounter = 0;
    initialize();
    startSimulation();
  }

  private void initialize()
  {
    if (simulationCounter < 8)
    {
      map = MapFactory.arena(NUMBER_OF_ROWS, NUMBER_OF_COLS);
      currentMapName = "Arena";
      team1.setupForArena( 1 + simulationCounter%2);
      team2.setupForArena(2 -simulationCounter%2);
    }
    else
    {
      map = MapFactory.maze(NUMBER_OF_ROWS, NUMBER_OF_COLS);
      currentMapName = "Maze";
      team1.setupForMaze(1 + simulationCounter%2);
      team2.setupForMaze(2 -simulationCounter%2);
    }

    team1characters = team1.getCharacters();
    team2characters = team2.getCharacters();

    itemsOnMap = new ArrayList<Item>();
    for (int i=0; i<NUMBER_OF_STARTING_MINES; i++)
    {
      randomlyPlaceMineOnMap();
    }
    
  }

  private void randomlyPlaceMineOnMap()
  {
    boolean isPlaced = false;
    while (!isPlaced)
    {
      int row = (int) (1 + Math.random() * (map.getRows()-2));
      int col = (int) (1 + Math.random() * (map.getCols()-2));
      boolean spotIsTaken = false;
      if (!map.getCell(row,col).canPass())
      {
        spotIsTaken = true;
      }
      for (Item item:itemsOnMap)
      {
        if (item.isLocatedAt(row,col))
        {
          spotIsTaken = true;
        }
      }
      for (Movable player:team1characters)
      {
        if (player.isLocatedAt(row,col))
        {
          spotIsTaken = true;
        }
      }
      for (Movable player:team2characters)
      {
        if (player.isLocatedAt(row,col))
        {
          spotIsTaken = true;
        }
      }
      if (!spotIsTaken)
      {
        itemsOnMap.add(Item.create(Item.MINE,row,col));
        isPlaced = true;
      }
    }
  }

  private void randomlyPlacePotionOnMap()
  {
    boolean isPlaced = false;
    while (!isPlaced)
    {
      int row = (int) (map.getRows()/4 + Math.random() * (map.getRows()/2));
      int col = (int) (map.getCols()/4 + Math.random() * (map.getCols()/2));
      boolean spotIsTaken = false;
      if (!map.getCell(row,col).canPass())
      {
        spotIsTaken = true;
      }
      for (Item item:itemsOnMap)
      {
        if (item.isLocatedAt(row,col))
        {
          spotIsTaken = true;
        }
      }
      for (Movable player:team1characters)
      {
        if (player.isLocatedAt(row,col))
        {
          spotIsTaken = true;
        }
      }
      for (Movable player:team2characters)
      {
        if (player.isLocatedAt(row,col))
        {
          spotIsTaken = true;
        }
      }
      if (!spotIsTaken)
      {
        itemsOnMap.add(Item.create(Item.HEAL,row,col));
        isPlaced = true;
      }
    }
  }

  private void startSimulation()
  {
    keepPlaying = true;

    while (keepPlaying)
    {
      addItemToMap();
      putThingsOnTheMap();
      if (DOES_DISPLAY) {
        Util.clearConsole(); //clear the screen
        map.display(); //display the map
        displayInformation();
      }
      if (turnCounter%2 == 0){
        makeMoves(team1characters,team2characters);
        checkIfEventIsTriggered(team1characters);
      } else {
        makeMoves(team2characters,team1characters);
        checkIfEventIsTriggered(team2characters);
      }
      //checkIfEventIsTriggered(); //program what happens in certain cells
      bothTeamsTakeDamage();
      turnCounter++;

      sleep(SIMULATION_DELAY_MILLISECONDS);

      if (team1characters.isEmpty() && team2characters.isEmpty() ||
          team1characters.isEmpty() && teamIsPracticallyDead(team2characters) ||
          team2characters.isEmpty() && teamIsPracticallyDead(team1characters))
      {
        result = "TIE";
        team1.addTie();
        team2.addTie();
        sleep(PAUSE_TIME_MILLISECONDS);
        keepPlaying = false;
      }
      else if (team2characters.isEmpty() && team1characters.size() > 0)
      {
        result = team1.getName();
        team1.addWin();
        team2.addLoss();
        sleep(PAUSE_TIME_MILLISECONDS);
        keepPlaying = false;
      }
      else if (team1characters.isEmpty() && team2characters.size() > 0)
      {
        result = team2.getName();
        team1.addLoss();
        team2.addWin();
        sleep(PAUSE_TIME_MILLISECONDS);
        keepPlaying = false;
      }

      
    }

    displayResults();


    sleep(PAUSE_TIME_MILLISECONDS);
    if (simulationCounter < 10)
    {
      run(); //start game over
    }
    
  }

  private boolean teamIsPracticallyDead(ArrayList<Character> teamMembers) {
    for (Character member:teamMembers) {
      if (member.getHealth() > 2) {
        return false;
      }
    }
    return true;
  }

  private void addItemToMap()
  {
    int decider = turnCounter%20;
    if (decider%2==0 && decider < 18)
    {
      randomlyPlaceMineOnMap();
    }
    if (decider >= 18)
    {
      randomlyPlacePotionOnMap();
    }
  }

  private void putThingsOnTheMap()
  {
    map.cloneMap();
    for (Movable player:team1characters)
    {
      map.place(player);
    }
    for (Movable player:team2characters)
    {
      map.place(player);
    }
    for (Movable item:itemsOnMap)
    {
      map.place(item);
    }
  }

  public void displayInformation()
  {
    System.out.println("Simulation #" + simulationCounter);
    System.out.println("Turn:\t" + turnCounter);
    System.out.print(team1.getTeamColor() + team1.getName() + TextColor.RESET + ":\t");
    for (Character player:team1characters)
    {
      System.out.print(player.getHealth() + " " );
    }
    System.out.println();

    System.out.print(team2.getTeamColor() + team2.getName() + TextColor.RESET + ":\t");
    for (Character player:team2characters)
    {
      System.out.print(player.getHealth() + " " );
    }
    System.out.println();
  }

  private void makeMoves(ArrayList<Character> currentTeam, ArrayList<Character> opposingTeam)
  {
    for (Character player:currentTeam)
    {
      player.genericMove(map,itemsOnMap,currentTeam,opposingTeam);
    }
  }

  private void checkIfEventIsTriggered(ArrayList<Character> currentTeam)
  {
    removeIfCharacterOffMap(currentTeam);
    characterItemCollisions(currentTeam);
    characterToCharacterCollisions();
  }

  private void removeIfCharacterOffMap(ArrayList<Character> team)
  {
    for (int playerIndex=0; playerIndex<team.size(); playerIndex++)
    {
      Character player = team.get(playerIndex);
      if (player.getRow() < 1 || player.getCol() < 1 || player.getRow() >= map.getRows() || player.getCol() >=  map.getCols() || !map.getCell(player).canPass())
      {
        team.remove(playerIndex);
        playerIndex--;
      }
    }
  }

  private void characterItemCollisions(ArrayList<Character> team)
  {
    for (int playerIndex=0; playerIndex<team.size(); playerIndex++)
    {
      Character player = team.get(playerIndex);
      for (int itemIndex = 0; itemIndex < itemsOnMap.size(); itemIndex++)
      {
        Item item = itemsOnMap.get(itemIndex);
        if (player.isLocatedAt(item.getRow(),item.getCol()))
        {
          player.modifyHealth(item.getHealthModifier());
          itemsOnMap.remove(itemIndex);
          itemIndex--;
        }
      }

      if (player.getHealth() <= 0)
      {
        team.remove(playerIndex);
        playerIndex--;
      }
    }
  }

  private void characterToCharacterCollisions()
  {
    teamSelfCollisions(team1);
    teamSelfCollisions(team2);

    //teams attacking each other
    for (int playerIndex=0; playerIndex<team1characters.size(); playerIndex++)
    {
      Character player = team1characters.get(playerIndex);

      for (int enemyMemberIndex = 0; enemyMemberIndex < team2characters.size(); enemyMemberIndex++)
      {
        Character enemy = team2characters.get(enemyMemberIndex);
        if (player.isLocatedAt(enemy.getRow(),enemy.getCol()))
        {
          if (player.getHealth() > enemy.getHealth())
          {
            team2characters.remove(enemyMemberIndex);
            enemyMemberIndex--;
            System.out.println(team1.getName() + " KILLED " + team2.getName());
            team1.addKill();
            sleep(500);
          }
          else if (player.getHealth() == enemy.getHealth())
          {
            team2characters.remove(enemyMemberIndex);
            enemyMemberIndex--;
            team1characters.remove(playerIndex);
            playerIndex--;
            System.out.println("BOTH TEAMS DIE");
            team1.addKill();
            team2.addKill();
            sleep(500);
          }
          else
          {
            team1characters.remove(playerIndex);
            playerIndex--;
            System.out.println(team2.getName() + " KILLED " + team1.getName());
            team2.addKill();
            sleep(500);
          }
        }
      }
    }
  }

  private void teamSelfCollisions(Team team)
  {
    ArrayList<Character> teamCharacters = team.getCharacters();
    for (int playerIndex=0; playerIndex<teamCharacters.size(); playerIndex++)
    {
      Character player = teamCharacters.get(playerIndex);

      for (int teamMemberIndex = 0; teamMemberIndex < teamCharacters.size(); teamMemberIndex++)
      {
        Character teamMember = teamCharacters.get(teamMemberIndex);
        if (player != teamMember && player.isLocatedAt(teamMember.getRow(),teamMember.getCol()))
        {
          if (player.getHealth() > teamMember.getHealth())
          {
            teamCharacters.remove(teamMemberIndex);
            teamMemberIndex--;
            System.out.println(team1.getName() + " KILLED OWN TEAM");
            team.addKill();
            sleep(500);
          }
          else if (player.getHealth() == teamMember.getHealth())
          {
            teamCharacters.remove(teamMemberIndex);
            teamMemberIndex--;
            teamCharacters.remove(playerIndex);
            playerIndex--;
            System.out.println(team.getName() + "MUTUAL self team DEATH");
            team.addKill();
            team.addKill();
            sleep(500);
          }
          else
          {
            teamCharacters.remove(playerIndex);
            playerIndex--;
            System.out.println(team.getName() + " KILLED OWN TEAM");
            team.addKill();
            sleep(500);
          }
        }
      }
    }
  }

  private void bothTeamsTakeDamage()
  {
    for (Character player:team1characters)
    {
      player.modifyHealth(-1);
    }
    for (Character player:team2characters)
    {
      player.modifyHealth(-1);
    }
  }

  private void displayResults()
  {
    Util.clearConsole();
    SimulationManager.add(currentMapName, team1,team2, result);
    SimulationManager.printResults();
  }

  private void sleep(int milliseconds)
  {
    if (DOES_DISPLAY) {
      try {
        TimeUnit.MILLISECONDS.sleep(milliseconds);
      } catch (InterruptedException e) {
      }
    }
  }

  
}