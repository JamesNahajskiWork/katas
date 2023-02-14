package kata.connect4.agents;

import kata.game.framework.models.ITakeTurn;
import kata.game.framework.models.PlayPiece;
import kata.game.framework.service.IGameAgent;
import org.springframework.stereotype.Service;

public interface IConnect4Agent extends IGameAgent<Integer, Integer, PlayPiece<Integer>> {
}
