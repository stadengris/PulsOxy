package Controller;

public class Limits {

    private static short upperPulse;
    private static short lowerPulse;
    private static short upperSp02;
    private static short lowerSp02;
    private int age;

    public static short getUpperPulse() {
        return upperPulse;
    }

    public static short getLowerPulse() {
        return lowerPulse;
    }

    public static short getUpperSp02() {
        return upperSp02;
    }

    public static short getLowerSp02() {
        return lowerSp02;
    }

    public void setAge(int ageOfUser) {
        age = ageOfUser;
    }

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
