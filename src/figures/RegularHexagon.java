package figures;

import Config.Config;

import java.util.Date;

public class RegularHexagon extends Figure {
    private double side;
    private double area;
    private double perimeter;
    private Date timeCreated;

    public RegularHexagon(double a, int option) {
        switch (option) {
            case 1 -> calculateFromSide(a);
            case 2 -> calculateFromArea(a);
            case 3 -> calculateFromPerimeter(a);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create a Regular Hexagon?
                1 - From Side Length
                2 - From Area
                3 - From Perimeter
                0 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Side Length" };
            }
            case 2 -> {
                return new String[] { "Area" };
            }
            case 3 -> {
                return new String[] { "Perimeter" };
            }
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void calculateFromSide(double side) {
        this.side = side;
        this.area = (3 * Math.sqrt(3) * Math.pow(side, 2)) / 2;
        this.perimeter = 6 * side;
        this.timeCreated = new Date();
    }

    private void calculateFromArea(double area) {
        this.area = area;
        this.side = Math.sqrt((2 * area) / (3 * Math.sqrt(3)));
        this.perimeter = 6 * side;
        this.timeCreated = new Date();
    }

    private void calculateFromPerimeter(double perimeter) {
        this.perimeter = perimeter;
        this.side = perimeter / 6;
        this.area = (3 * Math.sqrt(3) * Math.pow(side, 2)) / 2;
        this.timeCreated = new Date();
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\nRegular Hexagon"
                + "\nSide Length: " + String.format(Config.format, this.side)
                + "\nArea: " + String.format(Config.format, this.area)
                + "\nPerimeter: " + String.format(Config.format, this.perimeter)
                + "\nTime Created: " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[Regular Hexagon,"
                + " Side Length: " + String.format(Config.format, this.side)
                + " Area: " + String.format(Config.format, this.area)
                + " Perimeter: " + String.format(Config.format, this.perimeter)
                + " Time Created: " + this.timeCreated.toString()
                + "]";
    }

    public double getSideLength() {
        return this.side;
    }

    public double getArea() {
        return this.area;
    }

    public double getPerimeter() {
        return this.perimeter;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    @Override
    public Circle getCircumscribedCircle() {
        double radius = side;
        return new Circle(radius, 1);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        double doubledSide = side * Math.sqrt(2);

        return new RegularHexagon(doubledSide, 1);
    }

    public int getVertices() {
        return 6;
    }
}
