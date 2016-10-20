package com.homesoft.ali.aninterface.histogramme;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by ali on 17/10/16.
 */

public class HistogrammeCumule extends HistogrammeAbstract  {

    private int histoR[];
    private int histoG[];
    private int histoB[];

    private int nbpixels;

    public HistogrammeCumule(){
        histoR = new int[NBCOULEURS];
        histoG = new int[NBCOULEURS];
        histoB = new int[NBCOULEURS];
    }

    public void genererHistogramme(Bitmap bitmap) {
        int tabPixels[] = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(tabPixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight() );
        for (int i = 0; i < tabPixels.length; i++){
                histoR[Color.red(tabPixels[i])] ++;
                histoG[Color.green(tabPixels[i])] ++;
                histoB[Color.blue(tabPixels[i])] ++;
        }
        int cumulR = 0;
        int cumulG = 0;
        int cumulB = 0;
        for (int i =0; i < NBCOULEURS; i++){

            cumulR = histoR[i] + cumulR;
            cumulG = histoG[i] + cumulG;
            cumulB = histoB[i] + cumulB;

            histoR[i] = cumulR;
            histoG[i] = cumulG;
            histoB[i] = cumulB;
        }
        nbpixels = histoR[255];
    }
    public int getNombreUtilisationCouleurR(int index) {
        return histoR[index];
    }
    public int getNombreUtilisationCouleurG(int index) {
        return histoG[index];
    }
    public int getNombreUtilisationCouleurB(int index) {
        return histoB[index];
    }

    public int getNbpixels() {
        return nbpixels;
    }
}
