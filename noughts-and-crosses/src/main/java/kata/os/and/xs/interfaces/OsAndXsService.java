package kata.os.and.xs.interfaces;

import kata.game.framework.models.PlayPiece;
import kata.game.framework.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class OsAndXsService extends GameService<Player, Integer, PlayPiece<Player>> {
    public OsAndXsService(OsAndXsAgent aiPlayer1) {
        super(aiPlayer1);
    }

}
