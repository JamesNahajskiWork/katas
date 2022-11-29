package org.example;

public class Main {
    public static String printDiamond(char letter) {
//        switch (letter) {
//            case 'B':
//                return " A\nB B\n A";
//            case 'C':
//                return "  A\n B B\nC   C\n B B\n  A";
//            case 'D':
//                return "   A\n  B B\n C   C\nD     D\n C   C\n  B B\n   A";
//            default:
//                return "A";
//        }

        StringBuilder toReturn = new StringBuilder();
//        int spaces = 0;
//        for (char currLetter = letter; currLetter >= 'A'; currLetter--) {
//            String lineToAdd = "";
//            lineToAdd += xSpaces(spaces);
//            lineToAdd += currLetter;
//            if (currLetter != 'A') {
//                System.out.println((int)currLetter);
//                lineToAdd += xSpaces((((int)currLetter) - 66) *2 +1);
//                lineToAdd += currLetter;
//            }
//            if (currLetter != letter) {
//                toReturn = lineToAdd + "\n" + toReturn + "\n" + lineToAdd;
//            } else {
//                toReturn = lineToAdd;
//            }
//            spaces ++;
//        }
//        return toReturn;

        for (char currLetter = 'A'; currLetter < letter; currLetter++) {
            toReturn.append(generateLine(letter, currLetter)).append("\n");
        }
        for (char currLetter = letter; currLetter >= 'A'; currLetter--) {
            toReturn.append(generateLine(letter, currLetter)).append("\n");
        }
        return toReturn.toString().stripTrailing();


    }

    private static StringBuilder generateLine(int originalLetterValue, char currentLetter) {
        int betweenSpaces = (currentLetter -66) *2 +1;
        int beforeSpaces = originalLetterValue - currentLetter;
        StringBuilder lineToAdd = new StringBuilder("");
        lineToAdd.append(xSpaces(beforeSpaces));
        lineToAdd.append(currentLetter);
        if (currentLetter != 'A') {
            lineToAdd.append(xSpaces(betweenSpaces));
            lineToAdd.append(currentLetter);
        }
        return lineToAdd;
    }
    public static String xSpaces(int x) {
        String toReturn = "";
        for (int i = 0; i < x; i++) {
            toReturn += " ";
        }
        return toReturn;
    }

}