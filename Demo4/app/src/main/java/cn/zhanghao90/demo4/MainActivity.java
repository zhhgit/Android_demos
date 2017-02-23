package cn.zhanghao90.demo4;

import android.graphics.*;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.lang.*;
import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {
    byte[] bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //在activity_main.xml文件中设置android:onClick="sendMessage"
    public void sendMessage(View view) {
        new Thread(getThread).start();
    }

    private Thread getThread = new Thread(){
        public void run() {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("https://wallet.95516.com/s/wl/web/402/images/life/flags/USD.png");
                connection = (HttpURLConnection) url.openConnection();
                //设置允许输出
                connection.setDoOutput(true);
                connection.setDoInput(true);
                //设置不用缓存
                connection.setUseCaches(false);
                //设置传递方式
                connection.setRequestMethod("GET");
                //设置文件字符集:
                connection.setRequestProperty("Charset", "UTF-8");
                //请求成功
                if(connection.getResponseCode() == 200){
                    //请求得到的InputStream转成byte数组
                    InputStream is = connection.getInputStream();
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while((len = is.read(buffer)) != -1)
                    {
                        outStream.write(buffer,0,len);
                    }
                    is.close();
                    bt = outStream.toByteArray();
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
        }
    };

    private Handler getHandler = new Handler(){
        //重写handleMessage方法,根据msg中what的值判断是否执行后续操作
        @Override
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 0 && bt!=null){
                //不能再getThread中调用Toast，在线程中更新UI会导致闪退
                Toast.makeText(getApplicationContext(),"请求成功",Toast.LENGTH_SHORT).show();
                ImageView imgPic = (ImageView) findViewById(R.id.imageView1);
                //从byte数组转成bitmap，添加到ImageView中
                Bitmap bitmap = BitmapFactory.decodeByteArray(bt, 0, bt.length);
                imgPic.setVisibility(View.VISIBLE);
                imgPic.setImageBitmap(bitmap);
            }
        }
    };
}