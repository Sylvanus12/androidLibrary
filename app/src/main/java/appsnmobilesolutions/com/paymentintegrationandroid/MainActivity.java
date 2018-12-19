package appsnmobilesolutions.com.paymentintegrationandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import appsnmobilesolutions.com.paymentlibrary.PaymentLibrary;


public class MainActivity extends AppCompatActivity {

    PaymentLibrary paymentLibrary;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webView = findViewById(R.id.webView);

        paymentLibrary = new PaymentLibrary(getBaseContext());

        paymentLibrary.add_keys("FjgFTnx6sxIcbA7/emngbs6hePenxlJzZNmbGjKm7T/KRlI+8SB+BPHG3E6yQ7QMt7+Oii0wczueNQJsGevquQ=="
                , "Vzf0beMIKVf5EeU/cVneDOo+Gx3gLHUFsMfHASXd2vUYgbU+k2e2tCj+PSjJPcU8f4dcKAnbPr3/ughyzr70Qw=="
                , "8"
        );
//        paymentLibrary.sendJson("Hellworld");

        try {
            sendReq();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // paymentLibrary.sendTokenJson();
        // Log.d("Display", paymentLibrary.getPaymentTokenUrl());


    }

    private void sendReq() throws ExecutionException, InterruptedException {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        ;
        JsonObject object = new JsonObject();
        object.addProperty("client_id", paymentLibrary.getUserClientId());
        object.addProperty("reference", "testing mic two");
        object.addProperty("amount", 0.1);
        object.addProperty("exttrid", "D028");
        object.addProperty("callback_url", "");
        object.addProperty("trans_type", "DR");
        object.addProperty("ts", timeStamp);
        object.addProperty("nickname", "rhymez");

        Log.d("OBJ", String.valueOf(object));

        // String returnedStr =
        paymentLibrary.sendJson(String.valueOf(object));

//        Toast.makeText(this, returnedStr, Toast.LENGTH_LONG).show();
        doIt();
    }

    private void doIt() {
        webView.setWebViewClient(new WebViewClient());

        System.out.println("MainAct22----------------------------------" + PaymentLibrary.getPaymentTokenUrl() + "--------------------------------------");
        webView.loadUrl(paymentLibrary.getPaymentTokenUrl());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.canGoBack();
        } else {
            super.onBackPressed();
        }
    }
}
