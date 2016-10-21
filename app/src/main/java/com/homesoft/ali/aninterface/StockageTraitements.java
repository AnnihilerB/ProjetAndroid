package com.homesoft.ali.aninterface;

import java.util.HashMap;

/**
 * Created by ali on 21/10/16.
 */

public class StockageTraitements {

    private HashMap<String, int[]> traitements;

    public  StockageTraitements(){
        traitements = new HashMap<>();
    }

    public void ajouterTraitement(String str, int tabPixels[]){
        traitements.put(str, tabPixels);
    }

    public int[] getTraitement(String str){
        return traitements.get(str);
    }

    public void viderTraitements(){
        traitements.clear();
    }



}
