package src.ai;

import src.base.*;
import src.base.Character;

import java.util.ArrayList;

public class Codinator extends Character {

    public Codinator()
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
                points -= 200;
            }
            if (item.isLocatedAt(row,col) && item.isHeal())
            {
                points += 1000;
            }
        }
        for (Character teamMate:team)
        {
            if (teamMate.isLocatedAt(row,col))
            {
                points -= 500;
            }
        }
        //Target enemies with less health
        for (Character enemy : enemyTeam)
        {
            if (enemy.getHealth() < this.getHealth())
            {
                points += 700;
            }
        }
        //Stay away from borders
        if (row <= 1 || row >= map.getRows() - 2 || col <= 1 || col >= map.getCols() - 2)
        {
            points -= 500;
        }
        // Avoid player if they are behind the mine 
        for (Character enemy : enemyTeam)
        for (Item item:items)
        if (enemy.isLocatedAt(row,col) && item.isMine())
        {
            points -= 500;
        }

        return points;
    }





}