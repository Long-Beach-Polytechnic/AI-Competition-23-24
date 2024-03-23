package src.ai;

import java.util.ArrayList;

import src.base.*;
import src.base.Character;

public class Medium extends Character {

    public Medium(Cell token, int row, int col, int hp)
    {
      super(token,row,col,hp);
    }

    public Medium(Character character)
    {
        super(character.getToken(), character.getRow(), character.getCol(), character.getHealth());
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
        return points;
   }





}