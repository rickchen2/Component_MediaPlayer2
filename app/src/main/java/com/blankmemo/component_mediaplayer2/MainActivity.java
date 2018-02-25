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

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mPlayer = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //release resource before MediaPlay is initialized to play another song
        releaseMediaPlayer();



        mPlayButton = (Button)findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //create a new Audio Player Object
                mPlayer = MediaPlayer.create(MainActivity.this, R.raw.sample);
                //pop up to show a toast message after playback
                Toast.makeText(MainActivity.this, "play", Toast.LENGTH_SHORT).show();
                mPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(MainActivity.this, "I'm done!", Toast.LENGTH_SHORT).show();
                        //release resource after song finished playing
                        releaseMediaPlayer();
                    }
                });

            }
        });

        mPauseButton = (Button)findViewById(R.id.pause_button);
        mPauseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "pause", Toast.LENGTH_SHORT).show();
                mPlayer.pause();
            }
        });



    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

}
