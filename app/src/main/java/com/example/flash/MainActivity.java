package com.example.flash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Thread thread;
    Intent intent;
    Button button,button1;
    int status=1;
    Handler handler=new Handler();
    SeekBar seekBar;
    boolean isStart;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button1=findViewById(R.id.button3);
        seekBar=findViewById(R.id.seekBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isStart=true;
                mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.hello);
                mediaPlayer.start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (status<100){
                            if (isStart)
                            status+=1;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    seekBar.setProgress(status);

                                }
                            });
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        mediaPlayer.stop();
                    }
                }).start();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart=false;
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();

                }else {
                    mediaPlayer.start();
                }
            }
        });

        thread=new Thread(){
                public void run(){
                    try {
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*finally {
                        intent=new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);

                    }*/

                }
        } ;
        thread.start();
    }
}
