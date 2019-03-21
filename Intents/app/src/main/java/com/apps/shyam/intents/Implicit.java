package com.apps.shyam.intents;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Implicit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ((EditText)findViewById(R.id.url)).getText().toString();
                Uri page = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, page);
                if(intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });

        findViewById(R.id.loc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc = ((EditText)findViewById(R.id.location)).getText().toString();
                Uri page = Uri.parse("geo:0,0?q=" + loc);
                Intent intent = new Intent(Intent.ACTION_VIEW, page);
                if(intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });

        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ((EditText)findViewById(R.id.text)).getText().toString();
                ShareCompat.IntentBuilder
                        .from(Implicit.this)
                        .setType("text/plain")
                        .setChooserTitle("Share this text with: ")
                        .setText(text)
                        .startChooser();
            }
        });
    }
}
