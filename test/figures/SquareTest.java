package figures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    private final Random generator = new Random();
    private double randomInput;

    @BeforeEach
    public void setUp() {
        randomInput = generator.nextDouble() * generator.nextInt(100);
    }

    @RepeatedTest(5)
    public void testCalculateFromSideLength() {
        Square square = new Square(randomInput, 1);
        assertAll(
                () -> assertEquals(square.getSideLength(), randomInput),
                () -> assertEquals(square.getArea(), randomInput * randomInput),
                () -> assertEquals(square.getDiagonalLength(), randomInput * Math.sqrt(2))
        );
    }

    @RepeatedTest(5)
    public void testCalculateFromDiagonalLength() {
        Square square = new Square(randomInput, 2);
        assertAll(
                () -> assertEquals(square.getSideLength(), randomInput / Math.sqrt(2)),
                () -> assertEquals(square.getArea(), (randomInput / Math.sqrt(2)) * (randomInput / Math.sqrt(2))),
                () -> assertEquals(square.getDiagonalLength(), randomInput)
        );
    }

    @RepeatedTest(5)
    public void testCalculateFromArea() {
        Square square = new Square(randomInput, 3);
        assertAll(
                () -> assertEquals(square.getSideLength(), Math.sqrt(randomInput)),
                () -> assertEquals(square.getArea(), randomInput),
                () -> assertEquals(square.getDiagonalLength(), Math.sqrt(randomInput) * Math.sqrt(2))
        );
    }

    @Test
    public void testThrowingException() {
        assertThrows(IllegalArgumentException.class, () -> new Square(randomInput, -1));
    }


}