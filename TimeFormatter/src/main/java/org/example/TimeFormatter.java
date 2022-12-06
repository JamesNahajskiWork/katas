package org.example;

import java.security.KeyStore;
import java.util.*;

public class TimeFormatter {
    public static String formatDuration(int seconds) {
        // your code goes here
//        StringBuilder toReturn = new StringBuilder();
        List<Map.Entry<String, Integer>> timeUnits = new ArrayList<>();
        int remainingSeconds = seconds;
        remainingSeconds = addTimeUnit(timeUnits, remainingSeconds, 31536000, "year");
        remainingSeconds = addTimeUnit(timeUnits, remainingSeconds, 86400, "day");
        remainingSeconds = addTimeUnit(timeUnits, remainingSeconds, 3600, "hour");
        remainingSeconds = addTimeUnit(timeUnits, remainingSeconds, 60, "minute");
        addTimeUnit(timeUnits, remainingSeconds, 1, "second");
        return createStringToReturn(timeUnits);
    }

    private static int addTimeUnit(List<Map.Entry<String, Integer>> timeUnits, int seconds, int secondsPerTimeUnit, String timeUnitString) {
        int timeUnitCount = Math.floorDiv(seconds, secondsPerTimeUnit);
        if (timeUnitCount > 0) {
            if (timeUnitCount > 1) {
                timeUnits.add(Map.entry(timeUnitString + "s", timeUnitCount));
            } else {
                timeUnits.add(Map.entry(timeUnitString, timeUnitCount));
            }
        }
        return seconds - (secondsPerTimeUnit * timeUnitCount);
    }


    private static String createStringToReturn(List<Map.Entry<String, Integer>> timeUnits) {
        StringBuilder toReturn = new StringBuilder();
        if (timeUnits.size() < 1) {
            toReturn.append("now");
        }
//        Set<Map.Entry<String, Integer>> asd = timeUnits.entrySet();
        for (int i = 0; i < timeUnits.size(); i++) {
            String key = timeUnits.get(i).getKey();
            Integer value = timeUnits.get(i).getValue();
            toReturn.append(value);
            toReturn.append(" ");
            toReturn.append(key);
            if (i < timeUnits.size() -2) {
                toReturn.append(", ");
            } else if (i < timeUnits.size() -1) {
                toReturn.append(" and ");
            }
        }
        return toReturn.toString();
    }
}