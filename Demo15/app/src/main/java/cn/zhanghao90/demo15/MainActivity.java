package cn.zhanghao90.demo15;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2,button3,button4,button5;
    private SQLiteDatabase demo15DB;
    private Demo15DBHelper demo15DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDB();
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                createDB();
                break;
            case R.id.button2:
                add();
                break;
            case R.id.button3:
                query();
                break;
            case R.id.button4:
                update();
                break;
            case R.id.button5:
                delete();
                break;
        }
    }

    //创建数据库和表
    private void createDB(){
        demo15DBHelper = new Demo15DBHelper(getApplicationContext(),"demo15",null,1);
        demo15DB = demo15DBHelper.getWritableDatabase();
    }

    //添加数据
    private void add(){
        ContentValues values = new ContentValues();
        values.put("name","ZhangHao");
        values.put("age",27);
        demo15DB.insert("tbl_student",null,values);
        Toast.makeText(getApplicationContext(),"添加一条数据成功",Toast.LENGTH_LONG).show();
        query();
    }

    //查询数据
    private void query(){
        Cursor cursor = demo15DB.query("tbl_student",null,null,null,null,null,null);
        StringBuffer sb = new StringBuffer();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                sb.append(id).append("__").append(name).append("__").append(age).append(";");
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG).show();
    }

    //更新数据
    private void update(){
        ContentValues values = new ContentValues();
        values.put("name","HaoZhang");
        demo15DB.update("tbl_student",values,"age=?",new String[] {"27"});
        Toast.makeText(getApplicationContext(),"更新数据成功",Toast.LENGTH_LONG).show();
        query();
    }

    //删除数据
    private void delete(){
        demo15DB.delete("tbl_student","age=?",new String[] {"27"});
        Toast.makeText(getApplicationContext(),"删除数据成功",Toast.LENGTH_LONG).show();
        query();
    }
}
