package src.ai;

import java.util.ArrayList;

import src.base.*;
import src.base.Character;

public class Medium extends Character {

    public Medium(Character character)
    {
        super(character.getToken(), character.getRow(), character.getCol(), character.getHealth());
    }

    @Override
    public int getDirection(Map map, ArrayList<Item> items, ArrayList<Character> team, ArrayList<Character> enemyTeam)
   {
        return MOVE_LEFT;
   }



}