package com.chrisneric.ethwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    TextView outLabel;

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        EthProxy.updateEthPrice();
//        double value = EthProxy.EthVal.ethusd;

        outLabel = findViewById(R.id.outLabel);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpGetAsyncTask task = new HttpGetAsyncTask();
                task.setUpdateListener(new HttpGetAsyncTask.OnUpdateListener() {
                    public void onUpdate(InputStream obj) {
                        try {
                            outLabel.setText(convertStreamToString(obj));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                task.execute("https://api.etherscan.io/api?module=stats&action=ethprice&apikey=2FKCN18ST4SQ34KFYJQUKYXI8AIRGBV5SE");
            }
        });
    }

    public void WalletMenu(View v) {
        startActivity(new Intent(MainActivity.this, WalletMenu.class));
    }
}
