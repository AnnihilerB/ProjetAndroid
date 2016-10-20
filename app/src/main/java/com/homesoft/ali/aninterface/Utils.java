package com.homesoft.ali.aninterface;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.ImageView;


/**
 * Created by Ali on 08/10/2016.
 */

public class Utils {

    private static Resources res;
    private static BitmapFactory.Options options;

    public Utils(Resources res){
        this.res = res;
        options = new BitmapFactory.Options();
    }

    public static int distance(int couleur, int couleur2){
        return (int)( Math.sqrt(Math.pow( (Color.red(couleur) - Color.red(couleur2)),2 ) + Math.pow( (Color.green(couleur) - Color.green(couleur2)),2 ) +  Math.pow( (Color.blue(couleur) - Color.blue(couleur2)),2 )) );
    }


}
