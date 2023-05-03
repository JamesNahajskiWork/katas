package kata.annotation.annotations;

import com.google.common.base.Stopwatch;
import org.mockito.Mock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MockWrapperAnnotation {
    Mock mock();
//    Mock mockedMethod = Stopwatch.createUnstarted();
//    Runnable beforeFunction = timer::start;
//    Supplier<String> afterFunction = () -> String.valueOf(timer.stop().elapsed(TimeUnit.MILLISECONDS));
}
