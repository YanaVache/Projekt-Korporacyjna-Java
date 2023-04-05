class Rhombus extends Figure {
    private double length;
    private double area;
    private double diagonalFst;
    private double diagonalSnd;

    public Rhombus(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromDiagonals(a, b);
            case 2 -> calculateFromDiagonalAndArea(a, b);
            case 3 -> calculateFromDiagonalAndSideLength(a, b);
            default -> System.out.println("Rhombus: Option Error");
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

    @Override
    public String toString() {
        return "--------------------"
                + "\nRhombus"
                + "\nSide Length: " + String.format("%.2f", this.length)
                + "\nFirst Diagonal: " + String.format("%.2f", this.diagonalFst)
                + "\nSecond Diagonal: " + String.format("%.2f", this.diagonalSnd)
                + "\nArea: " + String.format("%.2f", this.area);
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