package e2.utils;

import java.util.HashSet;

import e2.Pair;

public class SafetyPositionRandomizer implements RandomizePositionGenerator{

    private BasicPositionRandomizer basicPositionRandomizer = new BasicPositionRandomizer();
    private HashSet<Pair<Integer, Integer>> positionGenerated = new HashSet<>();

    @Override
    public Pair<Integer, Integer> getRandomPosition(int exclusiveMax) {
        Pair<Integer, Integer> position = this.basicPositionRandomizer.getRandomPosition(exclusiveMax);
        if(this.positionGenerated.contains(position)){
            this.getRandomPosition(exclusiveMax);
        }
        this.positionGenerated.add(position);
        return position;
    }
    
}
