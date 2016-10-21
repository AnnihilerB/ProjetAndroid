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

    public void genererHistogramme(int tab[]) {
        for (int i = 0; i < tab.length; i++){
                histoR[Color.red(tab[i])] ++;
                histoG[Color.green(tab[i])] ++;
                histoB[Color.blue(tab[i])] ++;
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

    public void reinitHsito(){
        for (int i = 0; i < NBCOULEURS; i++){
            histoR[i] = 0;
            histoG[i] = 0;
            histoB[i] = 0;
        }
    }
}
