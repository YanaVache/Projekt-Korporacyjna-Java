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

    private void CreateFigure(Scanner scanner, FigureType fig) {
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
                if (choice == 5) {
                    return;
                }
                if (choice >= 1 && choice < 5) {
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
            createDziwneCircle(scanner);
        }
    }

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
                Circle circumscribedCircle = figure.getCircumscribedCircle();
                if (circumscribedCircle != null) {
                    System.out.println(circumscribedCircle.prettyString());
                }

            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            } catch (ArithmeticException e) {
                System.out.println("Can't create circle from this figure");
            }
        }
    }
}
