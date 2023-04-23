package workers;
import java.util.ArrayList;

import figures.*;

public class FigureFactory {
  private ArrayList<IFigureFactoryWorker> _workers = new ArrayList<IFigureFactoryWorker>();

  public FigureFactory() {
    _workers.add(new SquareWorker());
    //_workers.add(new CircleFactoryWorker());
    //_workers.add(new RectangleFactoryWorker());
    //_workers.add(new RhombusFactoryWorker());
    //_workers.add(new EquilateralTriangleFactoryWorker());
    //_workers.add(new IsoscelesTriangleFactoryWorker());
  }

  public void addWorker(IFigureFactoryWorker worker) {
    _workers.add(worker);
  }

  public String[] getProperties(FigureType fig, int option) {
    for (IFigureFactoryWorker worker : _workers) {
      if (worker.AcceptsOption(fig, option)) {
        return worker.getProperties(option);
      }
    }

    throw new IllegalArgumentException("Invalid figure type");
  }

  public Figure createFigure(FigureType fig, Double[] properties, int option) {
    for (IFigureFactoryWorker worker : _workers) {
      if (worker.AcceptsParameters(fig, properties)) {
        return worker.CreateFigure(properties, option);
      }
    }

    throw new IllegalArgumentException("Invalid figure type or properties");
  }
}