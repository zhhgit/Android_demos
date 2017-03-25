package cn.zhanghao90.demo11;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private List<ListItem> list = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        buildList();
        RecyclerView recyclerView3 = (RecyclerView) findViewById(R.id.recycler_view3);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView3.setLayoutManager(layoutManager);
        ListItemAdapter3 listItemAdapter3 = new ListItemAdapter3(list);
        recyclerView3.setAdapter(listItemAdapter3);
    }

    private void buildList(){
        StringBuffer sb = new StringBuffer();
        sb.append("文字");
        for(int i=0;i<20;i++){
            ListItem listEach = new ListItem(sb.toString()+i,R.mipmap.ic_launcher);
            list.add(listEach);
            sb.append("文字");
        }
    }
}
