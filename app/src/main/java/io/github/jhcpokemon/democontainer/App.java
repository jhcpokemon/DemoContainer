package io.github.jhcpokemon.democontainer;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import io.github.jhcpokemon.democontainer.util.LruBitmapCache;

public class App extends Application {
    public static final String TAG = App.class.getSimpleName();
    private static App appInstance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    public synchronized static App getAppInstance() {
        return appInstance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader(){
        getRequestQueue();
        if (imageLoader == null){
            imageLoader = new ImageLoader(requestQueue,new LruBitmapCache());
        }
        return imageLoader;
    }

    public <T> void addToRequestQueue(Request<T> request,String tag){
        request.setTag(TextUtils.isEmpty(tag) ? "TAG" : tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request){
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag){
        getRequestQueue().cancelAll(tag);
    }
}
