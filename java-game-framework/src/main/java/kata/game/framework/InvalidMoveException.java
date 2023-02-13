package kata.game.framework;

import kata.game.framework.models.MovePiece;

public class InvalidMoveException extends Exception{
    public <T> InvalidMoveException(MovePiece<T> proposedMove) {
        super("The AI tried to make an invalid move:" + proposedMove.getOldPosition() + proposedMove.getNewPosition());

    }
}
