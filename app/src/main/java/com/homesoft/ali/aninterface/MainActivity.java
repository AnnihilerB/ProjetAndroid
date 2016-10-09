package com.homesoft.ali.aninterface;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    boolean imageEstAffichée = false;

    ImageView imageView;
    TextView info;
    Traitements t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bloquage de la rotation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //ImageView
        this.imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);

        //TextView
        this.info = (TextView)findViewById(R.id.textView);

        //Bouton
        Button boutonNvxGris = (Button)findViewById(R.id.niveaugris);
        Button boutonSepia = (Button)findViewById(R.id.sepia);
        Button boutonReinit = (Button)findViewById(R.id.reinit);
        Button boutonColorize = (Button)findViewById(R.id.colorize);
        Button boutonIsoler = (Button)findViewById(R.id.isoler);


        boutonNvxGris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.niveauxGris();
            }
        });
        boutonSepia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.sepia();
            }
        });
        boutonReinit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.reinit();
            }
        });
        boutonColorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.colorize();
            }
        });
        boutonIsoler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.isolerCouleur();
            }
        });

        //Instanciation du traitement.
        this.t = new Traitements(imageView);
    }
}