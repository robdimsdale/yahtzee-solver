package com.rmd.personal.yahtzee.core;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

public class RulesTest {

    @Test
    public void hasOnlyPrivateNoArgConstructor() throws Exception {
        // Act
        Constructor[] constructors = Rules.class.getDeclaredConstructors();

        // Assert
        assertEquals(1, constructors.length);
        assertFalse(constructors[0].isAccessible());
        assertEquals(0, constructors[0].getParameterTypes().length);

        // Instantiate constructor to increase line-coverage
        constructors[0].setAccessible(true);
        constructors[0].newInstance();
    }
}
