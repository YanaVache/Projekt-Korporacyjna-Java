package figures;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import Config.Config;

public class Square extends Figure {
    private double sideLength; // Długość boku
    private double diagonalLength;// Długość przekątnej
    private double area; // Pole powierzchni
    private Date timeCreated;

    private static List<Square> createdFigures = new ArrayList<>();

    public Square(double n, int option) {
        switch (option) {
            case 1 -> calculateFromSideLength(n);
            case 2 -> calculateFromDiagonalLength(n);
            case 3 -> calculateFromArea(n);
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    public static void printGuide() {
        System.out.println(Config.bundle.getString("figure.create_square_guide"));
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Side" };
            }
            case 2 -> {
                return new String[] { "Diagonal" };
            }
            case 3 -> {
                return new String[] { "Area" };
            }
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
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
                + "\n" + Config.bundle.getString("figure.type_square")
                + "\n" + Config.bundle.getString("figure.side") + ": "
                + String.format(Config.format, this.sideLength)
                + "\n" + Config.bundle.getString("figure.diagonal") + ": "
                + String.format(Config.format, this.diagonalLength)
                + "\n" + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + "\n" + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[" + Config.bundle.getString("figure.type_square")
                + "," + " " + Config.bundle.getString("figure.side") + ":"
                + String.format(Config.format, this.sideLength)
                + " " + Config.bundle.getString("figure.diagonal") + ": "
                + String.format(Config.format, this.diagonalLength)
                + " " + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + " " + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString()
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

    public static boolean isDuplicate(Square figure) {
        for (Square existingFigure : createdFigures) {
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
        Square other = (Square) obj;
    
        return Double.compare(this.sideLength, other.sideLength) == 0 &&
               Double.compare(this.diagonalLength, other.diagonalLength) == 0 &&
               Double.compare(this.area, other.area) == 0;
    }
}
