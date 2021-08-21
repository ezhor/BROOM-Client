package es.arensis.broom;

public class BroomStatus {
    private static BroomStatus instance;

    private byte motorPower;
    private byte steering;
    private byte cameraRotationX;
    private byte cameraRotationY;
    private boolean led;
    private int gear = 1;

    private static final int MIN_GEAR = 1;
    private static final int MAX_GEAR = 3;

    private static final byte GEAR_1_MAX_SPEED = 20;
    private static final byte GEAR_2_MAX_SPEED = 50;
    private static final byte GEAR_3_MAX_SPEED = 100;

    private BroomStatus() {
    }

    public static BroomStatus getInstance() {
        if (instance == null) {
            instance = new BroomStatus();
        }
        return instance;
    }

    public byte getMotorPower() {
        return motorPower;
    }

    public void setMotorPower(byte motorPower) {
        this.motorPower = motorPower;
    }

    public byte getSteering() {
        return steering;
    }

    public void setSteering(byte steering) {
        this.steering = steering;
    }

    public byte getCameraRotationX() {
        return cameraRotationX;
    }

    public void setCameraRotationX(byte cameraRotationX) {
        this.cameraRotationX = cameraRotationX;
    }

    public byte getCameraRotationY() {
        return cameraRotationY;
    }

    public void setCameraRotationY(byte cameraRotationY) {
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
            gear++;
        }
    }

    public void increaseGear() {
        if (gear < MAX_GEAR) {
            gear++;
        }
    }

    public byte maxSpeed() {
        byte maxSpeed;
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
                + (led ? "1" : "0");
    }

    public byte[] toBytes() {
        return new byte[]{
                motorPower,
                steering,
                cameraRotationX,
                cameraRotationY,
                (byte) (led ? 1 : 0),
                '\n'
        };
    }

    private String format(int number) {
        return String.format("%04d", number);
    }
}
