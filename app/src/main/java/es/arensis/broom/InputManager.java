package es.arensis.broom;

import android.view.KeyEvent;
import android.view.MotionEvent;

public class InputManager {
    private static InputManager instance;

    private InputManager() {
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    public void HandleAxisInput(MotionEvent motionEvent) {
        setSpeed(motionEvent);
        setSteering(motionEvent);
        setCameraRotation(motionEvent);
    }

    public void HandleButtonInput(KeyEvent keyEvent) {
        setLed(keyEvent);
        setGear(keyEvent);
    }

    private void setSpeed(MotionEvent motionEvent) {
        byte speed = (byte) (motionEvent.getAxisValue(MotionEvent.AXIS_BRAKE) *
                -BroomStatus.getInstance().maxSpeed());

        if (speed > 0) {
            BroomStatus.getInstance().setMotorPower(speed);
        } else {
            speed = (byte) (motionEvent.getAxisValue(MotionEvent.AXIS_GAS) *
                    BroomStatus.getInstance().maxSpeed());
        }

        BroomStatus.getInstance().setMotorPower(speed);
    }

    private void setSteering(MotionEvent motionEvent) {
        BroomStatus.getInstance().setSteering(
                (byte) motionEvent.getAxisValue(MotionEvent.AXIS_X * 100));
    }

    private void setCameraRotation(MotionEvent motionEvent) {
        BroomStatus.getInstance().setCameraRotationX(
                (byte) motionEvent.getAxisValue(MotionEvent.AXIS_Z * 100));
        BroomStatus.getInstance().setCameraRotationY(
                (byte) motionEvent.getAxisValue(MotionEvent.AXIS_RZ * 100));
    }

    private void setLed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BUTTON_Y) {
            BroomStatus.getInstance().setLed(!BroomStatus.getInstance().isLed());
        }
    }

    private void setGear(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BUTTON_R1) {
            BroomStatus.getInstance().increaseGear();
        } else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BUTTON_L1) {
            BroomStatus.getInstance().decreaseGear();
        }
    }
}
