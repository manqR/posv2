package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.tPO0Entity;
import id.co.indomobil.test.Helper.TextHelper;

public class tPO0Helper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "tPurchaseOrder0";
    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String PO_SYS_NO = "PO_SYS_NO";
    public static final String PO_DOC_NO = "PO_DOC_NO";
    public static final String PO_DATE = "PO_DATE";
    public static final String PO_STATUS = "PO_STATUS";
    public static final String SUPPLIER_CODE = "SUPPLIER_CODE";
    public static final String SUGGOR_SYS_NO = "SUGGOR_SYS_NO";
    public static final String TOTAL = "TOTAL";
    public static final String GRAND_TOTAL = "GRAND_TOTAL";
    public static final String DPP = "DPP";
    public static final String VAT = "VAT";
    public static final String CREATION_USER_ID = "CREATION_USER_ID";
    public static final String CREATION_DATETIME = "CREATION_DATETIME";
    public static final String CHANGE_USER_ID = "CHANGE_USER_ID";
    public static final String CHANGE_DATETIME = "CHANGE_DATETIME";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;


    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + COMPANY_CODE + " VARCHAR(30) not null, "
            + PO_SYS_NO + " INTEGER not null, "
            + PO_DOC_NO + " VARCHAR(20) null, "
            + PO_DATE + " DATETIME null,"
            + PO_STATUS + " VARCHAR(10) null, "
            + SUPPLIER_CODE + " VARCHAR(10)  null, "
            + SUGGOR_SYS_NO + " INTEGER null, "
            + TOTAL + " NUMERIC(18,4) null, "
            + GRAND_TOTAL + " NUMERIC(18,4) null, "
            + DPP + " NUMERIC(18,4) null, "
            + VAT + " NUMERIC(18,4) null, "
            + CREATION_USER_ID + " VARCHAR(10) null, "
            + CREATION_DATETIME + " DATETIME null,"
            + CHANGE_USER_ID + " VARCHAR(10) null, "
            + CHANGE_DATETIME + " DATETIME null);";

    public tPO0Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(tPO0Helper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void InsertPO0(tPO0Entity c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMPANY_CODE, c.getCOMPANY_CODE());
        values.put(PO_SYS_NO, c.getPO_SYS_NO());
        values.put(PO_DOC_NO, c.getPO_DOC_NO());
        values.put(PO_DATE, String.valueOf(c.getPO_DATE()));
        values.put(PO_STATUS, c.getPO_STATUS());
        values.put(SUPPLIER_CODE, c.getSUPPLIER_CODE());
        values.put(SUGGOR_SYS_NO, c.getSUGGOR_SYS_NO());
        values.put(TOTAL, c.getTOTAL());
        values.put(GRAND_TOTAL, c.getGRAND_TOTAL());
        values.put(DPP, c.getDPP());
        values.put(VAT, c.getVAT());
        values.put(CREATION_USER_ID, c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID, c.getCREATION_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCREATION_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<tPO0Entity> SelectAllData(String criteria) {
        List<tPO0Entity> listData = new ArrayList<tPO0Entity>();
        if (!criteria.equals(""))
            criteria = " where " + criteria;
       /* String query = "select "
                + COMPANY_CODE + ", "
                + PO_SYS_NO + ", ifnull("
                + PO_DOC_NO + ",'DRAFT'), "
                + PO_DATE + ", "
                + PO_STATUS + ", "
                + SUPPLIER_CODE + ", "
                + SUGGOR_SYS_NO + ", "
                + TOTAL + ", "
                + GRAND_TOTAL + ", "
                + DPP + ", "
                + VAT + ", "
                + CREATION_USER_ID + ", "
                + CREATION_DATETIME + " from " + TABLE_NAME + " " + criteria;*/

        String query = "Select * from tPurchaseOrder0 po inner join mSupplier su on su.supplier_code = po.supplier_code" + criteria;;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //Cursor c = db.q
        TextHelper textHelper = new TextHelper();
        if (cursor.moveToFirst()) {
            do {
                tPO0Entity objData = new tPO0Entity();

                objData.setCOMPANY_CODE(cursor.getString(0));
                objData.setPO_SYS_NO(cursor.getInt(1));
                objData.setPO_DOC_NO(cursor.getString(2));
                objData.setPO_DATE (java.sql.Timestamp.valueOf(cursor.getString(3)));
                objData.setPO_STATUS(cursor.getString(4));
                objData.setSUPPLIER_CODE(cursor.getString(5));
                objData.setSUPPLIER_NAME(cursor.getString(16));
                objData.setSUGGOR_SYS_NO(cursor.getString(6));
                objData.setTOTAL(cursor.getDouble(7));
                objData.setGRAND_TOTAL(cursor.getDouble(8));
                objData.setDPP(cursor.getDouble(9));
                objData.setVAT(cursor.getDouble(10));
                objData.setCREATION_USER_ID(cursor.getString(11));
                objData.setCREATION_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(12)));
                objData.setCHANGE_USER_ID(cursor.getString(13));
                objData.setCHANGE_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(14)));
                listData.add(objData);
            } while (cursor.moveToNext());
        }
        db.close();
        return listData;
    }

    public int IsItemAlreadyExist (Integer iSysNo){
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME + " WHERE PO_SYS_NO = " + String.valueOf(iSysNo);
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        return count;
    }

    public boolean IsPOSysNoAlreadyExist(Integer iSysNo, String CompanyCode) {
        db = this.getReadableDatabase();
        boolean isExist = false;

        String query = " SELECT PO_SYS_NO FROM " + TABLE_NAME + " WHERE PO_SYS_NO = " + String.valueOf(iSysNo) + " AND COMPANY_CODE = '" + CompanyCode + "'";

        Cursor cursor = db.rawQuery(query, null);
        Integer tmpSysNo = 0;
        Integer nextLineNo = 0;

        if (cursor.moveToFirst()) {
            do {
                tmpSysNo = cursor.getInt(0);
            } while (cursor.moveToNext());
        }

        if (tmpSysNo.equals(iSysNo)) {
            return true;
        } else {
            return false;
        }

    }

    public Integer GetNextPOSysNO() {
        db = this.getReadableDatabase();
        String query = " SELECT IFNULL(MAX(PO_SYS_NO),'0') FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String sSysNo;
        Integer nextSysNo = 0;

        if (cursor.moveToFirst()) {
            do {
                sSysNo = cursor.getString(0);
                nextSysNo = Integer.valueOf(sSysNo) + 1;
            } while (cursor.moveToNext());
        }
        return nextSysNo;
    }

    public Integer GetNextPODocNO() {
        db = this.getReadableDatabase();
        String query = " SELECT IFNULL(MAX(substr(PO_DOC_NO,4)),'0'),* FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Integer sDocNo = 0;
        Integer nextDocNo = 0;

        if (cursor.moveToFirst()) {
            do {
                sDocNo = cursor.getInt(0);
                nextDocNo = Integer.valueOf(sDocNo) + 1;
            } while (cursor.moveToNext());
        }
        return nextDocNo;
    }

   /* public Integer GetOutStandingSysNo() {
        db = this.getReadableDatabase();
        String query = " SELECT PO_SYS_NO FROM " + TABLE_NAME + "  WHERE IFNULL(PO_SYS_NO ,'0') <> '0' AND IFNULL(PO_DOC_NO,'0') = '0'";
        Cursor cursor = db.rawQuery(query, null);
        Integer sSysNo = 0;

        if (cursor.moveToFirst()) {
            do {
                sSysNo = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return sSysNo;
    }*/

    public void UpdatePO0(tPO0Entity c) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = " UPDATE " + TABLE_NAME + " SET TOTAL = " + String.valueOf(c.getTOTAL()) + " , GRAND_TOTAL = " + String.valueOf(c.getTOTAL())
                + " WHERE PO_SYS_NO = '" + String.valueOf(c.getPO_SYS_NO()) + "'";
        db.execSQL(query);
        db.close();

    }

    public void UpdateDataPO0(tPO0Entity c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PO_DOC_NO, c.getPO_DOC_NO());
        values.put(SUPPLIER_CODE, c.getSUPPLIER_CODE());
        values.put(TOTAL, c.getTOTAL());
        values.put(GRAND_TOTAL, c.getGRAND_TOTAL());
        values.put(DPP, c.getDPP());
        values.put(VAT, c.getVAT());
        values.put(CHANGE_USER_ID, c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, c.getCHANGE_DATETIME().toString());

        String[] whereArgs = new String[]{String.valueOf(c.getPO_SYS_NO()), String.valueOf(c.getCOMPANY_CODE())};
        db.update(TABLE_NAME, values, PO_SYS_NO + "=? AND " + COMPANY_CODE + "=?", whereArgs);
        db.close();
    }

    public void UpdateDocNoPO0(tPO0Entity c) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = " UPDATE " + TABLE_NAME + " SET PO_DOC_NO = '" + c.getPO_DOC_NO()
                + "' WHERE PO_SYS_NO = '" + String.valueOf(c.getPO_SYS_NO()) + "'";
        db.execSQL(query);
        db.close();

    }

    public void UpdateDPPandVAT(tPO0Entity c) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = " UPDATE " + TABLE_NAME + " SET DPP = " + String.valueOf(c.getDPP()) + " , VAT = " + String.valueOf(c.getVAT())
                + " WHERE PO_SYS_NO = '" + String.valueOf(c.getPO_SYS_NO()) + "'";
        db.execSQL(query);
        db.close();

    }



}