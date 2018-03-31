package com.chrisneric.ethwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void WalletMenu (View v){
        startActivity(new Intent(MainActivity.this, WalletMenu.class));
    }

    public void HelpMenu (View v){
        startActivity(new Intent(MainActivity.this, HelpMenu.class));
    }
    public void MarketActivity (View v){
        startActivity(new Intent(MainActivity.this, MarketActivity.class));
    }
    public void SendActivity (View v){
        startActivity(new Intent(MainActivity.this, SendActivity.class));
    }
}
