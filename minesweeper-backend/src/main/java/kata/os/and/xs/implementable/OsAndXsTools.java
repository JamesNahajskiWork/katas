package kata.os.and.xs.implementable;

import kata.game.framework.models.Board;
import kata.game.framework.models.PlayPiece;
import kata.game.framework.service.IGameTools;
import kata.os.and.xs.interfaces.Player;
import org.springframework.stereotype.Service;

@Service
public class OsAndXsTools implements IGameTools<Player, PlayPiece<Player>> {

    @Override
    public boolean checkMoveIsValid(PlayPiece<Player> proposedMove, Board<Player> boardState) {
        return false;
    }
}