package src.ai;

import src.base.*;
import src.base.Character;

import java.util.ArrayList;

public class Extreme extends Character {

    private boolean isStrongest;
    private static int CLOSEST_ENEMY_BONUS = 500;
    private static int CLOSEST_HEAL_BONUS = 100;
  
  public Extreme()
  {
    super();
  }
  
  @Override
  public int getDirection(Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
  {
    int initialPoints = 500;
    int[] closestEnemyDirectionBonus = getClosestEnemyBonusIfStrongest(team,enemyTeam);
    int[] closestHealBonus = getClosestHealBonus(items);
    int upBonus = closestEnemyDirectionBonus[0] * CLOSEST_ENEMY_BONUS + closestHealBonus[0] * CLOSEST_HEAL_BONUS;
    int downBonus = closestEnemyDirectionBonus[1] * CLOSEST_ENEMY_BONUS + closestHealBonus[1] * CLOSEST_HEAL_BONUS;
    int leftBonus = closestEnemyDirectionBonus[2] * CLOSEST_ENEMY_BONUS + closestHealBonus[2] * CLOSEST_HEAL_BONUS;
    int rightBonus = closestEnemyDirectionBonus[3] * CLOSEST_ENEMY_BONUS + closestHealBonus[3] * CLOSEST_HEAL_BONUS;
    int bonusTowardsCenter = 100;
    if (row < map.getRows() / 4) {
        downBonus += bonusTowardsCenter;
    }
    if (row > map.getRows() / 4 + map.getRows() / 2) {
        upBonus += bonusTowardsCenter;
    }
    if (col < map.getCols() / 4) {
        rightBonus += bonusTowardsCenter;
    }
    if (col > map.getCols() / 4 + map.getCols() / 2) {
        leftBonus += bonusTowardsCenter;
    }
    int up    = pointsUp(   row, col, map, items, team, enemyTeam) + (int) (Math.random() * 10) + initialPoints + upBonus;
    int down  = pointsDown( row, col, map, items, team, enemyTeam) + (int) (Math.random() * 10) + initialPoints + downBonus;
    int left  = pointsLeft( row, col, map, items, team, enemyTeam) + (int) (Math.random() * 10) + initialPoints + leftBonus;
    int right = pointsRight(row, col, map, items, team, enemyTeam) + (int) (Math.random() * 10) + initialPoints + rightBonus;
    //System.out.println("UP:\t" + up + "\nDOWN:\t" + down + "\nLEFT:\t" + left + "\nRIGHT:\t" + right);
    
    int[] pts = {up,       down,      left,     right};
    int[] dir = {MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT};
    int max = 0;
    int direction = DONT_MOVE;
    for (int dirIndex = 0; dirIndex < pts.length; dirIndex++) {
        if (pts[dirIndex] > max) {
            max = pts[dirIndex];
            direction = dir[dirIndex];
        }
    }
    //Util.pauseConsole();
    return direction;
  }

  private int[] getClosestEnemyBonusIfStrongest(ArrayList<Character> team, ArrayList<Character> enemyTeam){
    isStrongest = true;
    int[] directionBonus = {0,0,0,0};
    for (Character teamMember:team) {
        if (this.health < teamMember.getHealth()) {
            isStrongest = false;
        }
    }
    for (Character enemCharacter:enemyTeam) {
        if (this.health < enemCharacter.getHealth()) {
            isStrongest = false;
        }
    }

    double minDistance = Integer.MAX_VALUE;
    Character closestEnemy = new Character();
    if (isStrongest) {
        for (Character enemy:enemyTeam) {
            double distToCharacter = Math.sqrt( Math.pow( this.row - enemy.getRow(),2) + Math.pow( this.col - enemy.getCol(),2));
            if (distToCharacter < minDistance) {
                minDistance = distToCharacter;
                closestEnemy = enemy;
            }
        }

        if (closestEnemy.getRow() < this.row) {
            directionBonus[0] = 1;
        }
        if (closestEnemy.getRow() > this.row) {
            directionBonus[1] = 1;
        }
        if (closestEnemy.getCol() < this.col) {
            directionBonus[2] = 1;
        }
        if (closestEnemy.getCol() > this.col) {
            directionBonus[3] = 1;
        }
    }
    return directionBonus;
  }

  private int[] getClosestHealBonus(ArrayList<Item> items) {
    int[] directionBonus = {0,0,0,0};
    if (!isStrongest) {
        double minDistance = Integer.MAX_VALUE;
        Item closestHeal = null;
        for (Item item:items) {
            if (item.isHeal()) {
                double distToHeal = Math.sqrt( Math.pow( this.row - item.getRow(),2) + Math.pow( this.col - item.getCol(),2));
                if (distToHeal < minDistance) {
                    closestHeal = item;
                    minDistance = distToHeal;
                }
            }
        }

        if (closestHeal != null) {
            if (closestHeal.getRow() < this.row) {
                directionBonus[0] = 1;
            }
            if (closestHeal.getRow() > this.row) {
                directionBonus[1] = 1;
            }
            if (closestHeal.getCol() < this.col) {
                directionBonus[2] = 1;
            }
            if (closestHeal.getCol() > this.col) {
                directionBonus[3] = 1;
            }
        }
    }
    return directionBonus;
  }

  private int pointsUp(int row, int col, Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam){
    int[] rows = {row-1, row-1, row-2, row-2, row-2, row-3};
    int[] cols = {col-1, col+1, col-1, col+1, col, col};
    return calculateMainCell(row-1, col, map, items, team, enemyTeam) +
            calculateSideCells(rows, cols, map, items, team, enemyTeam);
  }

  private int pointsDown(int row, int col, Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam){
    int[] rows = {row+1, row+1, row+2, row+2, row+2, row+3};
    int[] cols = {col-1, col+1, col-1, col+1, col,     col};
    return calculateMainCell(row+1, col, map, items, team, enemyTeam) +
            calculateSideCells(rows, cols, map, items, team, enemyTeam);
  }

  private int pointsLeft(int row, int col, Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam){
    int[] rows = {row-1, row+1, row-1, row+1, row, row};
    int[] cols = {col-1, col-1, col-2, col-2, col-2, col-3};
    return calculateMainCell(row, col-1, map, items, team, enemyTeam) +
            calculateSideCells(rows, cols, map, items, team, enemyTeam);
  }

  private int pointsRight(int row, int col, Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam){
    int[] rows = {row-1, row+1, row-1, row+1, row, row};
    int[] cols = {col+1, col+1, col+2, col+2, col+2, col+3};
    return calculateMainCell(row, col+1, map, items, team, enemyTeam) +
            calculateSideCells(rows, cols, map, items, team, enemyTeam);
  }

  private boolean isInBounds(int row, int col, Map map){
    return (row >= 0 && row < map.getRows()-1) && (col >= 0 && col < map.getCols() -1);
  }
  


  private int calculateMainCell(int cellRow, int cellCol, Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam) {
    int points = 0;
    if (isInBounds(cellRow, cellCol, map))
    {
        if (!map.getCell(cellRow,cellCol).canPass()) {
            return Integer.MIN_VALUE + 10_000;
       }
       for (Item item:items) {
           if (item.isLocatedAt(cellRow, cellCol) && item.isHeal()) {
               points += Item.HEAL * 60;
           }
           if (item.isLocatedAt(cellRow, cellCol) && item.isMine()) {
               points -= Item.MINE;
           }
       }
       for (Character enemy:enemyTeam) {
           if (enemy.isLocatedAt(cellRow, cellCol) && enemy.getHealth() < this.getHealth() ) {
               points += 1000;
           }
           if (enemy.isLocatedAt(cellRow, cellCol) && enemy.getHealth() > this.getHealth()) {
               points -= 500;
           }
       }
       for (Character teammate:team) {
           if (teammate.isLocatedAt(cellRow, cellCol)) {
               points -= 500;
           }
       }
    }
    return points;
  }

  private int calculateSideCells(int[] rows, int cols[], Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam) {
    int points = 0;
    
    for (int cellIndex = 0; cellIndex < rows.length; cellIndex++) {
        int cellRow = rows[cellIndex];
        int cellCol = cols[cellIndex];
        if (isInBounds(cellRow, cellCol, map)) {
            if (!map.getCell(cellRow,cellCol).canPass()) {
                points -= 10;
            }
            for (Item item:items) {
                if (item.isLocatedAt(cellRow, cellCol) && item.isHeal()) {
                    points += Item.HEAL * 4;
                }
                if (item.isLocatedAt(cellRow, cellCol) && item.isMine()) {
                    points -= Item.MINE;
                }
            }
            for (Character enemy:enemyTeam) {
                if (enemy.isLocatedAt(cellRow, cellCol) && enemy.getHealth() < this.getHealth() ) {
                    points += Item.HEAL;
                }
                if (enemy.isLocatedAt(cellRow, cellCol) && enemy.getHealth() > this.getHealth()) {
                    points -= Item.MINE;
                }
            }
        } 
    }
    return points;
  }


}


