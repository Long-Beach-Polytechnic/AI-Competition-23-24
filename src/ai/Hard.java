package src.ai;

import src.base.*;
import src.base.Character;

import java.util.ArrayList;

public class Hard extends Character {
  
  public Hard()
  {
    super();
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


