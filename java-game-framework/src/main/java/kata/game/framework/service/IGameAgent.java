package kata.game.framework.service;

import kata.game.framework.models.IPlayer;
import kata.game.framework.models.Board;
import kata.game.framework.models.ITakeTurn;
/**
 *
 * @param <T> The type that represents the value of a single square within the board
 * @param <Q> The type that is used to represent the value of a given board state
 */
public interface IGameAgent<T extends IPlayer, Q  extends Number & Comparable<Q>, V extends ITakeTurn<T>> {
    V findNextMove(Board<T> boardState, int maxTimeAllowedToFindMoveMillis);
    Q evaluateBoardState(Board<T> boardState);
}
