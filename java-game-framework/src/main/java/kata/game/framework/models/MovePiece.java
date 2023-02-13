package kata.game.framework.models;

import kata.game.framework.models.Coordinates;

public class MovePiece<T> {
    private final Coordinates<T> oldPosition;
    private final Coordinates<T> newPosition;

    public MovePiece(Coordinates<T> oldPosition, Coordinates<T> newPosition) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public Coordinates<T> getOldPosition() {
        return oldPosition;
    }
    public Coordinates<T> getNewPosition() {
        return newPosition;
    }
}
