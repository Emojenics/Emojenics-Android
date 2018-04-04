package com.example.imojen.imojnicsapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import android.app.ProgressDialog;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.HashMap;
import android.widget.Toast;
import org.json.JSONObject;
import  org.json.JSONArray;
import org.json.JSONException;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;


import android.util.Log;


public class registration extends AppCompatActivity implements View.OnClickListener{

    private static final String REGISTER_URL = "http://dev1.elagoon.in/emojenics/php/index.php/api/default_controller/register";
    private static  final  String phone="phone";
    private static  final  String password="password";
    private static  final  String email="email";
    private static  final  String dob="dob";
    private static  final  String active="active";
    private static  final  String name="full_name";

    private String TAG = "MainActivity";
//    IRResult mResultCallback = null;
//    VolleyService mVolleyService;

    EditText nametext;
    EditText phonetext;
    EditText nameemail;
    EditText passwordtext;
    EditText confirmpasswordtext;

    //    @"phone":_PhoneText.text,@"password":_PasswordText.text,@"email":_EmailText.text
//     ,@"dob":@"",@"active":@"1",@"full_name":_NameText.text
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        nametext=(EditText)findViewById(R.id.input_name);
        phonetext=(EditText)findViewById(R.id.input_phone);
        nameemail=(EditText)findViewById(R.id.input_email);
        passwordtext=(EditText)findViewById(R.id.input_passwword);
        confirmpasswordtext=(EditText)findViewById(R.id.input_confirm_password);
        TextView registrationClick= (TextView) findViewById(R.id.registerfildd);
        TextView loginClick= (TextView) findViewById(R.id.link_login);
        registrationClick.setOnClickListener(this);
        loginClick.setOnClickListener(this);
        ScrollView layout;
        layout = (ScrollView)findViewById(R.id.registrationpage);


