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
                    3 - Rectangle
                    4 - Rhombus
                    5 - Exit
                    --------------------""");
            try {
                int choice = scanner.nextInt();
                switch(choice) {
                    case 1:
                        createCircle(scanner);
                        break;
                    case 2:
                        createSquare(scanner);
                        break;
                    case 3:
                        createRectangle(scanner);
                        break;
                    case 4:
                        createRhombus(scanner);
                        break;
                    case 5:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Wrong number");
                        break;
                }   
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                scanner.next();
            }
        }
    }

    private void createFigure(Scanner scanner, int option, int args, String[] s, String figureToCreate) {
        double values[] = new double[args];
        for (int i = 0; i < args; i++) {
            System.out.printf("""
                    --------------------
                    Enter %s
                    --------------------%n""", s[i]);
            double value = scanner.nextDouble();
            if (value <= 0) {
                System.out.printf("%s has to be greater than zero%n", s[i]);
                return;
            }
            values[i] = value;
        }
    
        switch (figureToCreate) {
            case "Circle" -> {
                Circle circle = new Circle(values[0], option);
                System.out.println(circle);
            }
            case "Square" -> {
                Square square = new Square(values[0], option);
                System.out.println(square);
            }
            case "Rectangle" -> {
                Rectangle Rectangle = new Rectangle(values[0], values[1], option);
                System.out.println(Rectangle);
            }
            case "Rhombus" -> {
                Rhombus rhombus = new Rhombus(values[0], values[1], option);
                System.out.println(rhombus);
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
                    case 1 -> createFigure(scanner, option, 1, new String[]{"Radius"}, "Circle");
                    case 2 -> createFigure(scanner, option, 1, new String[]{"Diameter"}, "Circle");
                    case 3 -> createFigure(scanner, option, 1, new String[]{"Circumference"}, "Circle");
                    case 4 -> createFigure(scanner, option, 1, new String[]{"Area"}, "Circle");
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
                    case 1 -> createFigure(scanner, option, 1, new String[]{"Side length"}, "Square");
                    case 2 -> createFigure(scanner, option, 1, new String[]{"Diagonal"}, "Square");
                    case 3 -> createFigure(scanner, option, 1, new String[]{"Area"}, "Square");
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

    private void createRhombus(Scanner scanner){
        while (true) {
            System.out.println("""
                    --------------------
                    How do you want to create a rhombus?
                    1 - From Diagonals
                    2 - From Diagonal and Area
                    3 - From Diagonal and Side Length
                    4 - From Area and Side Length
                    5 - Go back
                    --------------------""");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> createFigure(scanner, option, 2, new String[]{"First Diagonal", "Second Diagonal"}, "Rhombus");
                    case 2 -> createFigure(scanner, option, 2, new String[]{"Diagonal", "Area"}, "Rhombus");
                    case 3 -> createFigure(scanner, option, 2, new String[]{"Diagonal", "Side Length"}, "Rhombus");
                    case 4 -> createFigure(scanner, option, 2, new String[] { "Area", "Side Length" }, "Rhombus");
                    case 5 -> {
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

    private void createRectangle(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    How do you want to create a Rectangle?
                    1 - From Side Lengths
                    2 - From Diagonal and Side Length
                    3 - From Area and Side Length
                    4 - From Area and Diagonal
                    5 - Go back
                    --------------------""");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> createFigure(scanner, option, 2, new String[]{"Side length 1", "Side length 2"}, "Rectangle");
                    case 2 -> createFigure(scanner, option, 2, new String[]{"Diagonal", "Side length"}, "Rectangle");
                    case 3 -> createFigure(scanner, option, 2, new String[]{"Area", "Side length"}, "Rectangle");
                    case 4 -> createFigure(scanner, option, 2, new String[]{"Area", "Diagonal"}, "Rectangle");
                    case 5 -> {
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
