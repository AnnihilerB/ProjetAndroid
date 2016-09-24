package com.homesoft.ali.aninterface;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/**
 * Created by ali on 23/09/16.
 */
public class Traitements {

    Bitmap bMap;
    ImageView iv;

    public Traitements(ImageView imageView){
        this.iv = imageView;
        iv.buildDrawingCache(true);

        BitmapDrawable drawable = (BitmapDrawable)iv.getDrawable();
        bMap = drawable.getBitmap();
    }

    public void niveauxGris(){

        Bitmap imageDest = bMap.copy(Bitmap.Config.ARGB_8888, true);

        int hauteur = bMap.getHeight();
        int largeur = bMap.getWidth();

        int couleurPixel;
        int couleurDest;

        double rouge;
        double vert;
        double bleu;

        for (int y = 0; y < hauteur; ++y){
            for (int x = 0; x < largeur; x++){
                couleurPixel = bMap.getPixel(x,y);

                rouge = 0.3 * Color.red(couleurPixel);
                vert = 0.69 * Color.green(couleurPixel);
                bleu = 0.11 * Color.blue(couleurPixel);

                couleurDest = Color.rgb((int)rouge, (int)vert, (int)bleu);
                imageDest.setPixel(x,y,couleurDest);
            }
        }
        this.iv.setImageBitmap(imageDest);
    }

}
