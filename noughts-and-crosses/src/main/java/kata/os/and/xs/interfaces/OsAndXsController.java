package kata.os.and.xs.interfaces;

import kata.game.framework.InvalidMoveException;
import kata.game.framework.models.Board;
import kata.game.framework.models.PlayPiece;
import kata.os.and.xs.implementable.JamesOsAndXsAgent;
import kata.os.and.xs.implementable.OsAndXsTools;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
public class OsAndXsController {
    private final OsAndXsService gameService;
    private final OsAndXsTools gameTools;

    public OsAndXsController(OsAndXsAgent connect4Agent, OsAndXsTools gameTools) {
        this.gameService = new OsAndXsService(connect4Agent);
        this.gameTools = gameTools;
    }
    public Board<Player> getAiNextMove(Board<Player> boardState) throws ExecutionException, InterruptedException, TimeoutException, InvalidMoveException {
        PlayPiece<Player> proposedMove = this.gameService.askAiForMove(boardState);
        if (gameTools.checkMoveIsValid(proposedMove, boardState)) {
            proposedMove.takeTurn(boardState);
            return boardState;
        }
        throw new InvalidMoveException(proposedMove);
    }
}
