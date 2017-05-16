package cn.zhanghao90.demo24;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
                BufferedReader reader = null;
                HttpURLConnection httpURLConnection = null;
                try{
                    //注意https
                    URL url = new URL("https://www.baidu.com");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        stringBuilder.append(line);
                    }
                    showText(stringBuilder.toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    if(reader != null){
                        try{
                            reader.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(httpURLConnection != null){
                        httpURLConnection.disconnect();
                    }
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
