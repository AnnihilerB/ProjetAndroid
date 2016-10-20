package com.homesoft.ali.aninterface.histogramme;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by ali on 10/10/16.
 */

public class HistogrammeRGB extends HistogrammeAbstract implements  Histogramme {

    private int histogrammeRouge[];
    private int histogrammeVert[];
    private int histogrammeBleu[];

    public HistogrammeRGB(){
        histogrammeRouge = new int[NBCOULEURS];
        histogrammeVert = new int[NBCOULEURS];
        histogrammeBleu = new int[NBCOULEURS];
    }

    public HistogrammeRGB getHistogramme() {
        return null;
    }

    public void genererHistogramme(Bitmap bitmap) {
        int hauteur = bitmap.getHeight();
        int largeur = bitmap.getWidth();

        int tabPix[] = new int[largeur * hauteur];
        bitmap.getPixels(tabPix, 0, largeur, 0, 0, largeur, hauteur);

        for (int i = 0; i < tabPix.length; i++){
            histogrammeRouge[Color.red(tabPix[i])] ++;
            histogrammeVert[Color.green(tabPix[i])] ++;
            histogrammeBleu[Color.blue(tabPix[i])] ++;
        }
    }
}
