package figures;

public abstract class Figure implements Comparable<Figure> {
    public abstract double getArea();

    public abstract String prettyString();

    @Override
    public int compareTo(Figure otherFigure) {
        return Double.compare(this.getArea(), otherFigure.getArea());
    }
}
