  package com.example.wunna.game3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

  public class MainActivity extends AppCompatActivity {
      //yellow 0 ,red 1
      int activePlayer = 0;
      boolean gameIsActive=true;
      //unplay playerState
      int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
      //winstate
      int[][] winingStates={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

      public void dropIn(View view) {
          ImageView counter = (ImageView) view;
          System.out.print(counter.getTag().toString());
          int tappedCounter = Integer.parseInt(counter.getTag().toString());
          if (gameState[tappedCounter] == 2&& gameIsActive){
              gameState[tappedCounter]=activePlayer;
              counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
              counter.setImageResource(R.drawable.yellow);
              activePlayer = 1;
            } else {
              counter.setImageResource(R.drawable.red);
              activePlayer = 0;
            }
              counter.animate().translationYBy(1000f).setDuration(300);
              for (int[] winingState:winingStates){
                    if (gameState[winingState[0]]==gameState[winingState[1]]&&
                            gameState[winingState[1]]==gameState[winingState[2]]
                          &&gameState[winingState[1]]!=2){
                        gameIsActive=false;
                        Log.d("state", String.valueOf(gameState[winingState[0]]));
                        String winner="red";
                        if (gameState[winingState[0]]==0){
                            winner="yellow";
                        }
                        TextView winnerMessage=(TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText(winner+"has won!");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                    else{
                        boolean gameOver=true;
                        for (int counterState:gameState){
                            if (counterState==2) gameOver=false;
                        }
                        if (gameOver){
                            TextView winnerMessage=(TextView) findViewById(R.id.winnerMessage);
                            winnerMessage.setText("No one win");
                            LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                            layout.setVisibility(View.VISIBLE);
                        }
                    }

              }
          }

  }

    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout layout=(LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        //unplay playerState
         for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridlayout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
