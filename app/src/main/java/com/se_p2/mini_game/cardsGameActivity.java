package com.se_p2.mini_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class cardsGameActivity extends AppCompatActivity {

    //Creating ImageView,TextView and Button instances
    ImageView iv_card_left, iv_card_right;
    TextView tv_score_left, tv_score_right,winnerText;
    Button deal,backButton,resetButton;

    //Creating Integer variables to store the score
    int leftScore=0,rightScore=0;

    //Creating an instance of random to random numbers for the card
    Random ran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




        //Adding the values in the textView and ImageView instances
        iv_card_left=(ImageView)findViewById(R.id.iv_card_left);
        iv_card_right=(ImageView)findViewById(R.id.iv_card_right);
        tv_score_left=(TextView)findViewById(R.id.tv_score_left);
        tv_score_right=(TextView)findViewById(R.id.tv_score_right);
        winnerText=(TextView)findViewById(R.id.winnerText);

        tv_score_left.setText(String.valueOf(players.player1+" "+leftScore));
        tv_score_right.setText(String.valueOf(players.player2+" "+rightScore));

        //Adding the value in Button instances
        deal=(Button)findViewById(R.id.dealButton);
        backButton=(Button)findViewById(R.id.backButton);
        resetButton=(Button)findViewById(R.id.resetButton);

        //Initialising the ran instance
        ran=new Random();

        //Setting an onClickListener event on button deal to play the game
        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Generating the two cards number
                int leftCard=ran.nextInt(13)+2;
                int rightCard=ran.nextInt(13)+2;

                //show card images
                int leftImage=getResources().getIdentifier("card"+leftCard,"drawable",getPackageName());
                iv_card_left.setImageResource(leftImage);

                int rightImage=getResources().getIdentifier("card"+rightCard,"drawable",getPackageName());
                iv_card_right.setImageResource(rightImage);

                //Compare cards and add points and display
                if(leftCard>rightCard){
                    leftScore++;
                    tv_score_left.setText(String.valueOf(players.player1+":"+leftScore));
                    Toast.makeText(cardsGameActivity.this,players.player1+" Wins",Toast.LENGTH_SHORT).show();
                }else if(leftCard<rightCard) {
                    rightScore++;
                    tv_score_right.setText(String.valueOf(players.player2+":"+rightScore));
                    Toast.makeText(cardsGameActivity.this,players.player2+" Wins",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(cardsGameActivity.this,"WAR",Toast.LENGTH_SHORT).show();
                }

                //Compare the scores and display who is winning
                if(leftScore>rightScore){
                    winnerText.setText(String.valueOf(players.player1+" is leading !"));
                }
                else if(leftScore<rightScore){
                    winnerText.setText(String.valueOf(players.player2+" is leading !"));
                }
                else {
                    winnerText.setText(String.valueOf("Both have equal luck !"));
                }
            }
        });

        //Setting an onClickListener event on button back to go back to home screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

        //Setting an onClickListener event on button RESET to reset the score

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftScore=0;
                rightScore=0;
                tv_score_left.setText(String.valueOf(players.player1+":"+leftScore));
                tv_score_right.setText(String.valueOf(players.player2+":"+rightScore));
                winnerText.setText(String.valueOf("Good Luck Again !"));
            }
        });
    }
    //Creating a function to go back to home screen
    public void openHomeActivity(){
        Intent home=new Intent(cardsGameActivity.this, home.class);
        startActivity(home);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("leftScore",leftScore);
        outState.putInt("rightScore",rightScore);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        leftScore=savedInstanceState.getInt("lrftScore");
        rightScore=savedInstanceState.getInt("rightScore");
    }

}
