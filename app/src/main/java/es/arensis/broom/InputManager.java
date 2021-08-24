package es.arensis.broom;

import android.view.KeyEvent;
import android.view.MotionEvent;

public class InputManager {
    private static InputManager instance;

    private IBroomUI broomUI;
    private MotionEvent motionEvent;
    private KeyEvent keyEvent;

    private InputManager() {
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    public void setBroomUI(IBroomUI broomUI) {
        this.broomUI = broomUI;
    }

    public void HandleAxisInput(MotionEvent motionEvent) {
        this.motionEvent = motionEvent;
        updateSpeed();
        updateSteering();
        updateCameraRotation();
        broomUI.UpdateBroomUI();
    }

    public void HandleButtonInput(KeyEvent keyEvent) {
        this.keyEvent = keyEvent;
        updateLed();
        updateGear();
        broomUI.UpdateBroomUI();
    }

    // Axes

    private void updateSpeed() {
        int speed = (int) (motionEvent.getAxisValue(MotionEvent.AXIS_BRAKE) *
                        -BroomStatus.getInstance().maxSpeed());

        if (speed < 0) {
            BroomStatus.getInstance().setMotorPower(speed);
        } else {
            speed = (int) (motionEvent.getAxisValue(MotionEvent.AXIS_GAS) *
                    BroomStatus.getInstance().maxSpeed());
        }

        BroomStatus.getInstance().setMotorPower(speed);
    }

    private void updateSteering() {
        BroomStatus.getInstance().setSteering(
                (int) (motionEvent.getAxisValue(MotionEvent.AXIS_X) * 100));
    }

    private void updateCameraRotation() {
        BroomStatus.getInstance().setCameraRotationX(
                (int) (motionEvent.getAxisValue(MotionEvent.AXIS_Z) * 100));
        BroomStatus.getInstance().setCameraRotationY(
                (int) (motionEvent.getAxisValue(MotionEvent.AXIS_RZ) * 100));
    }

    // Buttons

    private void updateLed() {
        if (isButtonDown(KeyEvent.KEYCODE_BUTTON_Y)) {
            BroomStatus.getInstance().setLed(!BroomStatus.getInstance().isLed());
        }
    }

    private void updateGear() {
        if (isButtonDown(KeyEvent.KEYCODE_BUTTON_R1)) {
            BroomStatus.getInstance().increaseGear();
        } else if (isButtonDown(KeyEvent.KEYCODE_BUTTON_L1)) {
            BroomStatus.getInstance().decreaseGear();
        }
    }

    private boolean isButtonDown(int keyCode) {
        return keyEvent.getKeyCode() == keyCode
                && keyEvent.getAction() == KeyEvent.ACTION_DOWN;
    }

    public interface IBroomUI {
        public void UpdateBroomUI();
    }
}
