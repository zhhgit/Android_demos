package cn.zhanghao90.demo28;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2,button3,button4;
    private MyService.Downloader downloader;

    //与MyService服务的持久连接
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //调用服务中的功能
            downloader = (MyService.Downloader) service;
            downloader.funcA();
            downloader.funcB();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                start();
                break;
            case R.id.button2:
                stop();
                break;
            case R.id.button3:
                bind();
                break;
            case R.id.button4:
                unbind();
            default:
                break;
        }
    }

    //启动服务
    private void start(){
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    //停止服务
    private void stop(){
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }

    //绑定服务
    private void bind(){
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    //解绑服务
    private void unbind(){
        unbindService(serviceConnection);
    }
}