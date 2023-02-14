package kata.game.framework.models;

public class PlayPiece<T> implements ITakeTurn<T> {
    private final Coordinates newPosition;
    private final T value;

    public PlayPiece(Coordinates oldPosition, Coordinates newPosition, T value) {
        this.newPosition = newPosition;
        this.value = value;
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
