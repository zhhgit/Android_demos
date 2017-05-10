package cn.zhanghao90.demo23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWebView();
    }

    private void initWebView(){
        webView1 = (WebView) findViewById(R.id.webView1);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("http://zhanghao90.cn/Blog");
    }
}
