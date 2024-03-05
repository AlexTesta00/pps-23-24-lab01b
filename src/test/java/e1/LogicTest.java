package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.function.BiPredicate;

public class LogicTest {

  private final static int SIZE = 5;
  private Logics logics;
  private Pair<Integer, Integer> pawnPosition;
  private Pair<Integer, Integer> knightPosition;
  private final static int PAWN_X = 3;
  private final static int PAWN_Y = 2;
  private final static int KNIGHT_X = 2;
  private final static int KNIGHT_Y = 0;

  private Optional<Pair<Integer, Integer>> getEntityPosition(BiPredicate<Integer, Integer> predicate) {
    for (int row = 0; row < SIZE; row++) {
      for (int column = 0; column < SIZE; column++) {
        if (predicate.test(row, column)) {
          return Optional.of(new Pair<Integer, Integer>(row, column));
        }
      }
    }
    return Optional.empty();
  }

  private Optional<Pair<Integer, Integer>> getCurrentKnightPosition() {
    return this.getEntityPosition((row,column) -> this.logics.hasKnight(row, column));
  }

  private boolean isInCorrectPosition(Pair<Integer, Integer> position) {
    int minPosition = 0;
    int maxPosition = SIZE - 1;
    return ((position.getX() >= minPosition && position.getX() <= maxPosition) &&
        (position.getY() >= minPosition && position.getY() <= maxPosition)) ? true : false;
  }

  private Optional<Pair<Integer, Integer>> getPawnCurrentPosition() {
    return this.getEntityPosition((row, column) -> this.logics.hasPawn(row, column));
  }

  private boolean isEntityExist(Optional<Pair<Integer, Integer>> position) {
    return position.isPresent();
  }

  @BeforeEach
  public void init() {
    this.logics = new LogicsImpl(SIZE);
    this.pawnPosition = getPawnCurrentPosition().get();
    this.knightPosition = getCurrentKnightPosition().get();
  }

  @Test
  public void correctInitSpawnPosition() {
    assertAll(
        () -> assertTrue(isInCorrectPosition(this.knightPosition)),
        () -> assertTrue(isInCorrectPosition(this.pawnPosition)));
  }

  @Test
  public void checkEntityExistance() {
    assertAll(
        () -> assertFalse(!isEntityExist(this.getCurrentKnightPosition())),
        () -> assertFalse(!isEntityExist(this.getPawnCurrentPosition())));
  }

  @Test
  public void differentInitPosition() {
    assertTrue(this.pawnPosition != this.knightPosition);
  }

  @Test
  public void wrongHintMovment() {
    int wrongX = -1;
    int wrongY = -1;
    assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(wrongX, wrongY));
  }

  private void generatePossibleSituation(){
    this.pawnPosition = new Pair<Integer,Integer>(PAWN_X, PAWN_Y);
    this.knightPosition = new Pair<Integer,Integer>(KNIGHT_X, KNIGHT_Y);
    this.logics = new LogicsImpl(SIZE, this.pawnPosition, this.knightPosition);
  }

  @Test
  public void checkwin(){
    this.generatePossibleSituation();
    assertTrue(this.logics.hit(PAWN_X, PAWN_Y));
  }

  //TODO: Implement test to move knight in a possible move
  @Test
  public void checkCorrectKnightMove(){
    int correctXPosition = 2;
    int correctYPosition = 1;
    Pair<Integer, Integer> correctPosition = new Pair<>(correctXPosition, correctYPosition);
    this.generatePossibleSituation();
    this.logics.hit(correctXPosition, correctYPosition);
    Pair<Integer, Integer> currentKnightPosition = getCurrentKnightPosition().get();
    assertEquals(correctPosition, currentKnightPosition);
  }

  //TODO: Implement test to move knight in a not possible move


}
