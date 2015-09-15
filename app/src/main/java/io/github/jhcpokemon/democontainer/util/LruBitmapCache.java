package io.github.jhcpokemon.democontainer.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;


public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
    public static int getDefaultLurCacheSize() {
        final int memory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        return memory / 8;
    }

    public LruBitmapCache() {
        super(getDefaultLurCacheSize());
    }

    public LruBitmapCache(int newSize) {
        super(newSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}
