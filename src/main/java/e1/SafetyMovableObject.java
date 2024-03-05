package e1;

import java.util.function.BiPredicate;

public class SafetyMovableObject implements Movable{

    private BasicMovableObject movable;
    private BiPredicate<Integer, Integer> criterion;

    public SafetyMovableObject(Pair<Integer, Integer> initPosition, BiPredicate<Integer, Integer> moveLogics){
        this.movable = new BasicMovableObject(initPosition);
        this.criterion = moveLogics;
    }

    @Override
    public boolean move(Pair<Integer, Integer> positionToMove) {
        if(this.criterion.test(positionToMove.getX(), positionToMove.getY())){
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