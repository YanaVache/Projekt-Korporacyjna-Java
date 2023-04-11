package figures;

public class EquilateralTriangle extends Figure {
    private double edgeLength;
    private double area;
    private double perimeter;
    private double height;

    public EquilateralTriangle(double n, int option) {
        switch (option) {
            case 1 -> calculateFromEdgeLength(n);
            case 2 -> calculateFromArea(n);
            case 3 -> calculateFromPerimeter(n);
            case 4 -> calculateFromHeight(n);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void calculateFromEdgeLength(double a) {
        this.edgeLength = a;
        this.area = (a * a * Math.sqrt(3)) / 4;
        this.perimeter = 3 * a;
        this.height = (a * Math.sqrt(3)) / 2;
    }

    private void calculateFromArea(double a) {
        this.area = a;
        this.edgeLength = 2 * Math.sqrt(a / Math.sqrt(3));
        this.perimeter = 3 * this.edgeLength;
        this.height = (this.edgeLength * Math.sqrt(3)) / 2;
    }

    private void calculateFromPerimeter(double a) {
        this.perimeter = a;
        this.edgeLength = a / 3;
        this.height = (this.edgeLength * Math.sqrt(3)) / 2;
        this.area = (this.edgeLength * this.edgeLength * Math.sqrt(3)) / 4;
    }

    private void calculateFromHeight(double a) {
        this.height = a;
        this.edgeLength = 2 * a / Math.sqrt(3);
        this.perimeter = 3 * this.edgeLength;
        this.area = (this.edgeLength * this.edgeLength * Math.sqrt(3)) / 4;
    }

    @Override
    public String toString() {
        return "--------------------"
                + "\nEquilateral Triangle"
                + "\nEdge Length: " + String.format("%.2f", this.edgeLength)
                + "\nArea: " + String.format("%.2f", this.area)
                + "\nPerimeter: " + String.format("%.2f", this.perimeter)
                + "\nHeight: " + String.format("%.2f", this.height);
    }

}