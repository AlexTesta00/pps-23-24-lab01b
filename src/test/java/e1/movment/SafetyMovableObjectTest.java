package e1.movment;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BiPredicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e1.Pair;
import e1.movement.Movable;
import e1.movement.SafetyMovableObject;


public class SafetyMovableObjectTest {
    
    
    private final static int INIT_X = 2;
    private final static int INIT_Y = 0;
    private final Pair<Integer,Integer> initPosition = new Pair<Integer,Integer>(INIT_X, INIT_Y);
    private Movable movable;

    private final BiPredicate<Pair<Integer, Integer>, Pair<Integer, Integer>> knightCriterion = (starterPoint, arrivePoint) ->{
        int distanceOfX = starterPoint.getX() - arrivePoint.getX();
        int distanceOfY = starterPoint.getY() - arrivePoint.getY();
        return (distanceOfX != 0 && distanceOfY != 0 && Math.abs(distanceOfX) + Math.abs(distanceOfY) == 3);
    };

    private final BiPredicate<Pair<Integer, Integer>, Pair<Integer, Integer>> pawnCriterion = (starterPoint, arrivePoint) ->{
        return false;
    };

    @BeforeEach
    public void init(){
        this.movable = new SafetyMovableObject(new Pair<Integer,Integer>(INIT_X, INIT_Y), knightCriterion);
    }

    @Test
    public void correctInit(){
        assertEquals(this.initPosition, this.movable.getCurrentPosition());
    }

    @Test
    public void checkCorrectKnightMove() {
      int correctXPositionArrive = 3;
      int correctYPositionArrive = 2;
      Pair<Integer, Integer> correctArrivePosition = new Pair<>(correctXPositionArrive, correctYPositionArrive);
      boolean resultOfMove = this.movable.move(correctArrivePosition);
      Pair<Integer, Integer> currentKnightPositionAfterMove = this.movable.getCurrentPosition();
      assertAll(
        () -> assertEquals(correctArrivePosition, currentKnightPositionAfterMove),
        () -> assertTrue(resultOfMove)
      );
    }

    @Test
    public void checkIncorrectKnightMove(){
        int incorrectXPosition = 1;
        int incorrectYPosition = 1;
        Pair<Integer, Integer> incorrectPosition = new Pair<>(incorrectXPosition, incorrectYPosition);
        boolean resultOfMove = this.movable.move(incorrectPosition);
        Pair<Integer, Integer> currentKnightPositionAfterMove = this.movable.getCurrentPosition();
        assertAll(
          () -> assertNotEquals(incorrectPosition, currentKnightPositionAfterMove),
          () -> assertFalse(resultOfMove)
        );
    }

    @Test
    public void checkNotMoveFromUnmovabeObject(){
        int randomX = 1;
        int randomY = 2;
        this.movable = new SafetyMovableObject(initPosition, pawnCriterion);
        Pair<Integer, Integer> positionToMove = new Pair<Integer,Integer>(randomX, randomY);
        boolean resultOfMove = this.movable.move(positionToMove);
        Pair<Integer, Integer> currentPawnPosition = this.movable.getCurrentPosition();
        assertAll(
            () -> assertNotEquals(positionToMove, currentPawnPosition),
            () -> assertFalse(resultOfMove)
        );
    }
}
