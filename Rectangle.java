class Rectangle extends Figure {
    private double length;
    private double width;
    private double area;
    private double diagonal;

    public Rectangle(double a, double b, int option) {
        switch (option) {
            case 1 -> calculateFromLengthAndWidth(a, b);
            case 2 -> calculateFromDiagonalAndWidth(a, b);
            case 3 -> calculateFromAreaAndWidth(a, b);
            case 4 -> calculateFromAreaAndDiagonal(a, b);
        }
    }

    private void calculateFromLengthAndWidth(double l, double w) {
        this.length = l;
        this.width = w;
        this.area = l * w;
        this.diagonal = Math.sqrt(Math.pow(l, 2) + Math.pow(w, 2));
    }

    private void calculateFromDiagonalAndWidth(double d, double w) {
        this.diagonal = d;
        this.width = w;
        this.length = Math.sqrt(Math.pow(d, 2) - Math.pow(w, 2));
        this.area = this.length * this.width;
    }

    private void calculateFromAreaAndWidth(double a, double w) {
        this.area = a;
        this.width = w;
        this.length = a / w;
        this.diagonal = Math.sqrt(Math.pow(this.length, 2) + Math.pow(this.width, 2));
    }

    private void calculateFromAreaAndDiagonal(double a, double d) {
        this.area = a;
        this.diagonal = d;
        this.length = Math.sqrt(Math.pow(d, 2) / 2 + Math.sqrt(Math.pow(d, 4) / 4 - Math.pow(a, 2)));
        this.width = a / this.length;
    }

    @Override
    public String toString() {
        return "--------------------"
                + "\nRectangle"
                + "\nLength: " + String.format("%.2f", this.length)
                + "\nWidth: " + String.format("%.2f", this.width)
                + "\nDiagonal: " + String.format("%.2f", this.diagonal)
                + "\nArea: " + String.format("%.2f", this.area);
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getArea() {
        return area;
    }

    public double getDiagonal() {
        return diagonal;
    }
}

