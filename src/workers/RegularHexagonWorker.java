package workers;

import figures.Figure;
import figures.RegularHexagon;

public class RegularHexagonWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
        return fig == FigureType.RegularHexagon && properties != null && properties.length == 1;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.RegularHexagon && (option == 1 || option == 2 || option == 3);
    }

    @Override
    public String[] getProperties(int option) {
        return RegularHexagon.getRequiredProperties(option);
    }
    
    @Override
    public Figure CreateFigure(Double[] properties, int option) {
        return new RegularHexagon(properties[0], option);
    }
}
