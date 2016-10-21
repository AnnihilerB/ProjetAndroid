package com.homesoft.ali.aninterface;

import android.graphics.Color;

/**
 * Created by ali on 14/10/16.
 */

public class LutRGB {

    /**
     * Cette clase contiendra un tableauPixRGB de pixel RGB de 256 cases.
     * Chaque case contiendra la valeur des pixels modifiés. l'index de la case est donc la valeur de référence.
     * Par exemple, la case 234 contiendra un PixelRGB qui lui même contient les valeurs à appliquer.
     */

    private PixelRGB[] tableauPixRGB;

    private static final int CONTRASTE = 255;

    private int minRouge;
    private int minVert;
    private int minBleu;
    private int maxRouge;
    private int maxVert;
    private int maxBleu;

    protected static final int NBCOULEURS = 256;

    public LutRGB(){
        this.tableauPixRGB = new PixelRGB[NBCOULEURS];
    }

    public void genererLUT(int tab[]){

        calculerDynamique(tab);

        for (int i = 0; i < this.tableauPixRGB.length; i++){
            PixelRGB pix = new PixelRGB();

            pix.setRouge(calculValeurLUT(i, minRouge, maxRouge));
            pix.setVert(calculValeurLUT(i, minVert, maxVert));
            pix.setBleu(calculValeurLUT(i, minBleu, maxBleu));

            this.tableauPixRGB[i] = pix;
        }
    }

    public int getValeurRouge(int index){
        return tableauPixRGB[index].getRouge();
    }
    public int getValeurVert(int index){
        return tableauPixRGB[index].getVert();
    }
    public int getValeurBleu(int index){
        return tableauPixRGB[index].getBleu();
    }

    /**
     * Calcul de la dynamique de l'image pour chaque composante RGB.
     */

    private void calculerDynamique(int tableau[]){

        minRouge = Color.red(tableau[0]);
        minVert = Color.green(tableau[0]);
        minBleu = Color.blue(tableau[0]);

        maxRouge = maxVert = maxBleu = 0;

        for (int i = 0; i < tableau.length; i++){
            minRouge = Math.min(minRouge, Color.red(tableau[i]));
            minVert = Math.min(minVert, Color.green(tableau[i]));
            minBleu = Math.min(minBleu, Color.blue(tableau[i]));

            maxRouge = Math.max(maxRouge, Color.red(tableau[i]));
            maxVert = Math.max(maxVert, Color.green(tableau[i]));
            maxBleu = Math.max(maxBleu, Color.blue(tableau[i]));
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


