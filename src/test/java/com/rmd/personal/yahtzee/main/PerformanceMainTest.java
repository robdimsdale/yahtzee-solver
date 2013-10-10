package com.rmd.personal.yahtzee.main;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class PerformanceMainTest {

    private PerformanceMain main;
    private DummyOutputStream dummyStream;

    @Before
    public void setup() throws Exception {
        dummyStream = new DummyOutputStream();
        PrintStream printStream = new PrintStream(dummyStream);
        System.setOut(printStream);

        Class<?>[] parameterTypes = null;
        Constructor<? extends PerformanceMain> constructor
                = PerformanceMain.class.getDeclaredConstructor(parameterTypes);
        constructor.setAccessible(true);
        Object[] initArgs = null;
        main = constructor.newInstance(initArgs);
    }

    @Test
    public void hasOnlyPrivateNoArgConstructor() throws Exception {
        // Act
        Constructor[] constructors = YahtzeeMain.class.getDeclaredConstructors();

        // Assert
        assertEquals(1, constructors.length);
        assertFalse(constructors[0].isAccessible());
        assertEquals(0, constructors[0].getParameterTypes().length);

        // Instantiate constructor to increase line-coverage
        constructors[0].setAccessible(true);
        constructors[0].newInstance();
    }

    @Test
    public void runWritesToDummyStream() throws Exception {
        // Arrange
        Method run = main.getClass().getDeclaredMethod("run");
        run.setAccessible(true);

        // Act
        run.invoke(main);

        // Assert
        assertTrue(dummyStream.getData().size() > 0);
    }
}
