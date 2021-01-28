package com.nelkinda.billingtool.input;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputHandlerTest {

    private ByteArrayOutputStream testOut;
    private PrintStream standardOut;

    private ByteArrayInputStream testIn;
    private InputStream standardIn;

    private InputHandler inputHandler;

    @BeforeEach
    void setup() {
        standardOut = System.out;
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        standardIn = System.in;
        System.setIn(testIn);

        inputHandler = new InputHandler();
    }

    @Test
    @DisplayName("Returns a list of products to be displayed")
    void testGenerateInput() {
        final String expectedDisplay = "Please select from the following items\n\n"
                + "|  **product** | **unit**   | **cost** |\n"
                + "| :---  | :---: | :---: |\n"
                + " | 1 | soup | tin | 0.65 | \n"
                + " | 2 | bread | loaf | 0.80 | \n"
                + " | 3 | milk | bottle | 1.30 | \n"
                + " | 4 | apples | single | 0.10 | \n";
        final String actualDisplay = inputHandler.generateInput();
        assertEquals(expectedDisplay, actualDisplay);
    }

    // Fragile test. Added for increasing code coverage
    @Test
    @DisplayName("test interaction when 1 tins of soup is selected")
    void testInteractionWithOneTinOfSoup() {
        final String soup = "1 ";
        final String soupQuantity = "1 ";
        final String calculate = "c";
        provideInput(soup + soupQuantity + calculate);
        inputHandler.handleInput();
        assertTrue(testOut.toString().contains("0.65"));
    }

    // Fragile test. Added for increasing code coverage
    @Test
    @DisplayName("test interaction when 2 tins of soup and 1 loaf of bread is selected")
    void testInteractionWithTwoSoupsAndOneBread() {
        final String soup = "1 ";
        final String soupQuantity = "2 ";
        final String bread =  "2 ";
        final String breadQuantity = "1 ";
        final String calculate = "C ";
        provideInput(soup + soupQuantity + bread + breadQuantity + calculate);
        inputHandler.handleInput();
        assertTrue(testOut.toString().contains("2.10"));
    }

    private void provideInput(final String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }
}
