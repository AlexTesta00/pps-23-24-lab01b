package e2.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.Pair;
import e2.board.GameBoard;
import e2.entity.GameType;

public class MinesweeperBoardFactoryTest {
    
    private final static int SIZE = 5;
    private final static int NUMBER_OF_MINES = 3;
    private GameBoardFactory board;

    @BeforeEach
    public void initBoard(){
        this.board = new MinesweeperBoardFactory();
    }

    @Test
    public void correctCreationWithRandomMine() throws InstantiationException{
        GameBoard result = this.board.createBoardWithMinesInRandomPosition(SIZE, NUMBER_OF_MINES);
        long numberOfMineInBoard = result.getAllEntityInBoard().stream().filter(mine -> mine.getType() == GameType.MINE).count();
        assertEquals(NUMBER_OF_MINES, numberOfMineInBoard);
    }

    @Test
    public void wrongCreationWithANegativeNumberOfMines(){
        assertThrows(InstantiationException.class, () -> this.board.createBoardWithMinesInRandomPosition(SIZE, -1));
    }

    @Test
    public void wrongCreationBecauseMinesAreMoreThanSize(){
        assertThrows(InstantiationException.class, () -> this.board.createBoardWithMinesInRandomPosition(SIZE, SIZE + 1));
    }

    @Test
    public void correctCreationWithMinesPositionSetted() throws InstantiationException{
        Set<Pair<Integer, Integer>> minesPositions = new HashSet<>();
        Pair<Integer, Integer> firstMinePosition = new Pair<Integer,Integer>(0, 0);
        Pair<Integer, Integer> secondMinePosition = new Pair<Integer,Integer>(1, 0);
        minesPositions.addAll(Set.of(firstMinePosition, secondMinePosition));
        GameBoard board = this.board.createBoardWithMinesInSetPosition(SIZE, minesPositions);
        Set<Pair<Integer, Integer>> minesPositionsInBoard = board.getAllEntityInBoard()
                                                                 .stream()
                                                                 .filter(cell -> cell.getType() == GameType.MINE)
                                                                 .map(cell -> cell.getPosition())
                                                                 .collect(Collectors.toSet());
        assertEquals(minesPositions, minesPositionsInBoard);
    }

    @Test
    public void wrongCreationWithMinesPositionSettedWithEmptySet(){
        assertThrows(InstantiationException.class, () -> this.board.createBoardWithMinesInSetPosition(SIZE, Set.of()));
    }
}
