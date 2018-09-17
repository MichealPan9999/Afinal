package com.example.panzq.myproject;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.io.File;

public class PlayMp4Activity extends FinalActivity {

    @ViewInject(id = R.id.btn_start,click = "start_play")
    Button btn_play;
    @ViewInject(id = R.id.btn_pause,click = "pause_play")
    Button btn_pause;
    @ViewInject(id = R.id.btn_continue,click = "continue_play")
    Button btn_continue;
    @ViewInject(id = R.id.btn_end,click = "stop_play")
    Button btn_stop;
    @ViewInject(id = R.id.videoView)
    VideoView videoView;
    private MediaController mediaController;
    public void start_play(View v)
    {
        Log.e("panzqww","currentpostion = "+videoView.getCurrentPosition());
        if (videoView.getCurrentPosition() == 0)
        {
            initMedia();
            videoView.start();
        }else{
            if (!videoView.isPlaying())
                videoView.start();
        }
    }
    public void pause_play(View v)
    {
        if (videoView.isPlaying())
            videoView.pause();
    }
    public void continue_play(View v)
    {
            videoView.start();
    }
    public void stop_play(View v)
    {
        videoView.stopPlayback();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_mp4);
        mediaController=new MediaController(this);
        //用来设置控制台样式
        videoView.setMediaController(mediaController);
        //用来设置起始播放位置，为0表示从开始播放
        videoView.seekTo(0);
        //播放完成后回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        initMedia();
    }
    private void initMedia()
    {
        String path = getIntent().getStringExtra("file_path");
        File file = new File(path);
        if (path != null && file.exists())
        {
            //用来设置要播放的mp4文件
            videoView.setVideoPath(path);
            //videoView.setVideoURI(Uri.parse(uri));//播放网络视频 uri传入网络地址
            if (mediaController == null)
            {
                Log.d("panzqww","mediaController is null");
                mediaController.setMediaPlayer(videoView);
            }
        }
    }
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( PlayMp4Activity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
