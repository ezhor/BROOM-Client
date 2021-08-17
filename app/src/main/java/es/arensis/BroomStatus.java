package es.arensis;

import java.util.Locale;

public class BroomStatus {
    private static BroomStatus instance;
    private int motorPower;
    private int steering;
    private boolean boost;
    private int cameraRotationX;
    private int cameraRotationY;
    private boolean led;

    public static BroomStatus getInstance() {
        if(instance == null){
            instance = new BroomStatus();
        }
        return instance;
    }

    public int getMotorPower() {
        return motorPower;
    }

    public void setMotorPower(int motorPower) {
        this.motorPower = motorPower;
    }

    public int getSteering() {
        return steering;
    }

    public void setSteering(int steering) {
        this.steering = steering;
    }

    public int getCameraRotationX() {
        return cameraRotationX;
    }

    public void setCameraRotationX(int cameraRotationX) {
        this.cameraRotationX = cameraRotationX;
    }

    public int getCameraRotationY() {
        return cameraRotationY;
    }

    public void setCameraRotationY(int cameraRotationY) {
        this.cameraRotationY = cameraRotationY;
    }

    public boolean isBoost() {
        return boost;
    }

    public void setBoost(boolean boost) {
        this.boost = boost;
    }

    public boolean isLed() {
        return led;
    }

    public void setLed(boolean led) {
        this.led = led;
    }

    @Override
    public String toString() {
        return format(motorPower)
                + format(steering)
                + format(cameraRotationX)
                + format(cameraRotationY)
                + (led ? "1" : "0");
    }

    private String format(int number){
        return String.format("%04d", number);
    }
}
