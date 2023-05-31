package figures;

import java.util.Date;

import Config.Config;

public class AnyTriangle extends Figure {
  private double sideA;
  private double sideB;
  private double sideC;
  private double area;
  private Date timeCreated;

  public AnyTriangle(double a, double b, double c, int option) {
    switch (option) {
      case 1 -> calculateFromSides(a, b, c);
      default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
    }
  }

  public static void printGuide() {
    System.out.println(Config.bundle.getString("figure.create_any_triangle_guide"));
  }

  public static String[] getRequiredProperties(int option) {
    switch (option) {
      case 1 -> {
        return new String[] { "Side A", "Side B", "Side C" };
      }
      default -> throw new IllegalArgumentException(Config.bundle.getString("figure.wrong_option"));
    }
  }

  private void calculateFromSides(double a, double b, double c) {
    this.sideA = a;
    this.sideB = b;
    this.sideC = c;
    this.area = countArea(a, b, c);
    this.timeCreated = new Date();
  }

  private double countArea(double a, double b, double c) {
    double p = (a + b + c) / 2;
    return Math.sqrt(p * (p - a) * (p - b) * (p - c));
  }

  @Override
  public String prettyString() {
    return "--------------------"
        + "\n" + Config.bundle.getString("figure.type_anytriangle")
        + "\n" + Config.bundle.getString("figure.side") + " A: " + String.format(Config.format, this.sideA)
        + "\n" + Config.bundle.getString("figure.side") + " B: " + String.format(Config.format, this.sideB)
        + "\n" + Config.bundle.getString("figure.side") + " C: " + String.format(Config.format, this.sideC)
        + "\n" + Config.bundle.getString("figure.area") + String.format(Config.format, this.area)
        + "\n" + Config.bundle.getString("figure.perimeter")
        + String.format(Config.format, this.sideA + this.sideB + this.sideC)
        + "\n" + Config.bundle.getString("figure.time_created") + this.timeCreated.toString();
  }

  @Override
  public String toString() {
    return "[" + Config.bundle.getString("figure.type_anytriangle")
        + " " + Config.bundle.getString("figure.side") + " A: " + String.format(Config.format, this.sideA)
        + " " + Config.bundle.getString("figure.side") + " B: " + String.format(Config.format, this.sideB)
        + " " + Config.bundle.getString("figure.side") + " C: " + String.format(Config.format, this.sideC)
        + " " + Config.bundle.getString("figure.area") + ": " + String.format(Config.format, this.area)
        + " " + Config.bundle.getString("figure.perimeter") + ": "
        + String.format(Config.format, this.sideA + this.sideB + this.sideC)
        + " " + Config.bundle.getString("figure.time_created") + ": " + this.timeCreated.toString()
        + "]";
  }

  public double getSideA() {
    return sideA;
  }

  public double getSideB() {
    return sideB;
  }

  public double getSideC() {
    return sideC;
  }

  public double getArea() {
    return area;
  }

  public double getPerimeter() {
    return sideA + sideB + sideC;
  }

  public Date getTimeCreated() {
    return timeCreated;
  }

  @Override
  public Circle getCircumscribedCircle() {
    double radius = (sideA * sideB * sideC) / (4 * area);
    return new Circle(radius, 1);
  }

  @Override
  public Figure getDoubledAreaFigure() {
    double newSideA = this.sideA * Math.sqrt(2);
    double newSideB = this.sideB * Math.sqrt(2);
    double newSideC = this.sideC * Math.sqrt(2);
    return new AnyTriangle(newSideA, newSideB, newSideC, 1);
  }

  public int getVertices() {
    return 3;
  }

}
