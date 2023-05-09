package workers;

import figures.Elipse;
import figures.Figure;
import figures.Rhombus;

public class ElipseWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
        return fig == FigureType.Elipse && properties != null && properties.length == 2;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.Elipse && (option == 1 || option == 2 || option == 3);
    }

    @Override
    public String[] getProperties(int option) {
        return Elipse.getRequiredProperties(option);
    }

    @Override
    public Figure CreateFigure(Double[] properties, int option) {
        return new Elipse(properties[0], properties[1], option);
    }
}
