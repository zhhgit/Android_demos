package cn.zhanghao90.demo28;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    private Downloader mBinder = new Downloader();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","调用了onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","调用了onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","调用了onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //内部类实现Service具体功能
    class Downloader extends Binder{
        public void funcA(){
            Log.d("MyService","调用了funcA");
        }

        public void funcB(){
            Log.d("MyService","调用了funcB");
        }
    }
}
