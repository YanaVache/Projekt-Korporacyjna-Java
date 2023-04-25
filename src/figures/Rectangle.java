package figures;

public class Rectangle extends Figure {
    private double sideLength;
    private double width;
    private double area;
    private double diagonal;

    public Rectangle(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromsideLengthAndWidth(a, b);
            case 2 -> calculateFromDiagonalAndWidth(a, b);
            case 3 -> calculateFromAreaAndWidth(a, b);
            case 4 -> calculateFromAreaAndDiagonal(a, b);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create a Rectangle?
                1 - From Side Length and Width
                2 - From Diagonal and Width
                3 - From Area and Width
                4 - From Area and Diagonal
                5 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[]{"Side Length", "Width"};
            }
            case 2 -> {
                return new String[]{"Diagonal", "Width"};
            }
            case 3 -> {
                return new String[]{"Area", "Width"};
            }
            case 4 -> {
                return new String[]{"Area", "Diagonal"};
            }
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void calculateFromsideLengthAndWidth(double l, double w) {
        this.sideLength = l;
        this.width = w;
        this.area = l * w;
        this.diagonal = Math.sqrt(Math.pow(l, 2) + Math.pow(w, 2));
    }

    private void calculateFromDiagonalAndWidth(double d, double w) {
        this.diagonal = d;
        this.width = w;
        this.sideLength = Math.sqrt(Math.pow(d, 2) - Math.pow(w, 2));
        this.area = this.sideLength * this.width;
    }

    private void calculateFromAreaAndWidth(double a, double w) {
        this.area = a;
        this.width = w;
        this.sideLength = a / w;
        this.diagonal = Math.sqrt(Math.pow(this.sideLength, 2) + Math.pow(this.width, 2));
    }

    private void calculateFromAreaAndDiagonal(double a, double d) {
        this.area = a;
        this.diagonal = d;
        this.sideLength = Math.sqrt(Math.pow(d, 2) / 2 + Math.sqrt(Math.pow(d, 4) / 4 - Math.pow(a, 2)));
        this.width = a / this.sideLength;
    }

    public String prettyString() {
        return "--------------------"
                + "\nRectangle"
                + "\nSide Length: " + String.format("%.2f", this.sideLength)
                + "\nWidth: " + String.format("%.2f", this.width)
                + "\nDiagonal: " + String.format("%.2f", this.diagonal)
                + "\nArea: " + String.format("%.2f", this.area);
    }

    @Override
    public String toString() {
        return "[Rectangle," + "Side Length:" + String.format("%.2f", this.sideLength)
                + " Width: " + String.format("%.2f", this.width)
                + " Diagonal: " + String.format("%.2f", this.diagonal)
                + " Area: " + String.format("%.2f", this.area) + "]";
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

    @Override
    public Circle getCircumscribedCircle() {
        return new Circle(this.diagonal, 2);
    }
}

