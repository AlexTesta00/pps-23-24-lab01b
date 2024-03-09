package e2.factory;

import e2.Pair;
import e2.entity.GameEntityCell;
import e2.entity.GameEntityCellImpl;
import e2.entity.GameType;

public class GameEntityCellFactoryImpl implements GameEntityCellFactory{

    @Override
    public GameEntityCell createMineCell(Pair<Integer, Integer> positionOfMine) {
        return new GameEntityCellImpl(positionOfMine, GameType.MINE);
    }

    @Override
    public GameEntityCell createNormalCell(Pair<Integer, Integer> positionOfCell) {
        return new GameEntityCellImpl(positionOfCell, GameType.NORMAL);
    }
    
}
