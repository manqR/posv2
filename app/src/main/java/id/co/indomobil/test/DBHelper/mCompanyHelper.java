package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mCompanyEntity;

public class mCompanyHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "mCompany";
    public static final String RECORD_STATUS = "RECORD_STATUS";
    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String COMPANY_NAME = "COMPANY_NAME";
    public static final String COMPANY_ADDRESS = "COMPANY_ADDRESS";
    public static final String CITY_CODE = "CITY_CODE";
    public static final String PHONE_NO = "PHONE_NO";
    public static final String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static final String COMPANY_NPWP = "COMPANY_NPWP";
    public static final String PIC_NAME = "PIC_NAME";
    public static final String MAC_ADDRESS = "MAC_ADDRESS";
    public static final String LAST_DATA_CHANGED = "LAST_DATA_CHANGED";
    public static final String LAST_SYNC = "LAST_SYNC";
    public static final String CREATION_USER_ID = "CREATION_USER_ID";
    public static final String CREATION_DATETIME = "CREATION_DATETIME";
    public static final String CHANGE_USER_ID = "CHANGE_USER_ID";
    public static final String CHANGE_DATETIME = "CHANGE_DATETIME";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;

    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + RECORD_STATUS + " CHAR(1) null, "
            + COMPANY_CODE + " VARCHAR(30) not null, "
            + COMPANY_NAME + " VARCHAR(200) null, "
            + COMPANY_ADDRESS + " VARCHAR(200) null, "
            + CITY_CODE + " VARCHAR(10) null, "
            + PHONE_NO + " VARCHAR(20) null, "
            + EMAIL_ADDRESS + " VARCHAR(50) null, "
            + COMPANY_NPWP + " VARCHAR(50) null, "
            + PIC_NAME + " VARCHAR(100) null, "
            + MAC_ADDRESS + " VARCHAR(30) null, "
            + LAST_DATA_CHANGED + " DATETIME null, "
            + LAST_SYNC + " DATETIME null, "
            + CREATION_USER_ID + " VARCHAR(10) null, "
            + CREATION_DATETIME + " DATETIME null, "
            + CHANGE_USER_ID + " VARCHAR(10) null, "
            + CHANGE_DATETIME + " DATETIME null);";

    public mCompanyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public mCompanyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public mCompanyEntity SelectCompany(String companyCode) {
        db = this.getReadableDatabase();
        String query = "SELECT COMPANY_CODE ," +
                "               COMPANY_NAME ," +
                "               COMPANY_ADDRESS ," +
                "               CITY_CODE ," +
                "               PHONE_NO ," +
                "               EMAIL_ADDRESS ," +
                "               COMPANY_NPWP ," +
                "               PIC_NAME ," +
                "               MAC_ADDRESS " +
                "               FROM mCompany " +
                "               WHERE COMPANY_CODE = '" + companyCode + "'";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        mCompanyEntity m = new mCompanyEntity();
        b = "data not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(companyCode)) {
                    //b = cursor.getString(1);
                    m = new mCompanyEntity();
                    m.setCOMPANY_CODE(cursor.getString(0));
                    m.setCOMPANY_NAME(cursor.getString(1));
                    m.setCOMPANY_ADDRESS(cursor.getString(2));
                    m.setCITY_CODE(cursor.getString(3));
                    m.setPHONE_NO(cursor.getString(4));
                    m.setEMAIL_ADDRESS(cursor.getString(5));
                    m.setCOMPANY_NPWP(cursor.getString(6));
                    m.setPIC_NAME(cursor.getString(7));
                    m.setMAC_ADDRESS(cursor.getString(8));
                    break;
                }
            } while (cursor.moveToNext());
        }
        return m;
    }

    public mCompanyEntity SelectCompanyByMacAddress(String macaddress) {
        db = this.getReadableDatabase();
        String query = "SELECT COMPANY_CODE," +
                "               COMPANY_NAME ," +
                "               COMPANY_ADDRESS ," +
                "               CITY_CODE ," +
                "               PHONE_NO ," +
                "               EMAIL_ADDRESS ," +
                "               COMPANY_NPWP ," +
                "               PIC_NAME ," +
                "               MAC_ADDRESS " +
                "               FROM mCompany " +
                "               WHERE MAC_ADDRESS = '" + macaddress + "'";
        /*       String query = "SELECT * FROM mCompany";*/
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        mCompanyEntity m = new mCompanyEntity();
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(8);
                if (a.equals(macaddress)) {
                    m = new mCompanyEntity();
                    m.setCOMPANY_CODE(cursor.getString(0));
                    m.setCOMPANY_NAME(cursor.getString(1));
                    m.setCOMPANY_ADDRESS(cursor.getString(2));
                    m.setCITY_CODE(cursor.getString(3));
                    m.setPHONE_NO(cursor.getString(4));
                    m.setEMAIL_ADDRESS(cursor.getString(5));
                    m.setCOMPANY_NPWP(cursor.getString(6));
                    m.setPIC_NAME(cursor.getString(7));
                    m.setMAC_ADDRESS(cursor.getString(8));
                    break;
                }
            } while (cursor.moveToNext());
        }
        return m;
    }

    public void InsertCompany(mCompanyEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(COMPANY_CODE,c.getCOMPANY_CODE());
        values.put(COMPANY_NAME,c.getCOMPANY_NAME());
        values.put(COMPANY_ADDRESS,c.getCOMPANY_ADDRESS());
        values.put(CITY_CODE,c.getCITY_CODE());
        values.put(PHONE_NO,c.getPHONE_NO());
        values.put(EMAIL_ADDRESS,c.getEMAIL_ADDRESS());
        values.put(COMPANY_NPWP,c.getCOMPANY_NPWP());
        values.put(PIC_NAME,c.getPIC_NAME());
        values.put(MAC_ADDRESS,c.getMAC_ADDRESS());
        values.put(LAST_DATA_CHANGED, String.valueOf(c.getLAST_DATA_CHANGED()));
        values.put(LAST_SYNC, String.valueOf(c.getLAST_SYNC()));
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public mCompanyEntity SelectLastSync(String companycode) {
        db = this.getReadableDatabase();
        List<mCompanyEntity> listData = new ArrayList<mCompanyEntity>();
        String query = " SELECT datetime(LAST_SYNC, '-15 day') as LAST_SYNC, LAST_DATA_CHANGED FROM " + TABLE_NAME + " WHERE " + COMPANY_CODE + "= '" + companycode + "'";
        Cursor cursor = db.rawQuery(query, null);
        mCompanyEntity m = new mCompanyEntity();

        mCompanyEntity mC = new mCompanyEntity();

        if (cursor.moveToFirst()) {
            do {
                mC = new mCompanyEntity();
                mC.setLAST_SYNC(java.sql.Timestamp.valueOf(cursor.getString(0)));
                mC.setCOMPANY_CODE(companycode);
                break;

            } while (cursor.moveToNext());
        }
        return mC;
    }


    public int isExistLastUpdate(String companyCode) {
        db = this.getReadableDatabase();

        String query = " SELECT * FROM mCompany where company_code =" + companyCode + "\n" +
                "and LAST_DATA_CHANGED > last_sync";

        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        return count;

    }

    public int isExistData() {
        db = this.getReadableDatabase();

        String query = "SELECT * FROM mCompany";

        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean isCompanyExist (String CompanyCode){
        boolean retValue = false;

        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" where "+COMPANY_CODE+" = '"+CompanyCode+"' ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        if(count>0)
            retValue = true;
        return retValue;
    }

    public void UpdateCompany(mCompanyEntity c){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMPANY_CODE,c.getCOMPANY_CODE());

        String[] whereArgs = new String[] {String.valueOf(c.getCOMPANY_CODE())};
        db.update(TABLE_NAME, values, COMPANY_CODE + "=?", whereArgs);
        db.close();
    }


    public void UpdateLastChanged(mCompanyEntity c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LAST_DATA_CHANGED, c.getLAST_DATA_CHANGED().toString());

        String[] whereArgs = new String[]{String.valueOf(c.getCOMPANY_CODE())};
        db.update(TABLE_NAME, values, COMPANY_CODE + "=?", whereArgs);
        db.close();
    }

    public void UpdateLastSync(String companyCode, Timestamp LastSync) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LAST_SYNC, LastSync.toString());

        String[] whereArgs = new String[]{String.valueOf(companyCode)};
        db.update(TABLE_NAME, values, COMPANY_CODE + "=?", whereArgs);
        db.close();
    }

    public  String GetLastSync (String companycode){
        db = this.getReadableDatabase();


        String query = " select LAST_SYNC  from mcompany\n" +
                " where company_code = '" + companycode + "'";

        Cursor cursor = db.rawQuery(query,null);
        String sLastSync ="";

        if(cursor.moveToFirst()){
            do {
                sLastSync = cursor.getString(0).toString();
            }while (cursor.moveToNext());
        }

        return sLastSync;
    }

}