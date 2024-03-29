package src.ai;

import src.base.*;
import src.base.Character;

import java.util.ArrayList;

public class Ventis extends Character {

  public Ventis()
  {
    super();
  }

  @Override
    public int getDirection(Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
    {
        int pointsUp =      calcPointsIfGoingTo(row-1, col, map, items, team, enemyTeam);
        int pointsDown =    calcPointsIfGoingTo(row+1, col, map, items, team, enemyTeam);
        int pointsLeft =    calcPointsIfGoingTo(row, col-1, map, items, team, enemyTeam);
        int pointsRight =   calcPointsIfGoingTo(row, col+1, map, items, team, enemyTeam);

        if (pointsUp >= pointsDown && pointsUp >= pointsLeft && pointsUp >= pointsRight)
        {
            return MOVE_UP;
        }
        if (pointsDown >= pointsUp && pointsDown >= pointsLeft && pointsDown >= pointsRight)
        {
            return MOVE_DOWN;
        }
        if (pointsLeft >= pointsDown && pointsLeft >= pointsUp && pointsLeft >= pointsRight)
        {
            return MOVE_LEFT;
        }
        if (pointsRight >= pointsDown && pointsRight >= pointsLeft && pointsRight >= pointsUp)
        {
            return MOVE_RIGHT;
        }
        return DONT_MOVE;
    }

    private int calcPointsIfGoingTo(int row, int col, Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
    {
        int points = 0;
        if (!map.getCell(row,col).canPass())
        {
            points -= 5000;
        }
        if (map.getCell(row, col).canPass()) {
            points += 100;
        }
        for (Item item:items)
        {
            if (item.isLocatedAt(row,col) && item.isHeal())
            {
                points += 50;
            }
            else if (item.isLocatedAt(row-1,col) && item.isHeal())
            {
                points += 40;
            }
            else if (item.isLocatedAt(row+1,col) && item.isHeal())
            {
                points += 40;
            }
            else if (item.isLocatedAt(row,col-1) && item.isHeal())
            {
                points += 40;
            }
            else if (item.isLocatedAt(row, col+1) && item.isHeal()) {
                points += 40;
            }
            else if (item.isLocatedAt(row-2,col) && item.isHeal())
            {
                points += 30;
            }
            else if (item.isLocatedAt(row+2,col) && item.isHeal())
            {
                points += 30;
            }
            else if (item.isLocatedAt(row,col-2) && item.isHeal())
            {
                points += 30;
            }
            else if (item.isLocatedAt(row,col+2) && item.isHeal()) {
                points += 30;
            }
            else if (item.isLocatedAt(row-3,col) && item.isHeal())
            {
                points += 20;
            }
            else if (item.isLocatedAt(row+3,col) && item.isHeal())
            {
                points += 20;
            }
            else if (item.isLocatedAt(row,col-3) && item.isHeal())
            {
                points += 20;
            }
            else if (item.isLocatedAt(row,col+3) && item.isHeal()) {
                points += 20;
            }
            else if (item.isLocatedAt(row,col) && item.isMine())
            {
                points -= 50;
            }
            
        }
        for (Character teamMate:team)
        {
            if (teamMate.isLocatedAt(row,col))
            {
                points -= 500;
            }
        }

        for (Character enemy:enemyTeam)
        {
          if (enemy.isLocatedAt(row,col) && enemy.getHealth() <= health)
          {
            points += 1000;
          }
          else if (enemy.isLocatedAt(row,col) && enemy.getHealth() >= health) {
            points -= 1000;
          }
          else if (enemy.isLocatedAt(row +1,col) && enemy.getHealth() <= health)
          {
            points += 800;
          }
          else if (enemy.isLocatedAt(row -1,col) && enemy.getHealth() >= health) {
            points -= 800;
          }
          else if(enemy.isLocatedAt(row,col+1) && enemy.getHealth() <= health)
          {
            points += 800;
          }
          else if (enemy.isLocatedAt(row,col-1) && enemy.getHealth() >= health) {
            points -= 800;
          }
          else if (enemy.isLocatedAt(row +2,col) && enemy.getHealth() <= health)
          {
            points += 600;
          }
          else if (enemy.isLocatedAt(row -2,col) && enemy.getHealth() >= health) {
            points -= 600;
          }
          else if(enemy.isLocatedAt(row,col+2) && enemy.getHealth() <= health)
          {
            points += 600;
          }
          else if (enemy.isLocatedAt(row,col-2) && enemy.getHealth() >= health) {
            points -= 600;
          }
        }

        return points;
    }
}