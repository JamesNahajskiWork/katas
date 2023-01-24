package org.example;

public class WordCensorer {
    public static String censorWord(String inputText, String wordToCensor) {
        return inputText.replaceAll(wordToCensor, "X".repeat(wordToCensor.length()));
    }
}
