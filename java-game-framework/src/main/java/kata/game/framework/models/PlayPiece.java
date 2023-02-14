package kata.game.framework.models;

public class PlayPiece<T> implements ITakeTurn<T> {
    private final Coordinates newPosition;
    private final T value;

    public PlayPiece(Coordinates newPosition, T value) {
        this.newPosition = newPosition;
        this.value = value;
    }


    @Override
    public void takeTurn(Board<T> boardStateBefore) {
        boardStateBefore.setCell(newPosition.getColumn(), newPosition.getRow(), value);
    }

    @Override
    public String exceptionLogging() {
        return "Tried to play " + value + ", at column " + newPosition.getColumn() + ", row " + newPosition.getRow();
    }
}
