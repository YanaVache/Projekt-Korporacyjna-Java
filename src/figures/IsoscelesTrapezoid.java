package figures;

import Config.Config;

import java.util.Date;

public class IsoscelesTrapezoid extends Figure {
    private double base1;
    private double base2;
    private double leg;
    private double height;
    private double area;
    private Date timeCreated;

    public IsoscelesTrapezoid(double a, double b, double c, int option) {
        switch (option) {
            case 1 -> calculateFromBasesAndHeight(a, b, c);
            case 2 -> calculateFromBasesAndLeg(a, b, c);
            case 3 -> calculateFromBasesAndArea(a, b, c);
            case 4 -> calculateFromBase1AndLegAndHeight(a, b, c);
            case 5 -> calculateFromBase1AndHeightAndArea(a, b, c);
            case 6 -> calculateFromBase2AndLegAndHeight(a, b, c);
            case 7 -> calculateFromBase2AndHeightAndArea(a, b, c);
            case 8 -> calculateFromLegAndHeightAndArea(a, b, c);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create an Isosceles Trapezoid?
                1 - From Bases and Height
                2 - From Bases and Leg
                3 - From Bases and Area
                4 - From Base 1, Leg and Height
                5 - From Base 1, Height and Area
                6 - From Base 2, Leg and Height
                7 - From Base 2, Height and Area
                8 - From Leg, Height and Area
                0 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Base 1", "Base 2", "Height" };
            }
            case 2 -> {
                return new String[] { "Base 1", "Base 2", "Leg" };
            }
            case 3 -> {
                return new String[] { "Base 1", "Base 2", "Area" };
            }
            case 4 -> {
                return new String[] { "Base 1", "Leg", "Height" };
            }
            case 5 -> {
                return new String[] { "Base 1", "Height", "Area" };
            }
            case 6 -> {
                return new String[] { "Base 2", "Leg", "Height" };
            }
            case 7 -> {
                return new String[] { "Base 2", "Height", "Area" };
            }
            case 8 -> {
                return new String[] { "Leg", "Height", "Area" };
            }
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void calculateFromBasesAndHeight(double base1, double base2, double height) {
        this.base1 = base1;
        this.base2 = base2;
        this.height = height;
        this.area = (base1 + base2) * height / 2;
        this.leg = Math.sqrt(Math.pow((base2 - base1) / 2, 2) + Math.pow(height, 2));
        this.timeCreated = new Date();
    }

    private void calculateFromBasesAndLeg(double base1, double base2, double leg) {
        this.base1 = base1;
        this.base2 = base2;
        this.leg = leg;
        this.height = Math.sqrt(leg * leg - Math.pow((base2 - base1) / 2, 2));
        this.area = (base1 + base2) * height / 2;
        this.timeCreated = new Date();
    }

    private void calculateFromBasesAndArea(double base1, double base2, double area) {
        this.base1 = base1;
        this.base2 = base2;
        this.area = area;
        this.height = 2 * area / (base1 + base2);
        this.leg = Math.sqrt(Math.pow((base2 - base1) / 2, 2) + Math.pow(height, 2));
        this.timeCreated = new Date();
    }

    private void calculateFromBase1AndLegAndHeight(double base1, double leg, double height) {
        this.base1 = base1;
        this.leg = leg;
        this.height = height;
        this.base2 = base1 + 2 * Math.sqrt(Math.pow(leg, 2) - Math.pow(height, 2));
        this.area = (base1 + base2) * height / 2;
        this.timeCreated = new Date();
    }

    private void calculateFromBase2AndLegAndHeight(double base2, double leg, double height) {
        this.base2 = base2;
        this.leg = leg;
        this.height = height;
        this.base1 = base2 - 2 * Math.sqrt(Math.pow(leg, 2) - Math.pow(height, 2));
        this.area = (base1 + base2) * height / 2;
        this.timeCreated = new Date();
    }

    private void calculateFromBase1AndHeightAndArea(double base1, double height, double area) {
        this.base1 = base1;
        this.height = height;
        this.area = area;
        this.base2 = (2 * area / height) - base1;
        this.leg = Math.sqrt(Math.pow((base2 - base1) / 2, 2) + Math.pow(height, 2));
        this.timeCreated = new Date();
    }

    private void calculateFromBase2AndHeightAndArea(double base2, double height, double area) {
        this.base2 = base2;
        this.height = height;
        this.area = area;
        this.base1 = (2 * area / height) - base2;
        this.leg = Math.sqrt(Math.pow((base2 - base1) / 2, 2) + Math.pow(height, 2));
        this.timeCreated = new Date();
    }

    private void calculateFromLegAndHeightAndArea(double leg, double height, double area) {
        this.leg = leg;
        this.height = height;
        this.area = area;
        this.base1 = area / height - Math.sqrt(Math.pow(leg, 2) - Math.pow(height, 2));
        this.base2 = base1 + 2 * (Math.sqrt(Math.pow(leg, 2) - Math.pow(height, 2)));
        this.timeCreated = new Date();
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\nIsosceles Trapezoid"
                + "\nBase 1: " + String.format(Config.format, this.base1)
                + "\nBase 2: " + String.format(Config.format, this.base2)
                + "\nLeg: " + String.format(Config.format, this.leg)
                + "\nHeight: " + String.format(Config.format, this.height)
                + "\nArea: " + String.format(Config.format, this.area)
                + "\nTime Created: " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[Isosceles Trapezoid,"
                + " Base 1: " + String.format(Config.format, this.base1)
                + " Base 2: " + String.format(Config.format, this.base2)
                + " Leg: " + String.format(Config.format, this.leg)
                + " Height: " + String.format(Config.format, this.height)
                + " Area: " + String.format(Config.format, this.area)
                + " Time Created: " + this.timeCreated.toString()
                + "]";
    }

    public double getBase1() {
        return this.base1;
    }

    public double getBase2() {
        return this.base2;
    }

    public double getLeg() {
        return this.leg;
    }

    public double getHeight() {
        return this.height;
    }

    public double getArea() {
        return this.area;
    }

    public double getPerimeter() {
        return this.base1 + this.base2 + 2 * this.leg;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    @Override
    public Circle getCircumscribedCircle() {
        double diagonal = Math.sqrt(Math.pow(leg, 2) + base1 * base2);
        double degrees = Math.acos((base2 - base1) / (2 * leg));
        double sin = Math.sin(degrees);
        double radius = diagonal / (2 * sin);
        return new Circle(radius, 1);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        double scaleFactor = Math.sqrt(2);
        double doubledBase1 = base1 * scaleFactor;
        double doubledBase2 = base2 * scaleFactor;
        double doubledHeight = height * scaleFactor;

        return new IsoscelesTrapezoid(doubledBase1, doubledBase2, doubledHeight, 1);
    }

    public int getVertices() {
        return 4;
    }
}
