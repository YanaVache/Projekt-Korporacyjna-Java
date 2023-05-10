package figures;

import Config.Config;

import java.util.Date;

public class Ellipse extends Figure {
    private double majorAxis;
    private double minorAxis;
    private double area;
    private double perimeter;
    private Date timeCreated;

    public Ellipse(double a, double b, int option) {
        switch (option) {
            case 1 -> countMajorAxisFromMinorAxisAndArea(a, b);
            case 2 -> countMinorAxisFromMajorAxisAndArea(a, b);
            case 3 -> countAreaFromMinorAxisAndMajorAxis(a, b);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create a Ellipse?
                1 - From Minor Axis and Area
                2 - From Major Aaxis and Area
                3 - From Minor Axis and Major Axis
                0 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Minor Axis", "Area" };
            }
            case 2 -> {
                return new String[] { "Major Axis", "Area" };
            }
            case 3 -> {
                return new String[] { "Minor Axis", "Major Axis" };
            }
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void countMajorAxisFromMinorAxisAndArea(double minorAxis, double area) {
        this.majorAxis = area / ((Math.PI * minorAxis));
        this.minorAxis = minorAxis;
        this.area = area;
        this.perimeter = countPerimeter(this.majorAxis, this.minorAxis);
        this.timeCreated = new Date();
    }

    private void countMinorAxisFromMajorAxisAndArea(double majorAxis, double area) {
        this.majorAxis = majorAxis;
        this.minorAxis = area / (Math.PI * (majorAxis));
        this.area = area;
        this.perimeter = countPerimeter(this.majorAxis, this.minorAxis);
        this.timeCreated = new Date();
    }

    private void countAreaFromMinorAxisAndMajorAxis(double minorAxis, double majorAxis) {
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
        this.area = Math.PI * (minorAxis) * (majorAxis);
        this.perimeter = countPerimeter(this.majorAxis, this.minorAxis);
        this.timeCreated = new Date();
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\nEllipse"
                + "\nMajor Axis: " + String.format(Config.format, this.majorAxis)
                + "\nMinor Axis: " + String.format(Config.format, this.minorAxis)
                + "\nPerimeter: " + String.format(Config.format, this.perimeter)
                + "\nArea: " + String.format(Config.format, this.area)
                + "\nTime Created: " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[Ellipse,"
                + " Major Axis: " + String.format(Config.format, this.majorAxis)
                + " Minor Axis: " + String.format(Config.format, this.minorAxis)
                + " Perimeter: " + String.format(Config.format, this.perimeter)
                + " Area: " + String.format(Config.format, this.area)
                + " Time Created: " + this.timeCreated.toString()
                + "]";
    }


    public static double countPerimeter(double majorAxis, double minorAxis) {
        double h = Math.pow(majorAxis - minorAxis, 2) / Math.pow(majorAxis + minorAxis, 2);
        return Math.PI * (majorAxis + minorAxis) * (1 + (1/Math.pow(2,2)) * h + (1/Math.pow(2,6))*Math.pow(h, 2));
    }

    public double getMajorAxis() {
        return this.majorAxis;
    }

    public double getMinorAxis() {
        return this.minorAxis;
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
        return new Circle(this.majorAxis, 2);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        return new Ellipse(this.majorAxis * 2, this.minorAxis * 2, 3);
    }

    public int getVertices() {
        return Integer.MAX_VALUE;
    }
}