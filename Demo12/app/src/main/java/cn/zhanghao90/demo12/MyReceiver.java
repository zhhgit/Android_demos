package cn.zhanghao90.demo12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"静态注册BroadcastReceiver!",Toast.LENGTH_LONG).show();
    }
}
