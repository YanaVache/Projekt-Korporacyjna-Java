package workers;

import figures.Figure;
import figures.Rhombus;

public class RhombusWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
        return fig == FigureType.Rhombus && properties != null && properties.length == 2;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.Rhombus && (option == 1 || option == 2 || option == 3 || option == 4);
    }

    @Override
    public String[] getProperties(int option) {
        return Rhombus.getRequiredProperties(option);
    }

    @Override
    public Figure CreateFigure(Double[] properties, int option) {
        return new Rhombus(properties[0], properties[1], option);
    }
}
