package kata.game.framework.models;

import lombok.NonNull;

public class PlayPiece<T extends IPlayer> implements ITakeTurn<T> {
    private final Coordinates newPosition;
    private final T newValue;

    public PlayPiece(@NonNull Coordinates newPosition, @NonNull T newValue) {
        this.newPosition = newPosition;
        this.newValue = newValue;
    }


    @Override
    public void takeTurn(Board<T> boardStateBefore) {
        boardStateBefore.setCell(newPosition.column(), newPosition.row(), newValue);
    }

    @Override
    public String exceptionLogging() {
        return "Tried to play " + newValue + ", at column " + newPosition.column() + ", row " + newPosition.row();
    }

    public Coordinates getCoordinates() {
        return this.newPosition;
    }
}
