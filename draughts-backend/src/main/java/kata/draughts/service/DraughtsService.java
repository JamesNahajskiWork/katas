package kata.draughts.service;

import kata.draughts.agents.IDraughtsAgent;
import kata.game.framework.service.GameService;

public class DraughtsService extends GameService<Integer, Integer> {
    public DraughtsService(IDraughtsAgent aiPlayer1) {
        super(aiPlayer1);
    }
}
