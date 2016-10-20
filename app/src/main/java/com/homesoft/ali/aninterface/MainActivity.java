package com.homesoft.ali.aninterface;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CAPTURE = 1;

    private ImageView imageView;
    private Traitements t;

    private Uri imageURI;
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bloquage de la rotation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //ImageView
        this.imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);

        //Boutons

        final FloatingActionsMenu menuActions = (FloatingActionsMenu)findViewById(R.id.actionsMenu);
        FloatingActionButton fabCamera = (FloatingActionButton)findViewById(R.id.fabCamera);
        FloatingActionButton fabGallerie = (FloatingActionButton)findViewById(R.id.fabGallerie);

        Button boutonNvxGris = (Button)findViewById(R.id.niveaugris);
        Button boutonSepia = (Button)findViewById(R.id.sepia);
        Button boutonReinit = (Button)findViewById(R.id.reinit);
        Button boutonColorize = (Button)findViewById(R.id.colorize);
        Button boutonIsoler = (Button)findViewById(R.id.isoler);
        Button boutonHisto = (Button)findViewById(R.id.histo);

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
        boutonHisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {t.egaHistoExtensionContrasteRGB();
            }
        });

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCamera()){
                    capturePhoto();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Aucun appareil photo disponible.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fabGallerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
                menuActions.collapse();
            }
        });


                //Instanciation du traitement.
        this.t = new Traitements(imageView);
    }

    public boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAPTURE && resultCode == Activity.RESULT_OK) {
            extraireImage();
        }
    }

    private File createTemporaryFIle(String name, String extension)throws Exception{
        File temp = Environment.getExternalStorageDirectory();
        temp = new File(temp.getAbsolutePath() +"/.temp/");
        if (!temp.exists()){
            temp.mkdirs();
        }

        return File.createTempFile(name, extension, temp);
    }

    private void capturePhoto(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try{
            photoFile = createTemporaryFIle("picture", "jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (photoFile != null){

        }
        imageURI = Uri.fromFile(photoFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);
        startActivityForResult(cameraIntent, REQUEST_CAPTURE);

    }

    private void extraireImage(){
        this.getContentResolver().notifyChange(imageURI, null);
        ContentResolver cr = this.getContentResolver();
        Bitmap bitmap;
        try
        {
            bitmap = MediaStore.Images.Media.getBitmap(cr, imageURI);
            t.MettreAJourTableauxPixels(bitmap);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
            Log.d("GRAB", "Failed to load", e);
        }

    }
}