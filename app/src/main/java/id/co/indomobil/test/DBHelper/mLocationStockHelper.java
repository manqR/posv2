package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mLocationStockEntity;
import id.co.indomobil.test.Helper.TextHelper;

public class mLocationStockHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME0 = "mLocationStock";
    public static final String TABLE_NAME1 = "mLocationStockHistory";
    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String ITEM_CODE = "ITEM_CODE";
    public static final String PERIOD_YEAR = "PERIOD_YEAR";
    public static final String PERIOD_MONTH = "PERIOD_MONTH";
    public static final String QTY_BEGIN = "QTY_BEGIN";
    public static final String QTY_SALES = "QTY_SALES";
    public static final String QTY_SALES_RETURN = "QTY_SALES_RETURN";
    public static final String QTY_PURCHASE = "QTY_PURCHASE";
    public static final String QTY_ADJUSTMENT = "QTY_ADJUSTMENT";
    public static final String QTY_ALLOCATED = "QTY_ALLOCATED";
    public static final String QTY_END = "QTY_END";
    public static final String CREATION_USER_ID = "CREATION_USER_ID";
    public static final String CREATION_DATETIME = "CREATION_DATETIME";
    public static final String CHANGE_USER_ID = "CHANGE_USER_ID";
    public static final String CHANGE_DATETIME = "CHANGE_DATETIME";

    public static final String DATABASE_NAME = "POSv2.db";
    public static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;

    public static final String TABLE_CREATE0 = "CREATE TABLE "
            + TABLE_NAME0 + "("
            + COMPANY_CODE + " VARCHAR(30)  null, "
            + ITEM_CODE + " VARCHAR(30) null, "
            + QTY_BEGIN + " NUMERIC(18,2) Null, "
            + QTY_SALES + " NUMERIC(18,2) Null, "
            + QTY_SALES_RETURN + " NUMERIC(18,2) Null, "
            + QTY_PURCHASE + " NUMERIC(18,2) Null, "
            + QTY_ADJUSTMENT + " NUMERIC(18,2) Null, "
            + QTY_ALLOCATED + " NUMERIC(18,2) Null, "
            + QTY_END + " NUMERIC(18,2) Null, "
            + CREATION_USER_ID + " VARCHAR(10) null, "
            + CREATION_DATETIME + " DATETIME null,"
            + CHANGE_USER_ID + " VARCHAR(10) null, "
            + CHANGE_DATETIME + " DATETIME null);";

    public static final String TABLE_CREATE1 = "CREATE TABLE "
            + TABLE_NAME1 + "("
            + COMPANY_CODE + " VARCHAR(30)  null, "
            + PERIOD_YEAR + " VARCHAR(4) null, "
            + PERIOD_MONTH + " VARCHAR(2) null, "
            + ITEM_CODE + " VARCHAR(30) null, "
            + QTY_BEGIN + " NUMERIC(18,2) Null, "
            + QTY_SALES + " NUMERIC(18,2) Null, "
            + QTY_SALES_RETURN + " NUMERIC(18,2) Null, "
            + QTY_PURCHASE + " NUMERIC(18,2) Null, "
            + QTY_ADJUSTMENT + " NUMERIC(18,2) Null, "
            + QTY_ALLOCATED + " NUMERIC(18,2) Null, "
            + QTY_END + " NUMERIC(18,2) Null, "
            + CREATION_USER_ID + " VARCHAR(10) null, "
            + CREATION_DATETIME + " DATETIME null,"
            + CHANGE_USER_ID + " VARCHAR(10) null, "
            + CHANGE_DATETIME + " DATETIME null);";

    public mLocationStockHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE0);
        db.execSQL(TABLE_CREATE1);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(mCategoryHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME0);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        this.onCreate(db);
    }

    public void InsertLocationStock(mLocationStockEntity c) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            ContentValues values = new ContentValues();

            values.put(COMPANY_CODE, c.getCOMPANY_CODE());
            values.put(ITEM_CODE, c.getITEM_CODE());
            values.put(QTY_BEGIN, c.getQTY_BEGIN());
            values.put(QTY_SALES, c.getQTY_SALES());
            values.put(QTY_SALES_RETURN, c.getQTY_SALES_RETURN());
            values.put(QTY_PURCHASE, c.getQTY_PURCHASE());
            values.put(QTY_ADJUSTMENT, c.getQTY_ADJUSTMENT());
            values.put(QTY_ALLOCATED, c.getQTY_ALLOCATED());
            values.put(QTY_END, c.getQTY_END());
            values.put(CREATION_USER_ID, c.getCREATION_USER_ID());
            values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
            values.put(CHANGE_USER_ID, c.getCREATION_USER_ID());
            values.put(CHANGE_DATETIME, String.valueOf(c.getCREATION_DATETIME()));

            db.insert(TABLE_NAME0, null, values);
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("InsertLocationStock", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void InsertNewLocationStock(mLocationStockEntity c) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COMPANY_CODE, c.getCOMPANY_CODE());
            values.put(ITEM_CODE, c.getITEM_CODE());
            values.put(QTY_BEGIN, 0);
            values.put(QTY_SALES, 0);
            values.put(QTY_SALES_RETURN, 0);
            values.put(QTY_PURCHASE, 0);
            values.put(QTY_ADJUSTMENT, 0);
            values.put(QTY_ALLOCATED, 0);
            values.put(QTY_END, 0);
            values.put(CREATION_USER_ID, c.getCREATION_USER_ID());
            values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
            values.put(CHANGE_USER_ID, c.getCREATION_USER_ID());
            values.put(CHANGE_DATETIME, String.valueOf(c.getCREATION_DATETIME()));

            db.insert(TABLE_NAME0, null, values);
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("InsertNewLocationStock", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public List<mLocationStockEntity> SelectStockData(String criteria) {
        List<mLocationStockEntity> listData = new ArrayList<mLocationStockEntity>();
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (!criteria.equals(""))
                criteria = " where " + criteria;
            String query = "SELECT COMPANY_CODE,\n " +
                    "       ITEM_CODE,\n " +
                    "       QTY_BEGIN,\n " +
                    "       QTY_SALES,\n " +
                    "       QTY_SALES_RETURN,\n " +
                    "       QTY_PURCHASE,\n " +
                    "       QTY_ADJUSTMENT,\n" +
                    "       QTY_ALLOCATED,\n" +
                    "       QTY_END,\n " +
                    "       CREATION_USER_ID,\n " +
                    "       CREATION_DATETIME, \n " +
                    "       CHANGE_USER_ID,\n " +
                    "       CHANGE_DATETIME\n " +
                    "  FROM mLocationStock " + criteria;

            Cursor cursor = db.rawQuery(query, null);
            //Cursor c = db.q
            TextHelper textHelper = new TextHelper();
            if (cursor.moveToFirst()) {
                do {
                    mLocationStockEntity objData = new mLocationStockEntity();
                    objData.setCOMPANY_CODE(cursor.getString(0));
                    objData.setITEM_CODE(cursor.getString(1));
                    objData.setQTY_BEGIN(cursor.getDouble(2));
                    objData.setQTY_SALES(cursor.getDouble(3));
                    objData.setQTY_SALES_RETURN(cursor.getDouble(4));
                    objData.setQTY_PURCHASE(cursor.getDouble(5));
                    objData.setQTY_ADJUSTMENT(cursor.getDouble(6));
                    objData.setQTY_ALLOCATED(cursor.getDouble(7));
                    objData.setQTY_END(cursor.getDouble(8));
                    objData.setCREATION_USER_ID(cursor.getString(9));
                    objData.setCREATION_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(10)));
                    objData.setCHANGE_USER_ID(cursor.getString(11));
                    objData.setCHANGE_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(12)));

                    listData.add(objData);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("SelectStockData", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return listData;
        }
    }

    public void UpdateStock(mLocationStockEntity c) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = " UPDATE \n"
                    + TABLE_NAME0
                    + " SET QTY_END = '" + c.getQTY_END() + "' "
                    + ", CHANGE_USER_ID ='" + c.getCHANGE_USER_ID()
                    + "', CHANGE_DATETIME ='" + String.valueOf(c.getCHANGE_DATETIME())
                    + "' WHERE COMPANY_CODE ='" + String.valueOf(c.getCOMPANY_CODE()) + "' AND ITEM_CODE ='" + String.valueOf(c.getITEM_CODE()) + "' ";
            db.execSQL(query);
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("UpdateLocStock", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void UpdateLocationStock(String companyCode, mLocationStockEntity mLocStock) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = " UPDATE \n"
                    + TABLE_NAME0
                    + " SET QTY_BEGIN = QTY_BEGIN + " + Double.toString(mLocStock.getQTY_BEGIN()) + "\n"
                    + " , QTY_SALES = QTY_SALES + " + Double.toString(mLocStock.getQTY_SALES()) + "\n"
                    + " , QTY_SALES_RETURN = QTY_SALES_RETURN +" + Double.toString(mLocStock.getQTY_SALES_RETURN()) + "\n"
                    + " , QTY_PURCHASE = QTY_PURCHASE +" + Double.toString(mLocStock.getQTY_PURCHASE()) + "\n"
                    + " , QTY_ADJUSTMENT = QTY_ADJUSTMENT +" + Double.toString(mLocStock.getQTY_ADJUSTMENT()) + "\n"
                    + " , QTY_ALLOCATED = QTY_ALLOCATED +" + Double.toString(mLocStock.getQTY_ALLOCATED()) + "\n"
                    + " , QTY_END = QTY_END +" + Double.toString(mLocStock.getQTY_END()) + "\n"
                    + " WHERE COMPANY_CODE= '" + companyCode + "' AND ITEM_CODE='" + mLocStock.getITEM_CODE() + "'";
            db.execSQL(query);
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("UpdateLocStock", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void UpdateSales1COGS(String companyCode, Integer salesSysNo, String itemCode) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = " UPDATE tSales1 \n" +
                    " SET COGS = IFNULL((SELECT AVERAGE_COST FROM mItem1 WHERE mItem1.ITEM_CODE= tSales1.ITEM_CODE \n" +
                    " AND mItem1.COMPANY_CODE='" + companyCode + "'),0) \n" +
                    " , SALES_DOC_NO = IFNULL((SELECT SALES_DOC_NO FROM tSales0 WHERE tSales0.SALES_SYS_NO= tSales1.SALES_SYS_NO \n" +
                    " AND tSales0.COMPANY_CODE='" + companyCode + "'),'') \n" +
                    " WHERE SALES_SYS_NO = " + Integer.toString(salesSysNo) + " AND ITEM_CODE='" + itemCode + "'";
            db.execSQL(query);
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("UpdateSales1COGS", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void UpdateStockFromSalesPayment(String companyCode, Integer SysNo) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = "SELECT  "
                    + " S1.ITEM_CODE,  IFNULL(S1.QUANTITY,0), CATEGORY_CODE  "
                    + " FROM  tSales1 S1 LEFT JOIN mItem0 M0 ON M0.ITEM_CODE = S1.ITEM_CODE" +
                    " WHERE SALES_SYS_NO = " + SysNo.toString();

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    mLocationStockEntity item = new mLocationStockEntity();

                    item.setCOMPANY_CODE(companyCode);
                    item.setITEM_CODE(cursor.getString(0));
                    item.setQTY_BEGIN(0);
                    item.setQTY_SALES(cursor.getDouble(1));
                    item.setQTY_SALES_RETURN(0);
                    item.setQTY_PURCHASE(0);
                    item.setQTY_ADJUSTMENT(0);
                    item.setQTY_ALLOCATED(0);
                    item.setQTY_END(cursor.getDouble(1) * (-1));
                    item.setCATEGORY_CODE(cursor.getString(2));

                    String SubsCatCode = item.getCATEGORY_CODE().substring(0, 2);
                    if (!SubsCatCode.equals("JS")) {
                        this.UpdateLocationStock(companyCode, item);
                        //this.UpdateSales1COGS(companyCode, SysNo, cursor.getString(0));
                    }

                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("UpdateStockFrSlsPay", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void UpdateStockFromGRPO(String companyCode, Integer SysNo, String UserId) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = "SELECT  "
                    + " ITEM_CODE,  ifnull(QUANTITY,0) "
                    + " FROM  tGRPO1 WHERE GRPO_SYS_NO = " + SysNo.toString();

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    mLocationStockEntity item = new mLocationStockEntity();

                    item.setCOMPANY_CODE(companyCode);
                    item.setITEM_CODE(cursor.getString(0));
                    item.setQTY_BEGIN(0);
                    item.setQTY_SALES(0);
                    item.setQTY_SALES_RETURN(0);
                    item.setQTY_PURCHASE(cursor.getDouble(1));
                    item.setQTY_ADJUSTMENT(0);
                    item.setQTY_ALLOCATED(0);
                    item.setQTY_END(cursor.getDouble(1));
                    item.setCREATION_DATETIME(new java.sql.Timestamp(System.currentTimeMillis()));
                    item.setCREATION_USER_ID(UserId);

                    if (this.isLocationStockExist(companyCode, item.getITEM_CODE().toString())) {
                        this.UpdateLocationStock(companyCode, item);
                    } else {
                        this.InsertLocationStock(item);
                    }

                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("UpdateStockFromGRPO", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void UpdateStockFromSR(String companyCode, Integer SysNo) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = "SELECT  "
                    + " SR1.ITEM_CODE,  ifnull(SR1.QUANTITY_RETURN,0), CATEGORY_CODE "
                    + " FROM  tSalesReturn1 SR1 LEFT JOIN mItem0 M0 ON M0.ITEM_CODE = SR1.ITEM_CODE " +
                    " WHERE SALES_RETURN_SYS_NO = " + SysNo.toString();

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    mLocationStockEntity item = new mLocationStockEntity();

                    item.setCOMPANY_CODE(companyCode);
                    item.setITEM_CODE(cursor.getString(0));
                    item.setQTY_BEGIN(0);
                    item.setQTY_SALES(0);
                    item.setQTY_SALES_RETURN(cursor.getDouble(1));
                    item.setQTY_PURCHASE(0);
                    item.setQTY_ADJUSTMENT(0);
                    item.setQTY_ALLOCATED(0);
                    item.setQTY_END(cursor.getDouble(1));
                    item.setCATEGORY_CODE(cursor.getString(2));

                    String SubsCatCode = item.getCATEGORY_CODE().substring(0, 2);
                    if (!SubsCatCode.equals("JS")) {
                        if (this.isLocationStockExist(companyCode, item.getITEM_CODE().toString())) {
                            this.UpdateLocationStock(companyCode, item);
                        } else {
                            this.InsertLocationStock(item);
                        }
                    }

                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("UpdateStockFromSR", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void UpdateStockFromAdj(String companyCode, Integer SysNo, String UserId, Timestamp createdDateTime) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = "SELECT  "
                    + " ITEM_CODE,  ifnull(ADJ_QTY,0) "
                    + " FROM  tAdjustment1 WHERE ADJ_SYS_NO = " + SysNo.toString();

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    mLocationStockEntity item = new mLocationStockEntity();

                    item.setCOMPANY_CODE(companyCode);
                    item.setITEM_CODE(cursor.getString(0));
                    item.setQTY_BEGIN(0);
                    item.setQTY_SALES(0);
                    item.setQTY_SALES_RETURN(0);
                    item.setQTY_PURCHASE(0);
                    item.setQTY_ADJUSTMENT(cursor.getDouble(1));
                    item.setQTY_ALLOCATED(0);
                    item.setQTY_END(cursor.getDouble(1));
                    item.setCREATION_USER_ID(UserId);
                    item.setCREATION_DATETIME(createdDateTime);
                    item.setCHANGE_USER_ID(UserId);
                    item.setCHANGE_DATETIME(createdDateTime);

                    if (this.isLocationStockExist(companyCode, item.getITEM_CODE().toString())) {
                        this.UpdateLocationStock(companyCode, item);
                    } else {
                        this.InsertLocationStock(item);
                    }
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("UpdateStockFromAdj", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public Double GetCurrentStock(String companyCode, String itemCode) {
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        Double dQtyStock = (double) 0;
        try {
            String query = " SELECT QTY_END FROM mLocationStock WHERE COMPANY_CODE= '" + String.valueOf(companyCode) + "'\n"
                    + " AND ITEM_CODE = '" + itemCode + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dQtyStock = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("GetCurrentStock", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dQtyStock;
        }
    }

    public boolean isLocationStockExist(String companyCode, String itemCode) {
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        int count = 0;
        try {
            String query = "SELECT * FROM " + TABLE_NAME0 + " WHERE COMPANY_CODE='" + companyCode + "'\n" +
                    " AND ITEM_CODE='" + itemCode + "'";
            Cursor cursor = db.rawQuery(query, null);
            count = cursor.getCount();
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("isLocationStockExist", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean IsItemAlreadyExist(String companyCode, String itemCode) {
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        String tmpItemCode = "";
        try {
            boolean isExist = false;
            String query = " SELECT ITEM_CODE FROM " + TABLE_NAME0 + " WHERE COMPANY_CODE='" + companyCode + "'\n" +
                    " AND ITEM_CODE='" + itemCode + "'";
            Cursor cursor = db.rawQuery(query, null);

            Integer nextLineNo = 0;

            if (cursor.moveToFirst()) {
                do {
                    tmpItemCode = cursor.getString(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("IsItemAlreadyExist", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            if (tmpItemCode.equals(itemCode)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public List<mLocationStockEntity> SelectStock(String companycode) {
        List<mLocationStockEntity> listData = new ArrayList<mLocationStockEntity>();
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = "SELECT ITEM_CODE ," +
                    "               QTY_END, " +
                    "               ITEM_CODE, " +
                    "       FROM mLocationStock " +
                    "               WHERE COMPANY_CODE = '" + companycode + "' ORDER BY ITEM_CODE DESC LIMIT 6";

            query = "SELECT ls.ITEM_CODE , " +
                    " QTY_END ,  " +
                    " ITEM_NAME         " +
                    "FROM mLocationStock ls " +
                    "LEFT JOIN mItem0 mi " +
                    "ON mi.ITEM_CODE = ls.ITEM_CODE " +
                    "WHERE ls.COMPANY_CODE = '" + companycode + "' ORDER BY ls.ITEM_CODE DESC";

            Cursor cursor = db.rawQuery(query, null);
            mLocationStockEntity m = new mLocationStockEntity();
            if (cursor.moveToFirst()) {
                do {
                    mLocationStockEntity objData = new mLocationStockEntity();
                    objData.setITEM_CODE(cursor.getString(0));
                    objData.setQTY_END(cursor.getDouble(1));
                    objData.setITEM_NAME(cursor.getString(2));
                    listData.add(objData);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("SelectStock", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return listData;
        }
    }
}