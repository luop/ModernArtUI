package com.project.luo.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.util.Log;


public class MainActivity extends Activity {

    private static final String TAG = "ModernArtUI";

    private TextView topLeft;
    private TextView bottomLeft;
    private TextView topRight;
    private TextView centerRight;
    private TextView bottomRight;
    private SeekBar changeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topLeft = (TextView) findViewById(R.id.topLeft);
        bottomLeft = (TextView) findViewById(R.id.bottomLeft);
        topRight = (TextView) findViewById(R.id.topRight);
        centerRight = (TextView) findViewById(R.id.centerRight);
        bottomRight = (TextView) findViewById(R.id.bottomRight);

        changeColor = (SeekBar) findViewById(R.id.changeColor);

        changeColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // Change color of the rectangles
                updateBackground();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

        });

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

        //More Information option is selected
        if (id == R.id.info) {

            // Show More Information dialog
            popUpAlert();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Change color of the rectangles
    private void updateBackground()
    {
        Log.i(TAG,"Entered updateBackground()");

        // get the current progress of the seekbar
        int progress = changeColor.getProgress();

        // get the current colors of 5 rectangles
        int topLeftColor = ((ColorDrawable) topLeft.getBackground()).getColor();
        int bottomLeftColor = ((ColorDrawable) bottomLeft.getBackground()).getColor();
        int topRightColor = ((ColorDrawable) topRight.getBackground()).getColor();
        int centerRightColor = ((ColorDrawable) centerRight.getBackground()).getColor();
        int bottomRightColor = ((ColorDrawable) bottomRight.getBackground()).getColor();

        // gradually change rectangles' color if they are not white or grey
        // increase R, G, and B by the progress
        // Mod 256 so neither of those will be in [0...255]
        if (topLeftColor != Color.GRAY && topLeftColor != Color.WHITE){
            topLeft.setBackgroundColor(Color.rgb((Color.red(topLeftColor) + progress) % 256, (Color.green(topLeftColor) + progress) % 256, (Color.blue(topLeftColor) + progress) % 256));
        }

        if (bottomLeftColor != Color.GRAY && bottomLeftColor != Color.WHITE){
            bottomLeft.setBackgroundColor(Color.rgb((Color.red(bottomLeftColor) + progress) % 256, (Color.green(bottomLeftColor) + progress) % 256, (Color.blue(bottomLeftColor) + progress) % 256));
        }

        if (topRightColor != Color.GRAY && topRightColor != Color.WHITE){
            topRight.setBackgroundColor(Color.rgb((Color.red(topRightColor) + progress) % 256, (Color.green(topRightColor) + progress) % 256, (Color.blue(topRightColor) + progress) % 256));
        }

        if (centerRightColor != Color.GRAY && centerRightColor != Color.WHITE){
            centerRight.setBackgroundColor(Color.rgb((Color.red(centerRightColor) + progress) % 256, (Color.green(centerRightColor) + progress) % 256, (Color.blue(centerRightColor) + progress) % 256));
        }

        if (bottomRightColor != Color.GRAY && bottomRightColor != Color.WHITE){
            bottomRight.setBackgroundColor(Color.rgb((Color.red(bottomRightColor) + progress) % 256, (Color.green(bottomRightColor) + progress) % 256, (Color.blue(bottomRightColor) + progress) % 256));
        }
    }

    // Show More Information dialog
    private void popUpAlert(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);

        alertBuilder.setTitle("Inspired by the works of artists such as Piet Mondrian and Ben Nicholson");
        alertBuilder.setMessage("Click below to learn more!");

        alertBuilder.setPositiveButton("Visit MOMA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org/"));
                startActivity(i);
            }
        });

        alertBuilder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertBuilder.create();

        alertDialog.show();

    }
}
