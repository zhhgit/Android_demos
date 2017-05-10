package cn.zhanghao90.demo22;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Button buttonPlay,buttonPause,buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPause = (Button) findViewById(R.id.buttonPause);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonPlay.setOnClickListener(this);
        buttonPause.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        request();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPlay:
                play();
                break;
            case R.id.buttonPause:
                pause();
                break;
            case R.id.buttonReset:
                reset();
                break;
            default:
                break;
        }
    }

    //申请权限
    private void request(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else{
            initPlayer();
        }
    }

    //已经做出权限选择
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initPlayer();
                }
                else{
                    Toast.makeText(getApplicationContext(),"没有权限",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    //初始化MediaPlayer
    private void initPlayer(){
        try{
            File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //播放
    private void play(){
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    //暂停
    private void pause(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    //重置
    private void reset(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.reset();
            initPlayer();
        }
    }

    //释放资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
