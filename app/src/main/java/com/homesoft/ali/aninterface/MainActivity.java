package com.homesoft.ali.aninterface;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean imageEstAffichée = false;

    ImageView logo;
    TextView info;
    Traitements t;
    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metrics = getResources().getDisplayMetrics();

        this.logo = (ImageView)findViewById(R.id.imageView);
        logo.setVisibility(View.INVISIBLE);

        this.info = (TextView)findViewById(R.id.textView);

        Button action1 = (Button)findViewById(R.id.niveaugris);
        Button action2 = (Button)findViewById(R.id.sepia);
        Button afficherCacher = (Button)findViewById(R.id.afficherCacher);

        info.setText("Aucune image");

        action1.setOnClickListener(this);
        action2.setOnClickListener(this);
        afficherCacher.setOnClickListener(this);

        this.t = new Traitements(logo);
    }

    public void onClick(View view){
        if (view.getId() == R.id.niveaugris) {
            t.niveauxGris();
            logo.setVisibility(View.INVISIBLE);
            logo.setVisibility(View.VISIBLE);
        }
        if (view.getId() == R.id.sepia)
            Toast.makeText(this, "Action 2", Toast.LENGTH_SHORT).show();
        if (view.getId() == R.id.afficherCacher)
            afficherCacher(logo, info);
    }

    public void afficherCacher(ImageView logo, TextView info){
        if (imageEstAffichée == false) {
            logo.setVisibility(View.VISIBLE);
            info.setText("Largeur : " + logo.getDrawable().getIntrinsicWidth() + " hauteur : " +logo.getDrawable().getIntrinsicHeight() );
            imageEstAffichée = true;
            System.out.println(metrics);
        }
        else{
            logo.setVisibility(View.INVISIBLE);
            info.setText("Aucune image");
            imageEstAffichée = false;
        }

    }


}