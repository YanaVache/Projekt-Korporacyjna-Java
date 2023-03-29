import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                --------------------
                What figure do you want to create?
                1 - Circle
                2 - Square
                --------------------""");
        Scanner scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();
            if (choice == 1) {
                createCircle();
            } else if (choice == 2) {
                createSquare();
            } else {
                System.out.println("Wrong number");
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }
    }

    private static void createCircle() {
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("""
                            --------------------
                            Enter Radius
                            --------------------""");
                    Circle circle = new Circle(scanner.nextDouble(), option); // 40. i 41. linijka się powtarzają
                    System.out.println(circle);                               // Potrzebna drobna refaktoryzacja
                }                                                             // Wywalić do osobnej metody?
                case 2 -> {                                                   // Albo stworzyć nową w klasie Circle
                    System.out.println("""
                            --------------------
                            Enter Diameter
                            --------------------""");
                    Circle circle = new Circle(scanner.nextDouble(), option);
                    System.out.println(circle);
                }
                case 3 -> {
                    System.out.println("""
                            --------------------
                            Enter Circumference
                            --------------------""");
                    Circle circle = new Circle(scanner.nextDouble(), option);
                    System.out.println(circle);
                }
                case 4 -> {
                    System.out.println("""
                            --------------------
                            Enter Area
                            --------------------""");
                    Circle circle = new Circle(scanner.nextDouble(), option);
                    System.out.println(circle);
                }
                default -> System.out.println("Wrong number");
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }
    }

    private static void createSquare() {
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("""
                            --------------------
                            Enter Side Length
                            --------------------""");
                    Square square = new Square(scanner.nextDouble(), option);
                    System.out.println(square);
                }
                case 2 -> {
                    System.out.println("""
                            --------------------
                            Enter Diagonal
                            --------------------""");
                    Square square = new Square(scanner.nextDouble(), option);
                    System.out.println(square);
                }
                case 3 -> {
                    System.out.println("""
                            --------------------
                            Enter Area
                            --------------------""");
                    Square square = new Square(scanner.nextDouble(), option);
                    System.out.println(square);
                }
                default -> System.out.println("Wrong number");
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }
    }
}
