package kata.game.framework.service;

import kata.game.framework.models.IPlayer;
import kata.game.framework.models.Board;
import kata.game.framework.models.ITakeTurn;
import org.springframework.stereotype.Service;

@Service
public interface IGameTools<T extends IPlayer, V extends ITakeTurn<T>> {
    boolean checkMoveIsValid(V proposedMove, Board<T> boardState);
}
