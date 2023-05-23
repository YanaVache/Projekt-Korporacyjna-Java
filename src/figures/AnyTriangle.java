package figures;

import Config.Config;

import java.util.Date;

public class AnyTriangle extends Figure {
  private double sideA;
  private double sideB;
  private double sideC;
  private double area;
  private Date timeCreated;

  public AnyTriangle(double a, double b, double c, int option) {
    switch (option) {
      case 1 -> calculateFromSides(a, b, c);
      default -> throw new IllegalArgumentException("Wrong option");
    }
  }

  public static void printGuide() {
    System.out.println(
        """
            --------------------
            How do you want to create a AnyTriangle?
            1 - From Sides
            0 - Go back
            --------------------""");
  }
  
  public static String[] getRequiredProperties(int option) {
    switch (option) {
      case 1 -> {
        return new String[] { "Side A", "Side B", "Side C" };
      }
      default -> throw new IllegalArgumentException("Wrong option");
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
            + "\nAny Triangle"
            + "\nSide A: " + String.format(Config.format, this.sideA)
            + "\nSide B: " + String.format(Config.format, this.sideB)
            + "\nSide C: " + String.format(Config.format, this.sideC)
            + "\nArea: " + String.format(Config.format, this.area)
            + "\nPerimeter: " + String.format(Config.format, this.sideA + this.sideB + this.sideC)
        + "\nTime created: " + this.timeCreated.toString();
  }
    
  @Override
  public String toString() {
    return "[Any Triangle"
        + " Side A: " + String.format(Config.format, this.sideA)
        + " Side B: " + String.format(Config.format, this.sideB)
        + " Side C: " + String.format(Config.format, this.sideC)
        + " Area: " + String.format(Config.format, this.area)
        + " Perimeter: " + String.format(Config.format, this.sideA + this.sideB + this.sideC)
        + " Time created: " + this.timeCreated.toString()
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
    double newSideA = sideA * 2;
    double newSideB = sideB * 2;
    double newSideC = sideC * 2;
    return new AnyTriangle(newSideA, newSideB, newSideC, 1);
  }

  public int getVertices() {
    return 3;
  }

}
