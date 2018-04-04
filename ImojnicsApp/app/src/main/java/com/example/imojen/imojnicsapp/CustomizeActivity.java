package com.example.imojen.imojnicsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class CustomizeActivity extends AppCompatActivity  implements View.OnClickListener {
    private RecyclerView mRecyclerView1;
    private RecyclerView.LayoutManager mRecylerLayout1;
    private RecyclerView.Adapter mRecyclerViewAdapter1;
    private RecyclerView mRecyclerView2;
    private RecyclerView.LayoutManager mRecylerLayout2;
    private RecyclerView.Adapter mRecyclerViewAdapter2;

    private ArrayList<Integer> mdataset1;
    private ArrayList<Integer> mdataset2;

    public  static  final  int My_Request_custom_Camera=110;
    public  static  final  int My_Request_custom_Write_Camera=111;
    public  static  final  int My_Request_Capture_Camera=112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        ImageView back = (ImageView)findViewById(R.id.backonpreview);
        ImageView doneImage=(ImageView)findViewById(R.id.toolbardoneimage);
        TextView doneText=(TextView)findViewById(R.id.donebtnntext);
        ImageView cameraImage=(ImageView)findViewById(R.id.camera_on_preview);
        back.setOnClickListener(this);
        doneImage.setOnClickListener(this);
        doneText.setOnClickListener(this);
        cameraImage.setOnClickListener(this);


        mdataset1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mdataset1.add(R.mipmap.stylebottom);
        }
        mdataset2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mdataset2.add(R.mipmap.styletop);
        }


        mRecyclerView1 = findViewById(R.id.recyclerttoplist);
        mRecyclerView1.setHasFixedSize(true);
        mRecylerLayout1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView1.setLayoutManager(mRecylerLayout1);
        mRecyclerViewAdapter1 = new MainAdapter(mdataset1, 1);
        mRecyclerView1.setAdapter(mRecyclerViewAdapter1);

        mRecyclerView2 = findViewById(R.id.recyclerbottomlist);
        mRecyclerView2.setHasFixedSize(true);
        mRecylerLayout2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView2.setLayoutManager(mRecylerLayout2);
        mRecyclerViewAdapter2 = new MainAdapter(mdataset2, 2);
        mRecyclerView2.setAdapter(mRecyclerViewAdapter2);


        if (getIntent().hasExtra("imagePath")) {
            String firstvalue = getIntent().getStringExtra("imagePath");
//            imagePath=firstvalue;
            File imgFile = new File(firstvalue);
//            Uri myUri = Uri.parse(firstvalue);
            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                ImageView myImage = (ImageView) findViewById(R.id.selectedimage);

                myImage.setImageBitmap(myBitmap);

            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.backonpreview:
                finish();
                break;
            case R.id.toolbardoneimage: {
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("slection", "1");
                startActivity(i);
            }
                break;
            case R.id.donebtnntext: {
                Intent it = new Intent(this, MainActivity.class);
                it.putExtra("slection", "1");
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
                finish();
            }
                break;
            case R.id.camera_on_preview: {

                openCamera();
            }
                break;
        }
    }

    private void openCamera()
    {
        checkPermissionCW();
        Log.e("em","Camera clicked");

    }
    public  void  checkPermissionCW()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},My_Request_custom_Write_Camera);
        }
        else
        {
            checkPermissionCA();
        }
    }
    public  void  checkPermissionCA()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},My_Request_Capture_Camera);
        }
        else
        {
            catchPhoto();
        }
    }
    public void onRequestPermissionsResult(int requestcode,String[] permissions,int[] grantResults)
    {
        switch (requestcode)
        {
            case My_Request_Capture_Camera:
                catchPhoto();
                break;
            case My_Request_custom_Write_Camera:
                checkPermissionCA();;
                break;

        }
    }

    public void catchPhoto()
    {
        Intent cameraIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, My_Request_custom_Camera);


//        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        fileuri= Uri.fromFile(nfile);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileuri);
//        startActivityForResult(intent, My_Request_Capture_Camera);


//        nfile = getAFile();
//        if(nfile != null)
//        {
//            Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            try {
//                Uri uri= Uri.fromFile(nfile);//nfile
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
//                startActivityForResult(intent,My_Request_Capture_Camera);
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//        }
    }
    @Override
    public void onActivityResult(int requestcode,int responseCode , Intent data)
    {

        if(responseCode != RESULT_OK)
        {
            return;
        }

        switch (requestcode)
        {
            case My_Request_Capture_Camera: {
                Bitmap picture = (Bitmap) data.getExtras().get("data");//this is your bitmap image and now you can do whatever you want with this
//                ImageView previewimageview = findViewById(R.id.previewimageview);
//                previewimageview.setImageBitmap(picture);
                String path = getOutputMediaFile().getAbsolutePath();
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(path);
                    picture.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                    // PNG is a lossless format, the compression factor (100) is ignored
                    Intent i = new Intent(this,PreviewActivity.class);
                    i.putExtra("imagePath", path);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (Exception e) {
//                         e.printStackTrace();
                    }
                }
            }
//                 imageView.setImageBitmap(picture); //for example I put bmp in an ImageView
//            break;
           /* case My_Request_Gallery:
                try
                {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    ImageView imageView = (ImageView) findViewById(R.id.previewimageview);
                    imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    Bitmap bitmap=BitmapFactory.decodeFile(picturePath);
                    String path1 =getOutputMediaFile().getAbsolutePath();

                    FileOutputStream out = null;
                    try {
                        out = new FileOutputStream(path1);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                        // PNG is a lossless format, the compression factor (100) is ignored

                        Intent i = new Intent(this,PreviewActivity.class);
                        i.putExtra("imagePath", path1);startActivity(i);
                        startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (out != null) {
                                out.close();
                            }
                        } catch (Exception e) {
//                         e.printStackTrace();
                        }
                    }



                }*/
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//                break;
            default:
                break;
        }

    }


    private  File getOutputMediaFile(){
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
}
