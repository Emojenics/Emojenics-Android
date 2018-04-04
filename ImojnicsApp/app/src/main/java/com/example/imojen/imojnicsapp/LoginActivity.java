package com.example.imojen.imojnicsapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import android.content.SharedPreferences;

public class LoginActivity extends Activity  implements View.OnClickListener  {
    private static final String LOGIN_URL = "http://dev1.elagoon.in/emojenics/php/index.php/api/default_controller/login";
    private static  final  String identity="identity";
    private static  final  String password="password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText useremailtxtView=(EditText) findViewById(R.id.input_email_login);
        EditText useridtxtView=(EditText) findViewById(R.id.input_password_login);
        TextView userLogin = (TextView)findViewById(R.id.logintxtfld);
        TextView userfblogin=(TextView)findViewById(R.id.loginfbtxtfld);
        TextView registertxtView = (TextView)findViewById(R.id.link_signup);
        userLogin.setOnClickListener(this);
        userfblogin.setOnClickListener(this);
        registertxtView.setOnClickListener(this);
        LinearLayout layout = (LinearLayout)findViewById(R.id.loginpage);
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
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(nametext.getWindowToken(), 0);
//        imm.hideSoftInputFromWindow(phonetext.getWindowToken(), 0);
//        imm.hideSoftInputFromWindow(nameemail.getWindowToken(), 0);
//        imm.hideSoftInputFromWindow(passwordtext.getWindowToken(), 0);
//        imm.hideSoftInputFromWindow(confirmpasswordtext.getWindowToken(), 0);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.logintxtfld:
                login();
                break;
            case R.id.loginfbtxtfld:

                break;
            case R.id.link_signup:
                Intent intent= new Intent(this,registration.class);
                startActivity(intent);
                return;

        }

    }



    private void login(){


        EditText nameemail=(EditText)findViewById(R.id.input_email_login);
        EditText passwordtext=(EditText)findViewById(R.id.input_password_login);



        final String emailStr = nameemail.getText().toString().trim();
        final String passwordStr = passwordtext.getText().toString().trim();


        if(isValidAll()) {
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();
            final LoginActivity loginActivity=this;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
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
                                    Toast.makeText(getApplicationContext(),"Logged in successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(loginActivity,MainActivity.class);
                                    intent.putExtra("slection","4");
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                    params.put(identity, emailStr);
                    params.put(password, passwordStr);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
    private boolean isValidAll()
    {

        EditText nameemail=(EditText)findViewById(R.id.input_email_login);
        EditText passwordtext=(EditText)findViewById(R.id.input_password_login);
        final String emailStr = nameemail.getText().toString().trim();
        final String passwordStr = passwordtext.getText().toString().trim();



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
