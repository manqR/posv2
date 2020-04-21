package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mPromo1Entity;

public class mPromo1Helper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "mPromo1";
    public static final String RECORD_STATUS = "RECORD_STATUS";
    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String PROMO_NO= "PROMO_NO";
    public static final String ITEM_CODE = "ITEM_CODE";
    public static final String DISC_METHOD = "DISC_METHOD";
    public static final String DISC_PERCENT = "DISC_PERCENT";
    public static final String DISC_AMOUNT = "DISC_AMOUNT";
    public static final String CREATION_USER_ID = "CREATION_USER_ID";
    public static final String CREATION_DATETIME = "CREATION_DATETIME";
    public static final String CHANGE_USER_ID = "CHANGE_USER_ID";
    public static final String CHANGE_DATETIME = "CHANGE_DATETIME";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;

    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + RECORD_STATUS+ " CHAR(1) null, "
            + COMPANY_CODE+ " VARCHAR(30) not null, "
            + PROMO_NO+ " VARCHAR(20) not null, "
            + ITEM_CODE+ " VARCHAR(30) null, "
            + DISC_METHOD+ " CHAR(1) null, "
            + DISC_PERCENT+ " INTEGER null, "
            + DISC_AMOUNT+ "  NUMERIC(24,4) null, "
            + CREATION_USER_ID+ " VARCHAR(10) null, "
            + CREATION_DATETIME+ " DATETIME null, "
            + CHANGE_USER_ID+ " VARCHAR(10) null, "
            + CHANGE_DATETIME+ " DATETIME null);";

    public mPromo1Helper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(mPromo1Helper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public  boolean IsPromoExist(String itemCode,String companycode) {
        db = this.getReadableDatabase();
        boolean isExist = false;

        String query = "SELECT DISTINCT pm0.PROMO_NO, PERIOD_FROM, PERIOD_TO FROM mPromo0 pm0 \n" +
                " LEFT JOIN mPromo1 pm1 \n" +
                " ON pm1.PROMO_NO = pm0.PROMO_NO and pm1.RECORD_STATUS = pm0.RECORD_STATUS \n" +
                " WHERE  ITEM_CODE = '" + itemCode + "' and (COMPANY_CODE= '" + companycode + "' or COMPANY_CODE= 0)\n" +
                //" AND (date('now') between strftime('%d-%m-%Y', PERIOD_FROM) AND strftime('%d-%m-%Y', PERIOD_TO))";
                " AND (strftime('%Y-%m-%d', datetime('now')) between strftime('%Y-%m-%d', PERIOD_FROM) AND strftime('%Y-%m-%d', PERIOD_TO))";

        Cursor cursor = db.rawQuery(query, null);
        String tmpPromoNo = "";

        if (cursor.moveToFirst()) {
            do {
                tmpPromoNo = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        if (tmpPromoNo.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public List<mPromo1Entity> SelectPromo(String itemCode, String companycode, String Filter) {
        List<mPromo1Entity> listPromo = new ArrayList<mPromo1Entity>();
        String query="SELECT DISTINCT pm1.ITEM_CODE,\n" +
                "       pm0.PROMO_NO, \n" +
                "       pm0.PROMO_NAME, \n" +
                "       DISC_METHOD, \n" +
                "       IFNULL((SELECT DISC_PERCENT  \n" +
                "                   FROM mPromo1 pro \n" +
                "                       WHERE pro.PROMO_NO = pm1.PROMO_NO AND pro.ITEM_CODE = pm1.ITEM_CODE\n" +
                "                       AND pro.RECORD_STATUS = 'A'  \n" +
                "                       AND ( pro.COMPANY_CODE = '" + companycode + "' OR pro.COMPANY_CODE = 0) \n" +
                "               ORDER BY pro.COMPANY_CODE DESC LIMIT 1 ),0) DISC_PERCENT ,\n" +
                "       IFNULL((SELECT DISC_AMOUNT  \n" +
                "                   FROM mPromo1 pro \n" +
                "                       WHERE pro.PROMO_NO = pm1.PROMO_NO AND pro.ITEM_CODE = pm1.ITEM_CODE\n" +
                "                       AND pro.RECORD_STATUS = 'A'  \n" +
                "                       AND ( pro.COMPANY_CODE = '" + companycode + "' OR pro.COMPANY_CODE = 0) \n" +
                "               ORDER BY pro.COMPANY_CODE DESC LIMIT 1 ),0) DISC_AMOUNT , \n" +
                "       pm0.PERIOD_FROM ,\n" +
                "       pm0.PERIOD_TO \n" +
                "   FROM mPromo0 pm0\n" +
                " LEFT JOIN mPromo1 pm1 ON pm1.PROMO_NO = pm0.PROMO_NO \n" +
                " WHERE  ITEM_CODE = '" + itemCode + "' and (COMPANY_CODE= '" + companycode + "' or COMPANY_CODE= 0)\n" +
                //" AND date('now') >= strftime('%d-%m-%Y', PERIOD_FROM) AND date('now') <= strftime('%d-%m-%Y', PERIOD_TO)";
                " AND (strftime('%Y-%m-%d', datetime('now')) between strftime('%Y-%m-%d', PERIOD_FROM) AND strftime('%Y-%m-%d', PERIOD_TO))";

        if (!Filter.equals("")){
            query = query + " " + Filter;
        }

        query = query + " " + " ORDER BY pm0.PROMO_NO";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                mPromo1Entity objData = new mPromo1Entity();

                objData.setITEM_CODE(cursor.getString(0));
                objData.setPROMO_NO(cursor.getString(1));
                objData.setPROMO_NAME(cursor.getString(2));
                objData.setDISC_METHOD(cursor.getString(3));
                objData.setDISC_PERCENT(cursor.getInt(4));
                objData.setDISC_AMOUNT(cursor.getDouble(5));
                objData.setPERIOD_FROM (java.sql.Timestamp.valueOf(cursor.getString(6)));
                objData.setPERIOD_TO (java.sql.Timestamp.valueOf(cursor.getString(7)));

                listPromo.add(objData);
            } while (cursor.moveToNext());
        }
        db.close();
        return listPromo;
    }

    public boolean isDataExist (String PromoNo, String CompanyCode, String ItemCode){
        boolean retValue = false;
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" where "+PROMO_NO+" = '"+PromoNo+"' and "+COMPANY_CODE+" = '"+CompanyCode+"' and "+ITEM_CODE+" = '"+ItemCode+"' ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        if(count>0)
            retValue = true;
        return retValue;
    }

    public void InsertPromo1(mPromo1Entity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(COMPANY_CODE,c.getCOMPANY_CODE());
        values.put(PROMO_NO,c.getPROMO_NO());
        values.put(ITEM_CODE,c.getITEM_CODE());
        values.put(DISC_METHOD,c.getDISC_METHOD());
        values.put(DISC_PERCENT,c.getDISC_PERCENT());
        values.put(DISC_AMOUNT,c.getDISC_AMOUNT());
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void UpdatePromo1(mPromo1Entity c){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(DISC_METHOD,c.getDISC_METHOD());
        values.put(DISC_PERCENT,c.getDISC_PERCENT());
        values.put(DISC_AMOUNT,c.getDISC_AMOUNT());
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        String[] whereArgs = new String[] {String.valueOf(c.getPROMO_NO()), String.valueOf(c.getCOMPANY_CODE()), String.valueOf(c.getITEM_CODE())};
        db.update(TABLE_NAME, values, PROMO_NO + "=? AND " + COMPANY_CODE + "=? AND " + ITEM_CODE + "=?",  whereArgs);
        db.close();
    }

}