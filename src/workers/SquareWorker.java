package workers;

import figures.Square;

public class SquareWorker implements IFigureFactoryWorker {
    @Override
    public boolean AcceptsParameters(FigureType fig, Double[] properties) {
      return fig == FigureType.Square && properties != null && properties.length == 1;
    }

    @Override
    public boolean AcceptsOption(FigureType fig, int option) {
        return fig == FigureType.Square && (option == 1 || option == 2 || option == 3);
    }

    @Override
    public String[] getProperties(int option) {
      return Square.getRequiredProperties(option);
    }

    @Override
    public Square CreateFigure(Double[] properties, int option) {
        return new Square(properties[0], option);
    }
  
}
