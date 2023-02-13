package kata.draughts.service;

import kata.draughts.agents.IDraughtsAgent;
import kata.game.framework.InvalidMoveException;
import kata.game.framework.models.Board;
import kata.game.framework.models.MovePiece;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
public class DraughtsController {
    private final IDraughtsAgent iDraughtsAgent;
    private final DraughtsService gameService;
    private final DraughtsTools draughtsTools;

    public DraughtsController(IDraughtsAgent iDraughtsAgent, DraughtsTools draughtsTools) {
        this.iDraughtsAgent = iDraughtsAgent;
        this.gameService = new DraughtsService(iDraughtsAgent);
        this.draughtsTools = draughtsTools;
    }
    public Board<Integer> getAiNextMove(Board<Integer> boardState) throws ExecutionException, InterruptedException, TimeoutException, InvalidMoveException {
        MovePiece<Integer> proposedMove = this.gameService.askAiForMove(boardState);
        if (draughtsTools.validateMove(proposedMove, boardState)) {
            boardState.makeMove(proposedMove);
            return boardState;
        }
        throw new InvalidMoveException(proposedMove);
    }
}
