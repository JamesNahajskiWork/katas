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
    private final DraughtsService gameService;
    private final IDraughtsTools IDraughtsTools;

    public DraughtsController(IDraughtsAgent IDraughtsAgent, IDraughtsTools IDraughtsTools) {
        this.gameService = new DraughtsService(IDraughtsAgent);
        this.IDraughtsTools = IDraughtsTools;
    }
    public Board<Integer> getAiNextMove(Board<Integer> boardState) throws ExecutionException, InterruptedException, TimeoutException, InvalidMoveException {
        MovePiece<Integer> proposedMove = this.gameService.askAiForMove(boardState);
        if (IDraughtsTools.checkMoveIsValid(proposedMove, boardState)) {
            boardState.makeMove(proposedMove);
            return boardState;
        }
        throw new InvalidMoveException(proposedMove);
    }
}
