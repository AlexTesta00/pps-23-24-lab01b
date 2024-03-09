package e2.entity;

import e2.Pair;

public class GameEntityCellImpl implements GameEntityCell {

    private final Pair<Integer, Integer> currentPosition;
    private final GameType type;
    private boolean isFlagged;
    private boolean isReveald;

    public GameEntityCellImpl(e2.Pair<Integer, Integer> position, GameType type) {
        this.currentPosition = position;
        this.type = type;
        this.isFlagged = false;
        this.isReveald = false;
    }

    @Override
    public e2.Pair<Integer, Integer> getPosition() {
        return this.currentPosition;
    }

    @Override
    public boolean isReveald() {
        return this.isReveald;
    }

    @Override
    public boolean isFlagged() {
        return this.isFlagged;
    }

    @Override
    public void revealCell() {
        this.isReveald = true;
    }

    @Override
    public void flagCell() {
        this.isFlagged = !this.isFlagged;
    }

    @Override
    public e2.entity.GameType getType() {
        return this.type;
    }
    
}
