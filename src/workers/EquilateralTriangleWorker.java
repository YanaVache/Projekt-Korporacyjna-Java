package workers;

import figures.EquilateralTriangle;
import figures.Figure;


public class EquilateralTriangleWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
        return fig == FigureType.EquilateralTriangle && properties != null && properties.length == 1;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.EquilateralTriangle && (option == 1 || option == 2 || option == 3 || option == 4);
    }

    @Override
    public String[] getProperties(int option) {
        return EquilateralTriangle.getRequiredProperties(option);
    }

    @Override
    public Figure CreateFigure(Double[] properties, int option) {
        return new EquilateralTriangle(properties[0], option);
    }
}
