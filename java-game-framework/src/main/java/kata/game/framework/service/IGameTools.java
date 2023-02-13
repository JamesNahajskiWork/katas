package kata.game.framework.service;

import kata.game.framework.models.Board;
import kata.game.framework.models.MovePiece;

public interface IGameTools<T> {
    boolean validateMove(MovePiece<T> proposedMove, Board<T> boardState);
}
