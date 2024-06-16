package se.lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class main_1Test {
    Graph T = null;
    @Before
    public void setUp() throws Exception {
        T = new Graph("C:\\Users\\HITl\\Desktop\\test\\zw.txt");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testqueryBridgeWords1()
    {
        main_1 example = new main_1();
        String result = example.queryBridgeWords(T, "she", "because");
        assertEquals("No she or because in the graph!",result);
    }

    @Test
    public void testqueryBridgeWords5()
    {
        main_1 example = new main_1();
        String result = example.queryBridgeWords(T, "enjoys", "teacher");
        assertEquals("The bridge words from enjoys to teacher : a.",result);
    }

    @Test
    public void testqueryBridgeWords4()
    {
        main_1 example = new main_1();
        String result = example.queryBridgeWords(T, "money", "on");
        assertEquals("No bridge words from money to on!",result);
    }

    @Test
    public void testqueryBridgeWords3()
    {
        main_1 example = new main_1();
        String result = example.queryBridgeWords(T, "it", "that");
        assertEquals("The bridge words from it to that : is seems.",result);
    }

    @Test
    public void testqueryBridgeWords2()
    {
        main_1 example = new main_1();
        String result = example.queryBridgeWords(T, "when", "appeal");
        assertEquals("No when or appeal in the graph!",result);
    }

    @Test
    public void testqueryBridgeWords6()
    {
        main_1 example = new main_1();
        String result = example.queryBridgeWords(T, "reputation", "income");
        assertEquals("No bridge words from reputation to income!",result);
    }


}
