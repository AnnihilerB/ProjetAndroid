package com.homesoft.ali.aninterface;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.homesoft.ali.aninterface.histogramme.Histogramme;
import com.homesoft.ali.aninterface.histogramme.HistogrammeCumule;

/**
 * Created by ali on 23/09/16.
 */
public class Traitements {

    private final String NIVEAUXGRIS = "NiveauxGris";
    private final String SEPIA = "Sepia";
    private final String COLORIZE = "Colorize";
    private final String ISOLERCOULEUR = "Isoler Couleur";
    private final String EXTCONTRASTERGB = "Extension contraste lin";
    private final String EXTCONTRASTEEGARGB = "Extension Contraste ega";
    private final String SUREXPO = "Surexposition";

    private Bitmap bitmapOriginal;
    private Bitmap bitmapTraite;

    private int tabPixelsOriginal[];
    private int tabPixelsTraitement[];

    private ImageView iv;

    private int largeur;
    private int hauteur;

    LutRGB lut;
    HistogrammeCumule histo;
    StockageTraitements stock;

    public Traitements(ImageView imageView){
        this.iv = imageView;

        BitmapDrawable drawable = (BitmapDrawable)iv.getDrawable();
        bitmapOriginal = drawable.getBitmap();

        hauteur = bitmapOriginal.getHeight();
        largeur = bitmapOriginal.getWidth();

        bitmapTraite = Bitmap.createBitmap(largeur, hauteur, Bitmap.Config.ARGB_8888);

        tabPixelsOriginal = new int[largeur * hauteur];
        tabPixelsTraitement = new int[largeur * hauteur];

        bitmapOriginal.getPixels(tabPixelsOriginal, 0, largeur, 0, 0, largeur, hauteur);

        lut = new LutRGB();
        lut.genererLUT(bitmapOriginal);

        histo = new HistogrammeCumule();
        histo.genererHistogramme(bitmapOriginal);

        stock = new StockageTraitements();
    }

    public void niveauxGris(){

        if (stock.getTraitement(NIVEAUXGRIS) != null){
            bitmapTraite.setPixels(stock.getTraitement(NIVEAUXGRIS), 0, largeur, 0, 0, largeur,hauteur);
            iv.setImageBitmap(bitmapTraite);
        }
        else {

            for (int i = 0; i < tabPixelsOriginal.length; ++i) {
                int couleurGris = (int) ((0.299 * Color.red(tabPixelsOriginal[i])) + (0.587 * Color.green(tabPixelsOriginal[i])) + (0.114 * Color.blue(tabPixelsOriginal[i])));
                tabPixelsTraitement[i] = Color.rgb(couleurGris, couleurGris, couleurGris);
            }
            bitmapTraite.setPixels(tabPixelsTraitement, 0, largeur, 0, 0, largeur, hauteur);
            stock.ajouterTraitement(NIVEAUXGRIS,tabPixelsTraitement.clone());
            this.iv.setImageBitmap(bitmapTraite);
        }
    }

    public void sepia() {

        if (stock.getTraitement(SEPIA) != null){
            bitmapTraite.setPixels(stock.getTraitement(SEPIA), 0, largeur, 0, 0, largeur,hauteur);
            iv.setImageBitmap(bitmapTraite);
        }

        else {

            int rouge;
            int vert;
            int bleu;

            for (int i = 0; i < tabPixelsOriginal.length; ++i) {

                rouge = (int) ((Color.red(tabPixelsOriginal[i]) * .393) + (Color.green(tabPixelsOriginal[i]) * .769) + (Color.blue(tabPixelsOriginal[i]) * .189));
                vert = (int) ((Color.red(tabPixelsOriginal[i]) * .349) + (Color.green(tabPixelsOriginal[i]) * .686) + (Color.blue(tabPixelsOriginal[i]) * .168));
                bleu = (int) ((Color.red(tabPixelsOriginal[i]) * .272) + (Color.green(tabPixelsOriginal[i]) * .534) + (Color.blue(tabPixelsOriginal[i]) * .131));

                if (rouge > 255)
                    rouge = 255;
                if (vert > 255)
                    vert = 255;
                if (bleu > 255)
                    bleu = 255;

                tabPixelsTraitement[i] = Color.rgb(rouge, vert, bleu);
            }
            bitmapTraite.setPixels(tabPixelsTraitement, 0, largeur, 0, 0, largeur, hauteur);
            stock.ajouterTraitement(SEPIA, tabPixelsTraitement.clone());
            this.iv.setImageBitmap(bitmapTraite);
        }
    }

