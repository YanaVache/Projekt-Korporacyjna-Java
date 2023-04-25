import figures.*;

import java.util.*;

import workers.*;

public class Program {
    private ArrayList<Figure> createdFigures = new ArrayList<>();
    private FigureFactory factory = new FigureFactory();

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
                    6 - Isosceles Triangle
                    7 - Show all created figures
                    8 - Exit
                    --------------------""");
            try {
                int choice = scanner.nextInt();
                // TODO - przerobić + Naprawić "go backa"
                switch (choice) {
                    case 1 -> {
                        Circle.printGuide();
                        NEWCreateFigure(scanner, FigureType.Circle);
                    }
                    case 2 -> {
                        Square.printGuide();
                        NEWCreateFigure(scanner, FigureType.Square);
                    }
                    case 3 -> {
                        Rectangle.printGuide();
                        NEWCreateFigure(scanner, FigureType.Rectangle);
                    }
                    case 4 -> {
                        Rhombus.printGuide();
                        NEWCreateFigure(scanner, FigureType.Rhombus);
                    }
                    case 5 -> {
                        EquilateralTriangle.printGuide();
                        NEWCreateFigure(scanner, FigureType.EquilateralTriangle);
                    }
                    case 6 -> {
                        IsoscelesTriangle.printGuide();
                        NEWCreateFigure(scanner, FigureType.IsoscelesTriangle);
                    }
                    case 7 -> showFiguresList(scanner);
                    case 8 -> {
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

    private void NEWCreateFigure(Scanner scanner, FigureType fig) {
        try {
            int option = scanner.nextInt();
            String[] requiredProperties = factory.getProperties(fig, option);
            Double[] properties = inputProperties(scanner, requiredProperties);
            Figure newFigure = factory.createFigure(fig, properties, option);
            createdFigures.add(newFigure);
            System.out.println(newFigure.prettyString());
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
            scanner.nextLine();
        }
    }

    private Double[] inputProperties(Scanner scanner, String[] props) {
        Double[] values = new Double[props.length];
        for (int i = 0; i < props.length; i++) {
            System.out.printf("""
                    --------------------
                    Enter %s
                    --------------------
                    """, props[i]);
            values[i] = scanner.nextDouble();
        }
        return values;
    }

    // TODO - wszystko poniżej do usunięcia
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
            case "IsoscelesTriangle" -> newFigure = new IsoscelesTriangle(values[0], values[1], option);
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
                    case 1 -> createFigure(scanner, option, 2, new String[]{"First Diagonal", "Second Diagonal"},
                            "Rhombus");
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
                    case 1 -> createFigure(scanner, option, 2, new String[]{"Side length 1", "Side length 2"},
                            "Rectangle");
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

    private void createIsoscelesTriangle(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    How do you want to create a Isosceles Triangle?
                    1 - From Arm Length and Base Length
                    2 - From Arm Length and Area
                    3 - From Arm Length and Height (Dropped on Base)
                    4 - From Base Length and Area
                    5 - From Base Length and Height (Dropped on Base)
                    6 - From Area and Height (Dropped on Base)
                    7 - Go back
                    --------------------""");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> createFigure(scanner, option, 2, new String[]{"Arm Length", "Base Length"},
                            "IsoscelesTriangle");
                    case 2 -> createFigure(scanner, option, 2, new String[]{"Arm Length", "Area"}, "IsoscelesTriangle");
                    case 3 ->
                            createFigure(scanner, option, 2, new String[]{"Arm Length", "Height"}, "IsoscelesTriangle");
                    case 4 ->
                            createFigure(scanner, option, 2, new String[]{"Base Length", "Area"}, "IsoscelesTriangle");
                    case 5 ->
                            createFigure(scanner, option, 2, new String[]{"Base Length", "Height"}, "IsoscelesTriangle");
                    case 6 -> createFigure(scanner, option, 2, new String[]{"Area", "Height"}, "IsoscelesTriangle");
                    case 7 -> {
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

    private void sortFigures(int option) {
        if (option == 1) {
            this.createdFigures.sort((f1, f2) -> Double.compare(f1.getArea(), f2.getArea()));
        } else if (option == 2) {
            this.createdFigures.sort((f1, f2) -> Double.compare(f2.getArea(), f1.getArea()));
        } else if (option == 3) {
            this.createdFigures.sort((f1, f2) -> Double.compare(f1.getPerimeter(), f2.getPerimeter()));
        } else if (option == 4) {
            this.createdFigures.sort((f1, f2) -> Double.compare(f2.getPerimeter(), f1.getPerimeter()));
        }
    }

    private void showFiguresList(Scanner scanner) {
        while (true) {
            if (createdFigures.isEmpty()) {
                System.out.println("No figures created yet");
                return;
            }
            System.out.println("""
                    --------------------
                    Sort by:
                    1 - Area ascending
                    2 - Area descending
                    3 - Perimeter ascending
                    4 - Perimeter descending
                    5 - Go back
                    --------------------""");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> sortFigures(choice);
                    case 2 -> sortFigures(choice);
                    case 3 -> sortFigures(choice);
                    case 4 -> sortFigures(choice);
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Wrong number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                scanner.next();
            }

            System.out.println("Created figures:");
            int i = 1;
            for (Figure figure : createdFigures) {
                System.out.printf("\n#%d ", i++);
                System.out.println(figure);
            }
            createDziwneCircle(scanner);
        }
    }

    // TODO - do wywalenia
    private void createDziwneCircle(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    0 - Go back
                    # - Create circle from figure number #
                    --------------------""");
            try {
                int choice = scanner.nextInt();
                if (choice == 0) {
                    return;
                }
                if (choice > createdFigures.size()) {
                    throw new InputMismatchException();
                }

                Figure figure = createdFigures.get(choice - 1);

                if (figure instanceof Rhombus) {
                    if (((Rhombus) figure).getDiagonalFst() != ((Rhombus) figure).getDiagonal()) {
                        System.out.println("Can't make circle from this figure");
                        return;
                    } else {
                        Rhombus rhombus = (Rhombus) figure;
                        Circle circle = new Circle(rhombus.getDiagonalFst(), 2);

                        System.out.println(circle.prettyString());
                    }
                }

                if (figure instanceof Circle) {
                    Circle circle = (Circle) figure;
                    Circle newCircle = new Circle(circle.getRadius() * 2, 2);

                    System.out.println(newCircle.prettyString());
                }

                if (figure instanceof Square) {
                    Square square = (Square) figure;
                    Circle circle = new Circle(square.getDiagonalLength(), 2);

                    System.out.println(circle.prettyString());
                }

                if (figure instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) figure;
                    Circle circle = new Circle(rectangle.getDiagonal(), 2);

                    System.out.println(circle.prettyString());
                }

                if (figure instanceof EquilateralTriangle) {
                    EquilateralTriangle equilateralTriangle = (EquilateralTriangle) figure;
                    Circle circle = new Circle(equilateralTriangle.getHeight() * 4 / 3, 2);

                    System.out.println(circle.prettyString());
                }

                if (figure instanceof IsoscelesTriangle) {
                    IsoscelesTriangle isoscelesTriangle = (IsoscelesTriangle) figure;
                    Circle circle = new Circle(
                            (isoscelesTriangle.getArmLength() * 2 + isoscelesTriangle.getBaseLength())
                                    / (4 * isoscelesTriangle.getArea()),
                            2);

                    System.out.println(circle.prettyString());
                }

            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            } catch (ArithmeticException e) {
                System.out.println("Can't create circle from this figure");
            }
        }
    }
}
