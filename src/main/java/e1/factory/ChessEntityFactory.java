package e1.factory;

import java.util.function.BiPredicate;

import e1.Pair;
import e1.entity.ChessEntity;
import e1.entity.GameEntity;
import e1.entity.GameType;

public class ChessEntityFactory implements GameEntityFactory{

    private final BiPredicate<Pair<Integer, Integer>, Pair<Integer, Integer>> knightCriterion = (starterPoint, arrivePoint) ->{
        int distanceOfX = starterPoint.getX() - arrivePoint.getX();
        int distanceOfY = starterPoint.getY() - arrivePoint.getY();
        return (distanceOfX != 0 && distanceOfY != 0 && Math.abs(distanceOfX) + Math.abs(distanceOfY) == 3);
    };

    private final BiPredicate<Pair<Integer, Integer>, Pair<Integer, Integer>> pawnCriterion = (starterPoint, arrivePoint) ->{
        return false;
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
