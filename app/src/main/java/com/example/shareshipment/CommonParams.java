/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.shareshipment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This class includes a small subset of standard GATT attributes for demonstration purposes.
 */
public class CommonParams {

    public static String getServerURL() {
        return serverURL;
    }

    private static String serverURL = "http://3.73.174.22:8080";

    public  static  void jsonRequestSignIn(JSONObject js, final String resource, int method, final Context context, final Class className){
        RequestQueue queue =  Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, CommonParams.getServerURL()+resource, js,new Response.Listener<JSONObject>() {
                    Toast myToast;
                    @Override
                    public void onResponse(JSONObject response) {
                                if(response==null) {
                                    myToast = Toast.makeText(context,"failed  !", Toast.LENGTH_SHORT);
                                    myToast.show();
                                }else {
                                   // ((MyApplication) Activity.getApplication()).setCity();
                                    Intent intent = new Intent(context, className);
                                    try {
                                        intent.putExtra("city",response.getJSONObject("address").getString("city"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {


                try {
                    String json = new String(
                            response.data,
                            "UTF-8"
                    );

                    if (json.length() == 0) {
                        return Response.success(
                                null,
                                HttpHeaderParser.parseCacheHeaders(response)
                        );
                    }
                    else {
                        return super.parseNetworkResponse(response);
                    }
                }
                catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }


            }
        };
        queue.add(jsonObjReq);
    }

    public  static  void JSONRequestWithoutResponse(JSONObject js, final String resource, int method, final Context context, final Class className){
        RequestQueue queue =  Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, CommonParams.getServerURL()+resource, js,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response==null) {
                    Intent intent = new Intent(context, className);
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
                    Toast.makeText(context,
                            "The user has already registered!",
                            Toast.LENGTH_LONG).show();
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
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {


                try {
                    String json = new String(
                            response.data,
                            "UTF-8"
                    );

                    if (json.length() == 0) {
                        return Response.success(
                                null,
                                HttpHeaderParser.parseCacheHeaders(response)
                        );
                    }
                    else {
                        return super.parseNetworkResponse(response);
                    }
                }
                catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }


            }
        };
        queue.add(jsonObjReq);
    }

    public static void enhancedJSONArrayRequest(JSONArray js, final String resource, int method, final Context context, final Class className){
        RequestQueue queue =  Volley.newRequestQueue(context);
        Intent intent;

        // Make request for JSONObject

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(method, CommonParams.getServerURL()+resource, js,new Response.Listener<JSONArray>() {
            //Toast myToast;
            @Override
            public void onResponse(JSONArray  response) {
                if(response!=null) {
                        Intent intent = new Intent(context, className);
                        intent.putExtra("shipments",response.toString());
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

    public static void jsonRequestForRecipientAssurance(JSONObject js, final String resource, int method, final Context context, final Class ConfirmRecipientInformation, final String recipientPhoneNumber,final String notes){
        RequestQueue queue =  Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, CommonParams.getServerURL()+resource, js,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response!=null) {
                    try {
                        Intent intent = new Intent(context,ConfirmRecipientInformation);
                        intent.putExtra("recipientName",response.getString("userName"));
                        intent.putExtra("recipientPhoneNumber",recipientPhoneNumber);
                        intent.putExtra("notes",notes);
                        context.startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error"+error);
                Log.d("JsonObject Error ",error.toString());

                if (error instanceof NetworkError) {
                } else if (error instanceof ServerError) {
                    Toast.makeText(context,
                            "The receiver is not a member of the system",
                            Toast.LENGTH_LONG).show();
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
