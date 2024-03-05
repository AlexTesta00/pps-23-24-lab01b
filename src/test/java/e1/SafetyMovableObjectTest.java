package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BiPredicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/* TODO: Refactor this class */
public class SafetyMovableObjectTest {
    
    
    private final static int INIT_X = 3;
    private final static int INIT_Y = 2;
    private final Pair<Integer,Integer> initPosition = new Pair<Integer,Integer>(INIT_X, INIT_Y);
    private Movable movable;

    private final BiPredicate<Integer, Integer> knightCriterion = (x, y) ->{
        return (x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3) ? true : false;
    };

    private final BiPredicate<Integer, Integer> pawnCriterion = (x, y) ->{
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
      int correctXPosition = 1;
      int correctYPosition = 2;
      Pair<Integer, Integer> correctPosition = new Pair<>(correctXPosition, correctYPosition);
      boolean resultOfMove = this.movable.move(correctPosition);
      Pair<Integer, Integer> currentKnightPositionAfterMove = this.movable.getCurrentPosition();
      assertAll(
        () -> assertEquals(correctPosition, currentKnightPositionAfterMove),
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
