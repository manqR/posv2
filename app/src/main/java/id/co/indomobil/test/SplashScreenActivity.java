package id.co.indomobil.test;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import id.co.indomobil.test.DBHelper.DatabaseHelper;
import id.co.indomobil.test.DBHelper.mCompanyHelper;
import id.co.indomobil.test.Helper.ApplicationController;

public class SplashScreenActivity extends Activity {
    private static int splashInterval = 3000;
    String versionName = "";
    ApplicationController globalVar;
    Context context;
    DatabaseHelper generalDBHelper;
    mCompanyHelper dbComp;
    Boolean isFinishedSyncLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        versionName = "v" + BuildConfig.VERSION_NAME;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
        TextView tvVersion = (TextView) findViewById(R.id.tvVersion);
        tvVersion.setText(versionName);
        context = getApplicationContext();
        dbComp = new mCompanyHelper(getApplicationContext());
        generalDBHelper = new DatabaseHelper(this);

        delayedActivity();

    }

    public void delayedActivity() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

                //jeda selesai Splashscreen
                this.finish();
            }

            private void finish() {
                // TODO Auto-generated method stub

            }
        }, splashInterval);
    }
}
