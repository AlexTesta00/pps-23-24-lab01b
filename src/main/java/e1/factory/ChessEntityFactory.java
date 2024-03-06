package e1.factory;

import java.util.function.BiPredicate;

import e1.Pair;
import e1.entity.ChessEntity;
import e1.entity.GameEntity;
import e1.entity.GameType;

public class ChessEntityFactory implements GameEntityFactory{

    private final BiPredicate<Integer, Integer> pawnCriterion = (x, y) ->{
        return false;
    };

    private final BiPredicate<Integer, Integer> knightCriterion = (x, y) ->{
        return (x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3) ? true : false;
    };

    @Override
    public GameEntity createPawn(Pair<Integer, Integer> starterPosition) {
        return new ChessEntity(starterPosition, pawnCriterion, GameType.PAWN);
    }

    @Override
    public GameEntity createKnight(Pair<Integer, Integer> starterPosition) {
        return new ChessEntity(starterPosition, knightCriterion, GameType.KNIGHT);
    }
    
}
