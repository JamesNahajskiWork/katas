package kata.connect4.service;

import kata.game.framework.GameOverException;
import kata.game.framework.models.Board;
import kata.game.framework.models.PlayPiece;
import kata.game.framework.service.IGameTools;
import org.springframework.stereotype.Service;

@Service
public class Connect4Tools implements IGameTools<Integer, PlayPiece<Integer>> {

    @Override
    public boolean checkMoveIsValid(PlayPiece<Integer> proposedMove, Board<Integer> boardState) {
        int proposedColumn = proposedMove.getCoordinates().column();
        int proposedRow = proposedMove.getCoordinates().row();
        return false; //TODO: fix
    }

    @Override
    public boolean checkIfGameIsOver(Board<Integer> gameState) throws GameOverException {
        return false;
    }
}
