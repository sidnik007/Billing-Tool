package com.nelkinda.billingtool.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputHandlerTest {

    @Test
    @DisplayName("Returns a list of products to be displayed")
    void testGenerateInput() {
        final InputHandler handler = new InputHandler();
        final String expectedDisplay = "Please select from the following items\n\n"
                + "|  **product** | **unit**   | **cost** |\n"
                + "| :---  | :---: | :---: |\n"
                + " | 1 | soup | tin | 0.65 | \n"
                + " | 2 | bread | loaf | 0.80 | \n"
                + " | 3 | milk | bottle | 1.30 | \n"
                + " | 4 | apples | single | 0.10 | \n";
        final String actualDisplay = handler.generateInput();
        assertEquals(expectedDisplay, actualDisplay);
    }
}
