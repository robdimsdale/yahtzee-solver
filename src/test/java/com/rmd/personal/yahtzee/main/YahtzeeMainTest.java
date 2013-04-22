package com.rmd.personal.yahtzee.main;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class YahtzeeMainTest {

    private YahtzeeMain main;

    @Before
    public void setup() throws Exception {
        PrintStream dummyStream = new PrintStream(new DummyOutputStream());
        System.setOut(dummyStream);

        Constructor<? extends YahtzeeMain> constructor = YahtzeeMain.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        main = constructor.newInstance(null);
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
    public void runCallsThroughToPrintHelper() throws Exception {
        // Arrange
        PrintHelper mockPrintHelper = mock(PrintHelper.class);
        Field field = main.getClass().getDeclaredField("printHelper");
        field.setAccessible(true);
        field.set(main, mockPrintHelper);

        Method run = main.getClass().getDeclaredMethod("run");
        run.setAccessible(true);

        // Act
        run.invoke(main);

        // Assert
        verify(mockPrintHelper).printScoreTable();
        verify(mockPrintHelper).printAveragesTable();
        verify(mockPrintHelper).printScoreTypeAverages();
    }
}
