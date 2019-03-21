package com.apps.shyam.firebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText name;
    private EditText regno;
    private Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        name = findViewById(R.id.name);
        regno = findViewById(R.id.regno);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                user.put("Name", name.getText().toString());
                user.put("RegNo", regno.getText().toString());
                db.collection("users").add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                mToast = Toast.makeText(getApplicationContext(), "Data Uploaded!", Toast.LENGTH_SHORT);
                                mToast.show();
                                getData();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                mToast = Toast.makeText(getApplicationContext(), "Data Upload failed!", Toast.LENGTH_SHORT);
                                mToast.show();
                            }
                        });
            }
        });
    }
    public void getData(){
        db.collection("users")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    if(mToast != null)
                        mToast.cancel();
                    mToast = Toast.makeText(getApplicationContext(), "Data retrieval successful!", Toast.LENGTH_SHORT);
                    mToast.show();
                    if(documentSnapshot.get("Name").toString().equals(name.getText().toString())) {
                        String res = "Hey there, " + name.getText().toString() + "(" + regno.getText().toString() + ") !";
                        ((TextView) findViewById(R.id.result)).setText(res);
                    }
                }
            }
        });
    }
}
