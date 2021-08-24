package es.arensis.broom;

public class BroomStatus {
    private static BroomStatus instance;

    private int motorPower;
    private int steering;
    private int cameraRotationX;
    private int cameraRotationY;
    private boolean led;
    private int gear = 1;

    private static final int MIN_GEAR = 1;
    private static final int MAX_GEAR = 3;

    private static final int GEAR_1_MAX_SPEED = 20;
    private static final int GEAR_2_MAX_SPEED = 50;
    private static final int GEAR_3_MAX_SPEED = 100;

    private BroomStatus() {
    }

    public static BroomStatus getInstance() {
        if (instance == null) {
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

    public boolean isLed() {
        return led;
    }

    public void setLed(boolean led) {
        this.led = led;
    }

    public int getGear() {
        return gear;
    }

    public void decreaseGear() {
        if (gear > MIN_GEAR) {
            gear--;
            motorPower = maxSpeed(gear) * motorPower / maxSpeed(gear + 1);
        }
    }

    public void increaseGear() {
        if (gear < MAX_GEAR) {
            gear++;
            motorPower = maxSpeed(gear) * motorPower / maxSpeed(gear - 1);
        }
    }

    public int maxSpeed() {
        return maxSpeed(gear);
    }

    private int maxSpeed(int gear) {
        int maxSpeed;
        switch (gear) {
            case 1:
            default:
                maxSpeed = GEAR_1_MAX_SPEED;
                break;
            case 2:
                maxSpeed = GEAR_2_MAX_SPEED;
                break;
            case 3:
                maxSpeed = GEAR_3_MAX_SPEED;
        }
        return maxSpeed;
    }

    @Override
    public String toString() {
        return format(motorPower)
                + format(steering)
                + format(cameraRotationX)
                + format(cameraRotationY)
                + (led ? "1" : "0")
                + "\n";
    }

    private String format(int number) {
        return String.format("%04d", number);
    }
}
