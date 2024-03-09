package e2.board;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import e2.Pair;
import e2.entity.GameEntityCell;
import e2.factory.GameEntityCellFactory;
import e2.factory.GameEntityCellFactoryImpl;

public class MinesweeperBoard implements GameBoard{

    private final int size;
    private final Set<GameEntityCell> cells;
    private final Set<Pair<Integer, Integer>> minePosition;
    private final GameEntityCellFactory cellFactory;

    private boolean isInvalidDataForBoard(int size, Set<Pair<Integer, Integer>> minePosition){
        return (size <= 0 || minePosition.isEmpty() || (size <= minePosition.size()));
    }

    public MinesweeperBoard(int size, Set<Pair<Integer, Integer>> minePosition) throws InstantiationException {
        if(this.isInvalidDataForBoard(size, minePosition)){
            throw new InstantiationException();
        }else{
            this.size = size;
            this.cells = new HashSet<>();
            this.minePosition = new HashSet<>();
            this.minePosition.addAll(minePosition);
            this.cellFactory = new GameEntityCellFactoryImpl();
            this.populateMinesweeperBoard();
        }

    }

    private void populateMinesweeperBoard(){
        for (int row = 0; row < this.size; row++) {
            for (int column = 0; column < this.size; column++) {
                Pair<Integer, Integer> positionOfCell = new Pair<Integer,Integer>(row, column);
                if(this.minePosition.contains(positionOfCell)){
                    this.cells.add(this.cellFactory.createMineCell(positionOfCell));
                }else{
                    this.cells.add(this.cellFactory.createNormalCell(positionOfCell));
                }
            }            
        }
    }

    @Override
    public int getSize() {
        return this.cells.size();
    }

    @Override
    public Optional<GameEntityCell> getCell(Pair<Integer, Integer> cellPosition) {
        return this.cells.stream().filter(i -> i.getPosition().equals(cellPosition)).findFirst();
    }

    @Override
    public Set<GameEntityCell> getNeighboursOfCell(Pair<Integer, Integer> cellPosition,
            BiPredicate<Pair<Integer, Integer>, Pair<Integer, Integer>> neighboursCriterion) {
        return this.cells.stream()
                         .filter(i -> neighboursCriterion.test(cellPosition, i.getPosition()))
                         .collect(Collectors.toSet());
    }

    @Override
    public Set<GameEntityCell> getAllEntityInBoard() {
        return Collections.unmodifiableSet(this.cells);
    }
    
}
