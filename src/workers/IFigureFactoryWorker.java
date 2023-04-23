package workers;

import figures.Figure;

public interface IFigureFactoryWorker {
  boolean AcceptsParameters(FigureType fig, Double[] properties);
  boolean AcceptsOption(FigureType fig, int option);
  String[] getProperties(int option);
  Figure CreateFigure(Double[] properties, int option);
}
