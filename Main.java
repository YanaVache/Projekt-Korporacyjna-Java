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
            if (scanner.nextInt() == 1) {
                createCircle();
            } // else if 2 createSquare, else print "Wrong numberos"
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
                2 - From Diamater
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
                            Enter Diamater
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

}
