package figures;

public class IsoscelesTriangle extends Figure {
    private double armLength;
    private double baseLength;
    private double area;
    private double perimeter;
    private double heightDroppedOnBase;

    public IsoscelesTriangle(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromArmLengthAndBaseLength(a, b);
            case 2 -> calculateFromArmLengthAndArea(a, b);
            case 3 -> calculateFromArmLengthAndHeight(a, b);
            case 4 -> calculateFromBaseLengthAndArea(a, b);
            case 5 -> calculateFromBaseLengthAndHeight(a, b);
            case 6 -> calculateFromAreaAndHeight(a, b);
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    public static void printGuide() {
        System.out.println("""
                --------------------
                How do you want to create an Isosceles Triangle?
                1 - From Arm Length and Base Length
                2 - From Arm Length and Area
                3 - From Arm Length and Height
                4 - From Base Length and Area
                5 - From Base Length and Height
                6 - From Area and Height
                0 - Go back
                --------------------""");
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[]{"Arm Length", "Base Length"};
            }
            case 2 -> {
                return new String[]{"Arm Length", "Area"};
            }
            case 3 -> {
                return new String[]{"Arm Length", "Height"};
            }
            case 4 -> {
                return new String[]{"Base Length", "Area"};
            }
            case 5 -> {
                return new String[]{"Base Length", "Height"};
            }
            case 6 -> {
                return new String[]{"Area", "Height"};
            }
            default -> throw new IllegalArgumentException("Wrong option");
        }
    }

    private void calculateFromArmLengthAndBaseLength(double arm, double base) {
        this.armLength = arm;
        this.baseLength = base;
        this.heightDroppedOnBase = Math.sqrt(arm * arm - (base * base) / 4);
        this.area = (base * this.heightDroppedOnBase) / 2;
        this.perimeter = 2 * arm + base;
    }

    private void calculateFromArmLengthAndArea(double arm, double area) {
        this.armLength = arm;
        this.area = area;
        this.baseLength = Math.sqrt(2 * (Math.pow(arm, 2) - Math.sqrt(Math.pow(arm, 4) - 4 * Math.pow(area, 2))));
        this.heightDroppedOnBase = 2 * area / this.baseLength;
        this.perimeter = 2 * this.armLength + this.baseLength;
    }

    private void calculateFromArmLengthAndHeight(double arm, double height) {
        this.armLength = arm;
        this.heightDroppedOnBase = height;
        this.baseLength = 2 * Math.sqrt(arm * arm - height * height);
        this.area = (this.baseLength * this.heightDroppedOnBase) / 2;
        this.perimeter = 2 * this.armLength + this.baseLength;
    }

    private void calculateFromBaseLengthAndArea(double base, double area) {
        this.baseLength = base;
        this.area = area;
        this.heightDroppedOnBase = (2 * area) / base;
        this.armLength = Math.sqrt((base * base) / 4 + this.heightDroppedOnBase * this.heightDroppedOnBase);
        this.perimeter = 2 * this.armLength + this.baseLength;
    }

    private void calculateFromBaseLengthAndHeight(double base, double height) {
        this.baseLength = base;
        this.heightDroppedOnBase = height;
        this.armLength = Math.sqrt((base * base) / 4 + height * height);
        this.area = (base * height) / 2;
        this.perimeter = 2 * this.armLength + this.baseLength;
    }

    private void calculateFromAreaAndHeight(double area, double height) {
        this.area = area;
        this.heightDroppedOnBase = height;
        this.baseLength = (2 * area) / height;
        this.armLength = Math.sqrt((this.baseLength * this.baseLength) / 4 + height * height);
        this.perimeter = 2 * this.armLength + this.baseLength;
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\nIsosceles Triangle"
                + "\nArm Length: " + String.format("%.2f", this.armLength)
                + "\nBase Length: " + String.format("%.2f", this.baseLength)
                + "\nArea: " + String.format("%.2f", this.area)
                + "\nPerimeter: " + String.format("%.2f", this.perimeter)
                + "\nHeight Dropped on Base: " + String.format("%.2f", this.heightDroppedOnBase);
    }

    @Override
    public String toString() {
        return "[Isosceles Triangle"
                + " Arm Length: " + String.format("%.2f", this.armLength)
                + " Base Length: " + String.format("%.2f", this.baseLength)
                + " Area: " + String.format("%.2f", this.area)
                + " Perimeter: " + String.format("%.2f", this.perimeter)
                + " Height Dropped on Base: " + String.format("%.2f", this.heightDroppedOnBase)
                + "]";
    }

    public double getArmLength() {
        return armLength;
    }

    public double getBaseLength() {
        return baseLength;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getHeightDroppedOnBase() {
        return heightDroppedOnBase;
    }

    @Override
    public Circle getCircumscribedCircle() {
        return new Circle(this.armLength * 2 + this.baseLength / (4 * this.area), 2);
    }
}
