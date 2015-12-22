package io.github.jhcpokemon.democontainer.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by jhcpokemon on 12/17/15.
 */
public class ResizeImage {
    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int resourceId, int width, int height) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //解码时不进行内存分配
        BitmapFactory.decodeResource(resources, resourceId, options);
        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resourceId, options);
    }

    /**
     * 计算缩放倍数
     *
     * @param options   参数
     * @param reqWidth  请求宽度
     * @param reqHeight 请求高度
     * @return 缩放倍率
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqHeight) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize > reqWidth)) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
