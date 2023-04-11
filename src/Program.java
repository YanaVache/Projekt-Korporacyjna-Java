import figures.*;

import java.util.*;

public class Program {
    private ArrayList<Figure> createdFigures = new ArrayList<>();

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
                    5 - Equilateral Triangle
                    6 - Show all created figures
                    7 - Exit
                    --------------------""");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> createCircle(scanner);
                    case 2 -> createSquare(scanner);
                    case 3 -> createRectangle(scanner);
                    case 4 -> createRhombus(scanner);
                    case 5 -> createEquilateralTriangle(scanner);
                    case 6 -> {
                        Collections.sort(this.createdFigures);
                        System.out.println(this.createdFigures);
                    } // Chwilowo z tym sortem
                    case 7 -> {
                        System.out.println("Exiting program...");
                        return;
                    }
                    default -> System.out.println("Wrong number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                scanner.next();
            }
        }
    }

    private void createFigure(Scanner scanner, int option, int args, String[] s, String figureToCreate) {
        double[] values = new double[args];
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
        Figure newFigure;
        switch (figureToCreate) {
            case "Circle" -> newFigure = new Circle(values[0], option);
            case "Square" -> newFigure = new Square(values[0], option);
            case "Rectangle" -> newFigure = new Rectangle(values[0], values[1], option);
            case "Rhombus" -> newFigure = new Rhombus(values[0], values[1], option);
            case "EquilateralTriangle" -> newFigure = new EquilateralTriangle(values[0], option);
            default -> throw new IllegalArgumentException("");
        }
        System.out.println(newFigure.prettyString());
        this.createdFigures.add(newFigure);
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

    private void createRhombus(Scanner scanner) {
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
                    case 1 ->
                            createFigure(scanner, option, 2, new String[]{"First Diagonal", "Second Diagonal"}, "Rhombus");
                    case 2 -> createFigure(scanner, option, 2, new String[]{"Diagonal", "Area"}, "Rhombus");
                    case 3 -> createFigure(scanner, option, 2, new String[]{"Diagonal", "Side Length"}, "Rhombus");
                    case 4 -> createFigure(scanner, option, 2, new String[]{"Area", "Side Length"}, "Rhombus");
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
                    case 1 ->
                            createFigure(scanner, option, 2, new String[]{"Side length 1", "Side length 2"}, "Rectangle");
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

    private void createEquilateralTriangle(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    How do you want to create a Equilateral Triangle?
                    1 - From Edge Length
                    2 - From Area
                    3 - From Perimeter
                    4 - From Height
                    5 - Go back
                    --------------------""");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> createFigure(scanner, option, 1, new String[]{"Edge Length"}, "EquilateralTriangle");
                    case 2 -> createFigure(scanner, option, 1, new String[]{"Area"}, "EquilateralTriangle");
                    case 3 -> createFigure(scanner, option, 1, new String[]{"From Perimeter"}, "EquilateralTriangle");
                    case 4 -> createFigure(scanner, option, 1, new String[]{"From Height"}, "EquilateralTriangle");
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

