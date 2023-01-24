import org.example.WordCensorer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WordCensorerTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "aa", "aaa", "Aa", "a1"})
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
                Arguments.of("nice", "You are a NICE person nice", "You are a NICE person XXXX")
        );
    }

    @ParameterizedTest
    @MethodSource("multipleWords")
    public void ifMultipleWordsInLongerSentence(String[] wordsToCensor, String inputText, String expectedOutput) {
        String censoredText = WordCensorer.censorWord(inputText, wordsToCensor);
        assertEquals(expectedOutput, censoredText);
    }

    public static Stream<Arguments> multipleWords() {
        return Stream.of(
                Arguments.of(new String[] {"nice"}, "You are a nice person", "You are a XXXX person"),
                Arguments.of(new String[] {"nice", "pony", "sun", "light", "fun", "happy", "funny", "joy"}, "Such a nice day with a bright sun, makes me happy", "Such a XXXX day with a bright XXXX makes me XXXXX")
        );
    }

    @ParameterizedTest
    @MethodSource("prefixOneWord")
    public void ifPrefixInOneWord(String wordsToCensor, String inputText, String expectedOutput) {
        String censoredText = WordCensorer.censorWord(inputText, wordsToCensor);
        assertEquals(expectedOutput, censoredText);
    }

    @ParameterizedTest
    @MethodSource("prefixMultipleWords")
    public void ifPrefixInMultipleWords(String wordsToCensor[], String inputText, String expectedOutput) {
        String censoredText = WordCensorer.censorWord(inputText, wordsToCensor);
        assertEquals(expectedOutput, censoredText);
    }

    public static Stream<Arguments> prefixOneWord() {
        return Stream.of(
                Arguments.of("nice", "You are a nicety! person", "You are a XXXXXXX person")

        );
    }

    public static Stream<Arguments> prefixMultipleWords() {
        return Stream.of(
                Arguments.of(new String[] {"nice", "pony", "sun", "light", "fun", "happy", "funny", "joy"}, "Such a nicety! day with a bright sun, makes me happy", "Such a XXXXXXX day with a bright XXXX makes me XXXXX")
                );
    }
}
//Arguments.of(new String[] {"nice", "pony", "sun", "light", "fun", "happy", "funny", "joy"}, "Such a nicety! day with a bright sun, makes me happy", "Such a XXXXXXX day with a bright XXX, makes me XXXXX")
