package figures;

import java.util.Date;
import Config.Config;

public abstract class Figure implements Comparable<Figure> {

    public abstract Date getTimeCreated();

    public abstract int getVertices();

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract Circle getCircumscribedCircle();

    public abstract Figure getDoubledAreaFigure();

    public static String[] getRequiredProperties(int option) {
        throw new UnsupportedOperationException(Config.bundle.getString("figure.not_supported"));
    }

    public static void printGuide() {
        throw new UnsupportedOperationException(Config.bundle.getString("figure.not_supported"));
    }

    public abstract String prettyString();

    @Override
    public int compareTo(Figure otherFigure) {
        return Double.compare(this.getArea(), otherFigure.getArea());
    }
}
