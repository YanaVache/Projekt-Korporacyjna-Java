import figures.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.io.FileWriter;
import java.io.IOException;

//import Config.FigureAdapter;
import com.google.gson.GsonBuilder;

import Config.Config;

import com.google.gson.Gson;

import java.util.concurrent.CompletableFuture;

import workers.*;

public class Program {
    private ArrayList<Figure> createdFigures = new ArrayList<>();
    private FigureFactory factory = new FigureFactory();

    // TODO: Stringi wynieść, jakieś generyki zamiast switchowania po cyferkach,
    // więcej testów
    public void runProgram() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Config.bundle.getString("menu.main"));
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
                        Ellipse.printGuide();
                        CreateFigure(scanner, FigureType.Ellipse);
                    }
                    case 9 -> {
                        IsoscelesTrapezoid.printGuide();
                        CreateFigure(scanner, FigureType.IsoscelesTrapezoid);
                    }
                    case 10 -> {
                        AnyTriangle.printGuide();
                        CreateFigure(scanner, FigureType.AnyTriangle);
                    }
                    case 11 -> {
                        RegularHexagon.printGuide();
                        CreateFigure(scanner, FigureType.RegularHexagon);
                    }
                    case 12 -> showFiguresList(scanner);
                    case 13 -> saveFiguresToFile(scanner);
                    case 14 -> changeLanguage(scanner);
                    case -1 -> configureFormat(scanner);
                    case 0 -> {
                        System.out.println(Config.bundle.getString("menu.exit"));
                        return;
                    }
                    default -> System.out.println(Config.bundle.getString("menu.wrong_number"));
                }
            } catch (InputMismatchException e) {
                System.out.println(Config.bundle.getString("menu.wrong_input"));
                scanner.next();
            }
        }
    }

    private void changeLanguage(Scanner scanner){
        System.out.println(Config.bundle.getString("menu.language_option"));
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    Config.bundle = ResourceBundle.getBundle("language", new Locale("en", "US"));
                }
                case 2 -> {
                    Config.bundle = ResourceBundle.getBundle("language", new Locale("pl", "PL"));
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println(Config.bundle.getString("menu.wrong_number"));
            }
        } catch (InputMismatchException e) {
            System.out.println(Config.bundle.getString("menu.wrong_input"));
            scanner.next();
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
            System.out.println(Config.bundle.getString("menu.wrong_input"));
            scanner.nextLine();
        }
    }

    private Double[] inputProperties(Scanner scanner, String[] props) {
        Double[] values = new Double[props.length];
        for (int i = 0; i < props.length; i++) {
            System.out.printf(Config.bundle.getString("menu.enter"), props[i]);
            values[i] = scanner.nextDouble();
        }
        return values;
    }

    // TODO: Switch albo coś ładniejszego? opcja i porządek
    private void sortFigures(int option) {
        Comparator<Figure> comparator = null;

        switch (option) {
            case 1:
                comparator = Comparator.comparingInt(Figure::getVertices);
                break;
            case 2:
                comparator = Comparator.comparingInt(Figure::getVertices).reversed();
                break;
            case 3:
                comparator = Comparator.comparingDouble(Figure::getArea);
                break;
            case 4:
                comparator = Comparator.comparingDouble(Figure::getArea).reversed();
                break;
            case 5:
                comparator = Comparator.comparingDouble(Figure::getPerimeter);
                break;
            case 6:
                comparator = Comparator.comparingDouble(Figure::getPerimeter).reversed();
                break;
            case 7:
                comparator = Comparator.comparing(Figure::getTimeCreated);
                break;
            case 8:
                comparator = Comparator.comparing(Figure::getTimeCreated).reversed();
                break;
            default:
                throw new IllegalArgumentException(Config.bundle.getString("menu.wrong_number"));
        }

        if (comparator == null) {
            throw new IllegalArgumentException(Config.bundle.getString("menu.wrong_number"));
        }

        createdFigures.sort(comparator);
    }

    private void showFiguresList(Scanner scanner) {
        while (true) {
            if (createdFigures.isEmpty()) {
                System.out.println(Config.bundle.getString("menu.no_figures"));
                return;
            }

            int finalChoice = 0;
            System.out.println(Config.bundle.getString("menu.show_figures"));

            try {
                int choice = scanner.nextInt();
                if (choice == 0) {
                    return;
                }
                if (choice >= 1 && choice <= 8) {
                    finalChoice = choice;
                    sortFigures(finalChoice);
                } else {
                    System.out.println(Config.bundle.getString("menu.wrong_number"));
                }
            } catch (InputMismatchException e) {
                System.out.println(Config.bundle.getString("menu.wrong_number"));
                scanner.next();
            }

            System.out.println(Config.bundle.getString("menu.created_figures"));
            printFigures();
            pickAction(scanner);
        }
    }

    public CompletableFuture<Void> saveFiguresToFileAsync(String fileName) {
        return CompletableFuture.runAsync(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (Figure figure : createdFigures) {
                    writer.write(figure.toString());
                    writer.newLine();
                }
                System.out.println(Config.bundle.getString("menu.figures_saved") + fileName);
            } catch (IOException e) {
                System.out.println(Config.bundle.getString("menu.saving_error"));
            }
        });
    }

    private void saveFiguresToFile(Scanner scanner) {
        System.out.println(Config.bundle.getString("menu.enter_filename"));
        String fileName = scanner.next();

        CompletableFuture<Void> saveFuture = saveFiguresToFileAsync(fileName);
        saveFuture.join();
    }

    // TODO: Poniższe metody jakoś wynieść do abstrakcji
    private void pickAction(Scanner scanner) {
        while (true) {
            System.out.println(Config.bundle.getString("menu.pick_action"));
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
                } else if (choice == 4) {
                    saveFiguresToJson(scanner);
                } else {
                    System.out.println(Config.bundle.getString("menu.wrong_number"));
                }
            } catch (InputMismatchException e) {
                System.out.println(Config.bundle.getString("menu.wrong_input"));
            } catch (ArithmeticException e) {
                System.out.println(Config.bundle.getString("menu.cant_circle"));
            }

            System.out.println(Config.bundle.getString("menu.created_figures"));
            printFigures();
        }
    }

    private void saveFiguresToJson(Scanner scanner) {

        List<Figure> selectedFigures = new ArrayList<>();

        while (true) {
            printFigures();
            System.out.println(Config.bundle.getString("menu.save_to_json"));
            try {
                int choice = scanner.nextInt();
                if (choice == -1) {
                    return;
                } else if (choice == 0) {
                    Gson gson = new GsonBuilder().setPrettyPrinting()
                            // .registerTypeAdapter(Figure.class, new FigureAdapter())
                            .create();
                    String json = gson.toJson(selectedFigures);

                    try (FileWriter writer = new FileWriter("src/values/figures.json")) {
                        writer.write(json);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                } else if (choice > createdFigures.size()) {
                    throw new InputMismatchException();
                } else {
                    Figure figure = createdFigures.get(choice - 1);
                    if (!selectedFigures.contains(figure)) {
                        selectedFigures.add(figure);
                    } else {
                        System.out.println(Config.bundle.getString("menu.figure_already_selected"));
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println(Config.bundle.getString("menu.wrong_input"));
            }

        }
    }

    private void printFigures() {
        int i = 1;
        for (Figure figure : createdFigures) {
            System.out.printf("\n#%d ", i++);
            System.out.println(figure);
        }
    }

    // TODO: Zmienić nazwę tej metody
    private void createDziwneCircle(Scanner scanner) {
        while (true) {
            System.out.println(Config.bundle.getString("menu.create_weird_circle"));
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
                System.out.println(Config.bundle.getString("menu.wrong_input"));
            } catch (ArithmeticException e) {
                System.out.println(Config.bundle.getString("menu.cant_circle"));
            }
        }
    }

    private void doubleTheAreaOfFigure(Scanner scanner) {
        while (true) {
            System.out.println(Config.bundle.getString("menu.double_area"));
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
                System.out.println(Config.bundle.getString("menu.wrong_input"));
            }
        }
    }

    private void deleteFigure(Scanner scanner) {
        while (true) {
            System.out.println(Config.bundle.getString("menu.delete_figure"));
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
                System.out.println(Config.bundle.getString("menu.figure_deleted"));

            } catch (InputMismatchException e) {
                System.out.println(Config.bundle.getString("menu.wrong_input"));
            }
        }
    }

    public void configureFormat(Scanner scanner) {
        System.out.println(Config.bundle.getString("menu.configure_format"));
        try {
            int choice = scanner.nextInt();
            if (choice == 0) {
                return;
            }
            if (choice >= 0) {
                Config.format = String.format("%%.%df", choice);
            } else {
                System.out.println(Config.bundle.getString("menu.wrong_number"));
            }
        } catch (InputMismatchException e) {
            System.out.println(Config.bundle.getString("menu.wrong_input"));
        }
    }
}
