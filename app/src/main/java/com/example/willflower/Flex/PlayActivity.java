package com.example.willflower.Flex;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    private static final int circleRadius = 0; // TODO this needs to be update by graphics/activty flow
    private static final int touchRadius = 0;

    private static final int decayRate = 0; //TODO needs to be updated by.........
    private static final int startTime = 0; //TODO decide with group

    private int lives = 3;
    private int xCord = 0;
    private int yCord = 0;
    private int userScore = 0;
    private int userStreak = 0;
    private int timeRemain = 0;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private int heightTopDat = 0; //TODO this needs to be updated based on gui
    private int heightpause = 0; //TODO this needs to be updated based on gui
    private int inBoundsWidth = 0;
    private int inBoundsHeight = 0;
    private int bufferZone = 0; //TODO needs to be updated by GUI people
    private int topBound = 0;
    private int botBound = 0;

    private boolean clicked = false;

    private Circle circ;

    private String imageName = "redButton";

    private TextView textViewScore, textViewTime, textViewLives;
    private ImageView imageView;

    private Button buttonPause, startButton;
    private ImageButton redButton, greenButton;

    private HashMap<String, Drawable> drawableMap = new HashMap<String, Drawable>();

    private Random ran = new Random();

    private Thread computerThread = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        greenButton = findViewById(R.id.greenCirc);
        greenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                greenButton.setVisibility(View.GONE);
                redButton.setVisibility(View.GONE);
                startButton.setVisibility(View.GONE);
                start();   
            }
        });

        redButton = findViewById(R.id.redCirc);
        redButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                greenButton.setVisibility(View.GONE);
                redButton.setVisibility(View.GONE);
                startButton.setVisibility(View.GONE);
                start();
            }
        });

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                greenButton.setVisibility(View.GONE);
                redButton.setVisibility(View.GONE);
                startButton.setVisibility(View.GONE);
                start();
            }
        });
        buttonPause = findViewById(R.id.pauseButton);
        buttonPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                start();
            }
        });

    }
    private void start(){
        setScreenSize();
        setInBounds();
        setCircle();

        if(circ.getColor() == 0){
            redButton.setVisibility(View.VISIBLE);
            redButton.setX(circ.getxCord());
            redButton.setY(circ.getyCord());
        }
        else{
            greenButton.setVisibility(View.VISIBLE);
            greenButton.setX(circ.getxCord());
            greenButton.setY(circ.getyCord());
        }
    }
    private void setScreenSize(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
    }

    private void setInBounds (){
        topBound = circleRadius + heightTopDat;
        botBound = bufferZone + touchRadius + heightpause;
        inBoundsHeight = screenHeight - topBound - botBound;
        inBoundsWidth = screenWidth - (circleRadius*2);
    }
    private void setCircle (){
        yCord = ran.nextInt(inBoundsHeight) + topBound;
        xCord = ran.nextInt(inBoundsWidth) + circleRadius;
        circ = new Circle(yCord,xCord);
    }
}
