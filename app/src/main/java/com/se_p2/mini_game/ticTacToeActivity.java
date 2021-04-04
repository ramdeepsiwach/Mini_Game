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

public class ticTacToeActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating instance of buttons into an array
    public Button[][] buttons=new Button[3][3];

    //initialising an instance for reset button and back button
    public Button resetButton,backButton;

    //Creating a boolean variable to check if its turn for player 1 or player 2
    public boolean player1Turn=true;

    //creating variables to count score and rounds
    private int roundCount;
    private int player1Score,player2Score;

    //Creating instances of TextView
    private TextView textViewPlayer1,textViewPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Initialising textviews
        textViewPlayer1=(TextView)findViewById(R.id.player1Score);
        textViewPlayer2=(TextView)findViewById(R.id.player2Score);

        textViewPlayer1.setText(players.player1+":"+player1Score);
        textViewPlayer2.setText(players.player2+":"+player2Score);

        //initialising button with the help of arrays
        for (int i=0; i<3;i++){
            for(int j=0;j<3;j++){

                String buttonId="button_"+i+j;

                int resId=getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]=findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }

        //Initialising resetButton and setting a onclicklistener
       resetButton=findViewById(R.id.resetTicTacToe);
        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Score=0;
                player2Score=0;
                textViewPlayer1.setText(players.player1+":"+player1Score);
                textViewPlayer2.setText(players.player2+":"+player2Score);
                resetBoard();

            }
        });

        //Initialising backButton and setting a onclicklistener
        backButton=findViewById(R.id.backFromTicTacToeToHome);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });
    }

    public void openHomeActivity(){
        Intent home=new Intent(ticTacToeActivity.this,home.class);
        startActivity(home);
    }
    //OnclickListener for buttons in array
    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")){
            return;
        }
        //If its player1 turn then set button to 'X' else set button to 'o'
        if (player1Turn){
            ((Button)view).setText("X");

        }else {
            ((Button)view).setText("O");
        }
        roundCount++;

        //Check if anybody won or not
        if (checkForWin()){
            if(player1Turn){
                player1Wins();
            }else {
                player2Wins();
            }
        }else  if (roundCount==9){
            draw();
        }else {
            player1Turn=!player1Turn;
        }
    }

    //Check for winner function with all possible wins
    private boolean checkForWin(){
        String[][] field=new String[3][3];

        //getting the values of buttons
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();

            }
        }
        //Checks for rows for the win
        for (int i=0;i<3;i++){
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
              return true;
            }
        }

        //check columns for the win
        for (int i=0;i<3;i++){
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        //checks diagnols for the win
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }

        //else nobody won
        return false;
    }

    //Reset the board function
    private void resetBoard() {
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn=true;
    }

    //If player1 wins
    private void player1Wins(){
        player1Score++;
        Toast.makeText(this,players.player1+" Wins!",Toast.LENGTH_SHORT).show();
        textViewPlayer1.setText(players.player1+":"+player1Score);
        resetBoard();
    }

    //If player2 wins
    private void player2Wins(){
        player2Score++;
        Toast.makeText(this,players.player2+" Wins!",Toast.LENGTH_SHORT).show();
        textViewPlayer2.setText(players.player2+":"+player2Score);
        resetBoard();
    }

    //if the round is draw
    private void draw(){
        Toast.makeText(this,"DRAW!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    /* Creating,saving and restoring instance into bundles
    so score does not get changed when oriented */

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundCount);
        outState.putInt("player1Score",player1Score);
        outState.putInt("player2Score",player2Score);
        outState.putBoolean("player1Turn",player1Turn);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        roundCount=savedInstanceState.getInt("roundCount");
        player1Score=savedInstanceState.getInt("player1Score");
        player2Score=savedInstanceState.getInt("player2Score");
        player1Turn=savedInstanceState.getBoolean("player1Turn");
    }
}
