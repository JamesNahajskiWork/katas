package kata.game.framework;

import kata.game.framework.models.ITakeTurn;

public class InvalidMoveException extends Exception{
    public <T> InvalidMoveException(ITakeTurn<T> proposedMove) {
        super("The AI tried to make an invalid move:" + proposedMove.exceptionLogging());

    }
}
