package kata.annotation.model;

import com.github.javafaker.Faker;
import kata.annotation.annotations.LoggerAnnotation;
import org.springframework.stereotype.Component;

@LoggerAnnotation
@Component
public class DataObject {

    private String name;

    private int age;

    private Address address;

    public DataObject() {
        Faker jf = new Faker();
        this.name = jf.funnyName().name();
        this.address = new Address(new String[]{jf.address().fullAddress()}, jf.address().zipCode());
        this.age = jf.number().numberBetween(0,100);
    }

    public DataObject(String name, int age, String[] addressLines, String postcode) {
        this.name = name;
        this.age = age;
        this.address = new Address(addressLines, postcode);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private static class Address {
        private String[] addressLines;
        private String postcode;

        private Address(String[] addressLines, String postcode) {
            this.addressLines = addressLines;
            this.postcode = postcode;
        }
    }
}
