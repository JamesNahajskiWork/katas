package kata.os.and.xs.implementable;

import kata.game.framework.models.Board;
import kata.game.framework.models.PlayPiece;
import kata.os.and.xs.interfaces.OsAndXsAgent;
import kata.os.and.xs.interfaces.Player;
import org.springframework.stereotype.Service;



public class StevesOsAndXsAgent implements OsAndXsAgent {
    @Override
    public PlayPiece<Player> findNextMove(Board<Player> boardState, int maxTimeAllowedToFindMoveMillis) {
        return null;
    }

    @Override
    public Integer evaluateBoardState(Board<Player> boardState) {
        return null;
    }
}
