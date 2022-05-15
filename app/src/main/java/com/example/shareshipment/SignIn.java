package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {
    EditText phoneNumber;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signIn(View view) throws JSONException {
        //ToDo API verification for phone number and password

        phoneNumber = findViewById(R.id.PhoneNumber);
        password  = findViewById(R.id.password);
        JSONObject js = new JSONObject();
        js.put("phoneNumber",phoneNumber.getText().toString());
        js.put("password",password.getText().toString());
        String resource = "/users/login";
        jsonRequest(js,resource, Request.Method.POST,getApplicationContext());
    }
    public  static  void jsonRequest(JSONObject js, final String resource, int method, final Context context){
        RequestQueue queue =  Volley.newRequestQueue(context);
        BooleanRequest jsonObjReq = new BooleanRequest(method, CommonParams.getServerURL()+resource, js.toString(),new Response.Listener<Boolean>() {
            Toast myToast;
            @Override
            public void onResponse(Boolean response) {


                    if(!response) {
                        myToast = Toast.makeText(context,"wrong username or password  !", Toast.LENGTH_SHORT);
                        myToast.show();
                    }else{
                        Intent intent = new Intent(context, MainFunctionality.class);
                        context.startActivity(intent);
                    }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error"+error);
                Log.d("JsonObject Error ",error.toString());

                if (error instanceof NetworkError) {
                } else if (error instanceof ServerError) {
                } else if (error instanceof AuthFailureError) {
                } else if (error instanceof ParseError) {
                } else if (error instanceof NoConnectionError) {
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(context,
                            "Oops. Timeout error!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
        queue.add(jsonObjReq);
    }

}