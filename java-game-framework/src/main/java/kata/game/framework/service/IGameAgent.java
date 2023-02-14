package kata.game.framework.service;

import kata.game.framework.models.Board;
import kata.game.framework.models.ITakeTurn;
import kata.game.framework.models.MovePiece;

/**
 *
 * @param <T> The type that represents the value of a single square within the board
 * @param <Q> The type that is used to represent the value of a given board state
 */
public interface IGameAgent<T, Q  extends Number & Comparable<Q>, V extends ITakeTurn<T>> {
    V findNextMove(Board<T> boardState, int maxTimeAllowedToFindMoveMillis);
    Q evaluateBoardState(Board<T> boardState);
}
