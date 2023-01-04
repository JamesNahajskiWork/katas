package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ObservedPin {
    private static Map<String, String[]> adjacentKeys = Map.of(
            "1", new String[]{"1", "2", "4"},
    "2", new String[]{"1", "2", "3", "5"},
    "3", new String[]{"2", "3", "6"},
    "4", new String[]{"1", "4", "5", "7"},
    "5", new String[]{"2", "4", "5", "6", "8"},
    "6", new String[]{"3", "5", "6", "9"},
    "7", new String[]{"4", "7", "8"},
    "8", new String[]{"5", "7", "8", "9", "0"},
    "9", new String[]{"6", "8", "9"},
    "0", new String[]{"8", "0"}
            );

    public static List<String> getPINs(String observed) {
      try {
          List<String> currentPins = List.of(adjacentKeys.get(observed.substring(0,1)));
          if (observed.length() <= 1) {
              return currentPins;
          }
          String[] observedEntries = observed.substring(1).split("");
          for (String pin : observedEntries) {
              List<String> newPins = new ArrayList<>();
              for (String existingPin : currentPins) {
                  for (String newAddition : adjacentKeys.get(pin)) {
                      newPins.add(existingPin + newAddition);
                  }
              }
              currentPins = newPins;
          }
          return currentPins;
      } catch (Exception e) {

          return null;
      }
    } // getPINs

} // ObservedPin
