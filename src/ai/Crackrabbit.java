package src.ai;

import src.base.*;
import src.base.Character;

import java.util.ArrayList;

public class Crackrabbit extends Character {
    
  public Crackrabbit()
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
        for (Item item:items)
        {
            if (item.isLocatedAt(row,col) && item.isMine())
            {
                points -= 50;
            }
            if (item.isLocatedAt(row,col) && item.isHeal())
            {
                points += 60;
            }
            if (item.isLocatedAt(row-1,col) && item.isHeal() && health >= 32)
            {
                points += 55;
            }
            if (item.isLocatedAt(row,col-1) && item.isHeal() && health >= 32)
            {
                points += 55;
            }
            if (item.isLocatedAt(row,col+1) && item.isHeal() && health >= 32)
            {
                points += 55;
            }
            if (item.isLocatedAt(row+1,col) && item.isHeal() && health >= 32)
            {
                points += 55;
            }

            if (item.isLocatedAt(row-2,col) && item.isHeal() && item.isLocatedAt(row-1,col) && !item.isMine() )
            {
                points += 52;
            }
            if (item.isLocatedAt(row,col-2) && item.isHeal() && item.isLocatedAt(row,col-1) && !item.isMine() )
            {
                points += 52;
            }
            if (item.isLocatedAt(row,col+2) && item.isHeal() && item.isLocatedAt(row,col+1) && !item.isMine() )
            {
                points += 52;
            }
            if (item.isLocatedAt(row+2,col) && item.isHeal() && item.isLocatedAt(row+1,col) && !item.isMine() )
            {
                points += 52;
            }

            if (item.isLocatedAt(row-3,col) && item.isHeal() && item.isLocatedAt(row-1,col) && !item.isMine() )
            {
                points += 50;
            }
            if (item.isLocatedAt(row,col-3) && item.isHeal() && item.isLocatedAt(row,col-1) && !item.isMine() )
            {
                points += 50;
            }
            if (item.isLocatedAt(row,col+3) && item.isHeal() && item.isLocatedAt(row,col+1) && !item.isMine() )
            {
                points += 50;
            }
            if (item.isLocatedAt(row+3,col) && item.isHeal() && item.isLocatedAt(row+1,col) && !item.isMine() )
            {
                points += 50;
            }
            
            
        }
        for (Character teamMate:team)
        {
            if (teamMate.isLocatedAt(row,col))
            {
                points -= 500;
            }
        }
        for (Character enemyCharacter:enemyTeam)
        {
            if (enemyCharacter.isLocatedAt(row,col) && this.health > enemyCharacter.getHealth() )
            {
                points += 500;
            }
            if (enemyCharacter.isLocatedAt(row+1,col) && this.health >=35)
            {
                points += 200;
            }
            if (enemyCharacter.isLocatedAt(row-1,col) && this.health >=35)
            {
                points += 200;
            }
            if (enemyCharacter.isLocatedAt(row,col-1) && this.health >=35)
            {
                points += 200;
            }
            if (enemyCharacter.isLocatedAt(row,col+1) && this.health >=35)
            {
                points += 200;
            }

            if (enemyCharacter.isLocatedAt(row-2,col) && this.health >=75)
            {
                points += 51;
            }
            if (enemyCharacter.isLocatedAt(row,col-2) && this.health >=75)
            {
                points += 51;
            }
            if (enemyCharacter.isLocatedAt(row,col+2) && this.health >=75)
            {
                points += 51;
            }
            if (enemyCharacter.isLocatedAt(row+2,col) && this.health >=75)
            {
                points += 51;
            }
        }
        return points;

    }
}