package com.se_p2.mini_game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import android.os.Bundle;
import android.view.WindowManager;

public class dealNoDealActivity extends AppCompatActivity {

    //initialising an instance for reset button and back button
    public Button ResetButton, backToHomeButton,suitcase1Button,suitcase2Button,suitcase3Button;
    private int playerScore, bankerScore, suitcase1Value, suitcase2Value, suitcase3Value;
    //Creating instances of TextView
    private TextView textViewPlayer1, textViewPlayer2,resultTextView;
    String pName;
    ArrayList<Integer> values = new ArrayList<Integer>();
    public void pickedCase() {
        //Add the value to array list
        values.add(20);
        values.add(50);
        values.add(100);
        Collections.shuffle(values);

        //get the value
        suitcase1Value = values.get(0);
        suitcase2Value = values.get(1);
        suitcase3Value = values.get(2);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_no_deal);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Initialising textviews
        textViewPlayer1=(TextView)findViewById(R.id.player1Score);
        textViewPlayer2=(TextView)findViewById(R.id.BankerScore);
        resultTextView=(TextView)findViewById(R.id.resultText);

        textViewPlayer1.setText(players.player1+":" + playerScore);
        //Initialising resetButton and setting a onclicklistener
        ResetButton=findViewById(R.id.resetDealButton);
        ResetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playerScore=0;
                bankerScore=0;

                textViewPlayer1.setText(players.player1+":" + playerScore);
                textViewPlayer2.setText("Banker:"+bankerScore);
            }
        });

        //Initialising backButton and setting a onclicklistener
        backToHomeButton=findViewById(R.id.backFromDealToHome);
        backToHomeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

        //Deciding winner
        suitcase1Button=findViewById(R.id.suitcase1);
        suitcase1Button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                pickedCase();
                if(suitcase1Value>(suitcase2Value+suitcase3Value)){
                    playerWinner();
                }else {
                    bankerWinner();
                }
                changeScore();
            }
        });

        suitcase2Button=findViewById(R.id.suitcase2);
        suitcase2Button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pickedCase();
                if(suitcase2Value>(suitcase1Value+suitcase3Value)){
                    playerWinner();
                }else {
                    bankerWinner();
                }
                changeScore();
            }
        });

        suitcase3Button=findViewById(R.id.suitcase3);
        suitcase3Button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pickedCase();
                if(suitcase3Value>(suitcase1Value+suitcase2Value)){
                    playerWinner();
                }else {
                    bankerWinner();
                }
                changeScore();
            }
        });

    }
    public void changeScore(){
        textViewPlayer1.setText(players.player1+":"+playerScore);
        textViewPlayer2.setText("Banker:"+bankerScore);
    }
    public void openHomeActivity(){
        Intent home=new Intent(dealNoDealActivity.this,home.class);
        startActivity(home);
    }
    public void playerWinner(){
        resultTextView.setText("Suitcase 1 has value: "+suitcase1Value+"\nSuitcase 2 has value: "+suitcase2Value+"\nSuitcase 3 has value: "+suitcase3Value+"\nPlayer "+players.player1+" is the winner of the round!");
        playerScore++;
        Toast.makeText(this,players.player1+" Wins!",Toast.LENGTH_SHORT).show();

    }
    public void bankerWinner(){
        resultTextView.setText("Suitcase 1 has value: "+suitcase1Value+"\nSuitcase 2 has value: "+suitcase2Value+"\nSuitcase 3 has value: "+suitcase3Value+"\nBanker is the winner of the round!");
        bankerScore++;
        Toast.makeText(this,"Banker Wins!",Toast.LENGTH_SHORT).show();

    }
}

