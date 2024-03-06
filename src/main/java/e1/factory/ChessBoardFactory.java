package e1.factory;

import e1.Pair;
import e1.board.ChessBoard;
import e1.board.GameBoard;
import e1.movement.RandomizePositionGenerator;
import e1.movement.SafetyPositionRandomizer;

public class ChessBoardFactory implements BoardFactory{

    private GameEntityFactory chessFactory = new ChessEntityFactory();

    @Override
    public GameBoard createBoardWithKnightAndPawnInRandomStartPosition(int size) {
        RandomizePositionGenerator randomGenerator = new SafetyPositionRandomizer();
        Pair<Integer, Integer> pawnStarterPosition = randomGenerator.getRandomPosition(size);
        Pair<Integer, Integer> knightStarterPosition = randomGenerator.getRandomPosition(size);
        GameBoard chessBoard = new ChessBoard(size);
        chessBoard.add(chessFactory.createPawn(pawnStarterPosition));
        chessBoard.add(chessFactory.createKnight(knightStarterPosition));
        return chessBoard;
    }

    @Override
    public GameBoard createBoardWithKnightAndPawn(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition) {
        GameBoard chessBoard = new ChessBoard(size);
        chessBoard.add(chessFactory.createPawn(pawnPosition));
        chessBoard.add(chessFactory.createKnight(knightPosition));
        return chessBoard;
    }
    
}
