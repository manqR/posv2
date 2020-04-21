package id.co.indomobil.test.Helper;

import android.os.Handler;
import android.util.Log;


public class IntervalSender  {

    public static String SenderApi(){
        final Handler h = new Handler();
        h.postDelayed(new Runnable()
        {
            private long time = 0;

            @Override
            public void run()
            {
                // do stuff then
                // can call h again after work!
                time += 1000;
                Log.d("TimerExample", "Going for... " + time);
                h.postDelayed(this, 1000);
            }
        }, 1000);
        return "";
    }
}
