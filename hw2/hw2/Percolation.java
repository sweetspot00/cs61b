package hw2;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class Percolation {
    private int graphSize;
    private boolean site[][];
    private WeightedQuickUnionUF unionSet;
    private int openNum;
    private int percolateGround;



    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        openNum = 0;
        graphSize = N;
        site = new boolean[N][N];
        for (int i=0; i < N; i += 1) {
            for (int j=0; j<N; j+=1) {
                site[i][j] = false;

            }
        }
        unionSet = new WeightedQuickUnionUF(N*N + 2);
    }
    private int coor2num(int row, int col) {
        return row * graphSize +col + 1;
    }

    public void open(int row, int col) {
        if (row < 0 || row >= graphSize || col < 0 || col >= graphSize) {
            throw new IndexOutOfBoundsException();
        }
        int num = coor2num(row, col);
        if (!site[row][col]) {
            site[row][col] = true;
            openNum += 1;
        }

        //联通
        if (row - 1 >= 0 && site[row - 1][col]) {
            unionSet.union(coor2num(row - 1, col), num);

        }
        if (row + 1 < graphSize && site[row + 1][col]) {
            unionSet.union(num, coor2num(row + 1, col));

        }
        if (col - 1 >= 0 && site[row][col - 1]) {
            unionSet.union(coor2num(row, col - 1), num);

        }
        if (col + 1 < graphSize && site[row][col + 1]) {
            unionSet.union(coor2num(row, col + 1), num);

        }
        if (row == 0) {
            unionSet.union(num, 0);
        } else if (row == graphSize - 1) {
            if (!percolates()) {
                unionSet.union(num, graphSize * graphSize + 1);
            }

        }

    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= graphSize || col < 0 || col >= graphSize) {
            throw  new IndexOutOfBoundsException();
        }
        return site[row][col];

    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= graphSize || col < 0 || col >= graphSize) {
            throw  new IndexOutOfBoundsException();
        }

        return unionSet.connected(0, coor2num(row, col)) && site[row][col];

    }
    public int numberOfOpenSites() {
        return openNum;
    }
    public boolean percolates() {
        return unionSet.connected(0, graphSize * graphSize + 1);
    }


    public static void main(String[] args) {

        Percolation graph = new Percolation(5);
        Stopwatch timer1 = new Stopwatch();
        double sum1 = 0.0;
        graph.open(1,2);
        double time1 = timer1.elapsedTime();
        graph.open(4,4);
        graph.isFull(4,4);
        graph.open(0,3);
        graph.open(1,3);
        StdOut.printf("%e (%.2f seconds)\n", sum1, time1);
        graph.open(2,3);
        graph.open(3,3);
        graph.open(4,3);



    }

}
