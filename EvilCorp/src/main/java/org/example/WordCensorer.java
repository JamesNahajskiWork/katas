package org.example;

import java.util.ArrayList;

public class WordCensorer {
    public static String censorWord(String inputText, String wordToCensor) {
        StringBuilder toReturn = new StringBuilder();
        for (String wordInInputText: inputText.split(" ")) {
            if (wordInInputText.startsWith(wordToCensor)) {
                toReturn.append(" " + "X".repeat(wordInInputText.length()));
            } else {
                toReturn.append(" " + wordInInputText);
            }
        }
        if (toReturn.isEmpty()) {
            return "";
        }
        return toReturn.substring(1);
    }

    public static String censorWord(String inputText, String[] wordsToCensor) {
        String toReturn = inputText;
        for (String singleWordToCensor: wordsToCensor) {
            toReturn = censorWord(toReturn, singleWordToCensor);
        }
        return toReturn;
    }
}
