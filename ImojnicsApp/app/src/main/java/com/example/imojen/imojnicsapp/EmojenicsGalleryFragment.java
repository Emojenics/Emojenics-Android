package com.example.imojen.imojnicsapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toolbar;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmojenicsGalleryFragment extends Fragment  implements Toolbar.OnMenuItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emojenics_gallery, container, false);
        setHasOptionsMenu(true);

//        Toolbar toolbar= (Toolbar) getActivity().findViewById(R.id.toolbar);
//        toolbar.inflateMenu(R.menu.main);
//        toolbar.setOnMenuItemClickListener(this);

        return rootView;
    }

    public EmojenicsGalleryFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_plus:
//                Toast.makeText(this,"skip selected",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),CaptureActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main, menu);
        inflater.inflate(R.menu.settings, menu);
        MenuItem item = menu.findItem(R.id.action_plus);

                item.setVisible(true);


    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_plus:
                //do sth here
                Context ctx=App.context;
                Intent i = new Intent(getActivity(),CaptureActivity.class);
                startActivity(i);
                return true;
        }
        return false;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstance)
    {
        super.onViewCreated(view,savedInstance);
//        getActivity().setTitle("Emojenics");
        getActivity().setTitle(Html.fromHtml("<font color='#0068A0'>Saved Emojis</font>"));
        Context context = App.context;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;


        GridView gridView = (GridView) getView().findViewById(R.id.gridviewfragment);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        float density = context.getResources().getDisplayMetrics().density;
        layoutParams.width = dpToPx( Math.round(dpWidth),density); //this is in pixels
        layoutParams.width=layoutParams.width-40;
        layoutParams.height = dpToPx(Math.round(layoutParams.height*(381/272)),density); //this is in pixels
        gridView.setLayoutParams(layoutParams);
//         Instance of ImageAdapter Class


        gridView.setAdapter(new ImageGridAdapter(this.getContext()));
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_emojenics_gallery, container, false);
//    }

    public int dpToPx(int dp,float density) {
        return Math.round((float) dp * density);
    }

}
