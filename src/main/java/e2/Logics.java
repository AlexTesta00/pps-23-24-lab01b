package e2;

import java.util.Optional;

import e2.entity.GameEntityCell;
import e2.utils.GameState;

public interface Logics {
    
    GameState reveal(Pair<Integer, Integer> positionOfCell);
    void toogleFlagCell(Pair<Integer, Integer> positionOfCell);
    Optional<GameEntityCell> getCellAtPosition(Pair<Integer, Integer> positionOfCell);
    long getNumberOfNeighbourMines(Pair<Integer, Integer> positionOfCell);
}
