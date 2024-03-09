package e2.entity;

import e2.Pair;

public interface GameEntityCell{
    Pair<Integer,Integer> getPosition();
    boolean isReveald();
    boolean isFlagged();
    void revealCell();
    void flagCell();
    GameType getType();
}
