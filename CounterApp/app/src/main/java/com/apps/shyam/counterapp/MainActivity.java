package com.apps.shyam.counterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button increment;
    private TextView counter;
    private static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        increment = findViewById(R.id.inc_btn);
        counter = findViewById(R.id.counter);

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currVal = Integer.valueOf(String.valueOf(counter.getText()));
                currVal = currVal+1;
                counter.setText(String.valueOf(currVal));
                if(toast != null)
                    toast.cancel();
                toast = Toast.makeText(getApplicationContext(), "Value incremented to " + String.valueOf(currVal), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
