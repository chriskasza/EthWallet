package com.chrisneric.ethwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HelpMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_menu);

    }

    public void MainActivity(View v) {
        startActivity(new Intent(HelpMenu.this, MainActivity.class));
    }
}
