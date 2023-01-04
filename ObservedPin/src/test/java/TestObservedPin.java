import java.util.*;

import org.example.ObservedPin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestObservedPin {

    public static HashMap<String, String[]> expectations = new HashMap<>() {
        {
            put("1", new String[]{"1", "2", "4"});
            put("2", new String[]{"1", "2", "3", "5"});
            put("3", new String[]{"2", "3", "6"});
            put("4", new String[]{"1", "4", "5", "7"});
            put("5", new String[]{"2", "4", "5", "6", "8"});
            put("6", new String[]{"3", "5", "6", "9"});
            put("7", new String[]{"4", "7", "8"});
            put("8", new String[]{"5", "7", "8", "9", "0"});
            put("9", new String[]{"6", "8", "9"});
            put("0", new String[]{"8", "0"});
            put("11", new String[]{"11", "21", "41", "12", "22", "42", "14", "24", "44"});
            put("369", new String[]{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"});
         }
    };
    private final static Comparator<String> comp = String::compareTo;

    public static List<Arguments> testPins() {
        List<Arguments> asd = new ArrayList<>();
        for (String entered : expectations.keySet()) {
            asd.add(Arguments.of(entered, expectations.get(entered), ObservedPin.getPINs(entered)));
        }
        return asd;
    }

    @ParameterizedTest
    @MethodSource("testPins")
    public void testSomething(String entered, String[] expectedImmutable, List<String> result) {
        List<String> given = new ArrayList<>(result);
        given.sort(Comparator.naturalOrder());
        List<String> expected = new ArrayList<>(List.of(expectedImmutable));
        expected.sort(Comparator.naturalOrder());
        assertEquals(expected, given, "For observed PIN " + entered);
    }

} // Test Class
