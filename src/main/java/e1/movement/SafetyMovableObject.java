package e1.movement;

import java.util.function.BiPredicate;

import e1.Pair;

public class SafetyMovableObject implements Movable{

    private BasicMovableObject movable;
    private BiPredicate<Pair<Integer, Integer>, Pair<Integer, Integer>> criterion;

    public SafetyMovableObject(Pair<Integer, Integer> initPosition, BiPredicate<Pair<Integer, Integer>, Pair<Integer, Integer>> moveLogics){
        this.movable = new BasicMovableObject(initPosition);
        this.criterion = moveLogics;
    }

    @Override
    public boolean move(Pair<Integer, Integer> positionToMove) {
        if(this.criterion.test(this.getCurrentPosition(), positionToMove)){
            this.movable.move(positionToMove);
            return true;
        }
        return false;
    }

    @Override
    public Pair<Integer, Integer> getCurrentPosition() {
        return this.movable.getCurrentPosition();
    }

    
}