package com.example.imojen.imojnicsapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TermsConditionFragment extends Fragment {


    public TermsConditionFragment() {
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
//        getActivity().setTitle("Terms and Condition");
        getActivity().setTitle(Html.fromHtml("<font color='#0068A0'>Terms and Condition </font>"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terms_condition, container, false);
    }

}
