package src.base;
import java.util.ArrayList;

public class Character extends Movable {

  protected int health;

  private static final int STARTING_HEALTH = 250;
  
  protected static final int DONT_MOVE = 0;
  protected static final int MOVE_UP = 1;
  protected static final int MOVE_DOWN = 2;
  protected static final int MOVE_LEFT = 3;
  protected static final int MOVE_RIGHT = 4;

  public Character()
  {
    super(new Cell("o",TextColor.WHITE), 0, 0);
    this.health = STARTING_HEALTH;
  }

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
    int direction = DONT_MOVE;
    try {
      direction = getDirection(map, items,team,enemyTeam);
    } catch (IndexOutOfBoundsException e) {
    }
    
    if (direction == MOVE_UP) 
    {
      moveUp();
      health--;
    }
    else if (direction == MOVE_DOWN) 
    {
      moveDown();
      health--;
    }
    else if (direction == MOVE_LEFT) 
    {
      moveLeft();
      health--;
    }
    else if (direction == MOVE_RIGHT) 
    {
      moveRight();
      health--;
    }
    else
    {
      health -= 2;
    }
  }

  public int getDirection(Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
  {
    return DONT_MOVE;
  }

  public void modifyHealth(int amount)
  {
    health += amount;
  }


  
}