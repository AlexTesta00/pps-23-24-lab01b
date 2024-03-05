package e1;

public interface Movable {
    boolean move(Pair<Integer, Integer> positionToMove);
    Pair<Integer, Integer> getCurrentPosition();
}
