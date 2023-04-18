package figures;

public class Rhombus extends Figure {
    private double sideLength;
    private double area;
    private double diagonalFst;
    private double diagonalSnd;

    public Rhombus(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromDiagonals(a, b);
            case 2 -> calculateFromDiagonalAndArea(a, b);
            case 3 -> calculateFromDiagonalAndSidesideLength(a, b);
            case 4 -> calculateFromAreaAndSidesideLength(a, b);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void calculateFromDiagonals(double rDiagonalFst, double rDiagonalSnd) {
        this.diagonalFst = rDiagonalFst;
        this.diagonalSnd = rDiagonalSnd;

        this.sideLength = Math.sqrt(Math.pow(rDiagonalSnd / 2, 2) + Math.pow(rDiagonalFst / 2, 2));
        this.area = (rDiagonalFst * rDiagonalSnd) / 2;
    }

    private void calculateFromDiagonalAndArea(double rDiagonal, double rArea) {
        this.area = rArea;
        this.diagonalFst = rDiagonal;
        this.diagonalSnd = 2 * rArea / rDiagonal;

        this.sideLength = Math.sqrt(Math.pow(diagonalFst / 2, 2) + Math.pow(diagonalSnd / 2, 2));
    }

    private void calculateFromDiagonalAndSidesideLength(double rDiagonal, double rsideLength) {
        this.diagonalFst = rDiagonal;
        this.sideLength = rsideLength;

        this.diagonalSnd = Math.sqrt(Math.pow(rsideLength, 2) - Math.pow(rDiagonal / 2, 2)) * 2;
        this.area = (this.diagonalFst * this.diagonalSnd) / 2;
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
        } else {
            System.out.println("Invalid input, the area and side Length combination is not possible for a rhombus.");
        }
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\nRhombus"
                + "\nSide Length: " + String.format("%.2f", this.sideLength)
                + "\nFirst Diagonal: " + String.format("%.2f", this.diagonalFst)
                + "\nSecond Diagonal: " + String.format("%.2f", this.diagonalSnd)
                + "\nArea: " + String.format("%.2f", this.area);
    }

    @Override
    public String toString() {
        return "[Rhombus,"
                + " Side Length: " + String.format("%.2f", this.sideLength)
                + " First Diagonal: " + String.format("%.2f", this.diagonalFst)
                + " Second Diagonal: " + String.format("%.2f", this.diagonalSnd)
                + " Area: " + String.format("%.2f", this.area);
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
}