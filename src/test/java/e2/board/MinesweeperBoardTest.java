package e2.board;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.entity.GameType;
import e2.Pair;
import e2.entity.GameEntityCell;

public class MinesweeperBoardTest {
    
    private final static int BOARD_SIZE = 5;
    private GameBoard minesweeperBoard;
    private Set<Pair<Integer, Integer>> positionOfMines;


    private void populatePositionOfMines(){
        this.positionOfMines = new HashSet<>();
        Pair<Integer, Integer> firstMinePosition = new Pair<Integer,Integer>(0, 0);
        Pair<Integer, Integer> secondMinePosition = new Pair<Integer,Integer>(4, 4);
        this.positionOfMines.addAll(Set.of(firstMinePosition, secondMinePosition));
    }

    @BeforeEach
    public void initBoard(){
        this.populatePositionOfMines();
    }

    @Test
    public void correctBoardInit(){
        assertDoesNotThrow(() -> this.minesweeperBoard = new MinesweeperBoard(BOARD_SIZE, this.positionOfMines));
    }

    @Test
    public void wrongBoardInit(){
        assertAll(
            () -> assertThrows(InstantiationException.class, () -> this.minesweeperBoard = new MinesweeperBoard(BOARD_SIZE, Set.of())),
            () -> assertThrows(InstantiationException.class, () -> this.minesweeperBoard = new MinesweeperBoard(0, this.positionOfMines))
        );
    }

    @Test
    public void correctGetCell(){
        this.correctBoardInit();
        Pair<Integer, Integer> mineCell = new Pair<Integer,Integer>(0, 0);
        Optional<GameEntityCell> result = this.minesweeperBoard.getCell(mineCell);
        assertAll(
            () -> assertTrue(result.isPresent()),
            () -> assertEquals(mineCell, result.get().getPosition()),
            () -> assertEquals(GameType.MINE, result.get().getType())
        );
    }

    @Test
    public void getCellNotInRange(){
        this.correctBoardInit();
        Pair<Integer, Integer> mineCell = new Pair<Integer,Integer>(-1, 0);
        Optional<GameEntityCell> result = this.minesweeperBoard.getCell(mineCell);
        assertFalse(result.isPresent());
    }

    private boolean isNeighbour(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint){
        return (!(firstPoint.equals(secondPoint)) && 
                Math.abs(firstPoint.getX() - secondPoint.getX()) <= 1 &&
                Math.abs(firstPoint.getY() - secondPoint.getY()) <= 1);
    }
    
    @Test
    public void correctNeighbour(){
        this.correctBoardInit();
        Pair<Integer, Integer> cellInBoard = new Pair<Integer,Integer>(2, 2);
        Set<Pair<Integer, Integer>> correctNeighbours = Set.of(
            new Pair<Integer,Integer>(1, 1),
            new Pair<Integer,Integer>(1, 2),
            new Pair<Integer,Integer>(1, 3),
            new Pair<Integer,Integer>(2, 1),
            new Pair<Integer,Integer>(2, 3),
            new Pair<Integer,Integer>(3, 1),
            new Pair<Integer,Integer>(3, 2),
            new Pair<Integer,Integer>(3, 3)
        );
        Set<Pair<Integer, Integer>> getNeighborCoordinates =
        this.minesweeperBoard.getNeighboursOfCell(cellInBoard, (i,j) -> isNeighbour(i, j)).stream().map(i -> i.getPosition()).collect(Collectors.toSet());
        assertEquals(correctNeighbours, getNeighborCoordinates);
    }
}
