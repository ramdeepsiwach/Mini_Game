package com.se_p2.mini_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class home extends AppCompatActivity {

    private Button dealNodealButton, cardsButton, ticTacToeButton,backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dealNodealButton=(Button)findViewById(R.id.dealNoDealButton);
        cardsButton=(Button)findViewById(R.id.cardsButton);
        ticTacToeButton=(Button)findViewById(R.id.ticTacToeButton);
        backButton=(Button)findViewById(R.id.backFromHomeToWelcomeButton);


        dealNodealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDealNoDealActivity();
            }
        });

        cardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCardsGameActivity();
            }
        });

        ticTacToeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTicTacToeActivity();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWelcomeActivity();
            }
        });
    }

    public void openDealNoDealActivity(){
        Intent openDealNoDeal=new Intent(this, dealNoDealActivity.class);
        startActivity(openDealNoDeal);
    }

    public void openCardsGameActivity(){
        Intent openCardsGameActiity=new Intent(this, cardsGameActivity.class);
        startActivity(openCardsGameActiity);
    }

    public void openTicTacToeActivity(){
        Intent openTicTacToeActivity=new Intent(this, ticTacToeActivity.class);
        startActivity(openTicTacToeActivity);
    }

    public void openWelcomeActivity(){
        Intent openHomeActivity=new Intent(this,WelcomeActivity.class);
        startActivity(openHomeActivity);
    }


}