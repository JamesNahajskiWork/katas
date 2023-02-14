package kata.game.framework.models;

public class MovePiece<T> implements ITakeTurn<T> {
    private final Coordinates oldPosition;
    private final Coordinates newPosition;
    private final T value;

    public MovePiece(Coordinates oldPosition, Coordinates newPosition, T value) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
        this.value = value;
    }

    public Coordinates getOldPosition() {
        return oldPosition;
    }

    @Override
    public Coordinates getNewPosition() {
        return newPosition;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public void makeMove(Board<T> boardStateBefore) {
        boardStateBefore.setCell(newPosition.getColumn(), newPosition.getRow(), value);
    }
}
