import java.util.ArrayList;

public class Easiest extends Character {

  public Easiest(Cell token, int row, int col, int hp)
  {
    super(token,row,col,hp);
  }

  public int getDirection(Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
  {
    int direction = (int) (Math.random() * 5);
    if (direction == MOVE_UP)
    {
      return MOVE_UP;
    }
    else if (direction == MOVE_DOWN)
    {
      return MOVE_DOWN;
    }
    else if (direction == MOVE_LEFT)
    {
      return MOVE_LEFT;
    }
    else if (direction == MOVE_RIGHT)
    {
      return MOVE_RIGHT;
    }
    else
    {
      return DONT_MOVE;
    }
  }

}