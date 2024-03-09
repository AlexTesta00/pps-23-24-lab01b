package e2.board;

import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;

import e2.Pair;
import e2.entity.GameEntityCell;

public interface GameBoard {

    int getSize();
    Optional<GameEntityCell> getCell(Pair<Integer,Integer> cellPosition);
    Set<GameEntityCell> getNeighboursOfCell(Pair<Integer,Integer> cellPosition, BiPredicate<Pair<Integer, Integer>, Pair<Integer, Integer>> neighboursCriterion);
    Set<GameEntityCell> getAllEntityInBoard();
}
