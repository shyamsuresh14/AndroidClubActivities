package com.apps.shyam.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Explicit2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit2);
        getSupportActionBar().setTitle("Activity-2");

        if(getIntent().getStringExtra("Reply") != null)
        {
            ((TextView) findViewById(R.id.reply_rec)).setText("Reply received!");
            ((TextView) findViewById(R.id.reply)).setText(getIntent().getStringExtra("Reply"));
        }
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Explicit.class).putExtra("Reply", ((EditText) findViewById(R.id.message)).getText().toString()));
                finish();
            }
        });
    }
}
