import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
    private final int x;
    private final int y;

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double p1Slop = slopeTo(p1);
            double p2Slop = slopeTo(p2);
            if (p1Slop == p2Slop)
                return 0;
            else if (p1Slop > p2Slop)
                return 1;
            else
                return -1;
        }
    }

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (this.x == that.x) {//vertical
            if (this.y == that.y)
                return Double.NEGATIVE_INFINITY;
            else
                return Double.POSITIVE_INFINITY;
        }
        if (this.y == that.y)
            return +0;
        else
            return (double) (that.y - this.y) / (double) (that.x - this.x);
    }

    public int compareTo(Point that) {/* YOUR CODE HERE */
        if (this.y < that.y || (this.y == that.y && this.x < that.x))
            return -1;
        else if (this.y == that.y && this.x == that.x)
            return 0;
        else
            return 1;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
