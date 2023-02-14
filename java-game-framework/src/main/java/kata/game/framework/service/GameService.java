package kata.game.framework.service;

import java.util.concurrent.*;

import kata.game.framework.models.Board;
import kata.game.framework.models.MovePiece;
import org.springframework.stereotype.Service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 *
 * @param <T> The type that represents the value of a single square within the board
 * @param <Q> The type that is used to represent the value of a given board state
 */
@Service
public class GameService<T, Q extends Number & Comparable<Q>> {
    private final static int EVALUATE_POSITION_TIMEOUT = 1000, FIND_NEXT_MOVE_TIMEOUT = 5000;
    private final IGameAgent<T, Q> aiPlayer1;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public GameService(IGameAgent<T, Q> aiPlayer1) {
        this.aiPlayer1 = aiPlayer1;
    }

    public MovePiece<T> askAiForMove(Board<T> boardState) throws ExecutionException, InterruptedException, TimeoutException {
        Callable<MovePiece<T>> evaluateBoardStateCall = () -> {
            return aiPlayer1.findNextMove(boardState, 5000);
        };
        return callWithTimeout(evaluateBoardStateCall, 5000);
    }

    public Q askAiToEvaluatePosition(Board<T> boardState) throws ExecutionException, InterruptedException, TimeoutException {
        Callable<Q> evaluateBoardStateCall = () -> {
            return aiPlayer1.evaluateBoardState(boardState);
        };
        return callWithTimeout(evaluateBoardStateCall, 1000);
    }


    private <U> U callWithTimeout(Callable<U> callableFunction, int timeoutInMills) throws TimeoutException, InterruptedException, ExecutionException {
        Future<U> future = executor.submit(callableFunction);
        try {
            return future.get(timeoutInMills, MILLISECONDS);
        } finally {
            future.cancel(true);
        }
    }


}
