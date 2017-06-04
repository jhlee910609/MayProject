package com.junhee.android.mayproject.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunHee on 2017. 6. 4..
 */

public class Loader {


    public static List<Img_data> getImgData(Context context) {
        List<Img_data> datas = new ArrayList<>();

        for (int i = 1; i <= 13; i++) {
            Img_data data = new Img_data();
            data.setTitle("IU 앨범" + "[ " + i + " ]");
            data.setImage("iu_" + i, context);
            datas.add(data);

        }

        return datas;
    }
}
