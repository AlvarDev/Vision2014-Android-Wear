package usmp.com.vision2014;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.Random;

public class Game extends Activity {

    private TextView mTextView;
    private ImageButton startBotton;

    public boolean playing = true;
    public static final int BUHO = 1;
    public static final int ANDROID = 2;

    int random = 0;
    int pressed = 0;
    int scoreGame = 0;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                startBotton = (ImageButton)findViewById(R.id.start);
                mTextView = (TextView)findViewById(R.id.score);
            }
        });
    }

    public void start(View view){
        if(playing){
            startGame();
        }else{
            startBotton.setImageResource(R.drawable.vision);
            Toast.makeText(getApplicationContext(), "Next time!", Toast.LENGTH_SHORT).show();
        }
    }

    public void startGame(){
            Random rand = new Random();
            int min = 1;
            int max = 2;

            random = rand.nextInt((max - min) + 1) + min;

            switch (random){
                case BUHO:  startBotton.setImageResource(R.drawable.buho);
                    break;
                case ANDROID: startBotton.setImageResource(R.drawable.android);
                    break;
                default: startBotton.setImageResource(R.drawable.vision);
                    break;
            }

            Runnable r = new Runnable() {
                @Override
                public void run() {

                if(pressed==0){
                    playing = false;
                }
                }
            };
            handler.postDelayed(r, 1500);
    }


    public void takeBuho(View view){
        if(playing){
            if(random==BUHO ){
                scoreGame++;
                mTextView.setText(""+scoreGame);
                startGame();
                startBotton.setImageResource(R.drawable.vision);
                pressed = BUHO;
            }else{
                playing =false;
                pressed = 0;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Sorry too late!", Toast.LENGTH_SHORT).show();
        }
    }
    public void takeAndroid(View view){
        if(playing){
            if(random==ANDROID ){
                scoreGame++;
                mTextView.setText(""+scoreGame);
                startGame();
                pressed = ANDROID;
                startBotton.setImageResource(R.drawable.vision);
            }else{
                playing =false;
                pressed = 0;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Sorry too late!", Toast.LENGTH_SHORT).show();
        }
    }

}
