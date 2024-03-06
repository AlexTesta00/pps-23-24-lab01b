package e1.board;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e1.factory.BoardFactory;
import e1.factory.ChessBoardFactory;

public class ChessBoardFactoryTest {
    
    private final static int SIZE = 5;
    private GameBoard chessBoard;
    private BoardFactory boardFactory;

    @BeforeEach
    public void initBoard(){
        this.boardFactory = new ChessBoardFactory();
        this.chessBoard = this.boardFactory.createBoardWithKnightAndPawnInRandomStartPosition(SIZE);
    }

    @Test
    public void correctInit(){
        int numberOfElementAfterInit = 2;
        assertAll(
            () -> assertFalse(this.chessBoard.isEmpty()),
            () -> assertEquals(numberOfElementAfterInit, this.chessBoard.getEntityOnBoard().size())
        );
    }
}
