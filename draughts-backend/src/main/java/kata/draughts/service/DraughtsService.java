package kata.draughts.service;

import kata.game.framework.service.GameService;
import kata.game.framework.service.IGameAgent;

public class DraughtsService extends GameService<Integer, Integer> {
    public DraughtsService(IGameAgent<Integer, Integer> aiPlayer1) {
        super(aiPlayer1);
    }
}
