package src.ai;
import src.base.*;

public class TeamExtreme extends Team{

    public TeamExtreme(String teamName){
        super(teamName);
    }

    @Override
    public void initializeArena(){
        characters.add(new Extreme());
        characters.add(new Extreme());
        characters.add(new Extreme());
        characters.add(new Extreme());
        characters.add(new Extreme());
    }

    @Override
    public void initializeMaze(){
        characters.add(new Extreme());
        characters.add(new Extreme());
        characters.add(new Extreme());
        characters.add(new Extreme());
        characters.add(new Extreme());
    }
    
}
