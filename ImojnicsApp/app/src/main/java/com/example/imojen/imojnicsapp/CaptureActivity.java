package com.example.imojen.imojnicsapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import android.os.Handler;
import  android.os.Looper;


public class CaptureActivity extends AppCompatActivity implements View.OnClickListener {
    public  static  final  int My_Request_Camera=10;
    public  static  final  int My_Request_Write_Camera=11;
    public  static  final  int My_Request_Capture_Camera=12;

    public  static  final  int My_Request_Gallery=13;
    public  static  final  int My_Request_Read_Gallery=14;
    public  static  final  int My_Request_Write_Gallery=15;

    private File nfile;
    Uri fileuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        ImageView galleryImageView = findViewById(R.id.gallery);
        ImageView captureImageView = findViewById(R.id.capture);
        ImageView crossImageView = findViewById(R.id.crossView);
        View crossView = findViewById(R.id.crosslayView);

        galleryImageView.setOnClickListener(this);
        captureImageView.setOnClickListener(this);
        crossImageView.setOnClickListener(this);
        crossView.setOnClickListener(this);
    }



    @Override
    public void onActivityResult(int requestcode, int responseCode , final Intent data)
    {

        if(responseCode != RESULT_OK)
        {
            return;
        }
         switch (requestcode)
         {
             case My_Request_Capture_Camera: {





                 Bitmap picture = (Bitmap) data.getExtras().get("data");//this is your bitmap image and now you can do whatever you want with this
//                 ImageView previewimageview = findViewById(R.id.previewimageview);
//                 previewimageview.setImageBitmap(picture);
                 String path = getOutputMediaFile().getAbsolutePath();
                 FileOutputStream out = null;
                 try {
                     out = new FileOutputStream(path);
                     picture.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                     // PNG is a lossless format, the compression factor (100) is ignored
                     Intent i = new Intent(this,PreviewActivity.class);
                     i.putExtra("imagePath", path);
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
                         Log.e( "1",e.toString() );
//                         e.printStackTrace();
                     }
                 }
             }
//                 imageView.setImageBitmap(picture); //for example I put bmp in an ImageView
                 break;
             case My_Request_Gallery:
                try
                {
//                    Handler uiHandler = new Handler(Looper.getMainLooper());
                    final Activity act=this;
                    Uri selectedImage1 = data.getData();

                    String[] filePathColumn1 = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(selectedImage1,
                            filePathColumn1, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn1[0]);
                   final String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        Bitmap bitmap=BitmapFactory.decodeFile(picturePath);
                        String path1 =getOutputMediaFile().getAbsolutePath();
//                        FileOutputStream out = null;
                       FileOutputStream out = new FileOutputStream(path1);

                        @Override
                        public void run() {

                            Log.d("UI thread", "I am the UI thread");
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                            // PNG is a lossless format, the compression factor (100) is ignored

//                            ImageView imageView = findViewById(R.id.previewimageview);
//                            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                            Intent i = new Intent(act,PreviewActivity.class);
                            i.putExtra("imagePath", path1);startActivity(i);
                            startActivity(i);
                            finish();
                        }
                    });
//                    Uri selectedImage = data.getData();
//                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                    Bitmap selectedImage1 = BitmapFactory.decodeStream(imageStream);
//
//                    selectedImage1 = getResizedBitmap(selectedImage1, 400);// 400 is for example, replace with desired size
//                    ImageView imageView = (ImageView) findViewById(R.id.previewimageview);
//                    imageView.setImageBitmap(selectedImage1);
//                    String fullpath=selectedImage.

//                    Cursor cursor = getContentResolver().query(selectedImage,
//                            filePathColumn, null, null, null);
//                    cursor.moveToFirst();
//
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    String picturePath = cursor.getString(columnIndex);
//                    cursor.close();








                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                 break;
                default:
                    break;
         }

    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    public void onClick(View v)
    {
     switch (v.getId())
     {
         case R.id.gallery:
                openGallery();
             break;
         case R.id.capture:
             openCamera();
             break;
         case R.id.crossView:
             closeActivity();
             break;
         case R.id.crosslayView:
             closeActivity();
             break;
     }
    }
    private void openGallery()
    {
        checkPermissionRG();
        Log.e("em","gallery clicked");
//        Toast.makeText(this,
//                "gallery clicked",
//                Toast.LENGTH_LONG).show();
    }
    private void openCamera()
    {
        checkPermissionCW();
        Log.e("em","Camera clicked");
//        Toast.makeText(this,
//                "Camera clicked",
//                Toast.LENGTH_LONG).show();
    }
    private  void closeActivity()
    {
        finish();
        Log.e("em","close clicked");
//        Toast.makeText(this,
//                "close clicked",
//                Toast.LENGTH_LONG).show();
    }

    public  void  checkPermissionCA()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},My_Request_Write_Camera);
        }
        else
        {
            catchPhoto();
        }
    }

    public  void  checkPermissionCW()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},My_Request_Camera);
        }
        else
        {
            checkPermissionCA();
        }
    }
    public void  checkPermissionRG()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},My_Request_Read_Gallery);
        }
        else
        {
            checkPermissionWG();
        }
    }
    public void  checkPermissionWG()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},My_Request_Write_Gallery);
        }
        else
        {
            getPhotos();
        }
    }
    public void getPhotos()
    {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, My_Request_Gallery);
//       Intent phptopick= new Intent(Intent.ACTION_PICK);
//       phptopick.setType("image/*");
//       startActivityForResult(phptopick,My_Request_Gallery);
    }
    public File getAFile()
    {
        File fileDir=new File(Environment.getExternalStorageDirectory()+"Android/data"+getApplicationContext().getPackageName()+"Files");
        if(!fileDir.exists())
        {
            if(!fileDir.mkdirs())
            {
                return  null;
            }
        }
        File mediaFile= new File(fileDir.getPath()+File.separator+"temp.jpg");
        return  mediaFile;
    }
    public void catchPhoto()
    {
        Intent cameraIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, My_Request_Capture_Camera);


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

    public void onRequestPermissionsResult(int requestcode,String[] permissions,int[] grantResults)
    {
        switch (requestcode)
        {
            case My_Request_Camera:
                catchPhoto();
                break;
            case My_Request_Write_Camera:
                checkPermissionCA();
                break;
            case My_Request_Read_Gallery:
                checkPermissionWG();
                break;
            case My_Request_Write_Gallery:
                getPhotos();
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

    @Override
    public void onDestroy(){
        super.onDestroy();
        clearMemory();
    }

    public void clearMemory(){
        nfile = null;
        fileuri = null;

    }

}
