package workers;

import figures.Figure;
import figures.IsoscelesTrapezoid;

public class IsoscelesTrapezoidWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
        return fig == FigureType.IsoscelesTrapezoid && properties != null && properties.length >= 3;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.IsoscelesTrapezoid && (option >= 1 && option <= 8);
    }

    @Override
    public String[] getProperties(int option) {
        return IsoscelesTrapezoid.getRequiredProperties(option);
    }

    @Override
    public Figure CreateFigure(Double[] properties, int option) {
        return new IsoscelesTrapezoid(properties[0], properties[1], properties[2], option);
    }
}
