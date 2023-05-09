package figures;

import java.util.Date;

public class Square extends Figure {
    private double sideLength;    // Długość boku
    private double diagonalLength;// Długość przekątnej
    private double area; // Pole powierzchni
    private Date timeCreated;

    public Square(double n, int option) {
        switch (option) {
            case 1 -> calculateFromSideLength(n);
            case 2 -> calculateFromDiagonalLength(n);
            case 3 -> calculateFromArea(n);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create a Square?
                1 - From Side Length
                2 - From Diagonal
                3 - From Area
                0 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[]{"Side"};
            }
            case 2 -> {
                return new String[]{"Diagonal"};
            }
            case 3 -> {
                return new String[]{"Area"};
            }
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void calculateFromSideLength(double s) {
        this.sideLength = s;
        this.diagonalLength = s * Math.sqrt(2);
        this.area = s * s;
        this.timeCreated = new Date();
    }

    private void calculateFromDiagonalLength(double d) {
        this.diagonalLength = d;
        this.sideLength = d / Math.sqrt(2);
        this.area = Math.pow(this.sideLength, 2);
        this.timeCreated = new Date();
    }

    private void calculateFromArea(double a) {
        this.area = a;
        this.sideLength = Math.sqrt(a);
        this.diagonalLength = this.sideLength * Math.sqrt(2);
        this.timeCreated = new Date();
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\nSquare"
                + "\nSide Length: " + String.format("%.2f", this.sideLength)
                + "\nDiagonal Length: " + String.format("%.2f", this.diagonalLength)
                + "\nArea: " + String.format("%.2f", this.area)
                + "\nTime Created: " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[Square,"
                + " Side Length: " + String.format("%.2f", this.sideLength)
                + " Diagonal Length: " + String.format("%.2f", this.diagonalLength)
                + " Area: " + String.format("%.2f", this.area)
                + " Time Created: " + this.timeCreated.toString()
                + "]";
    }

    public double getSideLength() {
        return sideLength;
    }

    public double getDiagonalLength() {
        return diagonalLength;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return 4 * this.sideLength;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }


    @Override
    public Circle getCircumscribedCircle() {
        return new Circle(this.diagonalLength, 2);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        return new Square(this.area * 2, 3);
    }

    public int getVertices() {
        return 4;
    }
}
