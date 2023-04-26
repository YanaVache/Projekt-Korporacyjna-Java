package workers;

import figures.Figure;
import figures.IsoscelesTriangle;

public class IsoscelesTriangleWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
        return fig == FigureType.IsoscelesTriangle && properties != null && properties.length == 2;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.IsoscelesTriangle && (option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6);
    }

    @Override
    public String[] getProperties(int option) {
        return IsoscelesTriangle.getRequiredProperties(option);
    }

    @Override
    public Figure CreateFigure(Double[] properties, int option) {
        return new IsoscelesTriangle(properties[0], properties[1], option);
    }
}
