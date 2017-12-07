package com.example.willflower.Flex;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    private static int circleDiam; // TODO this needs to be update by graphics/activty flow
    private static final double decayRate = .9; //TODO needs to be updated by.........


    private int startTime = 5000; //TODO decide with group
    private int lives = 3;
    private int redXCord = 0;
    private int redYCord = 0;
    private int greenXCord = 0;
    private int greenYCord = 0;
    private int userScore = 0;
    private int userStreak = 0;
    private int timeUsed = 0; // TODO use this variable if we want to average score and time used for overall
    private int screenWidth = 0;
    private int screenHeight = 0;
    private int heightTopDat = 500; //TODO this needs to be updated based on gui
    private int heightpause = 100; //TODO this needs to be updated based on gui
    private int inBoundsWidth = 0;
    private int inBoundsHeight = 0;
    private int bufferZone = 50; //TODO needs to be updated by GUI people
    private int topBound = 0;
    private int botBound = 0;

    private boolean redClick = false;
    private boolean greenClick = false;

    private ImageView im;

    private Circle redCirc;
    private Circle greenCirc;

    private TextView textViewScore, textViewLives, textViewStreak;

    private Button buttonPause, startButton;

    private ImageButton redButton, greenButton, gameOver;

    private Random ran = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        greenButton = findViewById(R.id.greenCirc);
        circleDiam = 60;
        greenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                greenClick = true;
                greenButton.setVisibility(View.GONE);
                redButton.setVisibility(View.GONE);
                score();
                Start();
            }
        });

        redButton = findViewById(R.id.redCirc);
        redButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                redClick = true;
                greenButton.setVisibility(View.GONE);
                redButton.setVisibility(View.GONE);
                score();
                Start();
            }
        });

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                greenButton.setVisibility(View.GONE);
                redButton.setVisibility(View.GONE);
                startButton.setVisibility(View.GONE);
                Start();
            }
        });
        buttonPause = findViewById(R.id.pauseButton);
        buttonPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Start();
            }
        });

        System.out.println("Setting screen size");
        setScreenSize();
        System.out.println("Setting in bounds");
        textViewScore = findViewById(R.id.score);
        textViewLives = findViewById(R.id.lives);
        textViewStreak = findViewById(R.id.streak);
        float topDatXcord = textViewScore.getX();
        float topDatYcord = textViewScore.getY();
        System.out.println("top dat x cord = "+topDatXcord);
        System.out.println("top dat y cord = "+topDatYcord);
        System.out.println("top dat height = "+heightTopDat);
        heightpause = buttonPause.getMeasuredHeight();
        System.out.println("pause x cord = "+buttonPause.getX());
        System.out.println("pause y cord = "+buttonPause.getY());
        System.out.println("circle diameter = "+circleDiam);
        System.out.println("in bounds height = "+inBoundsHeight);
        System.out.println("in bounds width = " +inBoundsWidth );
        setInBounds();
    }
    private void Start() {
        System.out.println("Start called");
        setCircles();

        redButton.setVisibility(View.VISIBLE);
        redButton.setX(redCirc.getxCord());
        redButton.setY(redCirc.getyCord());

        greenButton.setVisibility(View.VISIBLE);
        greenButton.setX(greenCirc.getxCord());
        greenButton.setY(greenCirc.getyCord());

        /*new CountDownTimer(startTime, 100) {
            @Override
            public void onTick(long millsUntilFinished) {
                System.out.println("mills remaining: " + millsUntilFinished);
                if(redClick || greenClick){
                    cancel();
                    System.out.println("Timer cancled");
                    System.out.println("Time left on timer" + millsUntilFinished);
                    System.out.println("Calling score");
                    score();
                }
            }

            @Override
            public void onFinish() {
                System.out.println("Timer finished.");
                lives--;
                userStreak = 0;
                score();
            }
        }.start();
        */
    }
    private void setScreenSize(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
    }

    private void setInBounds (){
        topBound = (circleDiam);
        botBound = 200 + circleDiam*3;
        inBoundsHeight = screenHeight - botBound - topBound-20;
        inBoundsWidth = screenWidth - (circleDiam*3);
    }

    private void setCircles (){
        //TODO it needs to be made so that the buttons don't appear on top of one another
        redYCord = ran.nextInt(inBoundsHeight) + circleDiam+20;
        System.out.println("in bounds height = " + inBoundsHeight);
        redXCord = ran.nextInt(inBoundsWidth);
        System.out.println("in bounds width = " + inBoundsWidth);
        redCirc = new Circle(redXCord,redYCord);
        System.out.println("Red X Cord = "+redXCord);
        System.out.println("Red Y Cord = "+redYCord);
        greenXCord = ran.nextInt(inBoundsWidth) + circleDiam;
        if (greenXCord < (redXCord-circleDiam) && greenXCord > (redXCord+circleDiam)){
            greenXCord = ran.nextInt(inBoundsWidth) + circleDiam;
        }
        greenYCord = ran.nextInt(inBoundsHeight) + circleDiam+20;
        if (greenYCord < (redYCord-circleDiam) && greenYCord > (redYCord+circleDiam)){
            greenYCord = ran.nextInt(inBoundsHeight) - circleDiam;
        }
        greenCirc = new Circle(greenXCord, greenYCord);
        System.out.println("Green X Cord = "+greenXCord);
        System.out.println("Green Y Cord = "+greenYCord);
        System.out.println("screen height = "+screenHeight);
        System.out.println("screen width = "+screenWidth);

    }

    private void score(){
        if (redClick){
            System.out.println("redClick == true");
            lives--;
            userStreak = 0;
            redClick  = false;
        }
        else{
            System.out.println("greenClick == true");
            userStreak++;
            userScore++;
            greenClick = false;
        }
        updateTextFeilds();
        System.out.println("updated textfeilds");
        //updateTime();
        //System.out.println("updated updatedTime");
        isGameOver();
        System.out.println("checked if game is over. It is not.");
        Start();
    }

    private void isGameOver(){
        if(lives == 0){
            updateTextFeilds();
            System.out.println("updated text feilds");
            System.out.println("game is over");
            userStreak = 0;
            userScore = 0;
            lives = 3;
            updateTextFeilds();
        }
    }

    private void updateTime(){

        System.out.println("updating time");
        System.out.println("startTime: "+ +startTime+"");
        startTime = startTime * (int)decayRate;
        System.out.println("Updated startTime");
    }

    private void pause(){
        //TODO PAUSE ACTIVITY!
    }

    private void updateTextFeilds(){
        //update score
        textViewScore.setText("Score: "+""+userScore+"");
        System.out.println("updated textViewScore");

        //update lives


        textViewLives.setText("Lives: "+""+lives+"");
        System.out.println("updated lives");

        //update streak
        textViewStreak = findViewById(R.id.streak);
        System.out.println("found textViewStreak");
        textViewStreak.setText("Streak: "+""+userStreak+"");
        System.out.println("set textViewStreak");
    }
}
