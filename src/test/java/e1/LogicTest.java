package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiPredicate;

public class LogicTest {

  private final static int SIZE = 5;
  private Logics logics;
  private Pair<Integer, Integer> pawnPosition;
  private Pair<Integer, Integer> knightPosition;


  /* TODO: Return Optional<Pair<Integer,Integer>> */
  private Pair<Integer, Integer> getPawnPosition(BiPredicate<Integer, Integer> predicate) {
    for (int row = 0; row < SIZE; row++) {
      for (int column = 0; column < SIZE; column++) {
        if (predicate.test(row, column)) {
          return new Pair<Integer, Integer>(row, column);
        }
      }
    }
    return new Pair<Integer, Integer>(null, null);
  }

  private boolean isInCorrectPosition(Pair<Integer, Integer> position){
    int minPosition = 0;
    int maxPosition = SIZE - 1;
    return ((position.getX() >= minPosition && position.getX() <= maxPosition) &&
            (position.getY() >= minPosition && position.getY() <= maxPosition)) 
            ? true : false;
  }

  private boolean isPositionEmpty(Pair<Integer, Integer> position){
    return (position.getX() != null && position.getY() != null) ? true : false;
  }

  @BeforeEach
  public void init() {
    this.logics = new LogicsImpl(SIZE);
    this.pawnPosition = this.getPawnPosition((row, column) -> this.logics.hasPawn(row, column));
    this.knightPosition = this.getPawnPosition((row, column) -> this.logics.hasKnight(row, column));
  }

  @Test
  public void correctInitSpawnPosition(){
    assertAll(
      () -> assertTrue(isInCorrectPosition(this.knightPosition)),
      () -> assertTrue(isInCorrectPosition(this.pawnPosition))
    );
  }

  @Test
  public void notInWrongPosition(){
    assertAll(
      () -> assertFalse(!isPositionEmpty(this.knightPosition)),
      () -> assertFalse(!isPositionEmpty(this.pawnPosition))
    );
  }

  @Test
  public void differentInitPosition() {
    assertTrue(pawnPosition != knightPosition);
  }

  /*
   * Introduce a constructor in Logics Impl to init the position of knight and pawn
   * Logics Test can be abstract class and have a abstract method checkwin
   * 
  */
}
