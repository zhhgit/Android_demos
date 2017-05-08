package cn.zhanghao90.demo16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1,button2,button3,button4;
    private StringBuffer sb;

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
        connect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                insert();
                break;
            case R.id.button2:
                query();
                break;
            case R.id.button3:
                update();
                break;
            case R.id.button4:
                delete();
                break;
            default:
                break;
        }
    }

    //连接数据库
    private void connect(){
        Connector.getDatabase();
        Toast.makeText(getApplicationContext(),"连接数据库成功",Toast.LENGTH_LONG).show();
    }

    //插入数据
    private void insert(){
        Student item = new Student();
        item.setName("ZhangHao");
        item.setAge(27);
        item.save();
        Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_LONG).show();
        query();
    }

    //查询数据
    private void query(){
        sb = new StringBuffer();
        List<Student> students = DataSupport.findAll(Student.class);
        for(Student student:students){
            int id = student.getId();
            String name = student.getName();
            int age = student.getAge();
            sb.append(id).append("__").append(name).append("__").append(age).append(";");
        }
        Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG).show();
    }

    //更新数据
    private void update(){
        Student item = new Student();
        item.setName("HaoZhang");
        item.updateAll("age=?","27");
        Toast.makeText(getApplicationContext(),"更新成功",Toast.LENGTH_LONG).show();
        query();
    }

    //删除数据
    private void delete(){
        DataSupport.deleteAll(Student.class,"name=?","ZhangHao");
        Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_LONG).show();
        query();
    }
}
