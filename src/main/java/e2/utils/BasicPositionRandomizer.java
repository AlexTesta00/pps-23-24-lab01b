package e2.utils;

import java.util.Random;

import e2.Pair;

public class BasicPositionRandomizer implements RandomizePositionGenerator{

    private final int MINIMUM = 0;
    private final Random random = new Random();

    @Override
    public Pair<Integer, Integer> getRandomPosition(int exclusiveMax) {
        int x = this.random.nextInt(MINIMUM, exclusiveMax);
        int y = this.random.nextInt(MINIMUM, exclusiveMax);
        return new Pair<>(x, y);
    }

}
