package com.rmd.personal.yahtzee.main;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DummyOutputStream extends OutputStream {

    private List<Integer> data;

    public DummyOutputStream() {
        this.data = new ArrayList<Integer>();
    }

    protected List<Integer> getData() {
        return data;
    }

    @Override
    public void write(int b) throws IOException {
        this.getData().add(b);
    }
}
