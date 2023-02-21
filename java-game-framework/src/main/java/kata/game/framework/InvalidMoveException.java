package kata.game.framework;

import kata.game.framework.models.IPlayer;
import kata.game.framework.models.ITakeTurn;

public class InvalidMoveException extends Exception{
    public <T extends IPlayer> InvalidMoveException(ITakeTurn<T> proposedMove) {
        super("Invalid move:" + proposedMove.exceptionLogging());

    }
}
