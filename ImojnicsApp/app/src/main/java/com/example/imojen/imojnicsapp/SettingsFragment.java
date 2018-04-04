package com.example.imojen.imojnicsapp;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener{
    public  static  final  int My_Request_Camera=10;
    public  static  final  int My_Request_Write_Camera=11;
    public  static  final  int My_Request_Capture_Camera=12;
    Activity curactivity;
    LinearLayout layout1;
    LinearLayout layout2;
    TextView layout3;
    TextView layout4;
    TextView layout5;
    TextView layout6;
    TextView layout7;
    TextView layout8;



    public SettingsFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main, menu);
        inflater.inflate(R.menu.settings, menu);
        MenuItem item = menu.findItem(R.id.action_plus);

        item.setVisible(false);


    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance)
    {
        super.onViewCreated(view,savedInstance);
//        getActivity().setTitle("Settings");
        curactivity=getActivity();
        curactivity.setTitle(Html.fromHtml("<font color='#0068A0'>Settings </font>"));
        TextView doneTxt=getActivity().findViewById(R.id.donebtnnn);
        doneTxt.setOnClickListener(this);

        layout1 = (LinearLayout)curactivity.findViewById(R.id.ods);
        layout2 = (LinearLayout)curactivity.findViewById(R.id.general);
        layout3 = (TextView) curactivity.findViewById(R.id.keyboard);
        layout4 = (TextView)curactivity.findViewById(R.id.keyboards);
        layout5 = (TextView) curactivity.findViewById(R.id.addkeyboard);
        layout6 = (TextView)curactivity.findViewById(R.id.emojenics);
        layout7 = (TextView) curactivity.findViewById(R.id.tapemojen);
        layout8 = (TextView)curactivity.findViewById(R.id.allowaccess);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);
        layout6.setOnClickListener(this);
        layout7.setOnClickListener(this);
        layout8.setOnClickListener(this);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.donebtnnn:

                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent);

                break;

            case R.id.ods:
                setAllClear();
                layout1.setBackgroundColor(getResources().getColor(R.color.orangecolor));
                break;
            case R.id.general:
                setAllClear();
                layout2.setBackgroundColor(getResources().getColor(R.color.orangecolor));
                break;
            case R.id.keyboard:
                setAllClear();
                layout3.setBackgroundColor(getResources().getColor(R.color.orangecolor));
                break;
            case R.id.keyboards:
                setAllClear();
                layout4.setBackgroundColor(getResources().getColor(R.color.orangecolor));
                break;
            case R.id.addkeyboard:
                setAllClear();
                layout5.setBackgroundColor(getResources().getColor(R.color.orangecolor));
                break;
            case R.id.emojenics:
                setAllClear();
                layout6.setBackgroundColor(getResources().getColor(R.color.orangecolor));
                break;
            case R.id.tapemojen:
                setAllClear();
                layout7.setBackgroundColor(getResources().getColor(R.color.orangecolor));
                break;
            case R.id.allowaccess:
                setAllClear();
                layout8.setBackgroundColor(getResources().getColor(R.color.orangecolor));
                break;


        }
    }

void setAllClear()
{
    layout1.setBackgroundColor(getResources().getColor(R.color.clearColor));
    layout2.setBackgroundColor(getResources().getColor(R.color.clearColor));
    layout3.setBackgroundColor(getResources().getColor(R.color.clearColor));
    layout4.setBackgroundColor(getResources().getColor(R.color.clearColor));
    layout5.setBackgroundColor(getResources().getColor(R.color.clearColor));
    layout6.setBackgroundColor(getResources().getColor(R.color.clearColor));
    layout7.setBackgroundColor(getResources().getColor(R.color.clearColor));
    layout8.setBackgroundColor(getResources().getColor(R.color.clearColor));
}

}
