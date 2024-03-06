package e1;

import e1.board.GameBoard;
import e1.entity.GameType;
import e1.factory.BoardFactory;
import e1.factory.ChessBoardFactory;

public class LogicsImpl implements Logics {

	private final BoardFactory boardFactory;
	public final GameBoard chessBoard;

	public LogicsImpl(int size) {
		this.boardFactory = new ChessBoardFactory();
		this.chessBoard = this.boardFactory.createBoardWithKnightAndPawnInRandomStartPosition(size);
	}

	//Only for test
	public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition){
		this.boardFactory = new ChessBoardFactory();
		this.chessBoard = this.boardFactory.createBoardWithKnightAndPawn(size, pawnPosition, knightPosition);
	}

	public boolean checkWin(){
		Pair<Integer, Integer> pawnPosition = this.chessBoard.getEntityByType(GameType.PAWN).stream().findFirst().get().getCurrentPosition();
		return (this.getCurrentKnightPosition().equals(pawnPosition));
	}

	private Pair<Integer, Integer> getCurrentKnightPosition(){
		return this.chessBoard.getEntityByType(GameType.KNIGHT).stream().findFirst().get().getCurrentPosition();
	}

	private Pair<Integer, Integer> getCurrentPawnPosition(){
		return this.chessBoard.getEntityByType(GameType.PAWN).stream().findFirst().get().getCurrentPosition();
	}
	
	@Override
	public boolean hit(int row, int col) {
		Pair<Integer, Integer> positionToMove = new Pair<Integer,Integer>(row, col);
		this.chessBoard.moveEntityToPosition(this.getCurrentKnightPosition(), positionToMove);
		return this.checkWin();
	}



	@Override
	public boolean hasKnight(int row, int col) {
		Pair<Integer, Integer> checkPosition = new Pair<Integer,Integer>(row, col);
		return this.getCurrentKnightPosition().equals(checkPosition);
	}

	@Override
	public boolean hasPawn(int row, int col) {
		Pair<Integer, Integer> checkPosition = new Pair<Integer,Integer>(row, col);
		return this.getCurrentPawnPosition().equals(checkPosition);
	}

}
