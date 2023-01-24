package org.example;

public class WordCensorer {
    public static String censorWord(String inputText, String wordToCensor) {
        return inputText.replaceAll(wordToCensor, "X".repeat(wordToCensor.length()));
    }

    public static String censorWord(String inputText, String[] wordsToCensor) {
        String toReturn = inputText;
        for (String singleWordToCensor: wordsToCensor) {
            toReturn = toReturn.replaceAll(singleWordToCensor, "X".repeat(singleWordToCensor.length()));
        }
        return toReturn;
    }
}
