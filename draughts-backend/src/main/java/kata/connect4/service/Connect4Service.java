package kata.connect4.service;

import kata.connect4.agents.IConnect4Agent;
import kata.game.framework.models.PlayPiece;
import kata.game.framework.service.GameService;
import kata.game.framework.service.IGameAgent;

public class Connect4Service extends GameService<Integer, Integer, PlayPiece<Integer>> {
    public Connect4Service(IConnect4Agent aiPlayer1) {
        super(aiPlayer1);
    }

}
