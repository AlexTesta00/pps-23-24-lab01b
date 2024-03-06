package e1.board;

import java.util.Optional;
import java.util.Set;

import e1.Pair;
import e1.entity.GameEntity;
import e1.entity.GameType;

public interface GameBoard {

    boolean isEmpty();

    void add(GameEntity gameEntity);

    void removeEntity(Pair<Integer, Integer> entityPosition);

    Optional<GameEntity> getEntityByCoordinates(Pair<Integer, Integer> entityPosition);

    boolean moveEntityToPosition(Pair<Integer, Integer> knight, Pair<Integer, Integer> moveToPosition);

	Set<GameEntity> getEntityOnBoard();

    Set<GameEntity> getEntityByType(GameType typeOfEntity);

}
