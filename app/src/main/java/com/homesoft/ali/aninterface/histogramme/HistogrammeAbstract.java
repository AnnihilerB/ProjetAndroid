package com.homesoft.ali.aninterface.histogramme;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by ali on 11/10/16.
 */

public abstract class HistogrammeAbstract implements Histogramme {

    // Couleur vont de 0 à 255
    protected static final int NBCOULEURS = 256;
    public abstract void genererHistogramme(Bitmap bitmap);
}
