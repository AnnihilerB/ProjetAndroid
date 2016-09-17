package com.homesoft.ali.aninterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean imageEstAffichéeI = false;

    ImageView logo;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.logo = (ImageView)findViewById(R.id.imageView);
        logo.setVisibility(View.INVISIBLE);

        this.info = (TextView)findViewById(R.id.textView);

        Button action1 = (Button)findViewById(R.id.action1);
        Button action2 = (Button)findViewById(R.id.action2);
        Button afficherCacher = (Button)findViewById(R.id.afficherCacher);

        info.setText("Aucune image");

        action1.setOnClickListener((View.OnClickListener)this);
        action2.setOnClickListener(this);
        afficherCacher.setOnClickListener(this);
    }

    public void onClick(View view){
        if (view.getId() == R.id.action1)
            Toast.makeText(this, "Action 1", Toast.LENGTH_SHORT).show();
        if (view.getId() == R.id.action2)
            Toast.makeText(this, "Action 2", Toast.LENGTH_SHORT).show();
        if (view.getId() == R.id.afficherCacher)
            afficherCacher(logo, info);
    }

    public void afficherCacher(ImageView logo, TextView info){
        if (imageEstAffichéeI == false) {
            logo.setVisibility(View.VISIBLE);
            info.setText("Largeur : " + logo.getDrawable().getIntrinsicWidth() + " hauteur : " +logo.getDrawable().getIntrinsicHeight() );
            imageEstAffichéeI = true;
        }
        else{
            logo.setVisibility(View.INVISIBLE);
            info.setText("Aucune image");
            imageEstAffichéeI = false;
        }

    }


}