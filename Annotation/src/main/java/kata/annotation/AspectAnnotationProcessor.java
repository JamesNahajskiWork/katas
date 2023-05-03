package kata.annotation;

import com.google.common.base.Stopwatch;
import kata.annotation.annotations.TimeoutAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.mockito.Mockito;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Aspect
@Component
public class AspectAnnotationProcessor {
    @Around("@annotation(kata.annotation.annotations.TimerWrapperAnnotation)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("In aspect");
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object toReturn = joinPoint.proceed();
//        System.out.println("Aspect millisecond timing: " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        return toReturn;
    }


    @Around("@annotation(kata.annotation.annotations.MockMethods)")
    public Object printMethodsCalled(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] arguments = joinPoint.getArgs();
        Object[] mockedArgs = Arrays.stream(arguments).map(arg -> Mockito.mock(arg.getClass())).toList().toArray();

        Object toReturn = joinPoint.proceed(mockedArgs);
//        System.out.println("Aspect millisecond timing: " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        System.out.println(joinPoint.getSignature() + " called the following methods:");
        Arrays.stream(mockedArgs).forEach(mock -> System.out.println(Mockito.mockingDetails(mock).getInvocations()));
        return toReturn;
    }

    @Around("@annotation(kata.annotation.annotations.TimeoutAnnotation)")
    public Object timeoutAfterX(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        TimeoutAnnotation myAnnotation = method.getAnnotation(TimeoutAnnotation.class);
        int timeout = myAnnotation.timeout();

        ExecutorService executor = Executors.newCachedThreadPool();
        Callable<Object> task = new Callable<Object>() {
            public Object call() {
                try {
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Future<Object> future = executor.submit(task);
        try {
            return future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
//            throw new RuntimeException(e);
            System.out.printf("Timed out after %d ms ", timeout);
            return null;
        }
    }


}
