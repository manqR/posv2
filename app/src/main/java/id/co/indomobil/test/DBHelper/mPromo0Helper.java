package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import id.co.indomobil.test.DBEntity.mPromo0Entity;

public class mPromo0Helper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "mPromo0";
    public static final String RECORD_STATUS = "RECORD_STATUS";
    public static final String PROMO_NO= "PROMO_NO";
    public static final String PROMO_NAME = "PROMO_NAME";
    public static final String PERIOD_FROM = "PERIOD_FROM";
    public static final String PERIOD_TO = "PERIOD_TO";
    public static final String PROMOTION_TYPE = "PROMOTION_TYPE";
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
            + PROMO_NO+ " VARCHAR(20) not null, "
            + PROMO_NAME+ " VARCHAR(100) null, "
            + PERIOD_FROM+ " DATETIME null, "
            + PERIOD_TO+ " DATETIME null, "
            + PROMOTION_TYPE+ " CHAR(1) null, "
            + CREATION_USER_ID+ " VARCHAR(10) null, "
            + CREATION_DATETIME+ " DATETIME null, "
            + CHANGE_USER_ID+ " VARCHAR(10) null, "
            + CHANGE_DATETIME+ " DATETIME null);";

    public mPromo0Helper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(mCityHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public boolean isPromoNoExist (String PromoNo){
        boolean retValue = false;
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" where "+PROMO_NO+" = '"+PromoNo+"' ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        if(count>0)
            retValue = true;
        return retValue;
    }

    public void InsertPromo0(mPromo0Entity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(PROMO_NO,c.getPROMO_NO());
        values.put(PROMO_NAME,c.getPROMO_NAME());
        values.put(PERIOD_FROM,String.valueOf(c.getPERIOD_FROM()));
        values.put(PERIOD_TO,String.valueOf(c.getPERIOD_TO()));
        values.put(PROMOTION_TYPE,c.getPROMOTION_TYPE());
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void UpdatePromo0(mPromo0Entity c){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(PROMO_NAME,c.getPROMO_NAME());
        values.put(PERIOD_FROM,String.valueOf(c.getPERIOD_FROM()));
        values.put(PERIOD_TO,String.valueOf(c.getPERIOD_TO()));
        values.put(PROMOTION_TYPE,c.getPROMOTION_TYPE());
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        String[] whereArgs = new String[] {String.valueOf(c.getPROMO_NO())};
        db.update(TABLE_NAME, values, PROMO_NO + "=?", whereArgs);
        db.close();
    }
}
