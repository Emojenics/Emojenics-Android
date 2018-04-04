package com.example.imojen.imojnicsapp;

/**
 * Created by abdurrahim on 2/20/18.
 */

import org.json.JSONObject;
import com.android.volley.VolleyError;

public interface IRResult {
    public void notifySuccess(String requestType,JSONObject response);
    public void notifyError(String requestType,VolleyError error);
}
