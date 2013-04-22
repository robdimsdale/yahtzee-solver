package com.rmd.personal.yahtzee.main;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

public class PrintHelperTest {

    private PrintHelper printHelper = new PrintHelper();

    @Before
    public void setup() {
        System.setOut(new PrintStream(new DummyOutputStream()));
    }

    @Test
    public void printScoreTableReturnsWithoutException() {
        // Act
        printHelper.printScoreTable();
    }

    @Test
    public void printScoreTypeAveragesReturnsWithoutException() {
        // Act
        printHelper.printScoreTypeAverages();
    }

    @Test
    public void printAveragesTableReturnsWithoutException() {
        // Act
        printHelper.printAveragesTable();
    }
}
