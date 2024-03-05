package e1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicMovableObjectTest {
    
    private final static int INIT_X = 0;
    private final static int INIT_Y = 0;
    private final Pair<Integer,Integer> initPosition = new Pair<Integer,Integer>(INIT_X, INIT_Y);
    private Movable movable;

    @BeforeEach
    public void init(){
        this.movable = new BasicMovableObject(this.initPosition);
    }

    @Test
    public void correctInit(){
        assertEquals(this.initPosition, this.movable.getCurrentPosition());
    }

    @Test
    public void positiveMove(){
        int x = 5;
        int y = 6;
        Pair<Integer, Integer> moveToPosition = new Pair<Integer,Integer>(x, y);
        this.movable.move(moveToPosition);
        assertEquals(moveToPosition, this.movable.getCurrentPosition());
    }

    @Test
    public void negativeMove(){
        int randomXNumber = -5;
        int randomYNumber = -6;
        Pair<Integer, Integer> moveToPosition = new Pair<Integer,Integer>(randomXNumber, randomYNumber);
        this.movable.move(moveToPosition);
        assertEquals(moveToPosition, this.movable.getCurrentPosition());
    }
}
