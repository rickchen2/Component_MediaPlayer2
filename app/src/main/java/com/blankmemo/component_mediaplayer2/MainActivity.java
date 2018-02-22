package com.blankmemo.component_mediaplayer2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private MediaPlayer mPlayer;
    private Button mPlayButton;
    private Button mPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a new Audio Player Object
        mPlayer = MediaPlayer.create(this, R.raw.sample);


        mPlayButton = (Button)findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "play", Toast.LENGTH_SHORT).show();
                mPlayer.start();

            }
        });

        mPauseButton = (Button)findViewById(R.id.pause_button);
        mPauseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "pause", Toast.LENGTH_SHORT).show();
                mPlayer.pause();
            }
        });

        //pop up to show a toast message after playback
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this, "I'm done!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
