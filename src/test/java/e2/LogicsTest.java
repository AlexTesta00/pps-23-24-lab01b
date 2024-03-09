package e2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.entity.GameType;
import e2.utils.GameState;
import e2.entity.GameEntityCell;

public class LogicsTest {
    
    private final static int SIZE = 5;
    private Pair<Integer,Integer> positionOfMine = new Pair<Integer,Integer>(0, 0);
    private Pair<Integer, Integer> positionOfCellToReveal = new Pair<Integer,Integer>(4, 4);
    private Set<Pair<Integer, Integer>> allCellInBoard = new HashSet<>();
    private Logics logics;

    private void populateAllPossibleCells() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                Pair<Integer, Integer> currentCell = new Pair<Integer,Integer>(row, column);
                this.allCellInBoard.add(currentCell);
            }
        }
    }

    @BeforeEach
    public void init() throws InstantiationException{
        Set<Pair<Integer, Integer>> mines = new HashSet<>();
        mines.add(positionOfMine);
        this.logics = new LogicsImpl(SIZE, mines);
        this.populateAllPossibleCells();
    }

    @Test
    public void correctMinePositionAfterInit(){
        Optional<GameEntityCell> result = this.logics.getCellAtPosition(positionOfMine);
        assertAll(
            () -> assertTrue(result.isPresent()),
            () -> assertEquals(GameType.MINE, result.get().getType()),
            () -> assertEquals(this.positionOfMine, result.get().getPosition())
        );
    }

    private Set<GameEntityCell> getCellsInBoard(){
        return this.allCellInBoard.stream()
                   .map(cell -> this.logics.getCellAtPosition(cell))
                   .map(Optional::get)
                   .collect(Collectors.toSet());
    }

    @Test
    public void allCellAreUnrevealdAfterInit(){
        long cellReveald = this.getCellsInBoard()
                               .stream()
                               .filter(cell -> cell.isReveald())
                               .count();
        assertEquals(0, cellReveald);
    }

    @Test
    public void revealCell(){
        this.logics.reveal(this.positionOfCellToReveal);
        GameEntityCell cell = this.logics.getCellAtPosition(this.positionOfCellToReveal).get();
        assertAll(
            () -> assertTrue(cell.isReveald()),
            () -> assertEquals(GameType.NORMAL, cell.getType())
        );
    }

    @Test
    public void revealAllNeighbourCell(){
        this.logics.reveal(this.positionOfCellToReveal);
        long allCellsAreRevealdExceptOne = (SIZE * SIZE) - 1;
        long numberOfCellsReveald = this.getCellsInBoard().stream().filter(cell -> cell.isReveald()).count();
        assertEquals(allCellsAreRevealdExceptOne, numberOfCellsReveald);
    }

    @Test
    public void win(){
        assertEquals(GameState.WIN, this.logics.reveal(positionOfCellToReveal));
    }

    @Test
    public void lose(){
        assertEquals(GameState.LOSE, this.logics.reveal(positionOfMine));
    }
}
