package e1.movement;

import e1.Pair;

public interface RandomizePositionGenerator {

    Pair<Integer, Integer> getRandomPosition(int exclusiveMax);

}
