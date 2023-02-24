package kata.os.and.xs.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import kata.game.framework.GameOverException;
import kata.game.framework.InvalidMoveException;
import kata.game.framework.models.Board;
import kata.game.framework.models.PlayPiece;
import kata.os.and.xs.implementable.OsAndXsTools;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@CrossOrigin("http://localhost:3000")
public class OsAndXsController {
    private final OsAndXsService gameService;
    private final OsAndXsTools gameTools;

    public OsAndXsController(OsAndXsAgent osAndXsAgent, OsAndXsTools gameTools) {
        this.gameService = new OsAndXsService(osAndXsAgent);
        this.gameTools = gameTools;
    }

    record BoardWithWinner(Board<Player> boardState, Player winner){}

    record ProposedMoveAndBoardState(PlayPiece<Player> proposedMove, Board<Player> boardState) {}

    @PostMapping("/make-move")
    public BoardWithWinner makeMoveIfValid(@RequestBody ProposedMoveAndBoardState proposedMoveAndBoardState) throws JsonProcessingException, InvalidMoveException, ExecutionException, InterruptedException, TimeoutException, GameOverException {

        if (gameTools.checkMoveIsValid(proposedMoveAndBoardState.proposedMove, proposedMoveAndBoardState.boardState)) {
            proposedMoveAndBoardState.proposedMove.takeTurn(proposedMoveAndBoardState.boardState);
            Optional<Player> gameWinner = gameTools.checkIfGameIsOver(proposedMoveAndBoardState.boardState);
            if (gameWinner.isPresent()) {
                return new BoardWithWinner(proposedMoveAndBoardState.boardState, gameWinner.get());
            }
            return getAiNextMove(proposedMoveAndBoardState.boardState);
        }
        throw new InvalidMoveException(proposedMoveAndBoardState.proposedMove);
    }

//    @PostMapping("/get-move")
    public BoardWithWinner getAiNextMove(@RequestBody Board<Player> boardState) throws ExecutionException, InterruptedException, TimeoutException, InvalidMoveException, GameOverException {
        PlayPiece<Player> proposedMove = this.gameService.askAiForMove(boardState);
        if (gameTools.checkMoveIsValid(proposedMove, boardState)) {
            proposedMove.takeTurn(boardState);
            Optional<Player> gameWinner = gameTools.checkIfGameIsOver(boardState);
            if (gameWinner.isPresent()) {
                return new BoardWithWinner(boardState, gameWinner.get());
            }
            return new BoardWithWinner(boardState, null);
        }
        throw new InvalidMoveException(proposedMove);
    }

    @GetMapping("/initial-state")
    public Board<Player> getInitialState() {
        return new Board<>(3);
    }
}
