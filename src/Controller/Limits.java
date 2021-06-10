package Controller;

public class Limits {

    private static short upperPulse;
    private static short lowerPulse;
    private static short upperSp02;
    private static short lowerSP02;
    private int age;

    public void setAge(int age) {
    }

    public static short getUpperPulse() {
        return upperPulse;
    }

    public static short getLowerPulse() {
        return lowerPulse;
    }

    public static short getUpperSp02() {
        return upperSp02;
    }

    public static short getLowerSP02() {
        return lowerSP02;
    }
}
