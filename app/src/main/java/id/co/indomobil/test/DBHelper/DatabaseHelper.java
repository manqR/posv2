package id.co.indomobil.test.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

public class DatabaseHelper  extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "SyncMasterTrans";
    private static final String COL1 = "idtrans";
    private static final String COL2 = "total_price";
    private static final String COL3 = "tanggal";
    private static final String COL4 = "is_sync";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS  "+ TABLE_NAME + "("+COL1+" INTEGER PRIMARY KEY ," +COL2 + " INTEGER, " +
                                                               COL3 +" INTEGER, "+ COL4 + " INTEGER)";
        db.execSQL(createTable);
        Log.d(TAG, "onCreate: "+ SynDetailHelper.TABLE_CREATE);
        db.execSQL(SynDetailHelper.TABLE_CREATE );
        db.execSQL(mCategoryHelper.TABLE_CREATE);
        db.execSQL(mCompanyHelper.TABLE_CREATE );
        db.execSQL(mEmployeeHelper.TABLE_CREATE );
        db.execSQL(mLocationStockHelper.TABLE_CREATE0);
        db.execSQL(mLocationStockHelper.TABLE_CREATE1);
        db.execSQL(mPriceListHelper.TABLE_CREATE);
        db.execSQL(mCityHelper.TABLE_CREATE);
        db.execSQL(mComGenVariableHelper.TABLE_CREATE);
        db.execSQL(mItemHelper.TABLE_CREATE);
        db.execSQL(mMixMatchHelper.TABLE_CREATE);
        db.execSQL(mPromo0Helper.TABLE_CREATE);
        db.execSQL(mPromo1Helper.TABLE_CREATE);
        db.execSQL(tGRPO0Helper.TABLE_CREATE);
        db.execSQL(tPaymentHelper.TABLE_CREATE);
        db.execSQL(tPO0Helper.TABLE_CREATE);
        db.execSQL(tSales1Helper.TABLE_CREATE);
        db.execSQL(tShiftLogHelper.TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(Integer idtrans, Integer total_price, Integer tanggal, Integer is_sync){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1, idtrans);
        contentValues.put(COL2, total_price);
        contentValues.put(COL3, tanggal);
        contentValues.put(COL4, is_sync);


        long result = db.insert(TABLE_NAME,null,contentValues);
        Log.d(TAG, "addData: Adding ID TRANS"+idtrans+" , totalPrice "+total_price+", Tanggal "+tanggal+" , Is_Sync "+is_sync+" to "+ TABLE_NAME);
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
    public static String getDBName(){
        return DATABASE_NAME;
    }
}
