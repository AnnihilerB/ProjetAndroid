package com.homesoft.ali.aninterface;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/**
 * Created by ali on 23/09/16.
 */
public class Traitements {

    private Bitmap bitmapOriginal;
    private Bitmap bitmapTraite;
    private ImageView iv;
    private int largeur;
    private int hauteur;

    public Traitements(ImageView imageView){
        this.iv = imageView;

        BitmapDrawable drawable = (BitmapDrawable)iv.getDrawable();
        bitmapOriginal = drawable.getBitmap();

        bitmapTraite = bitmapOriginal.copy(Bitmap.Config.ARGB_8888, true);

        hauteur = bitmapOriginal.getHeight();
        largeur = bitmapOriginal.getWidth();
    }

    public void niveauxGris(){

        int couleurPixel;
        int couleurDest;

        for (int y = 0; y < hauteur; ++y){
            for (int x = 0; x < largeur; x++){
                couleurPixel = bitmapOriginal.getPixel(x,y);

                couleurDest = (int)( (0.299 * Color.red(couleurPixel)) + (0.587 * Color.green(couleurPixel)) + (0.114 * Color.blue(couleurPixel)) );

                bitmapTraite.setPixel(x,y,Color.rgb(couleurDest, couleurDest, couleurDest));
            }
        }
        this.iv.setImageBitmap(bitmapTraite);
    }

    public void niveauxGris2(){

        int pixelSource[] = new int[largeur * hauteur];

        bitmapOriginal.getPixels(pixelSource, 0, largeur, 0, 0, largeur, hauteur);

        for (int i = 0; i < pixelSource.length; ++i ){
            int couleurGris = (int)( (0.299 * Color.red(pixelSource[i])) + (0.587 * Color.green(pixelSource[i])) + (0.114 * Color.blue(pixelSource[i])) );
            pixelSource[i] = Color.rgb(couleurGris, couleurGris, couleurGris);
        }
        bitmapTraite.setPixels(pixelSource, 0, largeur, 0, 0, largeur,hauteur);
        this.iv.setImageBitmap(bitmapTraite);
    }

    public void sepia() {

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

                bitmapTraite.setPixel(x, y, Color.rgb(rouge, vert, bleu));

            }
        }
        this.iv.setImageBitmap(bitmapTraite);
    }

    public void reinit(){
        iv.setImageBitmap(bitmapOriginal);
    }

}
