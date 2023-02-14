package kata.game.framework.service;

import kata.game.framework.models.Board;
import kata.game.framework.models.ITakeTurn;
import kata.game.framework.models.MovePiece;

public interface IGameTools<T> {
    boolean checkMoveIsValid(ITakeTurn<T> proposedMove, Board<T> boardState);
}
