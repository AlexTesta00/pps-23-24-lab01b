package e1.factory;

import e1.Pair;
import e1.entity.GameEntity;

public interface GameEntityFactory {

    GameEntity createPawn(Pair<Integer, Integer> starterPosition);
    GameEntity createKnight(Pair<Integer, Integer> starterPosition);
}
