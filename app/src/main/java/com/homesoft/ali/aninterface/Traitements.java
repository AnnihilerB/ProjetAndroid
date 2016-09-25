package com.homesoft.ali.aninterface;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/**
 * Created by ali on 23/09/16.
 */
public class Traitements {

    Bitmap bitmapOriginal;
    ImageView iv;

    public Traitements(ImageView imageView){
        this.iv = imageView;
        iv.buildDrawingCache(true);

        BitmapDrawable drawable = (BitmapDrawable)iv.getDrawable();
        bitmapOriginal = drawable.getBitmap();
    }

    public void niveauxGris(){

        Bitmap imageDest = bitmapOriginal.copy(Bitmap.Config.ARGB_8888, true);

        int hauteur = bitmapOriginal.getHeight();
        int largeur = bitmapOriginal.getWidth();

        int couleurPixel;
        int couleurDest;

        int rouge;
        int vert;
        int bleu;

        for (int y = 0; y < hauteur; ++y){
            for (int x = 0; x < largeur; x++){
                couleurPixel = bitmapOriginal.getPixel(x,y);

                couleurDest = (int)( (0.299 * Color.red(couleurPixel)) + (0.587 * Color.green(couleurPixel)) + (0.114 * Color.blue(couleurPixel)) );

                rouge = vert = bleu = couleurDest;
                couleurDest = Color.rgb(rouge, vert, bleu);
                imageDest.setPixel(x,y,couleurDest);
            }
        }
        this.iv.setImageBitmap(imageDest);
    }

    public void reinit(){
        iv.setImageBitmap(bitmapOriginal);
    }

}
