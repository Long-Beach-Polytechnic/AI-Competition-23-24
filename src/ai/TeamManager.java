package src.ai;

import java.util.ArrayList;

import src.base.Team;

public class TeamManager {

  private static ArrayList<Team> teams = new ArrayList<Team>();

  static {
    teams.add(new TeamCodinator("Codinators"));
    teams.add(new TeamVentis("Ventis AI"));
    teams.add(new TeamCrackrabbits("Crackrabbits"));
    teams.add(new TeamNothing("Nothing AI"));
    teams.add(new TeamHard("Hard AI"));
  }

  public static Team getTeam(int teamIndex)
  {
    return teams.get(teamIndex);
  }

  public static int getTotalTeams()
  {
    return teams.size();
  }


}