        layout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });


    }
    private void hideKeyboard(View view) {
        Context ctx = App.context;
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
//
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.registerfildd:
               registerUser();
                break;
            case R.id.link_login:
                Intent intent= new Intent(this,LoginActivity.class);
                startActivity(intent);
                return;

        }
    }
    private void registerUser(){

        nametext=(EditText)findViewById(R.id.input_name);
        phonetext=(EditText)findViewById(R.id.input_phone);
        nameemail=(EditText)findViewById(R.id.input_email);
        passwordtext=(EditText)findViewById(R.id.input_passwword);
        confirmpasswordtext=(EditText)findViewById(R.id.input_confirm_password);

        final String usernameStr = nametext.getText().toString().trim();
        final String phoneStr = phonetext.getText().toString().trim();
        final String emailStr = nameemail.getText().toString().trim();
        final String passwordStr = passwordtext.getText().toString().trim();
        final String confirmpasswordStr = confirmpasswordtext.getText().toString().trim();

        if(isValidAll()) {
           final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();

//            mVolleyService = new VolleyService(mResultCallback,this);
//            mVolleyService.getDataVolley("GETCALL","http://192.168.1.150/datatest/get/data");
//            JSONObject sendObj = null;
//
//            Map<String, String> params = new HashMap<String, String>();
//            params.put(name, usernameStr);
//            params.put(password, passwordStr);
//            params.put(email, emailStr);
//            params.put(phone, phoneStr);
//            params.put(active,"1");
//            params.put(dob,"");
//            sendObj = new JSONObject(params);
//            mVolleyService.postDataVolley("POSTCALL", REGISTER_URL, sendObj);


            final registration myactivity = this;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        int status=0;
                        @Override

                        public void onResponse(String response) {
//                            Toast.makeText(this, response, Toast.LENGTH_LONG).show();
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                String success=jsonObj.getString("status");
                                if(success.equalsIgnoreCase("success"))
                                {
                                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putBoolean("login", true);           // Saving boolean - true/false
// Save the changes in SharedPreferences
                                    editor.commit(); // commit changes
                                    editor.apply();

                                  status=1;
                                  JSONArray messageArray=jsonObj.getJSONArray("message");
                                  String strmessage = messageArray.getString(0);
                                    Toast.makeText(getApplicationContext(),strmessage, Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(myactivity,MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.putExtra("slection","4");
                                    startActivity(intent);

                                }
                                else
                                {
                                    String failmessage=jsonObj.getString("message");
                                    Toast.makeText(getApplicationContext(),failmessage, Toast.LENGTH_SHORT).show();
                                }



                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(),"Inavalid json", Toast.LENGTH_SHORT).show();
                            }

                            pDialog.hide();
//                            Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
                            pDialog.hide();
                            Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(name, usernameStr);
                    params.put(password, passwordStr);
                    params.put(email, emailStr);
                    params.put(phone, phoneStr);
                    params.put(active,"1");
                    params.put(dob,"");


                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    //web service response come
//    void initVolleyCallback(){
//        final registration myactivity = this;
//        mResultCallback = new IRResult() {
//            @Override
//            public void notifySuccess(String requestType,JSONObject response) {
//                try {
//                    String success=response.getString("status");
//                    if(success.equalsIgnoreCase("success"))
//                    {
//
//                        JSONArray messageArray=response.getJSONArray("message");
//                        String strmessage = messageArray.getString(0);
//                        Toast.makeText(getApplicationContext(),strmessage, Toast.LENGTH_SHORT).show();
//                        Intent intent= new Intent(myactivity,MainActivity.class);
//                        startActivity(intent);
//
//
//                    }
//                    else
//                    {
//                        String failmessage=response.getString("message");
//                        Toast.makeText(getApplicationContext(),failmessage, Toast.LENGTH_SHORT).show();
//                    }
//
//
//
//                } catch (JSONException e) {
//                    Toast.makeText(getApplicationContext(),"Inavalid json", Toast.LENGTH_SHORT).show();
//                }
//
//                pDialog.hide();
////
//            }
//
//            @Override
//            public void notifyError(String requestType,VolleyError error) {
//                Log.d(TAG, "Volley requester " + requestType);
//                Log.d(TAG, "Volley JSON post" + "That didn't work!");
//                pDialog.hide();
//                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        };
//    }



    private boolean isValidAll()
    {
        EditText nametext=(EditText)findViewById(R.id.input_name);
        EditText phonetext=(EditText)findViewById(R.id.input_phone);
        EditText nameemail=(EditText)findViewById(R.id.input_email);
        EditText passwordtext=(EditText)findViewById(R.id.input_passwword);
        EditText confirmpasswordtext=(EditText)findViewById(R.id.input_confirm_password);

        final String usernameStr = nametext.getText().toString().trim();
        final String phoneStr = phonetext.getText().toString().trim();
        final String emailStr = nameemail.getText().toString().trim();
        final String passwordStr = passwordtext.getText().toString().trim();
        final String confirmpasswordStr = confirmpasswordtext.getText().toString().trim();



        if(usernameStr.toCharArray().length < 4)
        {
            nametext.setFocusable(true);
            Toast.makeText(getApplicationContext(),"Name must not be less tahn 4 character", Toast.LENGTH_SHORT).show();
            return false;
        }
        CharSequence phonecs = phoneStr;
        boolean isvalidphone = isValidPhoneNumber(phonecs);
        if(!isvalidphone)
        {
            Toast.makeText(getApplicationContext(),"Please enter a valid phone number", Toast.LENGTH_SHORT).show();
            phonetext.setFocusable(true);
            return false;
        }


        String email = emailStr.trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

// onClick of button perform this simplest code.

        if (email.matches(emailPattern))
        {
//            Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
            nameemail.setFocusable(true);

            return false;
        }
        if(passwordStr.toCharArray().length < 6)
        {
            Toast.makeText(getApplicationContext(),"Password must not be less than 6 character", Toast.LENGTH_SHORT).show();
            passwordtext.setFocusable(true);

            return false;
        }
        if(!(passwordStr.equals(confirmpasswordStr)))
        {
            Toast.makeText(getApplicationContext(),"Password and confirm password must be match", Toast.LENGTH_SHORT).show();
            passwordtext.setFocusable(true);

            return false;
        }

    return true;
    }
    public  boolean isValidPhoneNumber(CharSequence target) {
        if (target.length()!=10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }
}
