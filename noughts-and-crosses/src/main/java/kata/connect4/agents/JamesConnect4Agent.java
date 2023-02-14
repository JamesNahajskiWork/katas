package kata.connect4.agents;

import kata.game.framework.models.Board;
import kata.game.framework.models.PlayPiece;
import org.springframework.stereotype.Service;


@Service
public class JamesConnect4Agent implements IConnect4Agent {
    @Override
    public PlayPiece<Integer> findNextMove(Board<Integer> boardState, int maxTimeAllowedToFindMoveMillis) {
        return null;
    }

    @Override
    public Integer evaluateBoardState(Board<Integer> boardState) {
        return null;
    }
}
