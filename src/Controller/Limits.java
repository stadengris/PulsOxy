package Controller;

public class Limits {

    private static short upperPulse;
    private static short lowerPulse;
    private static short upperSp02;
    private static short lowerSp02;
    private int age;

    public void setAge(int ageOfUser) {
        age = ageOfUser;
    }

    public short getUpperPulse() {
        if (age >= 18) {
            upperPulse = 80;
        } else {
            upperPulse = 100;
        }
        return upperPulse;
    }

    public short getLowerPulse() {
        if (age >= 18) {
            lowerPulse = 100;
        } else {
            lowerPulse = 80;
        }
        return lowerPulse;
    }

    public short getUpperSp02() {
        upperSp02 = 100;
        return upperSp02;
    }

    public short getLowerSp02() {
        lowerSp02 = 90;
        return lowerSp02;
    }

}
