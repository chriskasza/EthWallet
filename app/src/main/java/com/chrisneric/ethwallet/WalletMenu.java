package com.chrisneric.ethwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.chrisneric.ethwallet.EthProxy.getEthBalance;

public class WalletMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_menu);

        TextView outLabel = findViewById(R.id.textView3);

        getEthBalance(outLabel);
    }

    public void MainActivity(View v) {
        startActivity(new Intent(WalletMenu.this, MainActivity.class));
    }
}
