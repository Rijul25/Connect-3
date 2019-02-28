package com.example.dell.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameisactive=true;
    int activeplayer = 0 ;
    int[] gamestate ={2 ,2 ,2 ,2 ,2 ,2 ,2 ,2,2,2,2 };
    int[][] winningposition={{0,1,2} , {3,4,5} , {6,7,8} ,{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6} };
    public void dropin(View view) {

        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2) {
            gamestate[tappedcounter] = activeplayer;

            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.blueball);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.redball);
                activeplayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);
            for(int[] winningposition:  winningposition){
                if(gamestate[winningposition[0]]==gamestate[winningposition[1]] &&
                        gamestate[winningposition[1]]==gamestate[winningposition[2]] && gamestate[winningposition[0]]!=2)

                {
                    gameisactive=false;
                    String winner="Red";
                    if((gamestate[winningposition[0]]==0)){
                        winner="Blue";

                }
                    //who won
                    TextView winnermessage=findViewById(R.id.winnermessage);
                    winnermessage.setText( winner + "  has  won !");
                    LinearLayout linearLayout=findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);


                }
                else{
                    boolean gameisover=true;
                    for(int counterstate: gamestate){
                        if((counterstate)==2) gameisover=false;
                    }
                    if(gameisover){
                        TextView winnermessage=findViewById(R.id.winnermessage);
                        winnermessage.setText( "It is a draw");
                        LinearLayout linearLayout=findViewById(R.id.playAgainLayout);
                        linearLayout.setVisibility(View.VISIBLE);
                    }

                }
            }
        }
    }
    public void playAgain (View view){
        gameisactive=true;
        LinearLayout linearLayout=findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        activeplayer=0;
        for(int i=0; i<gamestate.length; i++){
            gamestate[i]=2; //yeh gamestate ko restate kar dega
        }
        android.support.v7.widget.GridLayout gridLayout= (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
