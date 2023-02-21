package kata.os.and.xs.implementable;

import kata.game.framework.models.Board;
import kata.game.framework.models.Coordinates;
import kata.game.framework.models.PlayPiece;
import kata.os.and.xs.interfaces.OsAndXsAgent;
import kata.os.and.xs.interfaces.Player;
import org.springframework.stereotype.Service;


@Service
public class JamesOsAndXsAgent implements OsAndXsAgent {
    @Override
    public PlayPiece<Player> findNextMove(Board<Player> boardState, int maxTimeAllowedToFindMoveMillis) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (boardState.getCell(y, x) == null) {
                    return new PlayPiece<>(new Coordinates(y, x), Player.O);
                }
            }
        }
        throw new RuntimeException("No valid move found");
    }

//    Loss 0, Win 1, Draw 0.5, Not over yet -1
    @Override
    public Integer evaluateBoardState(Board<Player> boardState) {
        return null;
    }
}
