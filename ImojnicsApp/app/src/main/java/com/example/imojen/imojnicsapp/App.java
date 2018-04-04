package com.example.imojen.imojnicsapp;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import android.media.ExifInterface;
import android.graphics.Matrix;

/**
 * Created by abdurrahim on 2/15/18.
 */

public class App extends Application
{
    public static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
//    public Bitmap wrightlightaline(File file)
//    {
//        BitmapFactory.Options bounds = new BitmapFactory.Options();
//        bounds.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(file, bounds);
//
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        Bitmap bm = BitmapFactory.decodeFile(file, opts);
//        ExifInterface exif = new ExifInterface(file);
//        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
//        int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;
//
//        int rotationAngle = 0;
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
//
//        Matrix matrix = new Matrix();
//        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
//        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//        return  rotatedBitmap;
//    }

}
