package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

import id.co.indomobil.test.DBEntity.mPriceListEntity;

public class mPriceListHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "mPriceList";
    public static final String RECORD_STATUS = "RECORD_STATUS";
    public static final String ITEM_CODE = "ITEM_CODE";
    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String EFF_DATE = "EFF_DATE";
    public static final String PRICE_AMOUNT = "PRICE_AMOUNT";
    public static final String CREATION_USER_ID = "CREATION_USER_ID";
    public static final String CREATION_DATETIME = "CREATION_DATETIME";
    public static final String CHANGE_USER_ID = "CHANGE_USER_ID";
    public static final String CHANGE_DATETIME = "CHANGE_DATETIME";
    public static final String DATABASE_NAME="POS_MOBILE.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;

    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + RECORD_STATUS + " CHAR(1), "
            + ITEM_CODE + " VARCHAR(30) not null, "
            + COMPANY_CODE + " VARCHAR(30) not null, "
            + EFF_DATE + " DATETIME null, "
            + PRICE_AMOUNT+ " NUMERIC(18,4) null, "
            + CREATION_USER_ID + " VARCHAR(10) null, "
            + CREATION_DATETIME + " DATETIME null,"
            + CHANGE_USER_ID + " VARCHAR(10) null, "
            + CHANGE_DATETIME + " DATETIME null);";

    public mPriceListHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(mCategoryHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void InsertmPriceList(mPriceListEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(ITEM_CODE,c.getITEM_CODE());
        values.put(COMPANY_CODE,c.getCOMPANY_CODE());
        values.put(EFF_DATE,String.valueOf(c.getEFF_DATE()));
        values.put(PRICE_AMOUNT,String.valueOf(c.getPRICE_AMOUNT()));
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void UpdatemPriceList(mPriceListEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRICE_AMOUNT,String.valueOf(c.getPRICE_AMOUNT()));
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        db.update(TABLE_NAME, values,
                (COMPANY_CODE+"=? and "+ ITEM_CODE+"=? and "+ EFF_DATE+"=?"),
                (new String[] {String.valueOf(c.getCOMPANY_CODE()), c.getITEM_CODE(), c.getEFF_DATE().toString()}));
        db.close();
    }
    public boolean isItemPriceExist (String ItemCode, String CompanyCode, Date EffDate){
        boolean retValue = false;

        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" where "+ITEM_CODE+" = '"+ItemCode+"' and "+COMPANY_CODE+"='"+CompanyCode+"' and "+EFF_DATE+"='"+EffDate+"'";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        if(count>0)
            retValue = true;
        return retValue;
    }

    public  double GetItemPrice (String itemCode, String companyCode){
        db = this.getReadableDatabase();
        String query = "select ITEM_CODE,COMPANY_CODE,PRICE_AMOUNT FROM \n"+
                TABLE_NAME + " WHERE ( COMPANY_CODE = 0 OR COMPANY_CODE='" + companyCode +"' ) \n" +
                " AND ITEM_CODE='" + itemCode + "'  ORDER BY COMPANY_CODE DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query,null);
        double SellingPrice = 0 ;

        if(cursor.moveToFirst()){
            do {
                SellingPrice = cursor.getDouble(2);
                break;

            }while (cursor.moveToNext());
        }

        return SellingPrice;
    }
}