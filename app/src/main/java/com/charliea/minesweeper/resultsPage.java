package com.charliea.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String time = extras.getString("time");
            boolean won = extras.getBoolean("won");
            TextView message = findViewById(R.id.message);
            if(won){
                String out = "Used "+time+ " seconds.\n        You Won.\n        Good job!";
                message.setText(out);
            }
            else{
                String out = "   Used " + time + " seconds.\n         You lost.\nBetter luck next time!";
                message.setText(out);
            }
        }
        Button play = findViewById(R.id.replay);
        play.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        returnToGame();
                    }
                });
    }

    public void returnToGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}