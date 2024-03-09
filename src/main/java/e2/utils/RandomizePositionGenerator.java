package e2.utils;

import e2.Pair;

public interface RandomizePositionGenerator {

    Pair<Integer, Integer> getRandomPosition(int exclusiveMax);

}
