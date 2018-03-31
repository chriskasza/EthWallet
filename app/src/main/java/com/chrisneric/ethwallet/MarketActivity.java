package com.chrisneric.ethwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.chrisneric.ethwallet.EthProxy.getEthValue;

public class MarketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_menu);

        TextView ethLabel = findViewById(R.id.etcValue);
        TextView btcLabel = findViewById(R.id.btcValue);

        getEthValue(ethLabel, btcLabel);
    }

    public void MainActivity(View v) {
        startActivity(new Intent(MarketActivity.this, MainActivity.class));
    }

    public void WalletMenu(View v) {
        startActivity(new Intent(MarketActivity.this, WalletMenu.class));
    }
}
