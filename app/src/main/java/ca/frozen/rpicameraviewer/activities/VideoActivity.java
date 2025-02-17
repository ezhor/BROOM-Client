// Copyright © 2016-2018 Shawn Baker using the MIT License.
package ca.frozen.rpicameraviewer.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import ca.frozen.library.classes.Log;
import ca.frozen.rpicameraviewer.classes.Camera;
import ca.frozen.rpicameraviewer.classes.Utils;
import ca.frozen.rpicameraviewer.R;
import es.arensis.broom.BroomStatus;
import es.arensis.broom.InputManager;

public class VideoActivity extends AppCompatActivity implements VideoFragment.OnFadeListener {
    // public constants
    public final static String CAMERA = "camera";

    // instance variables
    private FrameLayout frameLayout;
    private VideoFragment videoFragment;

    //******************************************************************************
    // onCreate
    //******************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // configure the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // initialize the logger
        Utils.initLogFile(getClass().getSimpleName());

        // load the settings and cameras
        Utils.loadData();

        // get the camera object
        Bundle data = getIntent().getExtras();
        Camera camera = data.getParcelable(CAMERA);
        Log.info("camera: " + camera.toString());

        // get the frame layout, handle system visibility changes
        frameLayout = findViewById(R.id.video);
        frameLayout.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    videoFragment.startFadeIn();
                }
            }
        });

        // set full screen layout
        int visibility = frameLayout.getSystemUiVisibility();
        visibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        frameLayout.setSystemUiVisibility(visibility);

        // create the video fragment
        videoFragment = VideoFragment.newInstance(camera, true);
        InputManager.getInstance().setBroomUI(videoFragment);
        FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();
        fragTran.add(R.id.video, videoFragment);
        fragTran.commit();
    }

    //******************************************************************************
    // onStartFadeIn
    //******************************************************************************
    @Override
    public void onStartFadeIn() {
    }

    //******************************************************************************
    // onStartFadeOut
    //******************************************************************************
    @Override
    public void onStartFadeOut() {
        // hide the status and navigation bars
        int visibility = frameLayout.getSystemUiVisibility();
        visibility |= View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        frameLayout.setSystemUiVisibility(visibility);
    }

    //******************************************************************************
    // onBackPressed
    //******************************************************************************
    @Override
    public void onBackPressed() {
        videoFragment.stop();
        super.onBackPressed();
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        InputManager.getInstance().HandleAxisInput(motionEvent);
        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        InputManager.getInstance().HandleButtonInput(keyEvent);
        return true;
    }
}
