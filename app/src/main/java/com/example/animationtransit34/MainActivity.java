package com.example.animationtransit34;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    ViewGroup pnjLayout;     // NB ConstraintLayout and RelativeLayout subclasses of ViewGroup

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pnjLayout = (ViewGroup) findViewById(R.id.pnj_layout);

        pnjLayout.setOnTouchListener(
                new ConstraintLayout.OnTouchListener()      // oops. NB capital case again!
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
//                        return false;
                        moveButton();       // sensibly, use new bespoke method/function
                        return true;        // true: 'touch' event handled
                    }
                }
                );


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /// button in corner, and bigger, with animation
    public void moveButton()
    {
        View pnjButton = findViewById(R.id.pnj_button);
        View pnjConstraintLayout = findViewById(R.id.pnj_layout);

        // 1) reposition button 2) resize button

        // video's code using RelativeLayout
//        ConstraintLayout.LayoutParams positionRules = new ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT);
//        // width & height
//
//        positionRules.addRule(ConstraintLayout.TEXT_ALIGNMENT_TEXT_END, ConstraintLayout.TR);
//        positionRules.addRule(ConstraintLayout.TEXT_ALIGNMENT_TEXT_END);


        // if using new-fangled ConstraintLayout instead of RelativeLayout
        // 1) Reposition button
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.pnj_layout);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(R.id.pnj_button, ConstraintSet.BOTTOM, R.id.pnj_layout, ConstraintSet.BOTTOM);
        // stick bottom of button to bottom of layout
        constraintSet.connect(R.id.pnj_button, ConstraintSet.RIGHT, R.id.pnj_layout, ConstraintSet.RIGHT);
        // stick right of button to right of layout
        constraintSet.applyTo(constraintLayout);

        // 2) Resize button
        ViewGroup.LayoutParams sizeRules = pnjButton.getLayoutParams();
        sizeRules.width = 450;
        sizeRules.height = 300;
        pnjButton.setLayoutParams(sizeRules);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
