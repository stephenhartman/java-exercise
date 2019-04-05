package validator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test.
     */
    @Test
    public void testEasy() {
        Input easy = new Input("This(is)a(passing)test(that)is(not)long");
        assertTrue((easy).process());
    }

    @Test
    public void testMedium() {
        Input medium = new Input("This(is(a(more)complicated(test)that)makes)things(a)bit(more)difficult");
        assertTrue((medium).process());
    }

    @Test
    public void testHard() {
        Input hard = new Input("This(is(a)much)more(complicated)string(than(we)imagined)though(it(really)does(not)cover)all(the)bases");
        assertTrue((hard).process());
    }

    @Test
    public void testFail1() {
        Input fail = new Input("This(test(does)not)pass)");
        assertFalse((fail).process());
    }

    @Test
    public void testFail2() {
        Input fail2 = new Input("This(((())))(");
        assertFalse((fail2).process());
    }

}
