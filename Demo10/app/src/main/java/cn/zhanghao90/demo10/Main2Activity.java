package cn.zhanghao90.demo10;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private List<ListItem> list = new LinkedList<>();
    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        buildList();
        listView2 = (ListView) findViewById(R.id.listview2);
        ListItemAdapter adapter = new ListItemAdapter(Main2Activity.this,R.layout.list_item,list);
        listView2.setAdapter(adapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem listItem = list.get(position);
                Toast.makeText(Main2Activity.this,listItem.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void buildList(){
        for(int i=0;i<20;i++){
            ListItem listEach = new ListItem("文字"+i,R.mipmap.ic_launcher);
            list.add(listEach);
        }
    }
}
