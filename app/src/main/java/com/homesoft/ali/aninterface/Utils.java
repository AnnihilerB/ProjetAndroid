package com.homesoft.ali.aninterface;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.widget.ImageView;


/**
 * Created by Ali on 08/10/2016.
 */

public class Utils {

    Resources res;
    BitmapFactory.Options options;

    public Utils(Resources res){
        this.res = res;
        options = new BitmapFactory.Options();
    }

    private int calculerLargeur(ImageView iv){
        BitmapFactory.decodeResource(res, iv.getId(),options);
        return options.outWidth;
    }

    private int calculerHauteur(ImageView iv){
        BitmapFactory.decodeResource(res, iv.getId(),options);
        return options.outHeight;
    }
}
