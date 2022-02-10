package com.example.friendsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4;
AppCompatButton b1;
String getName,getFname,getFnickname,getDescribe;
String furl="https://dummyapifriends.herokuapp.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.yname);
        e2=(EditText) findViewById(R.id.fname);
        e3=(EditText) findViewById(R.id.nickname);
        e4=(EditText) findViewById(R.id.describe);
        b1=(AppCompatButton) findViewById(R.id.submit);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getName=e1.getText().toString();
                getFname=e2.getText().toString();
                getFnickname=e3.getText().toString();
                getDescribe=e4.getText().toString();
               StringRequest fr=new StringRequest(Request.Method.POST, furl, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       e1.setText("");
                       e2.setText("");
                       e3.setText("");
                       e4.setText("");
                       Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                       Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                   }
               }){

                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                       HashMap<String,String> hm=new HashMap<>();
                       hm.put("name",getName);
                       hm.put("friendName",getFname);
                       hm.put("friendNickName",getFnickname);
                       hm.put("DescribeYourFriend",getDescribe);
                       return hm;

                   }

               };

                RequestQueue fq= Volley.newRequestQueue(getApplicationContext());
                fq.add(fr);

            }
        });
    }
}