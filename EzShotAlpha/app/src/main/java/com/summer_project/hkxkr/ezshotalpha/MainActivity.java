package com.summer_project.hkxkr.ezshotalpha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    // Noted: final container may not be button, this is just initial design for easier implementation on onClickEvent
    private final Map<Integer, Button> registeredFolders = new HashMap<>();
    /* todo: store this id outside this file so it becomes "unique" (not static since the methods aren't static,
             can look into whether methods can be static) */
    private int currentId = 0;
    public final int HOLD_DOWN_TIME_MS = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // invoke when the new folder UI button is pressed, will call initNewFolder function to actually initialise a new folder
    public void createNewFolder(View view) {
        LinearLayout layout = (LinearLayout)findViewById(R.id.main_folder_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //todo: change to grid layout, so that the folder will wrapped on screen edge


        layout.addView(initNewFolder(), params);

    }

    public Map<Integer, Button> getRegisteredFolders() {
        return registeredFolders;
    }

    // actually initialise a new folder, also set the folder button to start activity-inside_folder when clicked and activity-editing_menu when holded
    public Button initNewFolder() {
        final Button myButton = new Button(this);
        myButton.setId(currentId);
        currentId += 1;
        myButton.setText("Newly added");

        // hold the button for 1.5 second to activate edit menu
        myButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                boolean holded = false;
                final Timer timer = new Timer();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        view.setPressed(true);
                        // Start count down how long the user has been holding for
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                //todo: activate activity-editing_menu
                            }
                        }, HOLD_DOWN_TIME_MS);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                        view.setPressed(false);
                        timer.cancel();
                        // Stop count down how long the user has been holding for
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_MOVE:
                    default:
                        break;
                }

                return true;
            }
        });

        return myButton;
    }

    public void activateHead(View view) {
        //todo: activate floating head
    }
}
