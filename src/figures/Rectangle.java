package figures;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import Config.Config;

public class Rectangle extends Figure {
    private double sideLength;
    private double width;
    private double area;
    private double diagonal;
    private Date timeCreated;

    private static List<Rectangle> createdFigures = new ArrayList<>();

    public Rectangle(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromsideLengthAndWidth(a, b);
            case 2 -> calculateFromDiagonalAndWidth(a, b);
            case 3 -> calculateFromAreaAndWidth(a, b);
            case 4 -> calculateFromAreaAndDiagonal(a, b);
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    public static void printGuide() {
        System.out.println(Config.bundle.getString("figure.create_rectangle_guide"));
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Side Length", "Width" };
            }
            case 2 -> {
                return new String[] { "Diagonal", "Width" };
            }
            case 3 -> {
                return new String[] { "Area", "Width" };
            }
            case 4 -> {
                return new String[] { "Area", "Diagonal" };
            }
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    private void calculateFromsideLengthAndWidth(double l, double w) {
        this.sideLength = l;
        this.width = w;
        this.area = l * w;
        this.diagonal = Math.sqrt(Math.pow(l, 2) + Math.pow(w, 2));
        this.timeCreated = new Date();
    }

    private void calculateFromDiagonalAndWidth(double d, double w) {
        this.diagonal = d;
        this.width = w;
        this.sideLength = Math.sqrt(Math.pow(d, 2) - Math.pow(w, 2));
        this.area = this.sideLength * this.width;
        this.timeCreated = new Date();
    }

    private void calculateFromAreaAndWidth(double a, double w) {
        this.area = a;
        this.width = w;
        this.sideLength = a / w;
        this.diagonal = Math.sqrt(Math.pow(this.sideLength, 2) + Math.pow(this.width, 2));
        this.timeCreated = new Date();
    }

    private void calculateFromAreaAndDiagonal(double a, double d) {
        this.area = a;
        this.diagonal = d;
        this.sideLength = Math.sqrt(Math.pow(d, 2) / 2 + Math.sqrt(Math.pow(d, 4) / 4 - Math.pow(a, 2)));
        this.width = a / this.sideLength;
        this.timeCreated = new Date();
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\n" + Config.bundle.getString("figure.type_rectangle")
                + "\n" + Config.bundle.getString("figure.side") + ": " + String.format(Config.format, this.sideLength)
                + "\n" + Config.bundle.getString("figure.width") + ": " + String.format(Config.format, this.width)
                + "\n" + Config.bundle.getString("figure.diagonal") + ": " + String.format(Config.format, this.diagonal)
                + "\n" + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + "\n" + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[" + Config.bundle.getString("figure.type_rectangle")
                + "," + " " + Config.bundle.getString("figure.side") + ":"
                + String.format(Config.format, this.sideLength)
                + " " + Config.bundle.getString("figure.width") + ": " + String.format(Config.format, this.width)
                + " " + Config.bundle.getString("figure.diagonal") + ": " + String.format(Config.format, this.diagonal)
                + " " + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + " " + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString()
                + "]";
    }

    public double getsideLength() {
        return sideLength;
    }

    public double getWidth() {
        return width;
    }

    public double getArea() {
        return area;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public double getPerimeter() {
        return 2 * this.sideLength + 2 * this.width;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    @Override
    public Circle getCircumscribedCircle() {
        return new Circle(this.diagonal, 2);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        return new Rectangle(Math.sqrt(2) * this.sideLength, Math.sqrt(2) * this.width, 1);
    }

    public int getVertices() {
        return 4;
    }
    public static boolean isDuplicate(Rectangle figure) {
        for (Rectangle existingFigure : createdFigures) {
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
        Rectangle other = (Rectangle) obj;
    
        return Double.compare(this.sideLength, other.sideLength) == 0 &&
               Double.compare(this.width, other.width) == 0 &&
               Double.compare(this.area, other.area) == 0 &&
               Double.compare(this.diagonal, other.diagonal) == 0;
    }
}
