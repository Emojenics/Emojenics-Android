package com.example.imojen.imojnicsapp;

import android.support.v7.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * Created by abdurrahim on 2/27/18.
 */

public class ImageAdapter //extends RecyclerView.Adapter<MainAdapter.ViewHolder>
{
   /* private ArrayList<Integer> mDataSet;
    private int Tag;
    private int selectedPos = RecyclerView.NO_POSITION;
    public  ImageAdapter(ArrayList<Integer>mDataSet,int tag)
    {
        this.mDataSet=mDataSet;
        this.Tag=tag;
    }



    @Override
//    public  MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
//    {
//        if(this.Tag ==1)
//        {
//            View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_layout,parent,false);
//            ViewHolderC vh=new ViewHolderC(v);
//            return vh;
//        }
//        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_layout2,parent,false);
//        ViewHolderC vh=new ViewHolderC(v);
//        return vh;
    }
@SuppressLint("ResourceAsColor")
@Override
    public void onBindViewHolder( MainAdapter.ViewHolder holder,int position)
    {
     holder.imageVew.setImageResource(mDataSet.get(position
     ));
     holder.itemView.setSelected(selectedPos==position);
     if(selectedPos==position)
     {
      holder.itemView.setBackgroundColor(Color.RED);
     }
     else
     {
         holder.itemView.setBackgroundColor(Color.parseColor("#00000000"));
     }
    }

    @Override
    public int getItemCount()
    {
        return mDataSet.size();
    }

    public class ViewHolderC extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public ImageView imageView;
        public ViewHolderC(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView=itemView.findViewById(R.id.icardimg);
        }
        @Override
        public void onClick(View view)
        {
            notifyItemChanged(selectedPos);
            selectedPos=getAdapterPosition();
            notifyItemChanged(selectedPos);
        }
    }*/
}

