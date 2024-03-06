package e1.factory;

import e1.Pair;
import e1.board.GameBoard;

public interface BoardFactory {

    GameBoard createBoardWithKnightAndPawnInRandomStartPosition(int size);

    GameBoard createBoardWithKnightAndPawn(int size, Pair<Integer,Integer> pawnPosition, Pair<Integer,Integer> knightPosition);
}
