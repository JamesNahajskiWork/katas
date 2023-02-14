package kata.game.framework.models;

public class PlayPiece<T extends IPlayer> implements ITakeTurn<T> {
    private final Coordinates newPosition;
    private final T value;

    public PlayPiece(Coordinates newPosition, T value) {
        this.newPosition = newPosition;
        this.value = value;
    }


    @Override
    public void takeTurn(Board<T> boardStateBefore) {
        boardStateBefore.setCell(newPosition.column(), newPosition.row(), value);
    }

    @Override
    public String exceptionLogging() {
        return "Tried to play " + value + ", at column " + newPosition.column() + ", row " + newPosition.row();
    }

    public Coordinates getCoordinates() {
        return this.newPosition;
    }
}
