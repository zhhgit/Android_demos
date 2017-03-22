package cn.zhanghao90.demo8;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2,button3,button4;
    private EditText editText1;
    private ProgressBar progressBar;
    private AlertDialog.Builder dialogBuilder;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.editText1);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                String inputText = editText1.getText().toString();
                Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                if(progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case R.id.button3:
                dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("标题");
                dialogBuilder.setMessage("提示内容");
                dialogBuilder.setCancelable(false);
                dialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"点击了确定",Toast.LENGTH_SHORT).show();
                    }
                });
                dialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"点击了取消",Toast.LENGTH_SHORT).show();
                    }
                });
                dialogBuilder.show();
                break;
            case R.id.button4:
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("标题");
                progressDialog.setMessage("提示内容");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
        }
    }
}
