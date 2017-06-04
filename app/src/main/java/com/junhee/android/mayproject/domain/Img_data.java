package com.junhee.android.mayproject.domain;

import android.content.Context;

/**
 * Created by JunHee on 2017. 6. 4..
 */

public class Img_data {
    String title;
    String imageName;
    int resId;

    public void setImage(String imageName, Context context){
        this.imageName = imageName;
        resId = context.getResources().getIdentifier(imageName, "mipmap", context.getPackageName());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
