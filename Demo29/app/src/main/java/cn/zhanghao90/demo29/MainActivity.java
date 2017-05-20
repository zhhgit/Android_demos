package cn.zhanghao90.demo29;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private List<Item> list = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar1);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout1);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //设置ToolBar左侧按钮
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.tel);
        }

        //设置NavigationView,左侧划出
        navigationView.setCheckedItem(R.id.navItem1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //设置悬浮按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用Snackbar
                Snackbar.make(v,"点击了悬浮按钮",Snackbar.LENGTH_LONG)
                        .setAction("取消",new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),"已取消",Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });

        //设置RecyclerView
        initList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ToolBar上添加图标菜单
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //ToolBar上的图标按钮
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"点击了ToolBar按钮",Toast.LENGTH_LONG).show();
                break;
            //点击左侧按钮，弹出DrawerLayout
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    //初始化RecyclerView数据
    private void initList(){
        for(int i=0;i<10;i++){
            Item item = new Item("item",R.drawable.logo);
            list.add(item);
        }
    }
}
