package org.example.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MinesweeperService {
    private final int GAME_BOARD_WIDTH = 20;
    private final int GAME_BOARD_HEIGHT = 14;
    private final int PERCENTAGE_MINES = 20;
    private final int[][] adjacentSquaresOffset = new int[][]{{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}};

    private final int[][] hiddenGameState = new int[GAME_BOARD_HEIGHT][GAME_BOARD_WIDTH];
    private final int[][] visibleGameState = new int[GAME_BOARD_HEIGHT][GAME_BOARD_WIDTH];

    public MinesweeperService() {
        resetBoard();
    }

    public void resetBoard() {
        Random rand = new Random();
        for (int rowIndex = 0; rowIndex < GAME_BOARD_HEIGHT; rowIndex++) {
            for (int columnIndex = 0; columnIndex < GAME_BOARD_WIDTH; columnIndex++) {
                visibleGameState[rowIndex][columnIndex] = -1;
                if (rand.nextInt(100) > (100 - PERCENTAGE_MINES)) {
                    hiddenGameState[rowIndex][columnIndex] = -2;
                } else {
                    hiddenGameState[rowIndex][columnIndex] = 0;
                }
            }
        }
    }

    public int[][] getGameState() {
        return visibleGameState;
    }

    public void playMove(int x, int y) {
        if (visibleGameState[y][x] != -1) {
            return;
        }
        int newState = convertHiddenValue(x, y);
        visibleGameState[y][x] = newState;
        if (newState == 0) {
            playAllAdjacentMoves(x, y);
        } else if (newState == -2) {
            showAllMines();
        }
    }

    private void showAllMines() {
        for (int rowIndex = 0; rowIndex < GAME_BOARD_HEIGHT; rowIndex++) {
            for (int columnIndex = 0; columnIndex < GAME_BOARD_WIDTH; columnIndex++) {
                if (visibleGameState[rowIndex][columnIndex] == -1) {
                    if (hiddenGameState[rowIndex][columnIndex] == -2) {
                        visibleGameState[rowIndex][columnIndex] = -2;
                    }
                }
            }
        }

    }


    private void playAllAdjacentMoves(int x, int y) {
        for (int[] offset : adjacentSquaresOffset) {
            int cellToCompareX = x + offset[0];
            int cellToCompareY = y + offset[1];
            if (cellToCompareY < GAME_BOARD_HEIGHT && cellToCompareY >= 0 && cellToCompareX < GAME_BOARD_WIDTH && cellToCompareX >= 0) {
                playMove(cellToCompareX, cellToCompareY);
            }
        }
    }

    private int convertHiddenValue(int x, int y) {
        if (isMine(x, y)) {
            return -2;
        }
        return getAdjacentCount(x, y);
    }

    private boolean isMine(int x, int y) {
        return hiddenGameState[y][x] == -2;
    }

    private int getAdjacentCount(int x, int y) {
        int adjacentCount = 0;
        for (int[] offset : adjacentSquaresOffset) {
            int cellToCompareX = x + offset[0];
            int cellToCompareY = y + offset[1];
            if (cellToCompareY < GAME_BOARD_HEIGHT && cellToCompareY >= 0 && cellToCompareX < GAME_BOARD_WIDTH && cellToCompareX >= 0) {
                int cellToCompare = hiddenGameState[cellToCompareY][cellToCompareX];
                if (cellToCompare == -2) {
                    adjacentCount++;
                }
            }
        }
        return adjacentCount;
    }
}
