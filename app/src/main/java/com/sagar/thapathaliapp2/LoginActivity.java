package com.sagar.thapathaliapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.sagar.thapathaliapp2.session.SessionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText username;
    TextInputEditText pass;
    Button loginBtn;

    String txt_rollno;
    String txt_pass;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        loginBtn = findViewById(R.id.loginBtn);

        sessionManager = new SessionManager(getApplicationContext());

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_rollno = Objects.requireNonNull(username.getText()).toString();
                txt_pass = Objects.requireNonNull(pass.getText()).toString();

                loginUser();
            }
        });

    }

    public void loginUser(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://192.168.1.65/LoginAPI.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(LoginActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        if(response.trim().equals("fail")){
                            Toast.makeText(LoginActivity.this, "Rollno and Password is incorrect", Toast.LENGTH_SHORT).show();
                        }else {
                            //open Main Activity
                            sessionManager.createSession(txt_rollno,txt_pass);
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(LoginActivity.this, ""+volleyError, Toast.LENGTH_SHORT).show();
                    }
                }
                ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("key_rollno",txt_rollno);
                hm.put("key_pass",txt_pass);
                return hm;
            }
        };

        requestQueue.add(stringRequest);
    }

}