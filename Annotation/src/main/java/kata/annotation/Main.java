package kata.annotation;

import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        MethodAnnotationProcessor methodAnnotationProcessor = context.getBean(MethodAnnotationProcessor.class);
        DownstreamService downstreamService = context.getBean(DownstreamService.class);
        Mockito.mock();


        System.out.println("Calling cat facts");
        methodAnnotationProcessor.callMethodWithWrapper(DownstreamService.class, downstreamService, "https://catfact.ninja/fact");
        System.out.println("Calling dad facts");
        methodAnnotationProcessor.callMethodWithWrapper(DownstreamService.class, downstreamService, "https://swapi.dev/api/planets/3/?format=json");

    }
}