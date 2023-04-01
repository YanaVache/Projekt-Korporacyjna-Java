import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public void runProgram() {
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
                    createCircle(scanner);
                } else if (choice == 2) {
                    createSquare(scanner);
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

    private void createFigure(Scanner scanner, int option, String s, String figureToCreate) {
        System.out.printf("""
                --------------------
                Enter %s
                --------------------%n""", s);
        double value = scanner.nextDouble();
        if (value <= 0) {
            System.out.printf("%s has to be greater than zero%n", s);
            return;
        }
        switch (figureToCreate) {
            case "Circle" -> {
                Circle circle = new Circle(value, option);
                System.out.println(circle);
            }
            case "Square" -> {
                Square square = new Square(value, option);
                System.out.println(square);
            }
            default -> throw new IllegalArgumentException("");
        }
    }

    private void createCircle(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    How do you want to create a circle?
                    1 - From Radius
                    2 - From Diameter
                    3 - From Circumference
                    4 - From Area
                    5 - Go back
                    --------------------""");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> createFigure(scanner, option, "Radius", "Circle");
                    case 2 -> createFigure(scanner, option, "Diameter", "Circle");
                    case 3 -> createFigure(scanner, option, "Circumference", "Circle");
                    case 4 -> createFigure(scanner, option, "Area", "Circle");
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Wrong number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, enter a positive number");
                scanner.nextLine();
            }
        }
    }

    private void createSquare(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    How do you want to create a square?
                    1 - From Side Length
                    2 - From Diagonal
                    3 - From Area
                    4 - Go back
                    --------------------""");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> createFigure(scanner, option, "Side length", "Square");
                    case 2 -> createFigure(scanner, option, "Diagonal", "Square");
                    case 3 -> createFigure(scanner, option, "Area", "Square");
                    case 4 -> {
                        return;
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
