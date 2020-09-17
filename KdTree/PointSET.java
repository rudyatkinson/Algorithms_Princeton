import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {

    private final Set<Point2D> points = new TreeSet<>();

    public PointSET() {
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException();
        points.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        return points.contains(p);
    }

    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException();
        List<Point2D> res = new ArrayList<>();
        for (Point2D p : points) {
            if (rect.contains(p)) res.add(p);
        }
        return res;
    }
    
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();
        double nearest = Double.MAX_VALUE;
        Point2D res = null;
        for (Point2D point : points) {
            double distance = point.distanceSquaredTo(p);
            if (distance < nearest) {
                nearest = distance;
                res = point;
            }
        }
        return res;
    }
}
