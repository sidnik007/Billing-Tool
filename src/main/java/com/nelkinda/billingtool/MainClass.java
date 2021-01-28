package com.nelkinda.billingtool;

import com.nelkinda.billingtool.input.InputHandler;

@SuppressWarnings("PMD.UseUtilityClass")
public class MainClass {
    public static void main(final String[] args) {
        new InputHandler().handleInput();
    }
}
