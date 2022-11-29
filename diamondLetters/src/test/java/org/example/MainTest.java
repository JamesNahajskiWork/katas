package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MainTest {
    static final String B_OUTPUT = """
             A
            B B
             A""";
    static final String C_OUTPUT = """
              A
             B B
            C   C
             B B
              A""";
    static final String D_OUTPUT = """
              A
             B B
            C   C
           D     D
            C   C
             B B
              A""";
    static final String E_OUTPUT = """
              A
             B B
            C   C
           D     D
          E       E
           D     D
            C   C
             B B
              A""";
    @ParameterizedTest
    @MethodSource("diamondTestSource")
    public void testDiamondPrinter(char input, String expectedOutput) {
        String output = Main.printDiamond(input);
        System.out.println("\nInput = " + input);
        System.out.println(output);
        assertEquals(expectedOutput, output);
    }

    public static List<Arguments> diamondTestSource() {
        return List.of(
                Arguments.of('A', "A"),
                Arguments.of('B', B_OUTPUT),
                Arguments.of('C', C_OUTPUT),
                Arguments.of('D', D_OUTPUT),
                Arguments.of('E', E_OUTPUT)

        );
    }


}