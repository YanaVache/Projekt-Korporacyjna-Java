package workers;

import figures.Figure;
import figures.OrthogonalTriangle;

public class OrthogonalTriangleWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
        return fig == FigureType.OrthogonalTriangle && properties != null && properties.length == 2;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.OrthogonalTriangle && (option == 1 || option == 2 || option == 3 || option == 4);
    }

    @Override
    public String[] getProperties(int option) {
        return OrthogonalTriangle.getRequiredProperties(option);
    }

    @Override
    public Figure CreateFigure(Double[] properties, int option) {
        return new OrthogonalTriangle(properties[0], properties[1], option);
    }
}
