package com.example.imojen.imojnicsapp;

/**
 * Created by abdurrahim on 2/20/18.
 */

import android.content.Context;
import org.json.JSONObject;
import org.json.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.Request;

public class VolleyService {
    IRResult mResultCallback = null;
    Context mContext;

    VolleyService(IRResult resultCallback, Context context){
        mResultCallback = resultCallback;
        mContext = context;
    }


    public void postDataVolley(final String requestType, String url,JSONObject sendObj){
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(url,sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(requestType,response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(requestType,error);
                }
            });

            queue.add(jsonObj);

        }catch(Exception e){

        }
    }

//    public void getDataVolley(final String requestType, String url){
//        try {
//            RequestQueue queue = Volley.newRequestQueue(mContext);
//
//            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    if(mResultCallback != null)
//                        mResultCallback.notifySuccess(requestType, response);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    if(mResultCallback != null)
//                        mResultCallback.notifyError(requestType, error);
//                }
//            });
//
//            queue.add(jsonObj);
//
//        }catch(Exception e){
//
//        }
//    }
}
