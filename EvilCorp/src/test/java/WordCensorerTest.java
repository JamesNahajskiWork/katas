import org.example.WordCensorer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WordCensorerTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "aa", "aaa", "Aa", "a1", " "})
    public void ifGivenWordTwiceXsAreReturned(String wordToCensor) {
        String censoredText = WordCensorer.censorWord(wordToCensor, wordToCensor);
        assertEquals("X".repeat(wordToCensor.length()), censoredText);
    }

    @ParameterizedTest
    @MethodSource("wordInLongSentence")
    public void ifGivenWordIsContainedInALongerSentence(String wordToCensor, String inputText, String expectedOutput) {
        String censoredText = WordCensorer.censorWord(inputText, wordToCensor);
        assertEquals(expectedOutput, censoredText);
    }

    public static Stream<Arguments> wordInLongSentence() {
        return Stream.of(
                Arguments.of("nice", "You are a nice person", "You are a XXXX person"),
                Arguments.of("nice", "You are a nice person nice", "You are a XXXX person XXXX"),
                Arguments.of("nice", "You are a ninicece person nice", "You are a niXXXXce person XXXX"),
                Arguments.of("nice", "You are a NICE person nice", "You are a NICE person XXXX")
        );
    }
}
