package com.example.a68.menuapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter {

    private Context context;
    private int[] dataset = {
            R.drawable.img1,  R.drawable.img2,
            R.drawable.img3,  R.drawable.img4,
            R.drawable.img5,  R.drawable.img6,
            R.drawable.img7,  R.drawable.img8,
            R.drawable.img9,  R.drawable.img10,
            R.drawable.img11,  R.drawable.img12
    };

    public ImageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataset.length;
    }

    @Override
    public Integer getItem(int position) {
        return dataset[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(
                decodeSampledBitmapFromResource(
                        context.getResources(),
                        dataset[position], 200, 100));
        //imageView.setImageResource(dataset[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new
                GridView.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, 400));
        return imageView;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = true;
        //BitmapFactory.decodeResource(res, resId, options);

        //options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inSampleSize = 8;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }
}
