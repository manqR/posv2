package id.co.indomobil.test;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;

import id.co.indomobil.test.DBEntity.mCompanyEntity;
import id.co.indomobil.test.DBEntity.mEmployeeEntity;
import id.co.indomobil.test.DBHelper.DatabaseHelper;
import id.co.indomobil.test.DBHelper.mCompanyHelper;
import id.co.indomobil.test.DBHelper.mEmployeeHelper;
import id.co.indomobil.test.Helper.ApplicationController;
import id.co.indomobil.test.Helper.SingletonApiASynch;
import id.co.indomobil.test.Helper.UserSessionManager;

import static id.co.indomobil.test.Helper.AndroidPonsel.isServerReachable;

public class LoginActivity extends AppCompatActivity {
    String url_api = "";//IndoStationServices.MetaDataAPI_DOWNLOAD;
    Button btnLogin;
    ProgressBar pb;
    TextView txtProgress;
    TextView txtLoginId;
    TextView txtPassword;
    TextView txtVersion;
    TextView txtMacAddress;
    TextView txtMsg;
    DatabaseHelper generalDBHelper;
    UserSessionManager session;
    ApplicationController globalVar;
    Integer backButtonCount = 0;
    mCompanyHelper dbComp;
    //    mSourceDocHelper dbDoc;
    boolean IsNew = false;
    String macAddressDevice = "";
    String CompanyCode = "";
    java.sql.Timestamp createdDateTime = null;
    Context context = null;
    //    IndoStationSynchDownloadData dtsD = null;
    View parentLayout = null;
    Boolean isFinishedSync = false;
    Boolean isFailedSync = false;
    Boolean isFinishedSyncLogin = false;
    Boolean isFailedSyncLogin = false;
    String message = "";
    TelephonyManager telephonyManager;
    private String deviceId = "";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        telephonyManager = (TelephonyManager) getSystemService(Context.
                TELEPHONY_SERVICE);

        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        deviceId = telephonyManager.getDeviceId();

        setContentView(R.layout.activity_login);
        session = new UserSessionManager(getApplicationContext());
        getSupportActionBar().hide();
        context = getApplicationContext();
        btnLogin = (Button) findViewById(R.id.btnLogin);
        pb = (ProgressBar) findViewById(R.id.pbLogin);
        txtProgress = (TextView) findViewById(R.id.txtProgressLogin);
        txtLoginId = (TextView) findViewById(R.id.txtLoginId);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtVersion = (TextView) findViewById(R.id.txtVersionName);
        txtMacAddress = (TextView) findViewById(R.id.txtMacAddress);
        txtMsg = (TextView) findViewById(R.id.txtMsg);
        parentLayout = findViewById(R.id.loginlayout);

        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

        txtVersion.setText("Versi : " + versionName + "/" + versionCode);
        txtMacAddress.setText(deviceId);
        txtMsg.setText("");
        txtProgress.setText("");
        pb.setVisibility(View.INVISIBLE);
        btnLogin.setText("Login");

        createdDateTime = new java.sql.Timestamp(System.currentTimeMillis());

        dbComp = new mCompanyHelper(context);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Login", "onClick: Go To Home..");
//                SyncMasterCompAndEmpData();

