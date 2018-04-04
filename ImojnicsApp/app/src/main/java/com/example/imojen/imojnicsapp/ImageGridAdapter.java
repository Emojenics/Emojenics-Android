package com.example.imojen.imojnicsapp;

import android.app.Fragment;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.imojen.imojnicsapp.R;

/**
 * Created by abdurrahim on 2/15/18.
 */

public class ImageGridAdapter extends BaseAdapter
{
    private Context mContext;
    private Fragment frag;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage,
            R.mipmap.emojenicsselectedimage, R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage,
            R.mipmap.emojenicsselectedimage, R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage,
            R.mipmap.emojenicsselectedimage, R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage,
            R.mipmap.emojenicsselectedimage, R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage,
            R.mipmap.emojenicsselectedimage, R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage,
            R.mipmap.emojenicsselectedimage, R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage,
            R.mipmap.emojenicsselectedimage, R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage,
            R.mipmap.emojenicsselectedimage, R.mipmap.emojenicsselectedimage,R.mipmap.emojenicsselectedimage
    };

    // Constructor
    public ImageGridAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        DisplayMetrics displayMetrics=mContext.getResources().getDisplayMetrics();
        float dpHeight=displayMetrics.heightPixels/displayMetrics.density;
        float dpWidth=displayMetrics.widthPixels/displayMetrics.density;
//        GridView gridView = (GridView) findViewById(R.id.gridviewfragment);
//        ViewGroup.LayoutParams params=gridView.getLayoutParams();
        float density=mContext.getResources().getDisplayMetrics().density;
        int width =dpToPx(Math.round((dpWidth)),density);
        int perwidth=(width-40)/3;

        int height=dpToPx(width,density);
//        gridView.setLayoutParams(params);
        int perheight=(int)Math.round((perwidth-10)*1.4);
        imageView.setLayoutParams(new GridView.LayoutParams( perwidth-10, perheight));
        return imageView;
    }
    public int dpToPx(int dp, float density) {
        return Math.round(((float) dp) * density);
    }

}

