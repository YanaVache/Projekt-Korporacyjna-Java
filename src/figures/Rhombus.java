package figures;

public class Rhombus extends Figure {
    private double length;
    private double area;
    private double diagonalFst;
    private double diagonalSnd;

    public Rhombus(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromDiagonals(a, b);
            case 2 -> calculateFromDiagonalAndArea(a, b);
            case 3 -> calculateFromDiagonalAndSideLength(a, b);
            case 4 -> calculateFromAreaAndSideLength(a, b);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void calculateFromDiagonals(double rDiagonalFst, double rDiagonalSnd) {
        this.diagonalFst = rDiagonalFst;
        this.diagonalSnd = rDiagonalSnd;

        this.length = Math.sqrt(Math.pow(rDiagonalSnd / 2, 2) + Math.pow(rDiagonalFst / 2, 2));
        this.area = (rDiagonalFst * rDiagonalSnd) / 2;
    }

    private void calculateFromDiagonalAndArea(double rDiagonal, double rArea) {
        this.area = rArea;
        this.diagonalFst = rDiagonal;
        this.diagonalSnd = 2 * rArea / rDiagonal;

        this.length = Math.sqrt(Math.pow(diagonalFst / 2, 2) + Math.pow(diagonalSnd / 2, 2));
    }

    private void calculateFromDiagonalAndSideLength(double rDiagonal, double rLength) {
        this.diagonalFst = rDiagonal;
        this.length = rLength;

        this.diagonalSnd = Math.sqrt(Math.pow(rLength, 2) - Math.pow(rDiagonal / 2, 2)) * 2;
        this.area = (this.diagonalFst * this.diagonalSnd) / 2;
    }

    private void calculateFromAreaAndSideLength(double rArea, double rLength) {
        this.area = rArea;
        this.length = rLength;

        double a = 1;
        double b = -4 * Math.pow(this.length, 2);
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
        } else {
            System.out.println("Invalid input, the area and side length combination is not possible for a rhombus.");
        }
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\nRhombus"
                + "\nSide Length: " + String.format("%.2f", this.length)
                + "\nFirst Diagonal: " + String.format("%.2f", this.diagonalFst)
                + "\nSecond Diagonal: " + String.format("%.2f", this.diagonalSnd)
                + "\nArea: " + String.format("%.2f", this.area);
    }

    @Override
    public String toString() {
        return "[Rhombus,"
                + " Side Length: " + String.format("%.2f", this.length)
                + " First Diagonal: " + String.format("%.2f", this.diagonalFst)
                + " Second Diagonal: " + String.format("%.2f", this.diagonalSnd)
                + " Area: " + String.format("%.2f", this.area);
    }

    public double getLength() {
        return this.length;
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
}