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
    private static final int circleRadius = 14; // TODO this needs to be update by graphics/activty flow
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
    private int heightTopDat = 0; //TODO this needs to be updated based on gui
    private int heightpause = 0; //TODO this needs to be updated based on gui
    private int inBoundsWidth = 0;
    private int inBoundsHeight = 0;
    private int bufferZone = 0; //TODO needs to be updated by GUI people
    private int topBound = 0;
    private int botBound = 0;

    private boolean redClick = false;
    private boolean greenClick = false;

    private ImageView im;

    private Circle redCirc;
    private Circle greenCirc;

    private TextView textViewScore, textViewLives, textViewStreak;

    private Button buttonPause, startButton;

    private ImageButton redButton, greenButton;

    private Random ran = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        im = findViewById(R.id.tmpTest);
        im.setVisibility(View.GONE);

        greenButton = findViewById(R.id.greenCirc);
        greenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                greenClick = true;
                greenButton.setVisibility(View.GONE);
                redButton.setVisibility(View.GONE);
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

    }
    private void Start(){
        System.out.println("Setting screen size");
        setScreenSize();
        setInBounds();
        setCircles();

        redButton.setVisibility(View.VISIBLE);
        redButton.setX(redCirc.getxCord());
        redButton.setY(redCirc.getyCord());

        greenButton.setVisibility(View.VISIBLE);
        greenButton.setX(greenCirc.getxCord());
        greenButton.setY(greenCirc.getyCord());

        new CountDownTimer(startTime, 100) {
            @Override
            public void onTick(long millsUntilFinished) {
                if(redClick || greenClick){
                    System.out.println("mills remaining: " + millsUntilFinished);
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
    }

    private void setScreenSize(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
    }

    private void setInBounds (){
        topBound = circleRadius + heightTopDat;
        botBound = bufferZone + heightpause;
        inBoundsHeight = screenHeight - topBound - botBound;
        inBoundsWidth = screenWidth - (circleRadius*2);
    }

    private void setCircles (){
        //TODO it needs to be made so that the buttons don't appear on top of one another
        redYCord = ran.nextInt(inBoundsHeight) + topBound;
        redXCord = ran.nextInt(inBoundsWidth) + circleRadius;
        redCirc = new Circle(redXCord,redYCord);
        greenXCord = ran.nextInt(inBoundsWidth) + circleRadius;
        greenYCord = ran.nextInt(inBoundsHeight) + topBound;
        greenCirc = new Circle(greenXCord, greenYCord);
    }

    private void score(){
        if (redClick){
            System.out.println("");
            lives--;
            userStreak = 0;
            redClick = false;
        }
        else{
            userStreak++;
            userScore++;
            greenClick = false;
        }
        updateTextFeilds();
        updateTime();
        isGameOver();
        Start();
    }

    private void isGameOver(){
        if(lives == 0){
            updateTextFeilds();
            greenButton.setVisibility(View.GONE);
            redButton.setVisibility(View.GONE);
            im.setVisibility(View.VISIBLE);
        }
    }

    private void updateTime(){

        System.out.println("updating time");
        System.out.println("startTime: "+startTime);
        startTime = startTime * (int)decayRate;
        System.out.println("Updated startTime");
    }

    private void pause(){
        //TODO PAUSE ACTIVITY!
    }

    private void updateTextFeilds(){
        //update score
        textViewScore = findViewById(R.id.score);
        System.out.println("found textViewScore");
        textViewScore.setText("Score: "+""+userScore+"");
        System.out.println("set textViewScore");

        //update lives
        textViewLives = findViewById(R.id.lives);
        System.out.println("found lives");
        textViewLives.setText("Lives: "+""+lives+"");
        System.out.println("set lives");

        //update streak
        textViewStreak = findViewById(R.id.streak);
        System.out.println("found textViewStreak");
        textViewStreak.setText("Streak: "+""+userStreak+"");
        System.out.println("set textViewStreak");
    }
}
