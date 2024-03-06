package e1.board;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import e1.Pair;
import e1.entity.GameEntity;
import e1.entity.GameType;

public class ChessBoard implements GameBoard {

    private final static int MINIMUM = 0;
    private int size;
    private HashSet<GameEntity> itemsOnBoard;

    public ChessBoard(int size){
        this.size = size;
        this.itemsOnBoard = new HashSet<>();
    }

    @Override
    public boolean isEmpty() {
        return this.itemsOnBoard.isEmpty();
    }

    private boolean checkXAxesInRange(Pair<Integer, Integer> axes){
        return (axes.getX() >= MINIMUM && axes.getX() < size);
    }

    private boolean checkYAxesInRange(Pair<Integer, Integer> axes){
        return (axes.getY() >= MINIMUM && axes.getY() < size);
    }

    private boolean isEntityInRange(GameEntity gameEntity){
        Pair<Integer, Integer> currentPosition = gameEntity.getCurrentPosition();
        return (checkXAxesInRange(currentPosition) && checkYAxesInRange(currentPosition));
    }

	@Override
	public void add(GameEntity gameEntity) {
        if(!isEntityInRange(gameEntity)){
            throw new IndexOutOfBoundsException();
        }else if(this.itemsOnBoard.contains(gameEntity)){
            throw new IllegalStateException();
        }else{
            this.itemsOnBoard.add(gameEntity);
        }
	}

	@Override
	public void removeEntity(Pair<Integer, Integer> entityPosition) {
        Optional<GameEntity> chessEntity = this.getEntityByCoordinates(entityPosition);
        if(chessEntity.isPresent()){
            this.itemsOnBoard.remove(chessEntity.get());
        }else{
            throw new IllegalStateException();
        }
	}

	@Override
	public Optional<GameEntity> getEntityByCoordinates(Pair<Integer, Integer> entityPosition) {
        return this.itemsOnBoard.stream()
                                .filter(i -> i.getCurrentPosition().equals(entityPosition))
                                .findFirst();
	}

	@Override
	public boolean moveEntityToPosition(Pair<Integer, Integer> knight, Pair<Integer, Integer> moveToPosition) {
        Optional<GameEntity> entity = this.getEntityByCoordinates(knight);
        if(!(this.checkXAxesInRange(moveToPosition) && this.checkYAxesInRange(moveToPosition))){
            throw new IndexOutOfBoundsException();
        }
        if(entity.isPresent()){
            return entity.get().move(moveToPosition);
        }
        return false;
	}

	@Override
	public Set<GameEntity> getEntityOnBoard() {
        return Collections.unmodifiableSet(this.itemsOnBoard);
	}

	@Override
	public Set<GameEntity> getEntityByType(GameType typeOfEntity) {
        return this.itemsOnBoard.stream().filter(i -> i.getType().equals(typeOfEntity)).collect(Collectors.toSet());
	}

}
