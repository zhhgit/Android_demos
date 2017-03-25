package cn.zhanghao90.demo11;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ListItem> list = new LinkedList<>();
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        buildList();
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView1.setLayoutManager(linearLayoutManager);
        ListItemAdapter listItemAdapter = new ListItemAdapter(list);
        recyclerView1.setAdapter(listItemAdapter);
    }

    private void buildList(){
        for(int i=0;i<20;i++){
            ListItem listEach = new ListItem("文字"+i,R.mipmap.ic_launcher);
            list.add(listEach);
        }
    }
}
