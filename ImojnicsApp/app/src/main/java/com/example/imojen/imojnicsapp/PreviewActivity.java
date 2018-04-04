package com.example.imojen.imojnicsapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;


public class PreviewActivity extends AppCompatActivity  implements View.OnClickListener {

    String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ImageView back=(ImageView)findViewById(R.id.backonpreview);
        View crossBtn=(View) findViewById(R.id.crossbutton);
        View wrightBtn=(View) findViewById(R.id.rightbutton);
        back.setOnClickListener(this);
        crossBtn.setOnClickListener(this);
        wrightBtn.setOnClickListener(this);


        if(getIntent().hasExtra("imagePath")) {
            String firstvalue = getIntent().getStringExtra("imagePath");
            imagePath=firstvalue;
            File imgFile = new  File(firstvalue);
//            Uri myUri = Uri.parse(firstvalue);
            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                ImageView myImage = (ImageView) findViewById(R.id.previewimageview);

                myImage.setImageBitmap(myBitmap);
//                myBitmap.recycle();
//                myBitmap=null;

            }
//            ImageView my_img_view = (ImageView ) findViewById (R.id.previewimageview);
//            my_img_view.setImageBitmap(bitmap);
        }


    }

    private File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name

        File mediaFile;
        String mImageName="Myimage.jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.backonpreview:
                finish();
                break;
            case R.id.crossbutton:
                finish();
                break;
            case R.id.rightbutton:
                Intent i = new Intent(this,CustomizeActivity.class);
                i.putExtra("imagePath", imagePath);
                startActivity(i);
                break;
        }
    }
}
