package workers;

import figures.AnyTriangle;

public class AnyTriangleWorker implements IFigureFactoryWorker {
  @Override
  public boolean AcceptsParameters(FigureType fig, Double[] properties) {
    return fig == FigureType.AnyTriangle && properties != null && properties.length == 3;
  }

  @Override
  public boolean AcceptsOption(FigureType fig, int option) {
    return fig == FigureType.AnyTriangle && (option == 1);
  }

  @Override
  public String[] getProperties(int option) {
    return AnyTriangle.getRequiredProperties(option);
  }

  @Override
  public AnyTriangle CreateFigure(Double[] properties, int option) {
    return new AnyTriangle(properties[0], properties[1], properties[2], option);
  }
}
