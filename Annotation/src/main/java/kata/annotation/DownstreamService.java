package kata.annotation;

import kata.annotation.annotations.TimeoutAnnotation;
import kata.annotation.annotations.TimerWrapperAnnotation;
import kata.annotation.model.DataObject;
import org.mockito.Mockito;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DownstreamService {
    final HttpClient httpClient;


    final DataObject dataObject = Mockito.mock(DataObject.class);


    public DownstreamService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @TimerWrapperAnnotation
    public String callDownstream(String url){
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
        try {
            String body = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
            System.out.println(body);
            return body;
        } catch (IOException | InterruptedException e) {
            System.out.println("FAILED");
            return "FAILED";
        }
    }

    @TimeoutAnnotation(timeout = 1000)
    public void getAddress(DataObject dataObject) {
        for (int i = 0; i < 10; i++) {
            dataObject.getName();
        }
    }
}
