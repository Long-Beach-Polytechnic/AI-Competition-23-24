package src.base;

import src.ai.TeamManager;

public class SimulationRunner {

    public void run()
    {
        for (int team1index = 0; team1index < TeamManager.getTotalTeams(); team1index++)
        {
            for (int team2index = team1index+1; team2index < TeamManager.getTotalTeams(); team2index++)
            {
                Team team1 = TeamManager.getTeam(team1index);
                Team team2 = TeamManager.getTeam(team2index);

                if (team1 != team2)
                {
                    Game game = new Game(team1,team2);
                    game.run();
                }
            }
        }

        System.out.println("\n\nRESULTS");
        for (int teamIndex = 0; teamIndex < TeamManager.getTotalTeams(); teamIndex++)
        {
            Team currentTeam = TeamManager.getTeam(teamIndex);
            System.out.println("Team:\t" + currentTeam.getName() + "\t" + 
                                    currentTeam.getWins() + " W\t" +
                                    currentTeam.getLosses() + " L\t" +
                                    currentTeam.getTies() + " T\t" +
                                    currentTeam.getNumKills() + " Kills " +
                                    currentTeam.getPoints() + " points"
                                );
        }
    }


}