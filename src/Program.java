import figures.*;

import java.util.*;

import workers.*;

public class Program {
    private ArrayList<Figure> createdFigures = new ArrayList<>();
    private FigureFactory factory = new FigureFactory();

    // TODO: Stringi wynieść, jakieś generyki zamiast switchowania po cyferkach, więcej testów
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
                    7 - Orthogonal Triangle
                    8 - Elipse
                    9 - Show all created figures
                    0 - Exit
                    --------------------""");
            try {
                int choice = scanner.nextInt();
                // TODO - przerobić, wrzucić timeCreated do konstruktorów
                switch (choice) {
                    case 1 -> {
                        Circle.printGuide();
                        CreateFigure(scanner, FigureType.Circle);
                    }
                    case 2 -> {
                        Square.printGuide();
                        CreateFigure(scanner, FigureType.Square);
                    }
                    case 3 -> {
                        Rectangle.printGuide();
                        CreateFigure(scanner, FigureType.Rectangle);
                    }
                    case 4 -> {
                        Rhombus.printGuide();
                        CreateFigure(scanner, FigureType.Rhombus);
                    }
                    case 5 -> {
                        EquilateralTriangle.printGuide();
                        CreateFigure(scanner, FigureType.EquilateralTriangle);
                    }
                    case 6 -> {
                        IsoscelesTriangle.printGuide();
                        CreateFigure(scanner, FigureType.IsoscelesTriangle);
                    }
                    case 7 -> {
                        OrthogonalTriangle.printGuide();
                        CreateFigure(scanner, FigureType.OrthogonalTriangle);
                    }
                    case 8 -> {
                        Elipse.printGuide();
                        CreateFigure(scanner, FigureType.Elipse);
                    }
                    case 9 -> showFiguresList(scanner);
                    case 0 -> {
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

    // TODO: Zmienić try catch NPE na coś ładnego xD
    private void CreateFigure(Scanner scanner, FigureType fig) {
        try {
            int option = scanner.nextInt();
            if (option == 0) {
                return;
            }
            String[] requiredProperties = factory.getProperties(fig, option);
            Double[] properties = inputProperties(scanner, requiredProperties);
            Figure newFigure = factory.createFigure(fig, properties, option);
            try {
                System.out.println(newFigure.prettyString());
                createdFigures.add(newFigure);
            } catch (NullPointerException ignored) {

            }
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

    // TODO: Switch albo coś ładniejszego? opcja i porządek
    private void sortFigures(int option) {
        Comparator<Figure> comparator = null;

        switch (option / 10) {
            case 1 -> comparator = Comparator.comparingInt(Figure::getVertices);
            case 2 -> comparator = Comparator.comparingInt(Figure::getVertices).reversed();
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Wrong option");
        }

        switch (option % 10) {
            case 1 -> comparator = comparator.thenComparingDouble(Figure::getArea);
            case 2 -> comparator = comparator.thenComparingDouble(Figure::getArea).reversed();
            case 3 -> comparator = comparator.thenComparingDouble(Figure::getPerimeter);
            case 4 -> comparator = comparator.thenComparingDouble(Figure::getPerimeter).reversed();
            case 5 -> comparator = comparator.thenComparing(Figure::getTimeCreated);
            case 6 -> comparator = comparator.thenComparing(Figure::getTimeCreated).reversed();
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Wrong option");
        }

        createdFigures.sort(comparator);
    }

    private void showFiguresList(Scanner scanner) {
        while (true) {
            if (createdFigures.isEmpty()) {
                System.out.println("No figures created yet");
                return;
            }
            int finalChoice = 0;
            System.out.println("""
                    Choose primary sorting method:
                    1 - Vertices ascending
                    2 - Vertices descending
                    0 - Go back
                    """);
            try {
                int choice = scanner.nextInt();
                if (choice == 0) {
                    return;
                }
                if (choice >= 1 && choice < 3) {
                    finalChoice = choice;
                    finalChoice *= 10;
                } else {
                    System.out.println("Wrong number");
                }

            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                scanner.next();
            }
            System.out.println("""
                    --------------------
                    Choose secondary sorting method:
                    1 - Area ascending
                    2 - Area descending
                    3 - Perimeter ascending
                    4 - Perimeter descending
                    5 - Time Created ascending
                    6 - Time Created descending
                    0 - Go back
                    --------------------""");

            try {
                int choice = scanner.nextInt();
                if (choice == 0) {
                    return;
                }
                if (choice >= 1 && choice < 7) {
                    finalChoice += choice;
                    sortFigures(finalChoice);
                } else {
                    System.out.println("Wrong number");
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
            pickAction(scanner);
        }
    }

    // TODO: Poniższe metody jakoś wynieść do abstrakcji
    private void pickAction(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    0 - Go back
                    1 - Create circle from figure
                    2 - Double the area of figure
                    3 - Delete figure
                    --------------------""");
            try {
                int choice = scanner.nextInt();
                if (choice == 0) {
                    return;
                }
                if (choice == 1) {
                    createDziwneCircle(scanner);
                } else if (choice == 2) {
                    doubleTheAreaOfFigure(scanner);
                } else if (choice == 3) {
                    deleteFigure(scanner);
                } else {
                    System.out.println("Wrong number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            } catch (ArithmeticException e) {
                System.out.println("Can't create circle from this figure");
            }
        }
    }

    // TODO: Zmienić nazwę tej metody
    private void createDziwneCircle(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    0 - Go back
                    # - Create circle from figure #
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
                Circle circumscribedCircle = figure.getCircumscribedCircle();
                if (circumscribedCircle != null) {
                    System.out.println(circumscribedCircle.prettyString());
                    this.createdFigures.add(circumscribedCircle);
                }

            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            } catch (ArithmeticException e) {
                System.out.println("Can't create circle from this figure");
            }
        }
    }

    private void doubleTheAreaOfFigure(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    0 - Go back
                    # - Double the area of figure #
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
                Figure doubledFigure = figure.getDoubledAreaFigure();
                if (doubledFigure != null) {
                    System.out.println(doubledFigure.prettyString());
                    this.createdFigures.add(doubledFigure);
                }

            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            }
        }
    }

    private void deleteFigure(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    0 - Go back
                    # - Delete figure #
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
                createdFigures.remove(figure);
                System.out.println("Figure deleted");

            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            }
        }
    }
}
