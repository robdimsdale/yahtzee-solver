package com.rmd.personal.yahtzee.main;

import java.io.IOException;
import java.io.OutputStream;

public class DummyOutputStream extends OutputStream {
    @Override
    public void write(int b) throws IOException {
        //NO-OP
    }
}
