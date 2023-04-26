package workers;

import figures.Circle;
import figures.Figure;

public class CircleWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
        return fig == FigureType.Circle && properties != null && properties.length == 1;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.Circle && (option == 1 || option == 2 || option == 3 || option == 4);
    }

    @Override
    public String[] getProperties(int option) {
        return Circle.getRequiredProperties(option);
    }

    @Override
    public Figure CreateFigure(Double[] properties, int option) {
        return new Circle(properties[0], option);
    }
}
