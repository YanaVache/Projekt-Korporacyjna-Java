public class Circle implements Figure { // Muszę sprawdzić, czy obliczenia się zgadzają
    private double radius;         // Promień
    private double diamater;       // Średnica
    private double circumference;  // Obwód
    private double area;           // Pole

    public Circle(double n, int option) {
        switch (option) {
            case 1 -> calculateFromRadius(n);
            case 2 -> calculateFromDiameter(n);
            case 3 -> calculateFromCircumference(n);
            case 4 -> calculateFromArea(n);
        }
    }

    private void calculateFromCircumference(double c) {
        this.radius = c / Math.PI / 2;
        this.diamater = 2 * this.radius;
        this.circumference = c;
        this.area = Math.PI * this.radius * this.radius;
    }

    private void calculateFromDiameter(double d) {
        this.radius = d / 2;
        this.diamater = d;
        this.circumference = Math.PI * 2 * this.radius;
        this.area = Math.PI * this.radius * this.radius;
    }

    private void calculateFromRadius(double r) {
        this.radius = r;
        this.diamater = 2 * r;
        this.circumference = Math.PI * 2 * r;
        this.area = Math.PI * r * r;
    }

    private void calculateFromArea(double a) {
        this.radius = Math.sqrt(a / Math.PI);
        this.diamater = 2 * this.radius;
        this.circumference = Math.PI * 2 * this.radius;
        this.area = a;
    }

    @Override
    public String toString() {
        return "--------------------"
                + "\nCircle"
                + "\nRadius: " + String.format("%.2f", this.radius)
                + "\nDiamater: " + String.format("%.2f", this.diamater)
                + "\nCircumference: " + String.format("%.2f", this.circumference)
                + "\nArea: " + String.format("%.2f", this.area)
                + "\n--------------------";
    }
}
