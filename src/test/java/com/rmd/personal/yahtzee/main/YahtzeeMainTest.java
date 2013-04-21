package com.rmd.personal.yahtzee.main;

import org.junit.Test;

import java.io.OutputStream;
import java.io.PrintStream;

public class YahtzeeMainTest {

    @Test
    public void mainRunsWithoutException() {
        PrintStream dummyStream    = new PrintStream(new OutputStream() {
            public void write(int b) {
                //NO-OP
            }
        });
        System.setOut(dummyStream);
        YahtzeeMain.main(null);
    }
}
