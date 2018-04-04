package com.example.imojen.imojnicsapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by abdurrahim on 2/17/18.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>
{
    private ArrayList<Integer> mDataSet;
    private int Tag;
    private int selectedPos = RecyclerView.NO_POSITION;

    public MainAdapter(ArrayList<Integer> mDataSet,int tag) {
        this.mDataSet = mDataSet;
        this.Tag=tag;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(Tag==1)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyle_layout,parent,false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_layout2,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    //set image on the view
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
    holder.imageVew.setImageResource(mDataSet.get(position));
    holder.itemView.setSelected(selectedPos == position);
        if(Tag==1) {
            if (selectedPos == position) {
                holder.itemView.setBackgroundColor(Color.RED);
            }
            else {

                holder.itemView.setBackgroundColor(Color.parseColor("#00000000"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageVew;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageVew = itemView.findViewById(R.id.icardimg);
        }
        @Override
        public void onClick(View view) {
            notifyItemChanged(selectedPos);
//            selectedPos = getLayoutPosition();
            selectedPos = getAdapterPosition();
            notifyItemChanged(selectedPos);
        }
    }




}
