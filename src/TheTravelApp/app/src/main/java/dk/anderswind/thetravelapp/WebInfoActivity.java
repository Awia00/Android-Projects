package dk.anderswind.thetravelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_info);

        WebView www = (WebView) findViewById(R.id.webView);
        www.setWebViewClient(new WebViewClient());
        www.loadUrl("http://www.dsb.dk");
    }
}
