import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;


public class FastCollinearPoints {
    public static void main(String[] args) {

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point pArr[] = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            pArr[i] = p;
            p.draw();
        }
        Arrays.sort(pArr);

        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLUE);

        for (int p = 0; p < N - 3; p++) {
            Point slopArr[] = new Point[N - p - 1];
            for (int q = p + 1; q < N; q++) {
                slopArr[q - p - 1] = pArr[q];
            }
            Arrays.sort(slopArr, pArr[p].SLOPE_ORDER);
            int s = 0;
            double sameSlop = pArr[p].slopeTo(slopArr[s]);
            s++;
            int count = 1;
            while (s < N - p - 1) {
                if (sameSlop == pArr[p].slopeTo(slopArr[s])) {
                    count++;
                    s++;
                } else {
                    if (count >= 3) {
                        boolean exHas = false;
                        for (int ex = 0; ex < p; ex++) {
                            if (sameSlop == pArr[p].slopeTo(pArr[ex])) {
                                exHas = true;
                                break;
                            }
                        }
                        if (!exHas) {
                            Point outArr[] = new Point[count + 1];
                            outArr[0] = pArr[p];
                            for (int i = 1; i < count + 1; i++) {
                                outArr[i] = slopArr[s - count + i - 1];
                            }
                            Arrays.sort(outArr);
                            outArr[0].drawTo(outArr[count]);
                            for (int k = 0; k < count; k++) {
                                StdOut.print(outArr[k].toString() + " -> ");
                            }
                            StdOut.println(outArr[count].toString());
                        }
                    }
                    sameSlop = pArr[p].slopeTo(slopArr[s]);
                    count = 1;
                    s++;
                }
            }
            if (count >= 3) {
                boolean exHas = false;
                for (int ex = 0; ex < p; ex++) {
                    if (sameSlop == pArr[p].slopeTo(pArr[ex])) {
                        exHas = true;
                        break;
                    }
                }
                if (!exHas) {
                    Point outArr[] = new Point[count + 1];
                    outArr[0] = pArr[p];
                    for (int i = 1; i < count + 1; i++) {
                        outArr[i] = slopArr[s - count + i - 1];
                    }
                    Arrays.sort(outArr);
                    outArr[0].drawTo(outArr[count]);
                    for (int k = 0; k < count; k++) {
                        StdOut.print(outArr[k].toString() + " -> ");
                    }
                    StdOut.println(outArr[count].toString());
                }
            }
        }
        StdDraw.show(0);

        StdDraw.setPenRadius();
    }
}
