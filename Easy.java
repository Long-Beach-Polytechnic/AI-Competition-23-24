import java.util.ArrayList;

public class Easy extends Character {

  public Easy(Cell token, int row, int col, int hp)
  {
    super(token,row,col,hp);
  }

  public int getDirection(Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
  {
    int direction = (int) (Math.random() * 5);
    if (direction == MOVE_UP)
    {
      if (map.getCell(row-1,col).canPass())
      {
        return MOVE_UP;
      }
    }
    if (direction == MOVE_DOWN)
    {
      if (map.getCell(row+1,col).canPass())
      {
        return MOVE_DOWN;
      }
    }
    else if (direction == MOVE_LEFT)
    {
      if (map.getCell(row,col-1).canPass())
      {
        return MOVE_LEFT;
      }
    }
    else if (direction == MOVE_RIGHT)
    {
      if (map.getCell(row,col-1).canPass())
      {
        return MOVE_RIGHT;
      }
    }
    return DONT_MOVE;
  }
  
}