package cn.zhanghao90.demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //在activity_main.xml文件中设置android:onClick="sendMessage"
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Activity2.class);
        String message = "上一个Activity传递的信息";
        intent.putExtra("key", message);
        startActivity(intent);
    }
}
