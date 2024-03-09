package e2;

import java.util.Optional;
import java.util.Set;

import e2.board.GameBoard;
import e2.entity.GameEntityCell;
import e2.entity.GameType;
import e2.factory.MinesweeperBoardFactory;
import e2.utils.GameState;

public class LogicsImpl implements Logics {

    private final static int NO_MINES_NEARBY = 0;
    private final GameBoard board;
    private final MinesweeperBoardFactory factory;

    public LogicsImpl(int size, int numberOfMines) throws InstantiationException {
        this.factory = new MinesweeperBoardFactory();
        this.board = this.factory.createBoardWithMinesInRandomPosition(size, numberOfMines);
    }

    public LogicsImpl(int size, Set<Pair<Integer, Integer>> minesPositions) throws InstantiationException {
        this.factory = new MinesweeperBoardFactory();
        this.board = this.factory.createBoardWithMinesInSetPosition(size, minesPositions);
    }

    private boolean isNeighbour(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> secondPoint){
        return (!(firstPoint.equals(secondPoint)) && 
                Math.abs(firstPoint.getX() - secondPoint.getX()) <= 1 &&
                Math.abs(firstPoint.getY() - secondPoint.getY()) <= 1);
    }

    private Set<GameEntityCell> getNeigbour(Pair<Integer, Integer> positionOfCell){
        return this.board.getNeighboursOfCell(positionOfCell, (i,j) -> isNeighbour(i, j));
    }

    @Override
    public long getNumberOfNeighbourMines(Pair<Integer, Integer> positionOfCell){
        Set<GameEntityCell> neighbours = this.getNeigbour(positionOfCell);
        return neighbours.stream().filter(cell -> cell.getType() == GameType.MINE).count();
    }

    private boolean checkWin() {
        return this.board.getAllEntityInBoard().stream()
                                               .filter(cell -> cell.getType().equals(GameType.NORMAL))
                                               .allMatch(GameEntityCell::isReveald);
    }


    @Override
    public GameState reveal(Pair<Integer, Integer> positionOfCell) {
        Optional<GameEntityCell> currentCell = this.board.getCell(positionOfCell);
        if(!currentCell.isPresent()){
            throw new IllegalStateException();
        }

        GameEntityCell cell = currentCell.get();
        cell.revealCell();


        if(cell.getType() == GameType.MINE){
            return GameState.LOSE;
        }

        long nearbyMines = this.getNumberOfNeighbourMines(positionOfCell);

        if(nearbyMines == NO_MINES_NEARBY){
            this.getNeigbour(positionOfCell).stream()
                                            .filter(neighbourCell -> !neighbourCell.isReveald())
                                            .forEach(neighbourCell -> reveal(neighbourCell.getPosition()));
        }

        if(checkWin()){
            return GameState.WIN;
        }

        return GameState.NONE;
    }

    @Override
    public void toogleFlagCell(Pair<Integer, Integer> positionOfCell) {
        this.board.getCell(positionOfCell).ifPresent(cell -> cell.flagCell());
    }

    @Override
    public Optional<GameEntityCell> getCellAtPosition(Pair<Integer, Integer> positionOfCell) {
        Optional<GameEntityCell> cell = this.board.getCell(positionOfCell);
        return (cell.isPresent()) ? cell : Optional.empty();
    }

}
