package src.ai;

import java.util.ArrayList;
import src.base.*;

public class AllTeams {

  private static ArrayList<Team> teams = new ArrayList<Team>();

  static {
    teams.add(new TeamEasiest("Easiest AI"));
    teams.add(new TeamEasy("Easy AI"));
    teams.add(new TeamMedium("Medium AI"));
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