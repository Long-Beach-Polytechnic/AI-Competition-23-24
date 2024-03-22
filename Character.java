import java.util.ArrayList;

public class Character extends Movable {

  protected int health;
  protected static final int DONT_MOVE = 0;
  protected static final int MOVE_UP = 1;
  protected static final int MOVE_DOWN = 2;
  protected static final int MOVE_LEFT = 3;
  protected static final int MOVE_RIGHT = 4;

  public Character(Cell image, int row, int col, int health)
  {
    super(image,row,col);
    this.health = health;
  }

  public int getHealth()
  {
    return health;
  }

  public void genericMove(Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
  {
    //default behavior: do nothing
    int randDir = DONT_MOVE;
    try {
      randDir = getDirection(map, items,team,enemyTeam);
    } catch (IndexOutOfBoundsException e) {
    }
    
    if (randDir == MOVE_UP) 
    {
      moveUp();
    }
    if (randDir == MOVE_DOWN) 
    {
      moveDown();
    }
    if (randDir == MOVE_LEFT) 
    {
      moveLeft();
    }
    if (randDir == MOVE_RIGHT) 
    {
      moveRight();
    }
  }

  public int getDirection(Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
  {
    int up    = calculateCellViability(row-1, col, map, team, items);
    int down  = calculateCellViability(row+1, col, map, team, items);
    int left  = calculateCellViability(row, col-1, map, team, items);
    int right = calculateCellViability(row, col+1, map, team, items);
    //System.out.println("UP:\t" + up + "\nDOWN:\t" + down + "\nLEFT:\t" + left + "\nRIGHT:\t" + right);
    //Util.pauseConsole();
    if (up >= down && up >= left && up >= right)
    {
      return MOVE_UP;
    }
    else if (down >= up && down >= left && down >= right)
    {
      return MOVE_DOWN;
    }
    else if (left >= down && left >= up && left >= right)
    {
      return MOVE_LEFT;
    }
    else if (right >= down && right >= left && right >= up)
    {
      return MOVE_RIGHT;
    }
    return 0;
  }

  public void modifyHealth(int amount)
  {
    health += amount;
  }

  public int calculateCellViability(int row, int col, Map map, ArrayList<Character> team, ArrayList<Item> items)
  {
    int points = 0;
    if (!map.getCell(row,col).canPass())
    {
      points -= 10_000;
    }
    if ( row-1 > 0 && !map.getCell(row-1,col).canPass() ||
         col-1 > 0 && !map.getCell(row,col-1).canPass() ||
         col+1 < map.getCols() && !map.getCell(row,col+1).canPass())
    {
      points -= 50;
    }
    for (Item item:items)
    {
      if (item.isMine())
      {
        if (item.isLocatedAt(row,col))
        {
          points -= 1_000;
        }
        if (row > 1 && item.isLocatedAt(row-1,col) ||
            col > 1 && item.isLocatedAt(row,col-1) ||
           row < map.getRows()-1 && item.isLocatedAt(row+1,col) ||
           col < map.getCols()-1 && item.isLocatedAt(row,col+1))
        {
          points -= 50;
        }
      }
      if (item.isHeal())
      {
        if (item.isLocatedAt(row,col))
        {
          points+= 100_000;
        }
        if (row > 1 && item.isLocatedAt(row-1,col) ||
            col > 1 && item.isLocatedAt(row,col-1) ||
           row < map.getRows()-1 && item.isLocatedAt(row+1,col) ||
           col < map.getCols()-1 && item.isLocatedAt(row,col+1))
        {
          points += 500;
        }
      }
      
      
    }
    for (Character otherPlayer:team)
    {
      if (otherPlayer != this && otherPlayer.isLocatedAt(row,col))
      {
        points -= 1_000;
      }
    }
    //randomize
    points += (int) (Math.random() * 10);
    return points;
  }


  
}