package com.example.willflower.Flex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //play button
        Button play = this.findViewById(R.id.playbutton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(view.getContext(), PlayActivity.class);
                startActivityForResult(gameIntent, 0);
            }
        });

        //info button
        Button info = this.findViewById(R.id.authorButton);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(view.getContext(), AuthorActivity.class);
                startActivityForResult(infoIntent, 0);
            }
        });

    }
}
