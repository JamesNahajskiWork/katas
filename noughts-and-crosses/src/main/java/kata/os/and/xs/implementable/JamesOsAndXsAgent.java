package kata.os.and.xs.implementable;

import kata.game.framework.models.Board;
import kata.game.framework.models.Coordinates;
import kata.game.framework.models.PlayPiece;
import kata.os.and.xs.interfaces.OsAndXsAgent;
import kata.os.and.xs.interfaces.Player;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class JamesOsAndXsAgent implements OsAndXsAgent {
    private final Player agentPlayer;
    private final OsAndXsTools osAndXsTools;

    public JamesOsAndXsAgent(OsAndXsTools osAndXsTools) {
        this.agentPlayer = Player.O;
        this.osAndXsTools = osAndXsTools;
    }



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
    public Double evaluateBoardState(Board<Player> boardState) {
        Optional<Player> winnerOrNull = osAndXsTools.checkIfGameIsOver(boardState);
        if (winnerOrNull.isEmpty()) {
            return -1D;
        }
        Player winner = winnerOrNull.get();
        if (winner == agentPlayer) {
            return 1D;
        }
        if (winner == Player.DRAW) {
            return 0.5D;
        }
        return 0D;
    }
}