    public void colorize(){

        if (stock.getTraitement(COLORIZE) != null){
            bitmapTraite.setPixels(stock.getTraitement(COLORIZE), 0, largeur, 0, 0, largeur,hauteur);
            iv.setImageBitmap(bitmapTraite);
        }
        else {

            float hsv[] = new float[3];

            for (int i = 0; i < tabPixelsOriginal.length; ++i) {

                Color.colorToHSV(tabPixelsOriginal[i], hsv);
                hsv[0] = (float) 120;
                tabPixelsTraitement[i] = Color.HSVToColor(hsv);
            }
            bitmapTraite.setPixels(tabPixelsTraitement, 0, largeur, 0, 0, largeur, hauteur);
            stock.ajouterTraitement(COLORIZE, tabPixelsTraitement.clone());
            iv.setImageBitmap(bitmapTraite);
        }
    }

    public void reinit(){
        iv.setImageBitmap(bitmapOriginal);
    }

    public void isolerCouleur(){

        if (stock.getTraitement(ISOLERCOULEUR) != null){
            bitmapTraite.setPixels(stock.getTraitement(ISOLERCOULEUR), 0, largeur, 0, 0, largeur,hauteur);
            iv.setImageBitmap(bitmapTraite);
        }
        else {

            int couleurRef = Color.RED;
            int distanceMax = 200;

            for (int i = 0; i < tabPixelsOriginal.length; ++i) {
                if (Utils.distance(couleurRef, tabPixelsOriginal[i]) >= distanceMax)
                    tabPixelsTraitement[i] = couleurVersNG(tabPixelsOriginal[i]);
                else {
                    tabPixelsTraitement[i] = tabPixelsOriginal[i]; // REcopie des valeurs des pixels de base.
                }
            }

            bitmapTraite.setPixels(tabPixelsTraitement, 0, largeur, 0, 0, largeur,hauteur);
            stock.ajouterTraitement(ISOLERCOULEUR,tabPixelsTraitement.clone());
            this.iv.setImageBitmap(bitmapTraite);
        }

    }

    private int couleurVersNG(int couleur){
        int r = Color.red(couleur);
        int g = Color.green(couleur);
        int b = Color.blue(couleur);

        int niveauGris = (int) (0.3*r + 0.59 *g +0.11*b);
        int cM = Color.rgb(niveauGris, niveauGris, niveauGris);

        return cM;
    }

    public void extensionContrasteRGB(){

        if (stock.getTraitement(EXTCONTRASTERGB) != null){
            bitmapTraite.setPixels(stock.getTraitement(EXTCONTRASTERGB), 0, largeur, 0, 0, largeur,hauteur);
            iv.setImageBitmap(bitmapTraite);
        }
        else {

            int couleur;

            for (int i = 0; i < tabPixelsOriginal.length; ++i) {

                // Récupération dans la table de la valeur modifiée corespondante à la couleur de base.

                couleur = Color.rgb(lut.getValeurRouge(Color.red(tabPixelsOriginal[i])), lut.getValeurVert(Color.green(tabPixelsOriginal[i])), lut.getValeurBleu(Color.blue(tabPixelsOriginal[i])));
                tabPixelsTraitement[i] = couleur;

            }
            bitmapTraite.setPixels(tabPixelsTraitement, 0, largeur, 0, 0, largeur, hauteur);
            stock.ajouterTraitement(EXTCONTRASTERGB, tabPixelsTraitement.clone());
            iv.setImageBitmap(bitmapTraite);
        }

    }

