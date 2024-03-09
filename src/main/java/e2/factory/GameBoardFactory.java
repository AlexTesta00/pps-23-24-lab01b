package e2.factory;

import java.util.Set;

import e2.Pair;
import e2.board.GameBoard;

public interface GameBoardFactory {
    
    GameBoard createBoardWithMinesInRandomPosition(int size, int numberOfMines) throws InstantiationException;
    GameBoard createBoardWithMinesInSetPosition(int size, Set<Pair<Integer, Integer>> minesPosition) throws InstantiationException;
}
