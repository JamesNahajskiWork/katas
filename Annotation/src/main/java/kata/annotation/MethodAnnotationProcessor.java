package kata.annotation;


import com.fasterxml.jackson.core.type.TypeReference;
import kata.annotation.annotations.TimerWrapperAnnotation;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class MethodAnnotationProcessor {
    public void callMethodWithWrapper(Class<DownstreamService> serviceClass, DownstreamService downstreamService, Object... params) throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Method[] methods = serviceClass.getMethods();
        for (Method method : methods) {
            Optional<Annotation> wrapperAnnotation = Arrays.stream(method.getAnnotations()).filter(s -> s.annotationType().getName().endsWith("WrapperAnnotation")).findFirst();
//            System.out.println(wrapperAnnotation.isPresent());
            if (wrapperAnnotation.isPresent()) {
                Annotation annotation = wrapperAnnotation.get();
                Field beforeFunction = annotation.annotationType().getField("beforeFunction");
//                System.out.println("Assignable 1" + Runnable.class.isAssignableFrom(beforeFunction.getType()));
                if (Runnable.class.isAssignableFrom(beforeFunction.getType())) {
                    Runnable beforeRunnable = (Runnable) beforeFunction.get(annotation);
                    beforeRunnable.run();
                }
                method.invoke(downstreamService, params);
                Field afterFunction = annotation.annotationType().getField("afterFunction");
//                System.out.println("Assignable 2" + Supplier.class.isAssignableFrom(afterFunction.getType()));
                if (Supplier.class.isAssignableFrom(afterFunction.getType())) {
                    Object returnType = ((Supplier<?>) afterFunction.get(annotation)).get();
                    System.out.printf("Method took %s milliseconds%n", returnType);
                }
            }
        }


    }
}
