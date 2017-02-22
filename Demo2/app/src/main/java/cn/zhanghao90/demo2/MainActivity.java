package cn.zhanghao90.demo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //为WebView指定一个WebViewClient对象.WebViewClient可以辅助WebView处理各种通知,请求等事件。
        webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {
            //控制对新加载的Url的处理,返回true,说明主程序处理WebView不做处理,返回false意味着WebView会对其进行处理
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //getSettings返回一个WebSettings对象,用来控制WebView的属性设置,setJavaScriptEnabled()设置是否允许JS脚本执行
        webView.getSettings().setJavaScriptEnabled(true);
        //加载指定的Url
        webView.loadUrl("http://zhanghao90.cn/Blog/");
        setContentView(webView);
    }

    //我们需要重写回退按钮事件，当用户点击回退按钮webView.canGoBack()判断网页是否能后退,可以则goback()，如果不可以连续点击两次退出App,否则弹出提示Toast
    @Override
    public void onBackPressed() {
        //webview中能回退就回退
        if (webView.canGoBack()) {
            webView.goBack();
        }
        else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            //点击了两次时间差小于2000毫秒就退出
            else {
                super.onBackPressed();
            }
        }
    }
}
