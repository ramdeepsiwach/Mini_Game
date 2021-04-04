package com.se_p2.mini_game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private Button playButton;
    EditText player1Name,player2Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        player1Name= (EditText)findViewById(R.id.player1Name);
        player2Name=(EditText)findViewById(R.id.player2Name);

        playButton=(Button)findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
                players.player1=player1Name.getText().toString();
                players.player2=player2Name.getText().toString();
            }
        });

    }

    public void openHomeActivity(){
        Intent intent=new Intent(this, home.class);
        startActivity(intent);
    }
}