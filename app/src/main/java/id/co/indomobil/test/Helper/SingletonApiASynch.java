package id.co.indomobil.test.Helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.File;

public class SingletonApiASynch {

    private static SingletonApiASynch mInstance;
    private static RequestQueue mRequestQueue;
    //private ImageLoader mImageLoader;
    private static Context mCtx;

    private SingletonApiASynch(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized SingletonApiASynch getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SingletonApiASynch(context);
        }
        return mInstance;
    }

    public static synchronized RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public synchronized RequestQueue getRequestQueue(Context context, Integer NumOfThread) {
        // getApplicationContext() is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        File cacheDir = new File(context.getCacheDir(), "Volley");
        mRequestQueue = new RequestQueue(new DiskBasedCache(cacheDir), new BasicNetwork(new HurlStack()), NumOfThread);
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        //getRequestQueue().add(req);
        mRequestQueue.add(req);
    }
}
