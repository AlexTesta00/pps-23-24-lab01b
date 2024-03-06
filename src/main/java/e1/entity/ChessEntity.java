package e1.entity;

import java.util.function.BiPredicate;

import e1.Pair;
import e1.movement.SafetyMovableObject;

public class ChessEntity implements GameEntity{

    private final SafetyMovableObject movable;
    private final GameType type;

    public ChessEntity(Pair<Integer, Integer> initPosition, BiPredicate<Integer, Integer> movementLogics, GameType type){
        this.movable = new SafetyMovableObject(initPosition, movementLogics);
        this.type = type;
    }

    @Override
    public GameType getType() {
        return this.type;
    }

    @Override
    public boolean move(Pair<Integer, Integer> positionToMove) {
        return this.movable.move(positionToMove);
    }

    @Override
    public Pair<Integer, Integer> getCurrentPosition() {
        return this.movable.getCurrentPosition();
    }
    
}
