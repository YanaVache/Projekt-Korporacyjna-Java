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
        if (option == 1) {
            this.createdFigures.sort((f1, f2) -> Double.compare(f1.getArea(), f2.getArea()));
        } else if (option == 2) {
            this.createdFigures.sort((f1, f2) -> Double.compare(f2.getArea(), f1.getArea()));
        } else if (option == 3) {
            this.createdFigures.sort((f1, f2) -> Double.compare(f1.getPerimeter(), f2.getPerimeter()));
        } else if (option == 4) {
            this.createdFigures.sort((f1, f2) -> Double.compare(f2.getPerimeter(), f1.getPerimeter()));
        } else if (option == 5) {
            this.createdFigures.sort((f1, f2) -> f1.getTimeCreated().compareTo(f2.getTimeCreated()));
        } else if (option == 6) {
            this.createdFigures.sort((f1, f2) -> f2.getTimeCreated().compareTo(f1.getTimeCreated()));
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
                    sortFigures(choice);
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

    // TODO: Poniższe metody jakoś wynieśc do abstrakcji
    private void pickAction(Scanner scanner) {
        while (true) {
            System.out.println("""
                    --------------------
                    0 - Go back
                    1 - Create circle from figure
                    2 - Double the area of figure
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
}
