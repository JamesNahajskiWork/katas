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

    public List<T> getRow(int column) {
        return state.get(column);
    }

    public T getCell(int row, int column) {
        return state.get(column).get(row);
    }

    public void setCell(int column, int row, T value) {
        this.state.get(column).set(row, value);
    }

    public T getCellAtCoords(@NonNull Coordinates coordinates) {
        return getCell(coordinates.row(), coordinates.column());
    }
}
