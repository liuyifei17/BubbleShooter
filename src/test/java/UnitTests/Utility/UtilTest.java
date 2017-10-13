package UnitTests.Utility;

import Utility.Util;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * This class provides test cases for the Util class.
 */
public class UtilTest {

    /**
     * A test for the getDistance method with usual input.
     */
    @Test
    void getDistanceTest() {
        double[] arrange = {1, 10, 7, 2};

        double distance = Util.getDistance(arrange[0], arrange[1], arrange[2], arrange[3]);

        assertThat(distance).isEqualTo(10);
    }

    /**
     * A test for the getDistance method with zeroes as input.
     */
    @Test
    void getDistanceTest_zeroes() {
        double[] arrange = {0, 0, 0, 0};

        double distance = Util.getDistance(arrange[0], arrange[1], arrange[2], arrange[3]);

        assertThat(distance).isEqualTo(0);
    }

    /**
     * A test for the getDistance method with negative input.
     */
    @Test
    void getDistanceTest_negatives() {
        double[] arrange = {-2, 2, 2, -1};

        double distance = Util.getDistance(arrange[0], arrange[1], arrange[2], arrange[3]);

        assertThat(distance).isEqualTo(5);
    }


    /**
     * A test for the calculateRotatedCoordinates method for a rotation of 90deg.
     */
    @Test
    void calculateRotatedCoordinatesTest_90deg() {
        double rotatingX = 1;
        double rotatingY = -1;
        double centerX = 0;
        double centerY = 0;
        double rotation = -90;


        double[] result = Util.calculateRotatedCoordinates(rotatingX, rotatingY, centerX, centerY,
                rotation);


        assertAll("", () -> assertThat(result[0]).isBetween(0.999, 1.001),
                () -> assertThat(result[1]).isBetween(0.999, 1.001));
    }

    /**
     * A test for the calculateRotatedCoordinates method for a rotation of 60deg.
     */
    @Test
    void calculateRotatedCoordinatesTest_60deg() {
        double rotatingX = -1;
        double rotatingY = 0;
        double centerX = 0;
        double centerY = 0;
        double rotation = 60;


        double[] result = Util.calculateRotatedCoordinates(rotatingX, rotatingY, centerX, centerY,
                rotation);

        assertAll("", () -> assertThat(result[0]).isBetween(0.499, 0.501),
                () -> assertThat(result[1]).isBetween(0.865, 0.867));
    }

    /**
     * A test for the randomBetween method.
     */
    @Test
    void randomBetweenTest_100draws() {
        for (int i = 0; i < 100; i++) {
            int draw = Util.randomBetween(1, 10);
            assertThat(draw).isBetween(1, 10);
        }
    }

    /**
     * A test for the randomBetween method with zeroes as input.
     */
    @Test
    void randomBetweenTest_00() {
        int draw = Util.randomBetween(0, 0);
        assertThat(draw).isEqualTo(0);
    }

    /**
     * A test for the randomBetween method with incorrect input.
     */
    @Test
    void randomBetweenTest_53() {
        int draw = Util.randomBetween(5, 3);
        assertThat(draw).isEqualTo(0);
    }

}
