package figures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    private final Random generator = new Random();
    private double randomInput;
    private final double epsilon = 0.0000001;

    @BeforeEach
    public void setUp() {
        randomInput = generator.nextDouble() * generator.nextInt(1000);
    }

    @RepeatedTest(5)
    public void testCalculateFromRadius() {
        Circle circle = new Circle(randomInput, 1);
        assertAll(
                () -> assertEquals(circle.getCircumference(), 2 * Math.PI * randomInput, epsilon),
                () -> assertEquals(circle.getArea(), Math.PI * randomInput * randomInput, epsilon),
                () -> assertEquals(circle.getDiameter(), 2 * randomInput, epsilon),
                () -> assertEquals(circle.getRadius(), randomInput, epsilon)
        );
    }

    @RepeatedTest(5)
    public void testCalculateFromDiameter() {
        Circle circle = new Circle(randomInput, 2);
        assertAll(
                () -> assertEquals(circle.getCircumference(), 2 * Math.PI * (randomInput / 2), epsilon),
                () -> assertEquals(circle.getArea(), Math.PI * (randomInput / 2) * (randomInput / 2), epsilon),
                () -> assertEquals(circle.getDiameter(), randomInput, epsilon),
                () -> assertEquals(circle.getRadius(), randomInput / 2, epsilon)
        );
    }

    @RepeatedTest(5)
    public void testCalculateFromCircumference() {
        Circle circle = new Circle(randomInput, 3);
        assertAll(
                () -> assertEquals(circle.getCircumference(), randomInput, epsilon),
                () -> assertEquals(circle.getArea(), (randomInput * randomInput) / (4 * Math.PI), epsilon),
                () -> assertEquals(circle.getDiameter(), randomInput / Math.PI, epsilon),
                () -> assertEquals(circle.getRadius(), randomInput / (2 * Math.PI), epsilon)
        );
    }

    @RepeatedTest(5)
    public void testCalculateFromArea() {
        Circle circle = new Circle(randomInput, 4);
        assertAll(
                () -> assertEquals(circle.getCircumference(), 2 * Math.PI * Math.sqrt(randomInput / Math.PI), epsilon),
                () -> assertEquals(circle.getArea(), randomInput, epsilon),
                () -> assertEquals(circle.getDiameter(), 2 * Math.sqrt(randomInput / Math.PI), epsilon),
                () -> assertEquals(circle.getRadius(), Math.sqrt(randomInput / Math.PI), epsilon)
        );
    }

    @Test
    public void testThrowingException() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(randomInput, -1));
    }

}