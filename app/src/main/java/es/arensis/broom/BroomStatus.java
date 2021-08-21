package es.arensis.broom;

public class BroomStatus {
    private static BroomStatus instance;
    private byte motorPower;
    private byte steering;
    private byte cameraRotationX;
    private byte cameraRotationY;
    private boolean led;

    public static BroomStatus getInstance() {
        if(instance == null){
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
