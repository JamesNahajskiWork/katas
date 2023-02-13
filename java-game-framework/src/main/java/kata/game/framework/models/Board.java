package kata.game.framework.models;

import java.util.ArrayList;
import java.util.List;

public class Board<T> {
    private static final int DEFAULT_SIZE = 10;
    private List<List<T>> state;
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

    public void makeMove(MovePiece<T> proposedMove) {
        T valueOfOldCell = proposedMove.getOldPosition().getContents();
        T valueOfNewCell = proposedMove.getOldPosition().getContents();
        state.get(proposedMove.getNewPosition().getColumn()).set(proposedMove.getNewPosition().getRow(), valueOfOldCell);
        state.get(proposedMove.getOldPosition().getColumn()).set(proposedMove.getOldPosition().getRow(), valueOfNewCell);
    }
}
