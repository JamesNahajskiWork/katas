package kata.game.framework.service;

import java.util.List;
import java.util.concurrent.*;

import kata.game.framework.models.Board;
import kata.game.framework.models.ITakeTurn;
import org.springframework.stereotype.Service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 *
 * @param <T> The type that represents the value of a single square within the board
 * @param <Q> The type that is used to represent the value of a given board state
 */
@Service
public class GameService<T, Q extends Number & Comparable<Q>, V extends ITakeTurn<T>> {
    private final static int EVALUATE_POSITION_TIMEOUT = 1000, FIND_NEXT_MOVE_TIMEOUT = 5000;
    private final IGameAgent<T, Q, V> aiPlayer1;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public GameService(IGameAgent<T, Q, V> aiPlayer1) {
        this.aiPlayer1 = aiPlayer1;
    }

    public V askAiForMove(Board<T> boardState) throws ExecutionException, InterruptedException, TimeoutException {
        Callable<V> evaluateBoardStateCall = () -> {
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