    public void egaHistoExtensionContrasteRGB(){

        if (stock.getTraitement(EXTCONTRASTEEGARGB) != null){
            bitmapTraite.setPixels(stock.getTraitement(EXTCONTRASTEEGARGB), 0, largeur, 0, 0, largeur,hauteur);
            iv.setImageBitmap(bitmapTraite);
        }
        else {


            int couleurR;
            int couleurG;
            int couleurB;

            int nbpixels = histo.getNbpixels();

            for (int i = 0; i < tabPixelsOriginal.length; i++) {

                couleurR = (histo.getNombreUtilisationCouleurR(Color.red(tabPixelsOriginal[i]))) * 255 / nbpixels;
                couleurG = (histo.getNombreUtilisationCouleurG(Color.green(tabPixelsOriginal[i]))) * 255 / nbpixels;
                couleurB = (histo.getNombreUtilisationCouleurB(Color.blue(tabPixelsOriginal[i]))) * 255 / nbpixels;

                tabPixelsTraitement[i] = Color.rgb(couleurR, couleurG, couleurB);

            }
            bitmapTraite.setPixels(tabPixelsTraitement, 0, largeur, 0, 0, largeur, hauteur);
            stock.ajouterTraitement(EXTCONTRASTEEGARGB, tabPixelsTraitement.clone());
            iv.setImageBitmap(bitmapTraite);
        }

    }

    public void surexposition(){

        if (stock.getTraitement(SUREXPO) != null){
            bitmapTraite.setPixels(stock.getTraitement(SUREXPO), 0, largeur, 0, 0, largeur,hauteur);
            iv.setImageBitmap(bitmapTraite);
        }

        else {


            int CONSTANTE = 20;

            int rouge;
            int vert;
            int bleu;

            for (int i = 0; i < tabPixelsOriginal.length; i++) {

                rouge = Color.red(tabPixelsOriginal[i]) + CONSTANTE;
                vert = Color.green(tabPixelsOriginal[i]) + CONSTANTE;
                bleu = Color.blue(tabPixelsOriginal[i]) + CONSTANTE;

                if (rouge > 255)
                    rouge = 255;
                if (vert > 255)
                    vert = 255;
                if (bleu > 255)
                    bleu = 255;

                tabPixelsTraitement[i] = Color.rgb(rouge, vert, bleu);

            }
            bitmapTraite.setPixels(tabPixelsTraitement, 0, largeur, 0, 0, largeur, hauteur);
            stock.ajouterTraitement(SUREXPO,tabPixelsTraitement.clone());
            iv.setImageBitmap(bitmapTraite);
        }
    }

    /**
     * Cette méthode mets à jour les tableaux de pixels et les bitmap à la prise d'une photo.
     * @param bitmap récupéré lors de la prise de la photo
     */
    public void MettreAJourTableauxPixels(Bitmap bitmap){

        //Déréférencement pour libérer de la mémoire
        tabPixelsOriginal = null;
        tabPixelsTraitement = null;
        bitmapTraite.recycle();
        stock.viderTraitements();

        //Création du nouveau bitmap et recalcul des nouvelles largeur et hauteur.
        //Le bitmap capturé est rescalé pour ne pas causer de dépassemenr de mémoire.
        bitmapOriginal = Bitmap.createScaledBitmap(bitmap,1500, 1500, false);

        this.hauteur = bitmapOriginal.getHeight();
        this.largeur = bitmapOriginal.getWidth();

        tabPixelsOriginal = new int[largeur * hauteur];
        tabPixelsTraitement = new int[largeur * hauteur];

        bitmapTraite = Bitmap.createBitmap(largeur, hauteur, Bitmap.Config.ARGB_8888);

        bitmapOriginal.getPixels(tabPixelsOriginal, 0, largeur, 0, 0, largeur, hauteur);
        lut.genererLUT(bitmapOriginal);
        iv.setImageBitmap(bitmapOriginal);
    }
}
