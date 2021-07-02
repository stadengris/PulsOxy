package Controller;

/**
 * Class for limit values of Pulse and SpO2.
 */
public class Limits {

    /**
     * Short for upper pulse limit
     */
    private static short upperPulse;
    /**
     * Short for lower pulse limit
     */
    private static short lowerPulse;
    /**
     * Short for upper SpO2 limit
     */
    private static short upperSp02;
    /**
     * Short for lower SpO2 limit
     */
    private static short lowerSp02;

    public static short getUpperPulse() {
        return upperPulse;
    }

    public static short getLowerPulse() {
        return lowerPulse;
    }

    public static short getUpperSpO2() {
        return upperSp02;
    }

    public static short getLowerSpO2() {
        return lowerSp02;
    }

    /**
     * Initialize the limits of upper / lower pulse depending on the age of the user and of the upper / lower SpO2.
     * @param age   of the user.
     */
    public void initializeLimits(int age) {
        if (age >= 18) {
            upperPulse = 80;
            lowerPulse = 60;
        } else {
            upperPulse = 100;
            lowerPulse = 80;
        }
        upperSp02 = 100;
        lowerSp02 = 90;
    }
}
