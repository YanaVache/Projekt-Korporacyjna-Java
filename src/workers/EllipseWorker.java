package workers;

import figures.Ellipse;
import figures.Figure;

public class EllipseWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
        return fig == FigureType.Ellipse && properties != null && properties.length == 2;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.Ellipse && (option == 1 || option == 2 || option == 3);
    }

    @Override
    public String[] getProperties(int option) {
        return Ellipse.getRequiredProperties(option);
    }

    @Override
    public Figure CreateFigure(Double[] properties, int option) {
        return new Ellipse(properties[0], properties[1], option);
    }
}
