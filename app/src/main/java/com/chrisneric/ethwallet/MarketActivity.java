package com.chrisneric.ethwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MarketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_menu);
    }
    public void MainActivity (View v){
        startActivity(new Intent(MarketActivity.this, MainActivity.class));
    }

    public void WalletMenu (View v){
        startActivity(new Intent(MarketActivity.this, WalletMenu.class));
    }
}
