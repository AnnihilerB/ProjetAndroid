package com.homesoft.ali.aninterface;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.ImageView;


/**
 * Created by Ali on 08/10/2016.
 */

public class Utils {

    static Resources res;
    static BitmapFactory.Options options;

    public Utils(Resources res){
        this.res = res;
        options = new BitmapFactory.Options();
    }

    public static int calculerLargeur(ImageView iv){
        BitmapFactory.decodeResource(res, iv.getId(),options);
        return options.outWidth;
    }

    public static int calculerHauteur(ImageView iv){
        BitmapFactory.decodeResource(res, iv.getId(),options);
        return options.outHeight;
    }

    public static int distance(int couleur, int couleur2){
        return (int)( Math.sqrt(Math.pow( (Color.red(couleur) - Color.red(couleur2)),2 ) + Math.pow( (Color.green(couleur) - Color.green(couleur2)),2 ) +  Math.pow( (Color.blue(couleur) - Color.blue(couleur2)),2 )) );
    }


}
