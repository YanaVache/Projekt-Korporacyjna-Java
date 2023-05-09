package figures;

import java.util.Date;

public abstract class Figure implements Comparable<Figure> {

    public abstract Date getTimeCreated();

    public abstract int getVertices();

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract Circle getCircumscribedCircle();

    public abstract Figure getDoubledAreaFigure();

    public static String[] getRequiredProperties(int option) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void printGuide() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public abstract String prettyString();

    @Override
    public int compareTo(Figure otherFigure) {
        return Double.compare(this.getArea(), otherFigure.getArea());
    }
}

