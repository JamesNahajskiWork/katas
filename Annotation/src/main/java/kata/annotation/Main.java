package kata.annotation;

import kata.annotation.model.DataObject;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchFieldException, InterruptedException, IOException {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        MethodAnnotationProcessor methodAnnotationProcessor = context.getBean(MethodAnnotationProcessor.class);
        DownstreamService downstreamService = context.getBean(DownstreamService.class);
        Mockito.mock();


        System.out.println("\n\nCalling cat facts\n");
        methodAnnotationProcessor.callMethodWithWrapper(DownstreamService.class, downstreamService, "https://catfact.ninja/fact");
        System.out.println("\n\nCalling dad facts\n");
        methodAnnotationProcessor.callMethodWithWrapper(DownstreamService.class, downstreamService, "https://swapi.dev/api/planets/3/?format=json");

        System.out.println("\n\nCalling a method on a data object\n");
//        downstreamService.callDownstream("https://www.google.com");
        downstreamService.getAddress(new DataObject());
        System.out.println("\n\nCalling a slow endpoint\n");
        System.out.println(downstreamService.slowCall("https://www.google.com"));
    }
}