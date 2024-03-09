package e2.factory;

import e2.Pair;
import e2.entity.GameEntityCell;

public interface GameEntityCellFactory {
    
    GameEntityCell createMineCell(Pair<Integer, Integer> positionOfMine);
    GameEntityCell createNormalCell(Pair<Integer, Integer> positionOfCell);
}
