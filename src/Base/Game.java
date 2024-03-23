import java.util.Scanner;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Game {

  private Map map;
  private ArrayList<Character> team1;
  private ArrayList<Character> team2;
  private ArrayList<Item> itemsOnMap;
  private int turnCounter;
  private boolean keepPlaying;
  private int simulationCounter = 0;
  
  private static final int NUMBER_OF_ROWS = 20;
  private static final int NUMBER_OF_COLS = 20;
  private static final int NUMBER_OF_STARTING_MINES = 50;
  private static final boolean MAP_IS_MAZE = false;
  private static final int STARTING_HEALTH = 500;

  private static final int SIMULATION_DELAY_MILLISECONDS = 100;
  private static final int PAUSE_TIME_MILLISECONDS = 2000;

  public void run()
  {
    simulationCounter++;
    turnCounter = 0;
    initialize();
    startSimulation();
  }

  private void initialize()
  {
    if (MAP_IS_MAZE)
    {
      map = MapFactory.maze(NUMBER_OF_ROWS, NUMBER_OF_COLS);
    }
    else
    {
      map = MapFactory.arena(NUMBER_OF_ROWS, NUMBER_OF_COLS);
    }

    team1 = new ArrayList<Character>();
    for (int col=2; col<map.getCols()-2; col+= 3)
    {
      team1.add(new Character(new Cell("*",TextColor.WHITE), 5,col, STARTING_HEALTH));
    }

    team2 = new ArrayList<Character>();
    for (int col=2; col<map.getCols()-2; col+=3)
    {
      team2.add(new Easy(new Cell("*",TextColor.CYAN_BOLD), 15,col,STARTING_HEALTH));
    }

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
      for (Movable player:team1)
      {
        if (player.isLocatedAt(row,col))
        {
          spotIsTaken = true;
        }
      }
      for (Movable player:team2)
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
      for (Movable player:team1)
      {
        if (player.isLocatedAt(row,col))
        {
          spotIsTaken = true;
        }
      }
      for (Movable player:team2)
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
      Util.clearConsole(); //clear the screen
      map.display(); //display the map
      displayInformation();
      if (turnCounter%2 == 0){
        makeMoves(team1,team2);
        checkIfEventIsTriggered(team1);
      } else {
        makeMoves(team2,team1);
        checkIfEventIsTriggered(team2);
      }
      //checkIfEventIsTriggered(); //program what happens in certain cells
      bothTeamsTakeDamage();
      turnCounter++;

      sleep(SIMULATION_DELAY_MILLISECONDS);

      if (team1.isEmpty() || team2.isEmpty())
      {
        sleep(PAUSE_TIME_MILLISECONDS);
        keepPlaying = false;
      }
      
    }

    displaySimulationResults();
    sleep(PAUSE_TIME_MILLISECONDS);
    run(); //start game over
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
    for (Movable player:team1)
    {
      map.place(player);
    }
    for (Movable player:team2)
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
    System.out.print("Team 1:\t" + team1.size() + " ");
    for (Character player:team1)
    {
      System.out.print(player.getHealth() + " " );
    }
    System.out.println();

    System.out.print("Team 2:\t" + team2.size() + " ");
    for (Character player:team2)
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
    for (int playerIndex=0; playerIndex<team1.size(); playerIndex++)
    {
      Character player = team1.get(playerIndex);

      for (int teamMemberIndex = 0; teamMemberIndex < team1.size(); teamMemberIndex++)
      {
        Character teamMember = team1.get(teamMemberIndex);
        if (player != teamMember && player.isLocatedAt(teamMember.getRow(),teamMember.getCol()))
        {
          if (player.getHealth() > teamMember.getHealth())
          {
            team1.remove(teamMemberIndex);
            teamMemberIndex--;
            System.out.println("KILLED OWN TEAM");
            Util.pauseConsole();
          }
          else if (player.getHealth() == teamMember.getHealth())
          {
            team1.remove(teamMemberIndex);
            teamMemberIndex--;
            team1.remove(playerIndex);
            playerIndex--;
            System.out.println("MUTUAL self team DEATH");
            Util.pauseConsole();
          }
          else
          {
            team1.remove(playerIndex);
            playerIndex--;
            System.out.println("KILLED OWN TEAM");
            Util.pauseConsole();
          }
        }
      }

      for (int enemyMemberIndex = 0; enemyMemberIndex < team2.size(); enemyMemberIndex++)
      {
        Character enemy = team2.get(enemyMemberIndex);
        if (player.isLocatedAt(enemy.getRow(),enemy.getCol()))
        {
          if (player.getHealth() > enemy.getHealth())
          {
            team2.remove(enemyMemberIndex);
            enemyMemberIndex--;
            System.out.println("KILLED OTHER TEAM");
            Util.pauseConsole();
          }
          else if (player.getHealth() == enemy.getHealth())
          {
            team2.remove(enemyMemberIndex);
            enemyMemberIndex--;
            team1.remove(playerIndex);
            playerIndex--;
            System.out.println("MUTUAL TEAM 2 OTHERTEAM team DEATH");
            Util.pauseConsole();
          }
          else
          {
            team1.remove(playerIndex);
            playerIndex--;
            System.out.println("DIED to other TEAM");
            Util.pauseConsole();
          }
        }
      }
    }
  }

  private void bothTeamsTakeDamage()
  {
    for (Character player:team1)
    {
      player.modifyHealth(-1);
    }
    for (Character player:team2)
    {
      player.modifyHealth(-1);
    }
  }

  private void displaySimulationResults()
  {
    Util.clearConsole();
    SimulationManager.add(simulationCounter,turnCounter);
    SimulationManager.printResults();
  }

  private void sleep(int milliseconds)
  {
    try {
      TimeUnit.MILLISECONDS.sleep(milliseconds);
    } catch (InterruptedException e) {
    }
  }

  
}