package se.lab1;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class randwalkTest {
    Graph T = null;
    @Before
    public void setUp() throws Exception {
        T = new Graph("C:\\Users\\HITl\\Desktop\\test\\zw.txt");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testrandwalk3() {
        main_1 example = new main_1();
        String result = example.randomWalk(T, T.num, 90);
        assertEquals(null,result);
    }

    @Test
    public void testrandwalk1() {
        main_1 example = new main_1();
        String result = example.randomWalk(T, T.num, 0);
        assertEquals("talking",result);
    }

    @Test
    public void testrandwalk2() {
        main_1 example = new main_1();
        T.visited[4][5]=1;
        String result = example.randomWalk(T, T.num, 4);
        assertEquals("of no more",result);
    }

}
