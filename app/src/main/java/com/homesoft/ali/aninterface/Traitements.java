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

        for (int y = 0; y < hauteur; ++y){
            for (int x = 0; x < largeur; x++){
                couleurPixel = bitmapOriginal.getPixel(x,y);

                couleurDest = (int)( (0.299 * Color.red(couleurPixel)) + (0.587 * Color.green(couleurPixel)) + (0.114 * Color.blue(couleurPixel)) );

                imageDest.setPixel(x,y,Color.rgb(couleurDest, couleurDest, couleurDest));
            }
        }
        this.iv.setImageBitmap(imageDest);
    }

    public void sepia() {

        Bitmap imageDest = bitmapOriginal.copy(Bitmap.Config.ARGB_8888, true);

        int hauteur = bitmapOriginal.getHeight();
        int largeur = bitmapOriginal.getWidth();

        int couleurPixel;

        int rouge;
        int vert;
        int bleu;

        for (int y = 0; y < hauteur; ++y) {
            for (int x = 0; x < largeur; x++) {
                couleurPixel = bitmapOriginal.getPixel(x, y);

                rouge = (int) ((Color.red(couleurPixel) * .393) + (Color.green(couleurPixel) * .769) + (Color.blue(couleurPixel) * .189));
                vert = (int) ((Color.red(couleurPixel) * .349) + (Color.green(couleurPixel) * .686) + (Color.blue(couleurPixel) * .168));
                bleu = (int) ((Color.red(couleurPixel) * .272) + (Color.green(couleurPixel) * .534) + (Color.blue(couleurPixel) * .131));

                if (rouge > 255)
                    rouge = 255;
                if (vert > 255)
                    vert = 255;
                if (bleu > 255)
                    bleu = 255;

                imageDest.setPixel(x, y, Color.rgb(rouge, vert, bleu));

            }
            this.iv.setImageBitmap(imageDest);
        }
    }

    public void reinit(){
        iv.setImageBitmap(bitmapOriginal);
    }

}
