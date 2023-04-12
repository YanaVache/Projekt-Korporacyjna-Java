package figures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    private final Random generator = new Random();
    private double randomInput;
    private double randomInput2;
    private final double epsilon = 0.0000001;

    @BeforeEach
    public void setUp() {
        randomInput = generator.nextDouble() * generator.nextInt(1000);
        randomInput2 = generator.nextDouble() * generator.nextInt(1000);
    }

    @RepeatedTest(5)
    public void testCalculateFromLengthAndWidth() {
        Rectangle rectangle = new Rectangle(randomInput, randomInput2, 1);
        assertAll(
                () -> assertEquals(rectangle.getLength(), randomInput, epsilon),
                () -> assertEquals(rectangle.getWidth(), randomInput2, epsilon),
                () -> assertEquals(rectangle.getArea(), randomInput * randomInput2, epsilon),
                () -> assertEquals(rectangle.getDiagonal(), Math.sqrt(randomInput * randomInput + randomInput2 * randomInput2), epsilon)
        );
    }

    @RepeatedTest(5)
    public void testCalculateFromDiagonalAndWidth() {
        Rectangle rectangle = new Rectangle(randomInput, randomInput2, 2);
        assertAll(
                () -> assertEquals(rectangle.getLength(), Math.sqrt(randomInput * randomInput - randomInput2 * randomInput2), epsilon),
                () -> assertEquals(rectangle.getWidth(), randomInput2, epsilon),
                () -> assertEquals(rectangle.getArea(), Math.sqrt(randomInput * randomInput - randomInput2 * randomInput2) * randomInput2, epsilon),
                () -> assertEquals(rectangle.getDiagonal(), randomInput, epsilon)
        );
    }

    @RepeatedTest(5)
    public void testCalculateFromAreaAndWidth() {
        Rectangle rectangle = new Rectangle(randomInput, randomInput2, 3);
        assertAll(
                () -> assertEquals(rectangle.getLength(), randomInput / randomInput2, epsilon),
                () -> assertEquals(rectangle.getWidth(), randomInput2, epsilon),
                () -> assertEquals(rectangle.getArea(), randomInput, epsilon),
                () -> assertEquals(rectangle.getDiagonal(), Math.sqrt(Math.pow((randomInput / randomInput2), 2) + randomInput2 * randomInput2), epsilon)
        );
    }

    @RepeatedTest(5)
    public void testCalculateFromAreaAndDiagonal() {
        Rectangle rectangle = new Rectangle(randomInput, randomInput2, 4);
        assertAll(
                () -> assertEquals(rectangle.getLength(), randomInput / randomInput2, epsilon),
                () -> assertEquals(rectangle.getWidth(), randomInput2, epsilon),
                () -> assertEquals(rectangle.getArea(), randomInput, epsilon),
                () -> assertEquals(rectangle.getDiagonal(), randomInput2, epsilon)
        );
    }
}