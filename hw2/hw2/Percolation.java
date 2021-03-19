package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int graphSize;
    private boolean[][] site;
    private WeightedQuickUnionUF unionSet;
    private int openNum;
    private WeightedQuickUnionUF noGroundUnionSet;



    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        openNum = 0;
        graphSize = N;
        site = new boolean[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                site[i][j] = false;

            }
        }
        unionSet = new WeightedQuickUnionUF(N * N + 2);
        noGroundUnionSet = new WeightedQuickUnionUF(N * N + 1);
    }
    private int coor2num(int row, int col) {
        return row * graphSize + col + 1;
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
            noGroundUnionSet.union(coor2num(row - 1, col), num);

        }
        if (row + 1 < graphSize && site[row + 1][col]) {
            unionSet.union(num, coor2num(row + 1, col));
            noGroundUnionSet.union(num, coor2num(row + 1, col));

        }
        if (col - 1 >= 0 && site[row][col - 1]) {
            unionSet.union(coor2num(row, col - 1), num);
            noGroundUnionSet.union(coor2num(row, col - 1), num);

        }
        if (col + 1 < graphSize && site[row][col + 1]) {
            unionSet.union(coor2num(row, col + 1), num);
            noGroundUnionSet.union(coor2num(row, col + 1), num);

        }
        if (row == 0) {
            unionSet.union(num, 0);
            noGroundUnionSet.union(num, 0);
        }
        if (row == graphSize - 1) {
            unionSet.union(num, graphSize * graphSize + 1);
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

        return noGroundUnionSet.connected(0, coor2num(row, col)) && site[row][col];

    }
    public int numberOfOpenSites() {
        return openNum;
    }
    public boolean percolates() {
        return unionSet.connected(0, graphSize * graphSize + 1);
    }


    public static void main(String[] args) {


    }

}
