package figures;

import Config.Config;

import java.util.Date;

public class Circle extends Figure {
    private double radius;         // Promień
    private double diameter;       // Średnica
    private double circumference;  // Obwód
    private double area;           // Pole
    private Date timeCreated;

    public Circle(double n, int option) {
        switch (option) {
            case 1 -> calculateFromRadius(n);
            case 2 -> calculateFromDiameter(n);
            case 3 -> calculateFromCircumference(n);
            case 4 -> calculateFromArea(n);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create a Circle?
                1 - From Radius
                2 - From Diameter
                3 - From Circumference
                4 - From Area
                0 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[]{"Radius"};
            }
            case 2 -> {
                return new String[]{"Diameter"};
            }
            case 3 -> {
                return new String[]{"Circumference"};
            }
            case 4 -> {
                return new String[]{"Area"};
            }
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void calculateFromCircumference(double c) {
        this.radius = c / Math.PI / 2;
        this.diameter = 2 * this.radius;
        this.circumference = c;
        this.area = Math.PI * this.radius * this.radius;
        this.timeCreated = new Date();
    }

    private void calculateFromDiameter(double d) {
        this.radius = d / 2;
        this.diameter = d;
        this.circumference = Math.PI * 2 * this.radius;
        this.area = Math.PI * this.radius * this.radius;
        this.timeCreated = new Date();
    }

    private void calculateFromRadius(double r) {
        this.radius = r;
        this.diameter = 2 * r;
        this.circumference = Math.PI * 2 * r;
        this.area = Math.PI * r * r;
        this.timeCreated = new Date();
    }

    private void calculateFromArea(double a) {
        this.radius = Math.sqrt(a / Math.PI);
        this.diameter = 2 * this.radius;
        this.circumference = Math.PI * 2 * this.radius;
        this.area = a;
        this.timeCreated = new Date();
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\nCircle"
                + "\nRadius: " + String.format(Config.format, this.radius)
                + "\nDiameter: " + String.format(Config.format, this.diameter)
                + "\nCircumference: " + String.format(Config.format, this.circumference)
                + "\nArea: " + String.format(Config.format, this.area)
                + "\nTime Created: " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[Circle,"
                + " Radius: " + String.format(Config.format, this.radius)
                + " Diameter: " + String.format(Config.format, this.diameter)
                + " Circumference: " + String.format(Config.format, this.circumference)
                + " Area: " + String.format(Config.format, this.area)
                + " Time Created: " + this.timeCreated.toString()
                + "]";
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getPerimeter() {
        return circumference;
    }

    @Override
    public Circle getCircumscribedCircle() {
        return new Circle(this.radius, 1);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        return new Circle(this.area * 2, 4);
    }

    @Override
    public Date getTimeCreated() {
        return this.timeCreated;
    }

    public double getArea() {
        return area;
    }

    public int getVertices() {
        return Integer.MAX_VALUE;
    }
}
