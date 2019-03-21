package com.apps.healthcare.networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class MainActivity extends AppCompatActivity {
    final String URL = "https://api.github.com/";
    ArrayList<String> keys, values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        RecyclerView recyclerView = findViewById(R.id.recyclerLayout);
        final DataListAdapter dataListAdapter = new DataListAdapter(this, keys, values);
        recyclerView.setAdapter(dataListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void getData(){
        keys = new ArrayList<>(Arrays.asList("current_user_url","current_user_authorizations_html_url","authorizations_url","code_search_url","commit_search_url","emails_url","emojis_url","events_url","feeds_url","followers_url","following_url","gists_url","hub_url","issue_search_url","issues_url","keys_url","notifications_url","organization_repositories_url","organization_url","public_gists_url","rate_limit_url","repository_url","repository_search_url","current_user_repositories_url","starred_url","starred_gists_url","team_url","user_url","user_organizations_url","user_repositories_url","user_search_url"));
        values = new ArrayList<>();

        final StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Log.d("MyNetwork", response);
                    JSONObject obj = new JSONObject(response);
                    for(int i = 0; i < keys.size(); i++){
                        values.add(obj.getString(keys.get(i)));
                    }
                    Log.d("MyNetwork", values.toString());
                    Toast.makeText(MainActivity.this, "Data successfully retrieved!", Toast.LENGTH_SHORT).show();
                } catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this , "Error retrieving JSON Data", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this , "Error retrieving data from Server", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(request);
    }
}