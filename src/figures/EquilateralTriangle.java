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

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create an Equilateral Triangle?
                1 - From Edge Length
                2 - From Area
                3 - From Perimeter
                4 - From Height
                0 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[]{"Edge Length"};
            }
            case 2 -> {
                return new String[]{"Area"};
            }
            case 3 -> {
                return new String[]{"Perimeter"};
            }
            case 4 -> {
                return new String[]{"Height"};
            }
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
    public String prettyString() {
        return "--------------------"
                + "\nEquilateral Triangle"
                + "\nEdge Length: " + String.format("%.2f", this.edgeLength)
                + "\nArea: " + String.format("%.2f", this.area)
                + "\nPerimeter: " + String.format("%.2f", this.perimeter)
                + "\nHeight: " + String.format("%.2f", this.height);
    }

    @Override
    public String toString() {
        return "[Equilateral Triangle"
                + " Edge Length: " + String.format("%.2f", this.edgeLength)
                + " Area: " + String.format("%.2f", this.area)
                + " Perimeter: " + String.format("%.2f", this.perimeter)
                + " Height: " + String.format("%.2f", this.height) + "]";
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
}
