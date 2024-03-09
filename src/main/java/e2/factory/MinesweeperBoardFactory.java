package e2.factory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import e2.Pair;
import e2.board.GameBoard;
import e2.board.MinesweeperBoard;
import e2.utils.RandomizePositionGenerator;
import e2.utils.SafetyPositionRandomizer;

public class MinesweeperBoardFactory implements GameBoardFactory{

    private RandomizePositionGenerator generatorOfRandomPosition = new SafetyPositionRandomizer();

    Set<Pair<Integer, Integer>> randomMinePositions(int size, int numberOfMines){
        return Stream.generate(() -> this.generatorOfRandomPosition.getRandomPosition(size))
                     .limit(numberOfMines)
                     .collect(Collectors.toSet());
    }

    @Override
    public GameBoard createBoardWithMinesInRandomPosition(int size, int numberOfMines) throws InstantiationException{
        if(numberOfMines <= 0){
            throw new InstantiationException();  
        }
        return new MinesweeperBoard(size, this.randomMinePositions(size, numberOfMines));
    }

    @Override
    public GameBoard createBoardWithMinesInSetPosition(int size, Set<Pair<Integer, Integer>> minesPosition) throws InstantiationException{
        if(minesPosition.isEmpty()){
            throw new InstantiationException();
        }
        return new MinesweeperBoard(size, minesPosition);
    }
    
}
