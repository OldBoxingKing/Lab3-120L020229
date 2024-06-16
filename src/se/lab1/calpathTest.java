package se.lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class calpathTest {
    Graph T = null;
    @Before
    public void setUp() throws Exception {
        T = new Graph("C:\\Users\\HITl\\Desktop\\test\\zw.txt");

    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void calcShortestPath1() {
    }
}