package cn.zhanghao90.demo14;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2;
    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        editText1 = (EditText) findViewById(R.id.editText1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                save();
                break;
            case R.id.button2:
                load();
                break;
        }
    }

    private void save(){
        String str = editText1.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences("demo14", Context.MODE_PRIVATE).edit();
        editor.putString("key1",str);
        editor.apply();
        Toast.makeText(getApplicationContext(),"保存成功：" + str,Toast.LENGTH_LONG).show();
    }

    private void load(){
        SharedPreferences preferences = getSharedPreferences("demo14", Context.MODE_PRIVATE);
        String value = preferences.getString("key1","");
        Toast.makeText(getApplicationContext(),"读取成功：" + value,Toast.LENGTH_LONG).show();
    }
}
