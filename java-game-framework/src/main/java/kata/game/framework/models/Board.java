package kata.game.framework.models;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Board<T extends IPlayer> {
    private static final int DEFAULT_SIZE = 10;
    private final List<List<T>> state;

    public Board(int boardSize) {
        this.state = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            List<T> row = new ArrayList<>();
            for (int j = 0; j < boardSize; j++) {
                row.add(null);

            }
            this.state.add(row);
        }
    }

    public Board(Board<T> oldBoard) {
        this(oldBoard.getState());
    }

    public Board(List<List<T>> state) {
        this.state = new ArrayList<>();
        for (List<T> ts : state) {
            this.state.add(new ArrayList<>(ts));
        }
    }

    public Board() {
        this(DEFAULT_SIZE);
    }

    public List<List<T>> getState() {
        return state;
    }

    public List<T> getRow(int row) {
        return state.get(row);
    }

    public T getCell(int row, int column) {
        return state.get(row).get(column);
    }

    public void setCell(int column, int row, T value) {
        this.state.get(row).set(column, value);
    }

    public T getCellAtCoords(@NonNull Coordinates coordinates) {
        return getCell(coordinates.row(), coordinates.column());
    }

    public boolean isFull() {
        for (List<T> row : state) {
            for (T cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<T> getColumn(int column) {
        List<T> toReturn = new ArrayList<>();
        for (List<T> row : state) {
            toReturn.add(row.get(column));
        }
        return toReturn;
    }
}
