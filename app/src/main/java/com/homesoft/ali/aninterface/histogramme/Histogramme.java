package com.homesoft.ali.aninterface.histogramme;

import android.graphics.Bitmap;

/**
 * Created by ali on 17/10/16.
 */

public interface Histogramme {
    Histogramme getHistogramme();
    void genererHistogramme(Bitmap bitmap);
    int getNombreUtilisationCouleur(int index);
}
