package com.junhee.android.mayproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhee.android.mayproject.domain.Img_data;

import java.util.List;

/**
 * Created by JunHee on 2017. 6. 4..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    List<Img_data> datas;

    public RecyclerAdapter(List<Img_data> datas) {
        this.datas = datas;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Img_data data = datas.get(position);
        holder.title.setText(data.getTitle());
        holder.image.setImageResource(data.getResId());


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;


        public Holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_title);
            image = (ImageView) itemView.findViewById(R.id.list_image);
        }
    }
}