package e1.movement;

import e1.Pair;

public interface Movable {
    boolean move(Pair<Integer, Integer> positionToMove);
    Pair<Integer, Integer> getCurrentPosition();
}
