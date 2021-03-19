package hw2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class PercolationStats {
    private ArrayList<Double> estimateRes;
    private int times;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        } else {
            double prop;
            estimateRes = new ArrayList<>();
            times = T;
            while (T > 0) {
                Percolation graph = pf.make(N);
                while (!graph.percolates()) {
                    int row = StdRandom.uniform(N);
                    int col = StdRandom.uniform(N);
                    graph.open(row, col);
                }
                prop = graph.numberOfOpenSites() * 1.0 / (N * N);
                estimateRes.add(prop);
                T -= 1;
            }
        }

    }
    public double mean() {
        double sum = 0.0;
        for (Double d : estimateRes) {
            sum += d;
        }
        return sum / times;

    }
    public double stddev() {
        double mean = mean();
        double sum = 0.0;
        for (Double d : estimateRes) {
            sum += Math.pow((d - mean), 2);
        }
        return Math.pow((sum / (times - 1)), 0.5);

    }
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.pow(times, 0.5));

    }
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.pow(times, 0.5));

    }
}
