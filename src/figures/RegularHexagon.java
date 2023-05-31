package figures;

import java.util.Date;

import Config.Config;

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
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    public static void printGuide() {
        System.out.println(Config.bundle.getString("figure.create_regular_hexagon_guide"));
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
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
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
                + "\n" + Config.bundle.getString("figure.type_regular_hexagon")
                + "\n" + Config.bundle.getString("figure.side") + ": " + String.format(Config.format, this.side)
                + "\n" + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + "\n" + Config.bundle.getString("figure.perimeter") + ": "
                + String.format(Config.format, this.perimeter)
                + "\n" + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[" + Config.bundle.getString("figure.type_regular_hexagon")
                + "," + " " + Config.bundle.getString("figure.side") + ":" + String.format(Config.format, this.side)
                + " " + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + " " + Config.bundle.getString("figure.perimeter") + ": "
                + String.format(Config.format, this.perimeter)
                + " " + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString()
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
