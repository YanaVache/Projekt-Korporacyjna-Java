package figures;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import Config.Config;

public class Rhombus extends Figure {
    private double sideLength;
    private double area;
    private double diagonalFst;
    private double diagonalSnd;
    private Date timeCreated;

    private static List<Rhombus> createdFigures = new ArrayList<>();

    public Rhombus(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromDiagonals(a, b);
            case 2 -> calculateFromDiagonalAndArea(a, b);
            case 3 -> calculateFromDiagonalAndSidesideLength(a, b);
            case 4 -> calculateFromAreaAndSidesideLength(a, b);
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create a Rhombus?
                1 - From Diagonals
                2 - From Diagonal and Area
                3 - From Diagonal and Side Length
                4 - From Area and Side Length
                0 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Diagonal 1", "Diagonal 2" };
            }
            case 2 -> {
                return new String[] { "Diagonal", "Area" };
            }
            case 3 -> {
                return new String[] { "Diagonal", "Side Length" };
            }
            case 4 -> {
                return new String[] { "Area", "Side Length" };
            }
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    private void calculateFromDiagonals(double rDiagonalFst, double rDiagonalSnd) {
        this.diagonalFst = rDiagonalFst;
        this.diagonalSnd = rDiagonalSnd;

        this.sideLength = Math.sqrt(Math.pow(rDiagonalSnd / 2, 2) + Math.pow(rDiagonalFst / 2, 2));
        this.area = (rDiagonalFst * rDiagonalSnd) / 2;
        this.timeCreated = new Date();
    }

    private void calculateFromDiagonalAndArea(double rDiagonal, double rArea) {
        this.area = rArea;
        this.diagonalFst = rDiagonal;
        this.diagonalSnd = 2 * rArea / rDiagonal;

        this.sideLength = Math.sqrt(Math.pow(diagonalFst / 2, 2) + Math.pow(diagonalSnd / 2, 2));
        this.timeCreated = new Date();
    }

    private void calculateFromDiagonalAndSidesideLength(double rDiagonal, double rsideLength) {
        this.diagonalFst = rDiagonal;
        this.sideLength = rsideLength;

        this.diagonalSnd = Math.sqrt(Math.pow(rsideLength, 2) - Math.pow(rDiagonal / 2, 2)) * 2;
        this.area = (this.diagonalFst * this.diagonalSnd) / 2;
        this.timeCreated = new Date();
    }

    private void calculateFromAreaAndSidesideLength(double rArea, double rsideLength) {
        this.area = rArea;
        this.sideLength = rsideLength;

        double a = 1;
        double b = -4 * Math.pow(this.sideLength, 2);
        double c = 4 * Math.pow(this.area, 2);

        double delta = Math.pow(b, 2) - (4 * a * c);

        if (delta >= 0) {
            double d2Squared1 = (-b + Math.sqrt(delta)) / (2 * a);
            double d2Squared2 = (-b - Math.sqrt(delta)) / (2 * a);

            double d2Squared = Math.max(d2Squared1, d2Squared2);
            double d2 = Math.sqrt(d2Squared);
            double d1 = (2 * this.area) / d2;

            this.diagonalFst = d1;
            this.diagonalSnd = d2;
            this.timeCreated = new Date();
        } else {
            System.out.println(Config.bundle.getString("figure.invalid_input"));
        }
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\n" + Config.bundle.getString("figure.type_rhombus")
                + "\n" + Config.bundle.getString("figure.side") + ": " + String.format(Config.format, this.sideLength)
                + "\n" + Config.bundle.getString("figure.diagonal") + " 1" + ": "
                + String.format(Config.format, this.diagonalFst)
                + "\n" + Config.bundle.getString("figure.diagonal") + " 2" + ": "
                + String.format(Config.format, this.diagonalSnd)
                + "\n" + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + "\n" + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[" + Config.bundle.getString("figure.type_rhombus")
                + "," + " " + Config.bundle.getString("figure.side") + ":"
                + String.format(Config.format, this.sideLength)
                + " " + Config.bundle.getString("figure.diagonal") + " 1" + ": "
                + String.format(Config.format, this.diagonalFst)
                + " " + Config.bundle.getString("figure.diagonal") + " 2" + ": "
                + String.format(Config.format, this.diagonalSnd)
                + " " + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + " " + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString()
                + "]";
    }

    public double getSidesideLength() {
        return this.sideLength;
    }

    public double getDiagonalFst() {
        return this.diagonalFst;
    }

    public double getDiagonal() {
        return this.diagonalSnd;
    }

    public double getArea() {
        return this.area;
    }

    public double getPerimeter() {
        return 4 * this.sideLength;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    @Override
    public Circle getCircumscribedCircle() {
        if (this.diagonalFst != this.diagonalSnd) {
            System.out.println("Can't make circle from this figure");
            return null;
        }
        return new Circle(this.diagonalFst, 2);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        return new Rhombus(Math.sqrt(2) * this.diagonalFst, Math.sqrt(2) * this.diagonalSnd, 1);
    }

    public int getVertices() {
        return 4;
    }

    public static boolean isDuplicate(Rhombus figure) {
        for (Rhombus existingFigure : createdFigures) {
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
        Rhombus other = (Rhombus) obj;
    
        return Double.compare(this.sideLength, other.sideLength) == 0 &&
               Double.compare(this.area, other.area) == 0 &&
               Double.compare(this.diagonalFst, other.diagonalFst) == 0 &&
               Double.compare(this.diagonalSnd, other.diagonalSnd) == 0;
    }
}