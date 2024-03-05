package e1;

public class BasicMovableObject implements Movable{

    private Pair<Integer, Integer> position;

    public BasicMovableObject(Pair<Integer, Integer> initPosition){
        this.position = initPosition;
    }

    @Override
    public boolean move(Pair<Integer, Integer> positionToMove) {
        this.position = positionToMove;
        return true;
    }

    @Override
    public Pair<Integer, Integer> getCurrentPosition() {
        return this.position;
    }
    
}
