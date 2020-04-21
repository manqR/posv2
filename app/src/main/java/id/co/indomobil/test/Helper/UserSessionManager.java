package id.co.indomobil.test.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import id.co.indomobil.test.DBEntity.mCompanyEntity;
import id.co.indomobil.test.DBEntity.mEmployeeEntity;
import id.co.indomobil.test.DBHelper.mCompanyHelper;
import id.co.indomobil.test.DBHelper.mEmployeeHelper;
import id.co.indomobil.test.LoginActivity;
import id.co.indomobil.test.MainActivity;

import static id.co.indomobil.test.Helper.AndroidPonsel.getMacAddress0;

public class UserSessionManager {



    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context _context;


//    private String android_id = Secure.getString(_context.getContentResolver(),
//            Secure.ANDROID_ID);

    public int PRIVATE_MODE=0;
    public static final String PREFER_NAME = "UserSessionPref";
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_EMPPLOYEE_NO = "empNo";
    public static final String KEY_EMPPLOYEE_NAME = "empName";
    public static final String KEY_EMPPLOYEE_PASSWORD = "empPassword";
    public static final String KEY_JOB_POSITION = "jobPosition";
    public static final String KEY_COMPANY_CODE = "companyCode";
    public static final String KEY_COMPANY_NAME = "companyName";
    public static final String KEY_COMPANY_ADDRESS = "companyAddress";
    public static final String KEY_COMPANY_PHONE = "companyPhone";
    public Boolean isUserLoggedIn = false;
    private SQLiteDatabase db = null;
    private mEmployeeHelper dbCon = null;

    public UserSessionManager(Context context){
        this._context = context;
        preferences = _context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor = preferences.edit();
    }

    public boolean IsUserLoggedIn(){
        isUserLoggedIn = preferences.getBoolean(IS_USER_LOGIN,false);
        return isUserLoggedIn;
    }

    public boolean checkLogin(){
        if (!this.IsUserLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            return true;
        }
        return false;
    }

    public void createUserLoginSession(String empNo, String empName, String empPassword, String jobPosition, String companyCode,
                                       String companyName, String companyAddress, String companyPhone){
        editor.putBoolean(IS_USER_LOGIN,true);
        editor.putString(KEY_EMPPLOYEE_NO,empNo);
        editor.putString(KEY_EMPPLOYEE_NAME,empName);
        editor.putString(KEY_EMPPLOYEE_PASSWORD,empPassword);
        editor.putString(KEY_JOB_POSITION,jobPosition);
        editor.putString(KEY_COMPANY_CODE,companyCode);
        editor.putString(KEY_COMPANY_NAME,companyName);
        editor.putString(KEY_COMPANY_ADDRESS,companyAddress);
        editor.putString(KEY_COMPANY_PHONE,companyPhone);
        editor.commit();
    }

    public void loginUser(String loginID, String password){
//        mEmployeeHelper dbCon = new mEmployeeHelper(_context);
//        mEmployeeEntity employeeEntity = dbCon.SelectEmployee(loginID);
        if (loginID != "" && password != ""){
            String encriptPass = this.computeMD5Hash(password);
            this.createUserLoginSession("99999-05", "Dummy", "1", "-", "99999-05", "IMSI", "-", "0");
//            if (loginID.equals(employeeEntity.getEMPLOYEE_NO()) && encriptPass.equals(employeeEntity.getHASH_PASS())){
//                mCompanyHelper dbCon1 = new mCompanyHelper(_context);
//                mCompanyEntity companyEntity = dbCon1.SelectCompany(employeeEntity.getCOMPANY_CODE());
//                String macAddressDB = companyEntity.getMAC_ADDRESS().toLowerCase();
//                String macAddressDevice = getMacAddress0(_context).toLowerCase();
//                //if (macAddressDevice.equals(macAddressDB)){
//                String EmpNo = employeeEntity.getEMPLOYEE_NO();
//                String EmpName = employeeEntity.getEMPLOYEE_NAME();
//                String EmpPassword = employeeEntity.getHASH_PASS();
//                String JobPosition = employeeEntity.getJOB_POSITION();
//                String CompanyCode = companyEntity.getCOMPANY_CODE();
//                String CompanyName = companyEntity.getCOMPANY_NAME();
//                String CompanyAddress = companyEntity.getCOMPANY_ADDRESS();
//                String CompanyPhone = companyEntity.getPHONE_NO();
//
//                //Save session - selalu ada nilainya selama belum logout
//                this.createUserLoginSession(EmpNo,EmpName,EmpPassword,JobPosition,CompanyCode,CompanyName, CompanyAddress,CompanyPhone);
//
//                Intent i = new Intent(_context, MainActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                _context.startActivity(i);
//            }else{
//                Toast.makeText(_context,"ID Pengguna dan Kata Sandi tidak sesuai.",Toast.LENGTH_SHORT).show();
//            }
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }



    public String computeMD5Hash(String password)
    {
        StringBuffer MD5Hash = new StringBuffer();
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return MD5Hash.toString();
    }
}
