package figures;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import Config.Config;

public class EquilateralTriangle extends Figure {
    private double edgeLength;
    private double area;
    private double perimeter;
    private double height;
    private Date timeCreated;

    private static List<EquilateralTriangle> createdFigures = new ArrayList<>();

    public EquilateralTriangle(double n, int option) {
        switch (option) {
            case 1 -> calculateFromEdgeLength(n);
            case 2 -> calculateFromArea(n);
            case 3 -> calculateFromPerimeter(n);
            case 4 -> calculateFromHeight(n);
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    public static void printGuide() {
        System.out.println(Config.bundle.getString("figure.create_equilateral_triangle_guide"));

    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Edge Length" };
            }
            case 2 -> {
                return new String[] { "Area" };
            }
            case 3 -> {
                return new String[] { "Perimeter" };
            }
            case 4 -> {
                return new String[] { "Height" };
            }
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    private void calculateFromEdgeLength(double a) {
        this.edgeLength = a;
        this.area = (a * a * Math.sqrt(3)) / 4;
        this.perimeter = 3 * a;
        this.height = (a * Math.sqrt(3)) / 2;
        this.timeCreated = new Date();
    }

    private void calculateFromArea(double a) {
        this.area = a;
        this.edgeLength = 2 * Math.sqrt(a / Math.sqrt(3));
        this.perimeter = 3 * this.edgeLength;
        this.height = (this.edgeLength * Math.sqrt(3)) / 2;
        this.timeCreated = new Date();
    }

    private void calculateFromPerimeter(double a) {
        this.perimeter = a;
        this.edgeLength = a / 3;
        this.height = (this.edgeLength * Math.sqrt(3)) / 2;
        this.area = (this.edgeLength * this.edgeLength * Math.sqrt(3)) / 4;
        this.timeCreated = new Date();
    }

    private void calculateFromHeight(double a) {
        this.height = a;
        this.edgeLength = 2 * a / Math.sqrt(3);
        this.perimeter = 3 * this.edgeLength;
        this.area = (this.edgeLength * this.edgeLength * Math.sqrt(3)) / 4;
        this.timeCreated = new Date();
    }

    public String prettyString() {
        return "--------------------"
                + "\n" + Config.bundle.getString("figure.type_equilateral_triangle")
                + "\n" + Config.bundle.getString("figure.edge_length") + ": "
                + String.format(Config.format, this.edgeLength)
                + "\n" + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + "\n" + Config.bundle.getString("figure.perimeter") + ": "
                + String.format(Config.format, this.perimeter)
                + "\n" + Config.bundle.getString("figure.height") + ": " + String.format(Config.format, this.height)
                + "\n" + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[" + Config.bundle.getString("figure.type_equilateral_triangle") + ","
                + " " + Config.bundle.getString("figure.edge_length") + ": "
                + String.format(Config.format, this.edgeLength)
                + " " + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + " " + Config.bundle.getString("figure.perimeter") + ": "
                + String.format(Config.format, this.perimeter)
                + " " + Config.bundle.getString("figure.height") + ": " + String.format(Config.format, this.height)
                + " " + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString()
                + "]";
    }

    public double getEdgeLength() {
        return edgeLength;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Circle getCircumscribedCircle() {
        return new Circle(this.height * 4 / 3, 2);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        return new EquilateralTriangle(2 * this.area, 2);
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    public int getVertices() {
        return 3;
    }

    public static boolean isDuplicate(EquilateralTriangle figure) {
        for (EquilateralTriangle existingFigure : createdFigures) {
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
        EquilateralTriangle other = (EquilateralTriangle) obj;
    
        return Double.compare(this.edgeLength, other.edgeLength) == 0 &&
               Double.compare(this.area, other.area) == 0 &&
               Double.compare(this.perimeter, other.perimeter) == 0 &&
               Double.compare(this.height, other.height) == 0;
    }
}
