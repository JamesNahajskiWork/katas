package kata.draughts.agents;

import kata.game.framework.service.IGameAgent;
import org.springframework.stereotype.Service;

@Service
public interface IDraughtsAgent extends IGameAgent<Integer, Integer> {
}
