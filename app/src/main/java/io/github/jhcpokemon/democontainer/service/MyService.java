package io.github.jhcpokemon.democontainer.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jhcpokemon on 09/11/15.
 */
public class MyService extends Service {
    public static final String TAG = "MY_SERVICE";
    private String data = "Default Data";
    private boolean running = false;

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "On Bind");
//        //return new MyBinder();
//        return new IServiceRemoteBinder.Stub() {
//            @Override
//            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//
//            }
//
//            @Override
//            public void setData(String data) throws RemoteException {
//                MyService.this.data = data;
//            }
//        };
        return new Binder();
    }

//    public class MyBinder extends Binder {
//        public void setData(String data) {
//            MyService.this.data = data;
//        }
//    }

    @Override
    public void onCreate() {
        Log.i(TAG, "On Create");
        running = true;
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (running) {
                    Log.i(TAG, data);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "On Destroy");
        running = false;
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "Un Bind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
