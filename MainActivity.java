package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0: circle, 1: cross, 2: empty;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedCounter] == 2 && gameActive) {
            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.zero);

                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.cross);

                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(7200).setDuration(300);


            for (int[] winningposition : winningPositions) {

                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2)
                {
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView blueWinnerTextView = (TextView) findViewById(R.id.blueWinnerTextView);
                    TextView redWinnerTextView = (TextView) findViewById(R.id.redWinnerTextView);

                    playAgainButton.setVisibility(View.VISIBLE);

                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "BLUE";
                        blueWinnerTextView.setText(winner + " has won !");
                        blueWinnerTextView.setVisibility(View.VISIBLE);
                        }
                    else {
                        winner = "RED";
                        redWinnerTextView.setText(winner + " has won !");
                        redWinnerTextView.setVisibility(View.VISIBLE);
                        }
                }
            }
        }
    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView blueWinnerTextView = (TextView) findViewById(R.id.blueWinnerTextView);
        TextView redWinnerTextView = (TextView) findViewById(R.id.redWinnerTextView);
        blueWinnerTextView.setVisibility(View.INVISIBLE);
        redWinnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gamestate.length; i++) {

            gamestate[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
