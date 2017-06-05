package com.junhee.android.mayproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junhee.android.mayproject.domain.Img_data;
import com.junhee.android.mayproject.domain.Loader;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFrag extends Fragment {

    RecyclerView recycler;
    List<Img_data> datas;

    public GalleryFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);

        // 1. 데이터 꺼냄
        datas = Loader.getImgData(view.getContext());

        // 2.
        RecyclerAdapter adapter = new RecyclerAdapter(datas);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(view.getContext(), 3));

        return view;
    }


}
