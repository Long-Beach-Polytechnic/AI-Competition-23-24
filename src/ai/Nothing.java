package src.ai;

import src.base.*;
import src.base.Character;

import java.util.ArrayList;

public class Nothing extends Character {
    
  private int numMoves;
  private boolean goLeft;
  private boolean hasFoundEdge;


  public Nothing()
  {
    super();
    this.numMoves = 0;
    this.goLeft = false;
    this.hasFoundEdge = false;
  }

  public int getDirection(Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
    {
        numMoves ++;
        if (row == 1 )
        {
          hasFoundEdge = true;
        }
        
        if (!hasFoundEdge)
        {
            return MOVE_UP;
        }
        else
        {
            if (row == 1 && col != 1)
            {
                return MOVE_LEFT;
            }
            else if (col == 1 && row != map.getRows()-2)
            {
                return MOVE_DOWN;
            }
            else if (row == map.getRows()-2 && col != map.getCols()-2)
            {
                return MOVE_RIGHT;
            }
            else if (col == map.getCols()-2 && row != 1)
            {
                return MOVE_UP;
            }
        }
        return DONT_MOVE;
    
    }
  
  
}