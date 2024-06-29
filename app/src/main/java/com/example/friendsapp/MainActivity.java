package com.example.friendsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText d1,d2,d3,d4;
    AppCompatButton b1;
    String apiUrl="https://friendsapi-re5a.onrender.com/adddata\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        d1=(EditText) findViewById(R.id.ed1);
        d2=(EditText) findViewById(R.id.ed2);
        d3=(EditText) findViewById(R.id.ed3);
        d4=(EditText) findViewById(R.id.ed4);
        b1=(AppCompatButton) findViewById(R.id.bt1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String s1,s2,s3,s4;
                    s1=d1.getText().toString();
                    s2=d2.getText().toString();
                    s3=d3.getText().toString();
                    s4=d4.getText().toString();

                    //Object creation of JSON
                    JSONObject frnd=new JSONObject();
                    try{
                        frnd.put("name",s1);
                        frnd.put("friendName",s2);
                        frnd.put("friendNickName",s3);
                        frnd.put("DescribeYourFriend",s4);
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),e.toString()+ " ", Toast.LENGTH_SHORT).show();
                    }


                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                            Request.Method.POST,
                            apiUrl,
                            frnd,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );


                    RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(jsonObjectRequest);





                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString()+ " ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}