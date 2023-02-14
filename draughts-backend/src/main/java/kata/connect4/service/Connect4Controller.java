package kata.connect4.service;

import kata.connect4.agents.IConnect4Agent;
import kata.game.framework.InvalidMoveException;
import kata.game.framework.models.Board;
import kata.game.framework.models.PlayPiece;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
public class Connect4Controller {
    private final Connect4Service gameService;
    private final IConnect4Tools IConnect4Tools;

    public Connect4Controller(IConnect4Agent IConnect4Agent, IConnect4Tools IConnect4Tools) {
        this.gameService = new Connect4Service(IConnect4Agent);
        this.IConnect4Tools = IConnect4Tools;
    }
    public Board<Integer> getAiNextMove(Board<Integer> boardState) throws ExecutionException, InterruptedException, TimeoutException, InvalidMoveException {
        PlayPiece<Integer> proposedMove = this.gameService.askAiForMove(boardState);
        if (IConnect4Tools.checkMoveIsValid(proposedMove, boardState)) {
            proposedMove.takeTurn(boardState);
            return boardState;
        }
        throw new InvalidMoveException(proposedMove);
    }
}
