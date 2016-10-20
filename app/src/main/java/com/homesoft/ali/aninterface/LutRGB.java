package com.homesoft.ali.aninterface;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by ali on 14/10/16.
 */

public class LutRGB {

    /**
     * Cette clase contiendra un tableau de pixel RGB de 256 cases.
     * Chaque case contiendra la valeur des pixels modifiés. l'index de la case est donc la valeur de référence.
     * Par exemple, la case 234 contiendra un PixelRGB qui lui même contient les valeurs à appliquer.
     */

    PixelRGB[] tabPixels;

    private static final int CONTRASTE = 255;

    private int minRouge;
    private int minVert;
    private int minBleu;
    private int maxRouge;
    private int maxVert;
    private int maxBleu;

    protected static final int NBCOULEURS = 256;

    public void genererLUT(Bitmap bitmap){

        calculerDynamique(bitmap);

        tabPixels = new PixelRGB[NBCOULEURS];

        Log.i("MIN", String.valueOf(minRouge));
        Log.i("MIN", String.valueOf(minVert));
        Log.i("MIN", String.valueOf(minBleu));
        Log.i("MAX", String.valueOf(maxRouge));
        Log.i("MAX", String.valueOf(maxVert));
        Log.i("MAX", String.valueOf(maxBleu));

        for (int i = 0; i < tabPixels.length; i++){
            PixelRGB pix = new PixelRGB();

            pix.setRouge(calculValeurLUT(i, minRouge, maxRouge));
            pix.setVert(calculValeurLUT(i, minVert, maxVert));
            pix.setBleu(calculValeurLUT(i, minBleu, maxBleu));

            tabPixels[i] = pix;

        }
    }

    public int getValeurRouge(int index){
        return tabPixels[index].getRouge();
    }
    public int getValeurVert(int index){
        return tabPixels[index].getVert();
    }
    public int getValeurBleu(int index){
        return tabPixels[index].getBleu();
    }

    /**
     * Calcul de la dynamique de l'image pour chaque composante RGB.
     * @param bitmap image à analyser
     */

    private void calculerDynamique(Bitmap bitmap){

        int tabPixels[] = new int[bitmap.getHeight() * bitmap.getWidth()];

        bitmap.getPixels(tabPixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        minRouge = Color.red(tabPixels[0]);
        minVert = Color.green(tabPixels[0]);
        minBleu = Color.blue(tabPixels[0]);

        maxRouge = maxVert = maxBleu = 0;

        for (int i = 0; i < tabPixels.length; i++){
            minRouge = Math.min(minRouge, Color.red(tabPixels[i]));
            minVert = Math.min(minVert, Color.green(tabPixels[i]));
            minBleu = Math.min(minBleu, Color.blue(tabPixels[i]));

            maxRouge = Math.max(maxRouge, Color.red(tabPixels[i]));
            maxVert = Math.max(maxVert, Color.green(tabPixels[i]));
            maxBleu = Math.max(maxBleu, Color.blue(tabPixels[i]));
        }
    }

    /**
     * Cette méthode permet de générer une LUT servant à modifier le contraste de l'image.
     * La constante contraste permet de l'augmenter ou de le réduire.
     * Si elle est égale à 255, le contraste augmente. Sinon le contraste diminue.
     * @param val valeur de référence du pixel
     * @param min minimum de la dynamique de la composante RGB ou niveau de gris
     * @param max maximum de la dynanmique de la composante RGB ou niveau de gris
     * @return
     */
    private int calculValeurLUT(int val, int min, int max){
        return ( CONTRASTE * (val - min) ) / (max - min);
    }

}


