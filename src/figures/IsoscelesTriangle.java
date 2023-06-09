package figures;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import Config.Config;

public class IsoscelesTriangle extends Figure {
    private double armLength;
    private double baseLength;
    private double area;
    private double perimeter;
    private double heightDroppedOnBase;
    private Date timeCreated;

    private static List<IsoscelesTriangle> createdFigures = new ArrayList<>();

    public IsoscelesTriangle(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromArmLengthAndBaseLength(a, b);
            case 2 -> calculateFromArmLengthAndArea(a, b);
            case 3 -> calculateFromArmLengthAndHeight(a, b);
            case 4 -> calculateFromBaseLengthAndArea(a, b);
            case 5 -> calculateFromBaseLengthAndHeight(a, b);
            case 6 -> calculateFromAreaAndHeight(a, b);
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    public static void printGuide() {
        System.out.println(Config.bundle.getString("figure.create_isosceles_triangle_guide"));
    }

    public static String[] getRequiredProperties(int option) {
        switch (option) {
            case 1 -> {
                return new String[] { "Arm Length", "Base Length" };
            }
            case 2 -> {
                return new String[] { "Arm Length", "Area" };
            }
            case 3 -> {
                return new String[] { "Arm Length", "Height" };
            }
            case 4 -> {
                return new String[] { "Base Length", "Area" };
            }
            case 5 -> {
                return new String[] { "Base Length", "Height" };
            }
            case 6 -> {
                return new String[] { "Area", "Height" };
            }
            default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
        }
    }

    private void calculateFromArmLengthAndBaseLength(double arm, double base) {
        this.armLength = arm;
        this.baseLength = base;
        this.heightDroppedOnBase = Math.sqrt(arm * arm - (base * base) / 4);
        this.area = (base * this.heightDroppedOnBase) / 2;
        this.perimeter = 2 * arm + base;
        this.timeCreated = new Date();
    }

    private void calculateFromArmLengthAndArea(double arm, double area) {
        this.armLength = arm;
        this.area = area;
        this.baseLength = Math.sqrt(2 * (Math.pow(arm, 2) - Math.sqrt(Math.pow(arm, 4) - 4 * Math.pow(area, 2))));
        this.heightDroppedOnBase = 2 * area / this.baseLength;
        this.perimeter = 2 * this.armLength + this.baseLength;
        this.timeCreated = new Date();
    }

    private void calculateFromArmLengthAndHeight(double arm, double height) {
        this.armLength = arm;
        this.heightDroppedOnBase = height;
        this.baseLength = 2 * Math.sqrt(arm * arm - height * height);
        this.area = (this.baseLength * this.heightDroppedOnBase) / 2;
        this.perimeter = 2 * this.armLength + this.baseLength;
        this.timeCreated = new Date();
    }

    private void calculateFromBaseLengthAndArea(double base, double area) {
        this.baseLength = base;
        this.area = area;
        this.heightDroppedOnBase = (2 * area) / base;
        this.armLength = Math.sqrt((base * base) / 4 + this.heightDroppedOnBase * this.heightDroppedOnBase);
        this.perimeter = 2 * this.armLength + this.baseLength;
        this.timeCreated = new Date();
    }

    private void calculateFromBaseLengthAndHeight(double base, double height) {
        this.baseLength = base;
        this.heightDroppedOnBase = height;
        this.armLength = Math.sqrt((base * base) / 4 + height * height);
        this.area = (base * height) / 2;
        this.perimeter = 2 * this.armLength + this.baseLength;
        this.timeCreated = new Date();
    }

    private void calculateFromAreaAndHeight(double area, double height) {
        this.area = area;
        this.heightDroppedOnBase = height;
        this.baseLength = (2 * area) / height;
        this.armLength = Math.sqrt((this.baseLength * this.baseLength) / 4 + height * height);
        this.perimeter = 2 * this.armLength + this.baseLength;
        this.timeCreated = new Date();
    }

    @Override
    public String prettyString() {
        return "--------------------"
                + "\n" + Config.bundle.getString("figure.type_isosceles_triangle")
                + "\n" + Config.bundle.getString("figure.arm") + ": " + String.format(Config.format, this.armLength)
                + "\n" + Config.bundle.getString("figure.base") + ": " + String.format(Config.format, this.baseLength)
                + "\n" + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + "\n" + Config.bundle.getString("figure.perimeter") + ": "
                + String.format(Config.format, this.perimeter)
                + "\n" + Config.bundle.getString("figure.height_dropped_on_base") + ": "
                + String.format(Config.format, this.heightDroppedOnBase)
                + "\n" + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString();
    }

    @Override
    public String toString() {
        return "[" + Config.bundle.getString("figure.type_isosceles_triangle")
                + " " + Config.bundle.getString("figure.arm") + ": " + String.format(Config.format, this.armLength)
                + " " + Config.bundle.getString("figure.base") + ": " + String.format(Config.format, this.baseLength)
                + " " + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
                + " " + Config.bundle.getString("figure.perimeter") + ": "
                + String.format(Config.format, this.perimeter)
                + " " + Config.bundle.getString("figure.height_dropped_on_base") + ": "
                + String.format(Config.format, this.heightDroppedOnBase)
                + " " + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString()
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

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    @Override
    public Circle getCircumscribedCircle() {
        return new Circle(this.armLength * 2 + this.baseLength / (4 * this.area), 2);
    }

    @Override
    public Figure getDoubledAreaFigure() {
        return new IsoscelesTriangle(Math.sqrt(2) * this.baseLength, Math.sqrt(2) * this.heightDroppedOnBase, 5);
    }

    public int getVertices() {
        return 3;
    }

    public static boolean isDuplicate(IsoscelesTriangle figure) {
        for (IsoscelesTriangle existingFigure : createdFigures) {
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
        IsoscelesTriangle other = (IsoscelesTriangle) obj;
    
        return Double.compare(this.armLength, other.armLength) == 0 &&
               Double.compare(this.baseLength, other.baseLength) == 0 &&
               Double.compare(this.area, other.area) == 0 &&
               Double.compare(this.perimeter, other.perimeter) == 0 &&
               Double.compare(this.heightDroppedOnBase, other.heightDroppedOnBase) == 0;
    }
}
