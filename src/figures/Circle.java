package figures;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import Config.Config;

public class Circle extends Figure {
    private double radius; // Promień
    private double diameter; // Średnica
    private double circumference; // Obwód
    private double area; // Pole
    private Date timeCreated;

    private static List<Circle> createdFigures = new ArrayList<>();

    public Circle(double n, int option) {
        switch (option) {
            case 1 -> calculateFromRadius(n);
            case 2 -> calculateFromDiameter(n);
            case 3 -> calculateFromCircumference(n);
            case 4 -> calculateFromArea(n);
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    public static void printGuide() {
        System.out.println(Config.bundle.getString("figure.create_circle_guide"));
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Radius" };
            }
            case 2 -> {
                return new String[] { "Diameter" };
            }
            case 3 -> {
                return new String[] { "Circumference" };
            }
            case 4 -> {
                return new String[] { "Area" };
            }
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
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
                + "\n" + Config.bundle.getString("figure.type_circle")
                + "\n" + Config.bundle.getString("figure.radius") + ": " + String.format(Config.format, this.radius)
                + "\n" + Config.bundle.getString("figure.diameter") + " " + String.format(Config.format, this.diameter)
                + "\n" + Config.bundle.getString("figure.circumference") + " "
                + String.format(Config.format, this.circumference)
                + "\n" + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + "\n" + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[" + Config.bundle.getString("figure.type_circle") + ","
                + " " + Config.bundle.getString("figure.radius") + ": " + String.format(Config.format, this.radius)
                + " " + Config.bundle.getString("figure.diameter") + ": " + String.format(Config.format, this.diameter)
                + " " + Config.bundle.getString("figure.circumference") + ": "
                + String.format(Config.format, this.circumference)
                + " " + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + " " + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString()
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

    public static boolean isDuplicate(Circle figure) {
        for (Circle existingFigure : createdFigures) {
            if (existingFigure.equals(figure)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Circle other = (Circle) obj;

        return Double.compare(this.radius, other.radius) == 0 &&
                Double.compare(this.diameter, other.diameter) == 0 &&
                Double.compare(this.circumference, other.circumference) == 0 &&
                Double.compare(this.area, other.area) == 0;
    }
}
