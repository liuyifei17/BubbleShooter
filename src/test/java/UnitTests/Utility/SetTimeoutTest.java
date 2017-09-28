package UnitTests.Utility;

import Utility.SetTimeout;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;


/**
 * This class provides test cases for the SetTimeout class
 */
public class SetTimeoutTest {

    @Test
    public void SetTimeoutTest1() {
        SetTimeout st = new SetTimeout("Gina", 500, () -> { assert true; });
        long Timer = System.currentTimeMillis();
        assertTimeout(ofMillis(1000), () -> {
            st.run();
        });

        if(System.currentTimeMillis() - Timer < 500) assert false;
    }

    @Test
    public void SetTimeoutTest3() {
        SetTimeout st = new SetTimeout("Gina", 1200, () -> { assert true; });
        long Timer = System.currentTimeMillis();
        assertTimeout(ofMillis(1500), () -> {
            st.run();
        });

        if(System.currentTimeMillis() - Timer < 1200) assert false;
    }
}
