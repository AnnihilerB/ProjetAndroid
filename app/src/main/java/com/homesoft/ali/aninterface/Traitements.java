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

        Bitmap imageDest = bitmapOriginal.copy(Bitmap.Config.RGB_565, true);

        int hauteur = bitmapOriginal.getHeight();
        int largeur = bitmapOriginal.getWidth();

        int couleurPixel;
        int couleurDest;

        double rouge;
        double vert;
        double bleu;

        for (int y = 0; y < hauteur; ++y){
            for (int x = 0; x < largeur; x++){
                couleurPixel = bitmapOriginal.getPixel(x,y);

                rouge = 0.3 * Color.red(couleurPixel);

                System.out.println((int)rouge);

                vert = 0.69 * Color.green(couleurPixel);
                bleu = 0.11 * Color.blue(couleurPixel);

                couleurDest = Color.rgb((int) rouge, (int)vert, (int)bleu);
                imageDest.setPixel(x,y,couleurDest);
            }
        }
        this.iv.setImageBitmap(imageDest);
    }

    public void reinit(){
        iv.setImageBitmap(bitmapOriginal);
    }

}
