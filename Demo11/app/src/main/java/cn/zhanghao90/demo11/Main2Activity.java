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

public class Main2Activity extends AppCompatActivity {
    private List<ListItem> list = new LinkedList<>();
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });

        buildList();
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main2Activity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(linearLayoutManager);
        ListItemAdapter2 listItemAdapter2 = new ListItemAdapter2(list);
        recyclerView2.setAdapter(listItemAdapter2);
    }

    private void buildList(){
        for(int i=0;i<20;i++){
            ListItem listEach = new ListItem("文字文字文字"+i,R.mipmap.ic_launcher);
            list.add(listEach);
        }
    }
}
