package cn.zhanghao90.demo3;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {
    private TextView htmlView;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        htmlView = (TextView) findViewById(R.id.textView1);
    }

    //在activity_main.xml文件中设置android:onClick="sendMessage"
    public void sendMessage(View view) {
        new Thread(getThread).start();
    }

    private Thread getThread = new Thread(){
        public void run() {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://www.baidu.com");
                connection = (HttpURLConnection) url.openConnection();
                //设置请求方法，默认是GET
                connection.setRequestMethod("GET");
                //设置字符集
                connection.setRequestProperty("Charset", "UTF-8");
                //设置文件类型
                connection.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
                //请求成功
                if(connection.getResponseCode() == 200){
                    InputStream is = connection.getInputStream();
                    StringBuffer sb = new StringBuffer();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while((len = is.read(buffer)) != -1){
                        sb.append(new String(buffer,0,len));
                    }
                    is.close();
                    result = sb.toString();
                    //Handler消息传递
                    Message msg = Message.obtain();
                    msg.what = 0;
                    getHandler.sendMessage(msg);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(connection != null){
                    connection.disconnect();
                }
            }
        };
    };

    private Handler getHandler = new Handler(){
        //重写handleMessage方法,根据msg中what的值判断是否执行后续操作
        @Override
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 0 && result!=null){
                //不能再getThread中调用Toast，在线程中更新UI会导致闪退
                Toast.makeText(getApplicationContext(),"请求成功",Toast.LENGTH_SHORT).show();
                htmlView.setText("");
                htmlView.setText(result);
            }
        };
    };

    //在activity_main.xml文件中设置android:onClick="clean"
    public void clean(View view) {
        htmlView.setText("");
    }
}
