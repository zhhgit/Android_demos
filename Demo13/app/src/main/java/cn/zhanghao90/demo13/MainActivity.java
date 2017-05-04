package cn.zhanghao90.demo13;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1,button2;
    private EditText editText1;
    private OutputStream outputStream;
    private BufferedWriter bufferedWriter;
    private FileInputStream fileInputStream;
    private BufferedReader bufferedReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                save();
                break;
            case R.id.button2:
                String str = load();
                Toast.makeText(getApplicationContext(),"保存文件的内容为：" + str,Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void save(){
        editText1 = (EditText) findViewById(R.id.editText1);
        String str = editText1.getText().toString();
        try{
            outputStream = openFileOutput("demo13", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(str);
            Toast.makeText(getApplicationContext(),"保存文件成功",Toast.LENGTH_LONG).show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private String load(){
        StringBuffer content = new StringBuffer();
        try{
            fileInputStream = openFileInput("demo13");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return content.toString();
    }
}
