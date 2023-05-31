package figures;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import Config.Config;

public class OrthogonalTriangle extends Figure {
    private double aLeg;
    private double bLeg;
    private double hypoteneuse;
    private double area;
    private Date timeCreated;

    private static List<OrthogonalTriangle> createdFigures = new ArrayList<>();

    public OrthogonalTriangle(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromLegAndLeg(a, b);
            case 2 -> calculateFromLegAndHypoteneuse(a, b);
            case 3 -> calculateFromAreaAndHypoteneuse(a, b);
            case 4 -> calculateFromAreaAndLeg(a, b);
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    public static void printGuide() {
        System.out.println(Config.bundle.getString("figure.create_orthogonal_triangle_guide"));
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Leg 1 Length", "Leg 2 Length" };
            }
            case 2 -> {
                return new String[] { "Leg Length", "Hypoteneuse" };
            }
            case 3 -> {
                return new String[] { "Area", "Hypoteneuse" };
            }
            case 4 -> {
                return new String[] { "Area", "Leg Length" };
            }
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    private void calculateFromLegAndLeg(double a, double b) {
        this.aLeg = a;
        this.bLeg = b;
        this.hypoteneuse = Math.sqrt(a * a + b * b);
        this.area = (a * b) / 2;
        this.timeCreated = new Date();
    }

    private void calculateFromAreaAndHypoteneuse(double a, double b) {
        this.area = a;
        this.hypoteneuse = b;
        this.aLeg = Math.sqrt(Math.pow(b, 2) / 2 + Math.sqrt(Math.pow(b, 4) / 4 - Math.pow(a / 2, 2)));
        this.bLeg = (2 * a) / this.aLeg;
        this.timeCreated = new Date();
    }

    private void calculateFromLegAndHypoteneuse(double a, double b) {
        if (a >= b) {
            System.out.println(Config.bundle.getString("figure.invalid_input"));
            return;
        }
        this.aLeg = a;
        this.hypoteneuse = b;
        this.bLeg = Math.sqrt(this.hypoteneuse * this.hypoteneuse - this.aLeg * this.aLeg);
        this.area = this.aLeg * this.bLeg / 2;
        this.timeCreated = new Date();
    }

    private void calculateFromAreaAndLeg(double a, double b) {
        this.area = a;
        this.bLeg = b;
        this.aLeg = (2 * this.area) / this.bLeg;
        this.hypoteneuse = Math.sqrt(this.aLeg * this.aLeg + this.bLeg * this.bLeg);
        this.timeCreated = new Date();
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\n" + Config.bundle.getString("figure.type_orthogonal_triangle")
                + "\n" + Config.bundle.getString("figure.arm") + " 1" + ": " + String.format(Config.format, this.aLeg)
                + "\n" + Config.bundle.getString("figure.arm") + "2" + ": " + String.format(Config.format, this.bLeg)
                + "\n" + Config.bundle.getString("figure.hypoteneuse") + ": "
                + String.format(Config.format, this.hypoteneuse)
                + "\n" + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + "\n" + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[" + Config.bundle.getString("figure.type_orthogonal_triangle")
                + " " + Config.bundle.getString("figure.arm") + " 1" + ": " + String.format(Config.format, this.aLeg)
                + " " + Config.bundle.getString("figure.arm") + " 2" + ": " + String.format(Config.format, this.bLeg)
                + " " + Config.bundle.getString("figure.hypoteneuse") + ": "
                + String.format(Config.format, this.hypoteneuse)
                + " " + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + " " + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString()
                + "]";
    }

    public double getaLeg() {
        return aLeg;
    }

    public double getbLeg() {
        return bLeg;
    }

    public double getHypoteneuse() {
        return hypoteneuse;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public double getPerimeter() {
        return aLeg + bLeg + hypoteneuse;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    @Override
    public Circle getCircumscribedCircle() {
        return new Circle(hypoteneuse, 2);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        return new OrthogonalTriangle(Math.sqrt(2) * this.aLeg, Math.sqrt(2) * this.bLeg, 1);
    }

    public int getVertices() {
        return 3;
    }

    public static boolean isDuplicate(OrthogonalTriangle figure) {
        for (OrthogonalTriangle existingFigure : createdFigures) {
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
        OrthogonalTriangle other = (OrthogonalTriangle) obj;
    
        return Double.compare(this.aLeg, other.aLeg) == 0 &&
               Double.compare(this.bLeg, other.bLeg) == 0 &&
               Double.compare(this.hypoteneuse, other.hypoteneuse) == 0 &&
               Double.compare(this.area, other.area) == 0;
    }
}
