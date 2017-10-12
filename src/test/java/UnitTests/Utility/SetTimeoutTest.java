package UnitTests.Utility;

import Utility.SetTimeout;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;


/**
 * This class provides test cases for the SetTimeout class.
 */
public class SetTimeoutTest {

    /**
     * A test for the setTimeout with usual input.
     */
    @Test
    void setTimeoutTest1() {
        SetTimeout st = new SetTimeout("Gina", 500, () -> {
            assert true; });
        long timer = System.currentTimeMillis();
        assertTimeout(ofMillis(1000), () -> {
            st.run();
        });

        if (System.currentTimeMillis() - timer < 500) {
            assert false;
        }
    }

    /**
     * A test for the setTimeout with usual input.
     */
    @Test
    void setTimeoutTest3() {
        SetTimeout st = new SetTimeout("Gina", 1200, () -> {
            assert true; });
        long timer = System.currentTimeMillis();
        assertTimeout(ofMillis(1500), () -> {
            st.run();
        });

        if (System.currentTimeMillis() - timer < 1200) {
            assert false;
        }
    }
}
