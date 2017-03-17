package com.example.sajithaliyanage.geuss_and_win;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

import java.util.Random;

public class MainActivity extends BaseGameActivity implements View.OnClickListener {
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonAgain;
    private int number;
    private Random rand;
    private TextView info;
    private int numGuesses=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.show_achievements).setOnClickListener(this);
        findViewById(R.id.show_leaderboard).setOnClickListener(this);

        button0=(Button)findViewById(R.id.btn0);
        button1=(Button)findViewById(R.id.btn1);
        button2=(Button)findViewById(R.id.btn2);
        button3=(Button)findViewById(R.id.btn3);
        button4=(Button)findViewById(R.id.btn4);
        button5=(Button)findViewById(R.id.btn5);
        button6=(Button)findViewById(R.id.btn6);
        button7=(Button)findViewById(R.id.btn7);
        button8=(Button)findViewById(R.id.btn8);
        button9=(Button)findViewById(R.id.btn9);
        buttonAgain=(Button)findViewById(R.id.btnAgain);

        info=(TextView)findViewById(R.id.guess_text);

        rand=new Random();
        number=rand.nextInt(10);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button) {
            beginUserInitiatedSignIn();

        } else if (view.getId() == R.id.sign_out_button) {
            signOut();
//            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);

        }
        if(view.getId() == R.id.show_leaderboard){
            Log.v("Click","Leaderboard");
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(getApiClient(), getString(R.string.number_guesses_leaderboard)), 2);
        }
    }

    public void onSignInSucceeded() {
//        findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
    }

    @Override
    public void onSignInFailed() {
//        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.GONE);
    }

    private void disableNumbers(){
        button0.setEnabled(false); button0.setTextColor(Color.parseColor("#ff000033"));
        button1.setEnabled(false); button1.setTextColor(Color.parseColor("#ff000033"));
        button2.setEnabled(false); button2.setTextColor(Color.parseColor("#ff000033"));
        button3.setEnabled(false); button3.setTextColor(Color.parseColor("#ff000033"));
        button4.setEnabled(false); button4.setTextColor(Color.parseColor("#ff000033"));
        button5.setEnabled(false); button5.setTextColor(Color.parseColor("#ff000033"));
        button6.setEnabled(false); button6.setTextColor(Color.parseColor("#ff000033"));
        button7.setEnabled(false); button7.setTextColor(Color.parseColor("#ff000033"));
        button8.setEnabled(false); button8.setTextColor(Color.parseColor("#ff000033"));
        button9.setEnabled(false); button9.setTextColor(Color.parseColor("#ff000033"));
        buttonAgain.setEnabled(true); buttonAgain.setTextColor(Color.parseColor("#ff000033"));
    }
    private void enableNumbers(){
        button0.setEnabled(true); button0.setTextColor(Color.WHITE);
        button1.setEnabled(true); button1.setTextColor(Color.WHITE);
        button2.setEnabled(true); button2.setTextColor(Color.WHITE);
        button3.setEnabled(true); button3.setTextColor(Color.WHITE);
        button4.setEnabled(true); button4.setTextColor(Color.WHITE);
        button5.setEnabled(true); button5.setTextColor(Color.WHITE);
        button6.setEnabled(true); button6.setTextColor(Color.WHITE);
        button7.setEnabled(true); button7.setTextColor(Color.WHITE);
        button8.setEnabled(true); button8.setTextColor(Color.WHITE);
        button9.setEnabled(true); button9.setTextColor(Color.WHITE);
        buttonAgain.setEnabled(false); buttonAgain.setTextColor(Color.parseColor("#ffffff00"));
    }

    public void btnPressed(View v){
        int btn = Integer.parseInt(v.getTag().toString());
        if(btn<0){
            //again btn
            numGuesses=0;
            number=rand.nextInt(10);
            enableNumbers();
            info.setText("Set the number!");
        }
        else{
            //number button
            numGuesses++;
            if(btn==number){
                info.setText("Yes! It was "+number);
                if(getApiClient().isConnected()){
                    //Games.Achievements.unlock(getApiClient(), getString(R.string.correct_guess_achievement));
                    Games.Leaderboards.submitScore(getApiClient(), getString(R.string.number_guesses_leaderboard), numGuesses);
                }
                disableNumbers();
            }
            else if(numGuesses==5){
                info.setText("No! It was "+number);
                disableNumbers();
            }
            else
                info.setText("Try again!");
        }
    }


}
