package Controller;

public class Limits {

    private static short upperPulse;
    private static short lowerPulse;
    private static short upperSpO2;
    private static short lowerSpO2;

    public Limits(){
        this.upperPulse = -1;
        this.lowerPulse = -1;
        this.upperSpO2 = -1;
        this.lowerSpO2 = -1;
    }

    public static short getUpperPulse() {
        return upperPulse;
    }

    public static short getLowerPulse() {
        return lowerPulse;
    }

    public static short getUpperSpO2() {
        return upperSpO2;
    }

    public static short getLowerSpO2() {return lowerSpO2; }

    public void initializeLimits(int age) {
        if (age >= 18) {
            upperPulse = 80;
            lowerPulse = 60;
        } else {
            upperPulse = 100;
            lowerPulse = 80;
        }
        upperSpO2 = 100;
        lowerSpO2 = 90;
    }
}
