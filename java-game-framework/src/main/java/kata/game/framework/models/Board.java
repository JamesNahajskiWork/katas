package kata.game.framework.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Board<T> {
    private static final int DEFAULT_SIZE = 10;
    private final List<List<T>> state;
    public Board(int boardSize) {
        this.state = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            this.state.add(new ArrayList<>());
        }
    }

    public Board(Board<T> oldBoard) {
        this.state = new ArrayList<>();
        for (int i = 0; i < oldBoard.getState().size(); i++) {
            this.state.add(new ArrayList<>(oldBoard.getRow(i)));
        }
    }

    public Board() {
        this(DEFAULT_SIZE);
    }

    public List<List<T>> getState() {
        return state;
    }

    public List<T> getRow(int column) {
        return state.get(column);
    }

    public T getCell(int row, int column) {
        return state.get(column).get(row);
    }

    public void setCell(int column, int row, T value) {
        this.state.get(column).set(row, value);
    }
}
