package com.example.agrotrade.Farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.agrotrade.R;

import org.apache.http.util.EncodingUtils;

public class FinalPayment extends AppCompatActivity {

    WebView webView;

    String URRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_payment);

        webView=(WebView)findViewById(R.id.web);

        Intent  intent=getIntent();

        URRl=intent.getStringExtra("url");

        Uri uri=Uri.parse(URRl);


//        webView.loadUrl(URRl);





        WebSettings settings = webView.getSettings();
       // settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);



       // webView.setWebViewClient(new MyWebViewClient());
        String postData = "amount=1000&firstname=mtetno&email=mttee@gmail.com&phone=2145635784&productinfo=android&surl=success.php"
                + "&furl=failure.php&lastname=qwerty&curl=dsdsd.com&address1=dsdsds&address2=dfdfd&city=dsdsds&state=dfdfdfd&"
                + "country=fdfdf&zipcode=123456&udf1=dsdsds&udf2=fsdfdsf&udf3=jhghjg&udf4=fdfd&udf5=fdfdf&pg=dfdf";


        webView.loadUrl(URRl);
      // webView.postUrl(URRl,EncodingUtils.getBytes(postData, "BASE64"));

//        webView.postUrl(
//                "http://host/payment.php",
//                EncodingUtils.getBytes(postData, "BASE64"));



    }



    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                       SslError error) {
            handler.proceed();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
