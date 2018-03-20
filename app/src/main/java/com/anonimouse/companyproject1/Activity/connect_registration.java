package com.anonimouse.companyproject1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.anonimouse.companyproject1.R;
import com.anonimouse.companyproject1.app.App;
import com.special.ResideMenu.ResideMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class connect_registration extends Base {


     ResideMenu mResideMenu;
     EditText mInputFirstname,mInputMiddlename,mInputLastname;
     Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_connect_registration);
        InitializeVariables();

        showDiseaseDetails();
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname, midname, lname;
                fname = mInputFirstname.getText().toString();
                midname = mInputMiddlename.getText().toString();
                lname = mInputLastname.getText().toString();
                if(fname.isEmpty())
                    Toast.makeText(connect_registration.this, "enter your first name", Toast.LENGTH_SHORT).show();
                else if(midname.isEmpty())
                    Toast.makeText(connect_registration.this, "enter your mid name", Toast.LENGTH_SHORT).show();
                else if(lname.isEmpty())
                    Toast.makeText(connect_registration.this, "enter your last name", Toast.LENGTH_SHORT).show();
                else {
                    sendToServer(fname,midname,lname);
                }

            }
        });


    }



    private void InitializeVariables(){
        mResideMenu = initMenu(this, R.id.content_hamburger);
        mInputFirstname = (EditText)findViewById(R.id.input_firstname);
        mInputMiddlename = (EditText)findViewById(R.id.input_middlename);
        mInputLastname = (EditText)findViewById(R.id.input_lastname);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
    }

    private void sendToServer(final String fname, final String mname, final String lname) {

        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://192.168.1.18/api/v1/registration", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String val = jsonObject.getString("error");
                    if(val.equalsIgnoreCase("false")){
                        Toast.makeText(connect_registration.this, "Burat Success", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        JSONObject object = new JSONObject(res);
                    } catch (UnsupportedEncodingException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("first_name", fname);
                params.put("middle_name", mname);
                params.put("last_name", lname);
                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        App.getInstance().addToReqQueue(postRequest);
    }

    private void showDiseaseDetails() {
        StringRequest postRequest = new StringRequest(Request.Method.GET, "http://192.168.1.18/api/v1/getdetails", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("details");
                    for (int x = 0; x < jsonArray.length(); x++) {
                        JSONObject object = jsonArray.getJSONObject(x);


                        Log.d("puke", "  " +object.getString("first_name").toString() + " " + object.getString("middle_name").toString() + " " + object.getString("last_name").toString() );

                    } // end for request


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Iwatcher", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        JSONObject object = new JSONObject(res);
//                        showDialog("Error", object.getString("message"), SweetAlertDialog.ERROR_TYPE);
                    } catch (UnsupportedEncodingException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        App.getInstance().addToReqQueue(postRequest);
    }
}
