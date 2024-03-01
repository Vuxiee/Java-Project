package week02.GameOfLife;

import java.util.HashSet;
import java.util.Set;

public class MyGameOfLife implements GameOfLife {
    private boolean[][] bord;
    private int rows;
    private int columns;

    public MyGameOfLife(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.bord = new boolean[rows][columns];
    }

    @Override
    public void setAlive(int row, int col) {
        bord[row][col] = true;
    }

    @Override
    public int countAlive() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (bord[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public boolean[][] getBoard() {
        return bord;
    }

    @Override
    public void evolve(int generations) {
        for (int i = 0; i < generations; i++) {
            boolean[][] newBoard = new boolean[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    int aliveNeighbours = countAliveNeighbours(row, col);
                    if (bord[row][col] && (aliveNeighbours == 2 || aliveNeighbours == 3)) {
                        newBoard[row][col] = true;
                    } else if (!bord[row][col] && aliveNeighbours == 3) {
                        newBoard[row][col] = true;
                    }
                }
            }
            bord = newBoard;
        }
    }

    private int countAliveNeighbours(int row, int col) {
        int count = 0;
        for (int i = Math.max(0, row - 1); i <= Math.min(rows - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(columns - 1, col + 1); j++) {
                if ((i != row || j != col) && bord[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}