package src.ai;

import java.util.ArrayList;

import src.base.Team;

public class TeamManager {

  private static ArrayList<Team> teams = new ArrayList<Team>();

  static {
    teams.add(new TeamExtreme("Extreme AI"));
    teams.add(new TeamHard("Harddd AI"));
    teams.add(new TeamCodinator("Codinators"));
    teams.add(new TeamVentis("Ventis AI"));
    teams.add(new TeamCrackrabbits("Crackrabbits"));
    teams.add(new TeamNothing("Nothing AI"));
    
  }

  public static Team getTeam(int teamIndex)
  {
    return teams.get(teamIndex);
  }

  public static int getTotalTeams()
  {
    return teams.size();
  }

  public static void sortByMostPoints()
  {
    for (int outerIndex = 0; outerIndex < teams.size()-1; outerIndex++) {
      int minIndex = outerIndex;
      for (int innerIndex = outerIndex+1; innerIndex < teams.size(); innerIndex++) {
        Team currentTeam = teams.get(innerIndex);
        if (currentTeam.getPoints() > teams.get(minIndex).getPoints()) {
          minIndex = innerIndex;
        }
      }

      Team temp = teams.get(outerIndex);
      teams.set(outerIndex, teams.get(minIndex));
      teams.set(minIndex, temp);
    }
  }


}