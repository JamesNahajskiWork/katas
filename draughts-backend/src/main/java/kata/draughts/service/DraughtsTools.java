package kata.draughts.service;

import kata.game.framework.models.Board;
import kata.game.framework.models.MovePiece;
import kata.game.framework.service.IGameTools;

public class DraughtsTools implements IGameTools<Integer> {
    @Override
    public boolean validateMove(MovePiece<Integer> proposedMove, Board<Integer> boardState) {
        return false;
    }
}
