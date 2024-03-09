package e2.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.Pair;

public class GameEntityCellTest {
    
    private GameEntityCell cell;
    private final GameType cellType = GameType.NORMAL;
    private final Pair<Integer, Integer> position = new Pair<>(0, 0);

    @BeforeEach
    public void init(){
        this.cell = new GameEntityCellImpl(this.position, this.cellType);
    }

    @Test
    public void correctInit(){
        assertAll(
            () -> assertEquals(GameType.NORMAL, this.cell.getType()),
            () -> assertEquals(position, this.cell.getPosition()),
            () -> assertFalse(this.cell.isFlagged()),
            () -> assertFalse(this.cell.isReveald())
        );
    }

    @Test
    public void flagCell(){
        this.cell.flagCell();
        assertTrue(this.cell.isFlagged());
    }

    @Test
    public void revealCell(){
        this.cell.revealCell();
        assertTrue(this.cell.isReveald());
    }

    @Test
    public void revealAfterFlagCell(){
        this.flagCell();
        this.revealCell();
    }
}
