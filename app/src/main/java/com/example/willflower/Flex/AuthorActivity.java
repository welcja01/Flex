package com.example.willflower.Flex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AuthorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author_activity);

        //play button
        Button play = this.findViewById(R.id.backBut);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(gameIntent, 0);
            }
        });
    }
}
