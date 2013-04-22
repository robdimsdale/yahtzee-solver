package com.rmd.personal.yahtzee.main;

import org.junit.Test;

import java.io.PrintStream;

public class YahtzeeMainTest {

    @Test
    public void mainRunsWithoutException() {
        // Arrange
        PrintStream dummyStream = new PrintStream(new DummyOutputStream());
        System.setOut(dummyStream);

        // Act
        YahtzeeMain.main(null);
    }
}
