import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    --------------------
                    What figure do you want to create?
                    1 - Circle
                    2 - Square
                    3 - Exit
                    --------------------""");
            try {
                int choice = scanner.nextInt();
                if (choice == 1) {
                    createCircle();
                } else if (choice == 2) {
                    createSquare();
                } else if (choice == 3) {
                    System.out.println("Exiting program...");
                    break;
                } else {
                    System.out.println("Wrong number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                scanner.next();
            }
        }
    }

    private static void createCircle() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    --------------------
                    How do you want to create a circle?
                    1 - From Radius
                    2 - From Diameter
                    3 - From Circumference
                    4 - From Area
                    --------------------""");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        do {
                            System.out.println("""
                                    --------------------
                                    Enter Radius
                                    --------------------""");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Wrong input. Enter a number:");
                                scanner.next();
                            }
                            double radius = scanner.nextDouble();
                            if (radius < 0) {
                                System.out.println("Radius cannot be negative");
                                break;
                            } else {
                                Circle circle = new Circle(radius, option);
                                System.out.println(circle);
                                return;
                            }
                        } while (true);
                    }
                    case 2 -> {
                        do {
                            System.out.println("""
                                    --------------------
                                    Enter Diameter
                                    --------------------""");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Wrong input. Enter a number:");
                                scanner.next();
                            }
                            double diameter = scanner.nextDouble();
                            if (diameter < 0) {
                                System.out.println("Diameter cannot be negative");
                                break;
                            } else {
                                Circle circle = new Circle(diameter, option);
                                System.out.println(circle);
                                return;
                            }
                        } while (true);
                    }
                    case 3 -> {
                        do {
                            System.out.println("""
                                    --------------------
                                    Enter Circumference
                                    --------------------""");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Wrong input. Enter a number:");
                                scanner.next();
                            }
                            double circumference = scanner.nextDouble();
                            if (circumference < 0) {
                                System.out.println("Circumference cannot be negative");
                                break;
                            } else {
                                Circle circle = new Circle(circumference, option);
                                System.out.println(circle);
                                return;
                            }
                        } while (true);
                    }
                    case 4 -> {
                        do {
                            System.out.println("""
                                    --------------------
                                    Enter Area
                                    --------------------""");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Wrong input. Enter a number:");
                                scanner.next();
                            }
                            double area = scanner.nextDouble();
                            if (area < 0) {
                                System.out.println("Area cannot be negative");
                                break;
                            } else {
                                Circle circle = new Circle(area, option);
                                System.out.println(circle);
                                return;
                            }
                        } while (true);
                    }
                    default -> System.out.println("Wrong number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
    }

    private static void createSquare() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    --------------------
                    How do you want to create a square?
                    1 - From Side Length
                    2 - From Diagonal
                    3 - From Area
                    --------------------""");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        do {
                            System.out.println("""
                                    --------------------
                                    Enter Side Length
                                    --------------------""");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Wrong input. Enter a number:");
                                scanner.next();
                            }
                            double sideLength = scanner.nextDouble();
                            if (sideLength < 0) {
                                System.out.println("Side length cannot be negative");
                            } else {
                                Square square = new Square(sideLength, option);
                                System.out.println(square);
                                return;
                            }
                        } while (true);

                    }
                    case 2 -> {
                        do {
                            System.out.println("""
                                    --------------------
                                    Enter Diagonal
                                    --------------------""");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Wrong input. Enter a number:");
                                scanner.next();
                            }
                            double diagonal = scanner.nextDouble();
                            if (diagonal < 0) {
                                System.out.println("Diagonal cannot be negative");
                                break;
                            } else {
                                Square square = new Square(diagonal, option);
                                System.out.println(square);
                                return;
                            }
                        } while (true);
                    }
                    case 3 -> {
                        do {
                            System.out.println("""
                                    --------------------
                                    Enter Area
                                    --------------------""");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Wrong input. Enter a number:");
                                scanner.next();
                            }
                            double area = scanner.nextDouble();
                            if (area < 0) {
                                System.out.println("Area cannot be negative");
                            } else {
                                Square square = new Square(area, option);
                                System.out.println(square);
                                return;
                            }
                        } while (true);
                    }
                    default -> System.out.println("Wrong number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
    }
}