                session.loginUser("9999905-01", "1");
                session.createUserLoginSession("99999-05", "Dummy", "1", "-", "99999-05", "IMSI", "-", "0");

                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }


    @Override
    public void onBackPressed() {
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Tekan sekali lagi untuk keluar.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
        //super.onBackPressed();
    }

    private void LoggingIn() {


        final String loginID = txtLoginId.getText().toString();
        final String password = txtPassword.getText().toString();
        mEmployeeHelper dbEmp = new mEmployeeHelper(context);
        mEmployeeEntity employeeEntity = dbEmp.SelectEmployee(loginID);
        if (loginID != "" && password != "") {
            String encriptPass = session.computeMD5Hash(password);
            if (loginID.equals(employeeEntity.getEMPLOYEE_NO()) && encriptPass.equals(employeeEntity.getHASH_PASS())) {
                mCompanyEntity companyEntity = dbComp.SelectCompany(employeeEntity.getCOMPANY_CODE());
                String macAddressDB = companyEntity.getMAC_ADDRESS();
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                String macAddressDevice = deviceId;
                IsNew = globalVar.IsNew;
                globalVar.IsNew = false;
                if (macAddressDevice.toLowerCase().equals(macAddressDB.toLowerCase())) {
                    mCompanyEntity companyEntity2 = new mCompanyEntity();
                    companyEntity2 = dbComp.SelectCompanyByMacAddress(macAddressDevice);
                    CompanyCode = companyEntity2.getCOMPANY_CODE();
                    final String EmpNo = employeeEntity.getEMPLOYEE_NO();
                    final String EmpName = employeeEntity.getEMPLOYEE_NAME();
                    final String EmpPassword = employeeEntity.getHASH_PASS();
                    final String JobPosition = employeeEntity.getJOB_POSITION();
                    final String CompanyCode = companyEntity.getCOMPANY_CODE();
                    final String CompanyName = companyEntity.getCOMPANY_NAME();
                    final String CompanyAddress = companyEntity.getCOMPANY_ADDRESS();
                    final String PhoneNo = companyEntity.getPHONE_NO();
                    if (CompanyCode != "") {
                        if (IsNew == true) {

                            new SyncData() {
                                @Override
                                public void onPostExecute(String aVoid) {
                                    super.onPostExecute(aVoid);
                                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                                    isFinishedSync = prefs.getBoolean("isFinishedSync", false);
                                    isFailedSync = prefs.getBoolean("isFailedSync", false);
                                    // if (isFinishedSync) {
                                    txtVersion.setVisibility(View.VISIBLE);
                                    pb.setVisibility(View.GONE);
                                    txtProgress.setText("");
                                    progress.dismiss();
                                    //if (!isFailedSync) {
                                    //Update Last_Sync(now) To Mobile
                                    dbComp.UpdateLastSync(CompanyCode, createdDateTime);
                                    globalVar.KeyValuePairCollection.put("user", txtLoginId.getText().toString());
                                    globalVar.KeyValuePairCollection.put("passwordku", txtPassword.getText().toString());
                                    globalVar.EmpNo = EmpNo;
                                    globalVar.EmpName = EmpName;
                                    globalVar.JobPosition = JobPosition;
                                    globalVar.CompanyCode = CompanyCode;
                                    globalVar.CompanyName = CompanyName;
                                    globalVar.CompanyAddress1 = CompanyAddress;
                                    globalVar.CompanyTelp = PhoneNo;
                                    session.loginUser(loginID, password);
                                    session.createUserLoginSession(EmpNo, EmpName, EmpPassword, JobPosition, CompanyCode, CompanyName, CompanyAddress, PhoneNo);
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }
                            }.execute(CompanyCode);

                        } else {
                            //Update Last_Sync(now) To Mobile
                            dbComp.UpdateLastSync(CompanyCode, createdDateTime);
                            globalVar.KeyValuePairCollection.put("user", txtLoginId.getText().toString());
                            globalVar.KeyValuePairCollection.put("passwordku", txtPassword.getText().toString());
                            globalVar.EmpNo = EmpNo;
                            globalVar.EmpName = EmpName;
                            globalVar.JobPosition = JobPosition;
                            globalVar.CompanyCode = CompanyCode;
                            globalVar.CompanyName = CompanyName;
                            globalVar.CompanyAddress1 = CompanyAddress;
                            globalVar.CompanyTelp = PhoneNo;

                            session.loginUser(loginID, password);
                            session.createUserLoginSession(EmpNo, EmpName, EmpPassword, JobPosition, CompanyCode, CompanyName, CompanyAddress, PhoneNo);
                            //session.loginUser(loginID, password);
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            //overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Perangkat anda tidak terdaftar.", Toast.LENGTH_SHORT).show();
                    setStopSyncDataStatus(true, true);
                }
            } else {
                Toast.makeText(getApplicationContext(), "ID Pengguna dan Kata Sandi tidak sesuai.", Toast.LENGTH_SHORT).show();
                setStopSyncDataStatus(true, true);
            }
        }
    }

    public void setStopSyncDataStatus(boolean isFinishedSync, boolean isFailedSync) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFinishedSync", isFinishedSync); //InputString: from the EditText
        editor.putBoolean("isFailedSync", isFailedSync); //InputString: from the EditText
        editor.commit();

/*        SyncData obj = new SyncData();
        obj.onPostExecute(true);*/
    }

    public boolean SyncMasterCompAndEmpData() {
        //check internet || server connection
    /*    if (!AndroidPonsel.isNetworkConected(context)) {
            Snackbar.make(parentLayout, "No internet connection.", Snackbar.LENGTH_LONG).show();
            txtMsg.setText("No internet connection, please make sure internet connection is available\n Tidak ada koneksi internet, mohon pastikan koneksi internet tersedia.");
            return false;
        } else {*/
        new SyncMasterCompAndEmp().execute();
        return true;
        //}
    }


    public class SyncMasterCompAndEmp extends AsyncTask<Boolean, String, Boolean> {
        ProgressDialog progress;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            editor.putBoolean("isFinishedSyncLogin", false); //InputString: from the EditText
            editor.putBoolean("isFailedSyncLogin", false); //InputString: from the EditText
            editor.commit();
            isFinishedSyncLogin = false;
            isFailedSyncLogin = false;
            txtVersion.setVisibility(View.GONE);
            pb.setVisibility(View.VISIBLE);
            txtProgress.setText("Mengautentikasi. Mohon sabar menunggu..");
            progress = new ProgressDialog(LoginActivity.this);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Mengautentikasi");
            progress.setMessage("Mohon Tunggu...");
            progress.setCancelable(false);
            progress.show();
            //progress = ProgressDialog.show(MainActivity.this,"Synchronize Data", "Please wait...");
        }

        @Override
        protected Boolean doInBackground(Boolean... params) {
            try {
                dbComp = new mCompanyHelper(context);

                if (dbComp.isExistData() == 0) {
                    //Do all DB related Task Here
//                    IndoStationSynchDownloadData dtsD = new IndoStationSynchDownloadData(context);
                    //check if mobile already register to server
                    //WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    //get Mobile Mac Address
                    macAddressDevice = deviceId;
                    //IndoStationSynchDownloadData dtsD = new IndoStationSynchDownloadData(context1);
                    //Check Mac Address Mobile to the Server || If Exists then Download else send Err
                    if (!isServerReachable(context)) {
                        globalVar.IsNew = false;
                        editor.putBoolean("isFinishedSyncLogin", true); //InputString: from the EditText
                        editor.putBoolean("isFailedSyncLogin", true); //InputString: from the EditText
                        editor.commit();
                        Toast.makeText(context, "Indostation.indomobil.co.id is unreachable. Please try again later...", Toast.LENGTH_LONG).show();
                    } else {
                        RequestQueue queue = SingletonApiASynch.getInstance(context).getRequestQueue(context, 1);
//                        dtsD.asynchCheckMacAddressMobile(macAddressDevice);
                        queue.start();
                        globalVar.IsNew = true;
                        while (!isFinishedSyncLogin) {
                            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                            isFinishedSyncLogin = prefs.getBoolean("isFinishedSyncLogin", false);
                            message = prefs.getString("message", "");
                            publishProgress(message);
                            //Perform some repeating action.
                            //Thread.sleep(50);
                        }
                    }
                } else {
                    globalVar.IsNew = false;
                    editor.putBoolean("isFinishedSyncLogin", true); //InputString: from the EditText
                    editor.putBoolean("isFailedSyncLogin", true); //InputString: from the EditText
                    editor.commit();
                }
                return true;
            } catch (Exception e) {
                Log.e("SyncMasterCompAndEmp", e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            isFinishedSyncLogin = prefs.getBoolean("isFinishedSyncLogin", false);
            isFailedSyncLogin = prefs.getBoolean("isFailedSyncLogin", false);

            if (isFinishedSyncLogin || !globalVar.IsNew) {
                txtVersion.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                txtProgress.setText("");
                progress.dismiss();
                if (!isFailedSyncLogin || !globalVar.IsNew) {
                    if (dbComp.isExistData() == 1) {
                        LoggingIn();
                    } else if (dbComp.isExistData() > 1) {
                        Toast.makeText(getApplicationContext(), "Device " + deviceId + " terdaftar di lebih dari 1 company, silahkan hubungi admin.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Data Master Tidak Ditemukan.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... message) {
            super.onProgressUpdate(message);
            if (message[0] != null && message[0] != "")
                progress.setMessage(message[0]);
        }
    }


    public class SyncData extends AsyncTask<String, String, String> {
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isFinishedSync", false); //InputString: from the EditText
            editor.putBoolean("isFailedSync", false); //InputString: from the EditText
            editor.commit();
            isFinishedSync = false;
            isFailedSync = false;
            txtVersion.setVisibility(View.GONE);
            pb.setVisibility(View.VISIBLE);
            txtProgress.setText("Patching Data. Mohon sabar menunggu..");
            progress = new ProgressDialog(LoginActivity.this);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Patching Data");
            progress.setMessage("Mohon sabar menunggu...");
            progress.setCancelable(false);
            progress.show();
        }

        @Override
        protected String doInBackground(String... CompanyCode) {
            try {
                RequestQueue queue = SingletonApiASynch.getInstance(context).getRequestQueue(context, 1);
                //Download Master Data
                //IndoStationSynchDownloadData dtsD = new IndoStationSynchDownloadData(context);
//                dtsD.asynchDownloadMasterData(String.valueOf(CompanyCode[0]));
                //Download Data Transaction
//                SyncDataBL dt = new SyncDataBL(context);
                //globalVar.IsNew = true;
//                dt.asynchDownloadTransactionData(String.valueOf(CompanyCode[0]));
                queue.start();
                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                while (!isFinishedSync) {
                    //for (int l = 0; l <= 20; l++) {
                    isFinishedSync = prefs.getBoolean("isFinishedSync", false);
                    message = prefs.getString("message", "");
                    publishProgress(message);
                    //Perform some repeating action.
                    //Thread.sleep(500);
                }
            } catch (Exception e) {
                Log.e("SyncDataFail", e.getMessage());
            } finally {
                return String.valueOf(CompanyCode[0]);
            }
        }

        @Override
        protected void onProgressUpdate(String... message) {
            super.onProgressUpdate(message);
            if (message[0] != null && message[0] != "")
                progress.setMessage(message[0]);
        }
    }
}