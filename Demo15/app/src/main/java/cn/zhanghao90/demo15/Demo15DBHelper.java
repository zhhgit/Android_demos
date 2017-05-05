package cn.zhanghao90.demo15;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Demo15DBHelper extends SQLiteOpenHelper {
    private Context mContext;
    private String sql_create = "create table tbl_student("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "age integer)";

    public Demo15DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql_create);
        Toast.makeText(mContext.getApplicationContext(),"创建数据库" + "tbl_student" +"成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
