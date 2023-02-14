package kata.connect4.agents;

import kata.game.framework.service.IGameAgent;
import org.springframework.stereotype.Service;

@Service
public interface IConnect4Agent extends IGameAgent<Integer, Integer> {
}
