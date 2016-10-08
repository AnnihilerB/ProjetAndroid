package com.homesoft.ali.aninterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean imageEstAffichée = false;

    ImageView logo;
    TextView info;
    Traitements t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.logo = (ImageView)findViewById(R.id.imageView);
        logo.setVisibility(View.INVISIBLE);

        this.info = (TextView)findViewById(R.id.textView);

        Button boutonNvxGris = (Button)findViewById(R.id.niveaugris);
        Button boutonSepia = (Button)findViewById(R.id.sepia);
        Button boutonAfficherCacher = (Button)findViewById(R.id.afficherCacher);
        Button boutonReinit = (Button)findViewById(R.id.reinit);
        Button boutonColorize = (Button)findViewById(R.id.colorize);

        info.setText("Aucune image");

        boutonNvxGris.setOnClickListener(this);
        boutonSepia.setOnClickListener(this);
        boutonAfficherCacher.setOnClickListener(this);
        boutonReinit.setOnClickListener(this);
        boutonColorize.setOnClickListener(this);

        this.t = new Traitements(logo);
    }

    public void onClick(View view){
        if (view.getId() == R.id.niveaugris) {
            t.niveauxGris2();
            logo.setVisibility(View.INVISIBLE);
            logo.setVisibility(View.VISIBLE);
        }
        if (view.getId() == R.id.sepia) {
            t.sepia();
            logo.setVisibility(View.INVISIBLE);
            logo.setVisibility(View.VISIBLE);
        }
        if (view.getId() == R.id.afficherCacher)
            afficherCacher(logo, info);
        if (view.getId() == R.id.reinit)
            t.reinit();
        if (view.getId() == R.id.colorize) {
            t.colorize();
            logo.setVisibility(View.INVISIBLE);
            logo.setVisibility(View.VISIBLE);
        }
    }

    public void afficherCacher(ImageView logo, TextView info){
        if (imageEstAffichée == false) {
            logo.setVisibility(View.VISIBLE);
            imageEstAffichée = true;
        }
        else{
            logo.setVisibility(View.INVISIBLE);
            info.setText("Aucune image");
            imageEstAffichée = false;
        }

    }


}