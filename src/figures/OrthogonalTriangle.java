package figures;

import java.util.Date;

public class OrthogonalTriangle extends Figure {
    private double aLeg;
    private double bLeg;
    private double hypoteneuse;
    private double area;
    private Date timeCreated;

    public OrthogonalTriangle(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromLegAndLeg(a, b);
            case 2 -> calculateFromLegAndHypoteneuse(a, b);
            case 3 -> calculateFromAreaAndHypoteneuse(a, b);
            case 4 -> calculateFromAreaAndLeg(a, b);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create an Orthogonal Triangle?
                1 - From Legs Length
                2 - From Leg Length and Hypoteneuse
                3 - From Area and Hypoteneuse
                4 - From Area and Leg Length
                0 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[]{"Leg 1 Length", "Leg 2 Length"};
            }
            case 2 -> {
                return new String[]{"Leg Length", "Hypoteneuse"};
            }
            case 3 -> {
                return new String[]{"Area", "Hypoteneuse"};
            }
            case 4 -> {
                return new String[]{"Area", "Leg Length"};
            }
            default -> throw new IllegalArgumentException("Wrong option");
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
            System.out.println("Invalid input, Leg Length needs to be smaller than Hypoteneuse");
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
                + "\nOrthogonal Triangle"
                + "\nArm 1 Length: " + String.format("%.2f", this.aLeg)
                + "\nArm 2 Length: " + String.format("%.2f", this.bLeg)
                + "\nHypoteneuse: " + String.format("%.2f", this.hypoteneuse)
                + "\nArea: " + String.format("%.2f", this.area)
                + "\nTime Created: " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[Orthogonal Triangle"
                + " Arm 1 Length: " + String.format("%.2f", this.aLeg)
                + " Arm 2 Length: " + String.format("%.2f", this.bLeg)
                + " Hypoteneuse: " + String.format("%.2f", this.hypoteneuse)
                + " Area: " + String.format("%.2f", this.area)
                + " Time Created: " + this.timeCreated.toString()
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
}
