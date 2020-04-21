package id.co.indomobil.test.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.SyncDetailEntity;

public class SynDetailHelper extends SQLiteOpenHelper {


    private static final String TABLE_NAME = "SyncDetailTrans";
    private static final String COL1 = "idtrans";
    private static final String COL2 = "productID";
    private static final String COL3 = "price";
    private static final String COL4 = "qty";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;


    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + COL1 + " VARCHAR(1) null, "
            + COL2+ " VARCHAR(30) null, "
            + COL3 + " VARCHAR(30) null, "
            + COL4 + " VARCHAR(50) null);";


    private static final String TAG = "SynDetailHelper";

    public SynDetailHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        Log.d(TAG, "onCreate: "+TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SynDetailHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public boolean addDataDetail(Integer idtrans, Integer productID, Integer price, Integer qty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1, idtrans);
        contentValues.put(COL2, productID);
        contentValues.put(COL3, price);
        contentValues.put(COL4, qty);

        long result = db.insert(TABLE_NAME,null,contentValues);
        Log.d(TAG, "addData: Adding ID TRANS"+idtrans+" , PRODUCT_ID "+productID+", PRICE "+price+" , QTY "+qty+" to "+ TABLE_NAME);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    public static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        Log.d(TAG, "doesDatabaseExist: "+ dbFile.exists());
        return dbFile.exists();

    }

    public List<SyncDetailEntity> SelectDetailData(String criteria) {
        List<SyncDetailEntity> listData = new ArrayList<SyncDetailEntity>();
        if(!criteria.equals(""))
            criteria = " where " + criteria;
        String query = "SELECT idtrans,\n " +
                "       productID,\n " +
                "       price,\n " +
                "       qty\n " +

                "  FROM SyncDetailTrans "+ criteria;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //Cursor c = db.q
        if (cursor.moveToFirst()) {
            do {
                SyncDetailEntity objData = new SyncDetailEntity();
                objData.setTransID(cursor.getString(0));
                objData.setProductID(cursor.getString(1));
                objData.setPrice(cursor.getString(2));
                objData.setQty(cursor.getString(3));
                listData.add(objData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
//        Log.d(TAG, "SelectVoucherData: "+listData);
        return listData;

    }
    public static String getDBName(){
        return DATABASE_NAME;
    }
}
