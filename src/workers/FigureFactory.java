package workers;

import java.util.ArrayList;

import figures.*;

public class FigureFactory {
    private ArrayList<IFigureFactoryWorker> _workers = new ArrayList<IFigureFactoryWorker>();

    public FigureFactory() {
        _workers.add(new SquareWorker());
        _workers.add(new CircleWorker());
        _workers.add(new RectangleWorker());
        _workers.add(new RhombusWorker());
        _workers.add(new EquilateralTriangleWorker());
        _workers.add(new IsoscelesTriangleWorker());
        _workers.add(new OrthogonalTriangleWorker());
        _workers.add(new EllipseWorker());
        _workers.add(new AnyTriangleWorker());
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