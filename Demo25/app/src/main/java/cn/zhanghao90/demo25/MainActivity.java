package cn.zhanghao90.demo25;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        textView1 = (TextView) findViewById(R.id.textView1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                sendRequest();
                break;
        }
    }

    private void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("https://www.baidu.com").build();
                    Response response = okHttpClient.newCall(request).execute();
                    String resStr = response.body().string();
                    showText(resStr);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showText(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView1.setText(text);
            }
        });
    }
}
