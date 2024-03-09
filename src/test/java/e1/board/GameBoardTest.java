package e1.board;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e1.Pair;
import e1.entity.GameEntity;
import e1.entity.GameType;
import e1.factory.ChessEntityFactory;
import e1.factory.GameEntityFactory;

public class GameBoardTest {
    
    private final static int SIZE_OF_BOARD = 5;
    GameBoard chessBoard;
    GameEntityFactory factory;
    
    @BeforeEach
    public void initBoard(){
        this.chessBoard = new ChessBoard(SIZE_OF_BOARD);
        this.factory = new ChessEntityFactory();
    }

    @Test
    public void emptyBoardOnStart(){
        assertTrue(this.chessBoard.isEmpty());
    }

    private GameEntity populateBoardWithPawn(int startX, int startY){
        Pair<Integer, Integer> startPawnPosition = new Pair<Integer,Integer>(startX, startY);
        GameEntity pawnEntity = this.factory.createPawn(startPawnPosition);
        this.chessBoard.add(pawnEntity);
        return pawnEntity;
    }

    private GameEntity populateBoardWithKnight(int startX, int startY){
        Pair<Integer, Integer> startPawnPosition = new Pair<Integer,Integer>(startX, startY);
        GameEntity knightEntity = this.factory.createKnight(startPawnPosition);
        this.chessBoard.add(knightEntity);
        return knightEntity;
    }

    @Test
    public void addElementOnBoard(){
        int startX = 0;
        int startY = 0;
        populateBoardWithPawn(startX, startY);
        assertTrue(!this.chessBoard.isEmpty());
    }

    @Test
    public void incorrectAddOnBoard(){
        int startX = -1;
        int startY = 0;
        assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class, () -> populateBoardWithPawn(startX, startY)),
            () -> assertTrue(this.chessBoard.isEmpty())
        );
    }

    @Test
    public void correctEntityRemove(){
        int startX = 4;
        int startY = 4;
        GameEntity pawn = populateBoardWithPawn(startX, startY);
        this.chessBoard.removeEntity(pawn.getCurrentPosition());
        assertTrue(this.chessBoard.isEmpty());
    }

    @Test
    public void incorrectEntityRemove(){
        int startX = 3;
        int startY = 2;
        Pair<Integer, Integer> incorrectPosition = new Pair<Integer,Integer>(startY, startX);
        populateBoardWithPawn(startX, startY);
        assertAll(
            () -> assertThrows(IllegalStateException.class, () -> this.chessBoard.removeEntity(incorrectPosition)),
            () -> assertTrue(!this.chessBoard.isEmpty())
        );
    }

    @Test
    public void moveEntity(){
        int startX = 2;
        int startY = 0;
        Pair<Integer, Integer> moveToPosition = new Pair<Integer,Integer>(3, 2);
        GameEntity knight = this.populateBoardWithKnight(startX, startY);
        assertTrue(this.chessBoard.moveEntityToPosition(knight.getCurrentPosition(), moveToPosition));
    }

    @Test
    public void getAllEntityInBoard(){
        int startXPawn = 0;
        int startYPawn = 0;
        int startXKnight = 3;
        int startYknight = 2;
        GameEntity pawn = this.populateBoardWithPawn(startXPawn, startYPawn);
        GameEntity knight = this.populateBoardWithKnight(startXKnight, startYknight);
        Set<GameEntity> correctSet = new HashSet<>();
        correctSet.add(pawn);
        correctSet.add(knight);
        assertEquals(correctSet, this.chessBoard.getEntityOnBoard());
    }

    @Test
    public void getEntityByType(){
        int startXPawn = 0;
        int startYPawn = 0;
        GameEntity pawn = this.populateBoardWithPawn(startXPawn, startYPawn);
        Set<GameEntity> setWithPawn = new HashSet<>();
        setWithPawn.add(pawn);
        assertEquals(setWithPawn, this.chessBoard.getEntityByType(GameType.PAWN));
    }
}
