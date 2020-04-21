package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.co.indomobil.test.DBEntity.tSalesEntity;
import id.co.indomobil.test.Helper.TextHelper;

public class tSalesHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "tSales0";
    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String SALES_DATE = "SALES_DATE";
    public static final String SALES_SYS_NO = "SALES_SYS_NO";
    public static final String SALES_DOC_NO = "SALES_DOC_NO";
    public static final String SALES_STATUS = "SALES_STATUS";
    public static final String CUSTOMER_CODE = "CUSTOMER_CODE";
    public static final String MOBILE_PHONE_NO = "MOBILE_PHONE_NO";
    public static final String TNKB = "TNKB";
    public static final String TOTAL = "TOTAL";
    public static final String TOTAL_DISC = "TOTAL_DISC";
    public static final String GRAND_TOTAL = "GRAND_TOTAL";
    public static final String DPP = "DPP";
    public static final String VAT = "VAT";
    public static final String SHIFT_NO = "SHIFT_NO";
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
            + SALES_DATE + " DATETIME null, "
            + SALES_SYS_NO + " INTEGER not null, "
            + SALES_DOC_NO + " VARCHAR(20) null, "
            + SALES_STATUS + " VARCHAR(10) null, "
            + CUSTOMER_CODE + " VARCHAR(10)  null, "
            + MOBILE_PHONE_NO + " VARCHAR(50) null, "
            + TNKB + " VARCHAR(10)  null, "
            + TOTAL + " NUMERIC(18,4) null, "
            + TOTAL_DISC + " NUMERIC(18,4) null, "
            + GRAND_TOTAL + " NUMERIC(18,4) null, "
            + DPP + " NUMERIC(18,4) null, "
            + VAT + " NUMERIC(18,4) null, "
            + SHIFT_NO + " VARCHAR(15)  null, "
            + CREATION_USER_ID + " VARCHAR(10) null, "
            + CREATION_DATETIME + " DATETIME null,"
            + CHANGE_USER_ID + " VARCHAR(10) null, "
            + CHANGE_DATETIME + " DATETIME null);";

    public tSalesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    public void InsertSales0(tSalesEntity c) {
        db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COMPANY_CODE, c.getCOMPANY_CODE());
            values.put(SALES_DATE, String.valueOf(c.getSALES_DATE()));
            values.put(SALES_SYS_NO, c.getSALES_SYS_NO());
            values.put(SALES_DOC_NO, c.getSALES_DOC_NO());
            values.put(SALES_STATUS, c.getSALES_STATUS());
            values.put(CUSTOMER_CODE, c.getCUSTOMER_CODE());
            values.put(MOBILE_PHONE_NO, c.getMOBILE_PHONE_NO());
            values.put(TNKB, c.getTNKB());
            values.put(TOTAL, c.getTOTAL().toString());
            values.put(TOTAL_DISC, c.getTOTAL_DISC().toString());
            values.put(GRAND_TOTAL, c.getGRAND_TOTAL().toString());
            values.put(DPP, c.getDPP().toString());
            values.put(VAT, c.getVAT().toString());
            values.put(SHIFT_NO, c.getSHIFT_NO());
            values.put(CREATION_USER_ID, c.getCREATION_USER_ID());
            values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
            values.put(CHANGE_USER_ID, c.getCHANGE_USER_ID());
            values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

            db.insert(TABLE_NAME, null, values);
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("InsertSales0", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /*public List<tSalesEntity> SelectAllData(String criteria) {
        List<tSalesEntity> listData = new ArrayList<tSalesEntity>();
        if (!criteria.equals(""))
            criteria = " where " + criteria;
        String query = "SELECT so.COMPANY_CODE,\n " +
                "       SALES_SYS_NO,\n " +
                "       SALES_DOC_NO,\n " +
                "       SALES_STATUS,\n " +
                "       so.CUSTOMER_CODE,\n " +
                "       CUSTOMER_NAME,\n " +
                "       TOTAL,\n " +
                "       GRAND_TOTAL,\n " +
                "       DPP,\n " +
                "       VAT,\n " +
                "       so.CREATION_USER_ID,\n " +
                "       so.CREATION_DATETIME\n " +
                "  FROM tsales0 so LEFT JOIN\n " +
                "  mCUSTOMER cu ON cu.CUSTOMER_CODE = so.CUSTOMER_CODE";
*/
    public List<tSalesEntity> SelectAllData(String Sort, String Filter) {
        List<tSalesEntity> listData = new ArrayList<tSalesEntity>();
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = "SELECT so.COMPANY_CODE,\n " +
                    "       SALES_DATE,\n " +
                    "       so.SALES_SYS_NO,\n " +
                    "       SALES_DOC_NO,\n " +
                    "       SALES_STATUS,\n " +
                    "       so.CUSTOMER_CODE,\n " +
                    "       CUSTOMER_NAME,\n " +
                    "       TOTAL,\n " +
                    "       GRAND_TOTAL,\n " +
                    "       DPP,\n " +
                    "       VAT,\n " +
                    "       so.CREATION_USER_ID,\n " +
                    "       so.CREATION_DATETIME,\n " +
                    "       so.SHIFT_NO, \n " +
                    "       p.PAYMENT_AMOUNT, \n" +
                    "       p.CHANGE_AMOUNT \n" +
                    "  FROM tsales0 so LEFT JOIN\n " +
                    "  mCUSTOMER cu ON cu.CUSTOMER_CODE = so.CUSTOMER_CODE  LEFT JOIN \n" +
                    "  tPayment p ON p.SALES_SYS_NO = so.SALES_SYS_NO ";

            if (!Filter.equals("")) {
                query = query + Filter;
            }
            if (!Sort.equals("")) {
                Sort = " Order By " + Sort;
                query = query + Sort;
            }

            Cursor cursor = db.rawQuery(query, null);
            //Cursor c = db.q
            TextHelper textHelper = new TextHelper();
            if (cursor.moveToFirst()) {
                do {
                    tSalesEntity objData = new tSalesEntity();


                    objData.setCOMPANY_CODE(cursor.getString(0));
                    objData.setSALES_DATE(java.sql.Timestamp.valueOf(cursor.getString(1)));
                    //objData.setSALES_DATE(DateHelper.getSqlDateAndTime(cursor.getString(1)));
                    objData.setSALES_SYS_NO(cursor.getInt(2));
                    objData.setSALES_DOC_NO(cursor.getString(3));
                    objData.setSALES_STATUS(cursor.getString(4));
                    objData.setCUSTOMER_CODE(cursor.getString(5));
                    objData.setCUSTOMER_NAME(cursor.getString(6));
                    objData.setTOTAL(cursor.getDouble(7));
                    objData.setGRAND_TOTAL(cursor.getDouble(8));
                    objData.setDPP(cursor.getDouble(9));
                    objData.setVAT(cursor.getDouble(10));
                    objData.setCREATION_USER_ID(cursor.getString(11));
                    //objData.setCREATION_DATETIME(new Date(cursor.getLong(12)));
                    objData.setCREATION_DATETIME(new Timestamp(cursor.getLong(12)));
                    objData.setSHIFT_NO(cursor.getString(13));
                    objData.setPAYMENT_AMOUNT(cursor.getDouble(14));
                    objData.setCHANGE_AMOUNT(cursor.getDouble(15));
                    listData.add(objData);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("SelectAllData", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return listData;
        }
    }

    public List<tSalesEntity> SelectSales0Data(String criteria) {
        List<tSalesEntity> listData = new ArrayList<tSalesEntity>();
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (!criteria.equals(""))
                criteria = " where " + criteria;
            String query = "SELECT COMPANY_CODE,\n " +
                    "       SALES_DATE,\n " +
                    "       SALES_SYS_NO,\n " +
                    "       SALES_DOC_NO,\n " +
                    "       SALES_STATUS,\n " +
                    "       CUSTOMER_CODE,\n " +
                    "       MOBILE_PHONE_NO,\n" +
                    "       TNKB,\n" +
                    "       TOTAL,\n " +
                    "       TOTAL_DISC,\n " +
                    "       GRAND_TOTAL,\n " +
                    "       DPP,\n " +
                    "       VAT,\n " +
                    "       SHIFT_NO,\n " +
                    "       CREATION_USER_ID,\n " +
                    "       CREATION_DATETIME,\n " +
                    "       CHANGE_USER_ID,\n " +
                    "       CHANGE_DATETIME\n " +
                    "  FROM tsales0 " + criteria;

            Cursor cursor = db.rawQuery(query, null);
            //Cursor c = db.q
            TextHelper textHelper = new TextHelper();
            if (cursor.moveToFirst()) {
                do {
                    tSalesEntity objData = new tSalesEntity();
                    objData.setCOMPANY_CODE(cursor.getString(0));
                    objData.setSALES_DATE(java.sql.Timestamp.valueOf(cursor.getString(1)));
                    objData.setSALES_SYS_NO(cursor.getInt(2));
                    objData.setSALES_DOC_NO(cursor.getString(3));
                    objData.setSALES_STATUS(cursor.getString(4));
                    objData.setCUSTOMER_CODE(cursor.getString(5));
                    objData.setMOBILE_PHONE_NO(cursor.getString(6));
                    objData.setTNKB(cursor.getString(7));
                    objData.setTOTAL(cursor.getDouble(8));
                    objData.setTOTAL_DISC(cursor.getDouble(9));
                    objData.setGRAND_TOTAL(cursor.getDouble(10));
                    objData.setDPP(cursor.getDouble(11));
                    objData.setVAT(cursor.getDouble(12));
                    objData.setSHIFT_NO(cursor.getString(13));
                    objData.setCREATION_USER_ID(cursor.getString(14));
                    objData.setCREATION_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(15)));
                    objData.setCHANGE_USER_ID(cursor.getString(16));
                    objData.setCHANGE_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(17)));
                    listData.add(objData);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("SelectSales0Data", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return listData;
        }
    }

    public tSalesEntity SelectSalesDocData(String criteria) {
        tSalesEntity tSales = new tSalesEntity();
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (!criteria.equals(""))
                criteria = " where " + criteria;
            String query = " SELECT SALES_DOC_NO, SALES_DATE, EMPLOYEE_NAME, CUSTOMER_NAME, sls.TNKB, sls.MOBILE_PHONE_NO FROM tsales0 sls \n " +
                    "       LEFT JOIN mEmployee emp ON emp.EMPLOYEE_NO = sls.CREATION_USER_ID \n " +
                    "       LEFT JOIN mCustomer cus ON cus.CUSTOMER_CODE = sls.CUSTOMER_CODE \n " + criteria;

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    tSalesEntity objData = new tSalesEntity();
                    tSales.setSALES_DOC_NO(cursor.getString(0));
                    tSales.setSALES_DATE(java.sql.Timestamp.valueOf(cursor.getString(1)));
                    tSales.setSTAFF_NAME(cursor.getString(2));
                    tSales.setCUSTOMER_NAME(cursor.getString(3));
                    tSales.setTNKB(cursor.getString(4));
                    tSales.setMOBILE_PHONE_NO(cursor.getString(5));
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("SelectSalesDocData", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return tSales;
        }
    }

    public tSalesEntity SelectSales0DataEntity(String criteria) {
        tSalesEntity tSales = new tSalesEntity();
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (!criteria.equals(""))
                criteria = " where " + criteria;
            String query = "SELECT COMPANY_CODE,\n " +
                    "       SALES_DATE,\n " +
                    "       SALES_SYS_NO,\n " +
                    "       SALES_DOC_NO,\n " +
                    "       SALES_STATUS,\n " +
                    "       CUSTOMER_CODE,\n " +
                    "       MOBILE_PHONE_NO,\n" +
                    "       TNKB,\n" +
                    "       TOTAL,\n " +
                    "       TOTAL_DISC,\n " +
                    "       GRAND_TOTAL,\n " +
                    "       DPP,\n " +
                    "       VAT,\n " +
                    "       SHIFT_NO,\n " +
                    "       CREATION_USER_ID,\n " +
                    "       CREATION_DATETIME,\n " +
                    "       CHANGE_USER_ID,\n " +
                    "       CHANGE_DATETIME\n " +
                    "  FROM tsales0 " + criteria;

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    tSalesEntity objData = new tSalesEntity();
                    tSales.setCOMPANY_CODE(cursor.getString(0));
                    tSales.setSALES_DATE(java.sql.Timestamp.valueOf(cursor.getString(1)));
                    tSales.setSALES_SYS_NO(cursor.getInt(2));
                    tSales.setSALES_DOC_NO(cursor.getString(3));
                    tSales.setSALES_STATUS(cursor.getString(4));
                    tSales.setCUSTOMER_CODE(cursor.getString(5));
                    tSales.setMOBILE_PHONE_NO(cursor.getString(6));
                    tSales.setTNKB(cursor.getString(7));
                    tSales.setTOTAL(cursor.getDouble(8));
                    tSales.setTOTAL_DISC(cursor.getDouble(9));
                    tSales.setGRAND_TOTAL(cursor.getDouble(10));
                    tSales.setDPP(cursor.getDouble(11));
                    tSales.setVAT(cursor.getDouble(12));
                    tSales.setSHIFT_NO(cursor.getString(13));
                    tSales.setCREATION_USER_ID(cursor.getString(14));
                    tSales.setCREATION_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(15)));
                    tSales.setCHANGE_USER_ID(cursor.getString(16));
                    tSales.setCHANGE_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(17)));
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("SelectSales0DataEntity", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return tSales;
        }
    }

    public void UpdateSales0(tSalesEntity c) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SALES_DOC_NO, c.getSALES_DOC_NO());
            values.put(CUSTOMER_CODE, c.getCUSTOMER_CODE());
            values.put(MOBILE_PHONE_NO, c.getMOBILE_PHONE_NO());
            values.put(TNKB, c.getTNKB());
            values.put(SALES_STATUS, c.getSALES_STATUS());
            values.put(TOTAL, c.getTOTAL());
            values.put(TOTAL_DISC, c.getTOTAL_DISC());
            values.put(GRAND_TOTAL, c.getGRAND_TOTAL());
            values.put(CHANGE_USER_ID, c.getCHANGE_USER_ID());
            values.put(CHANGE_DATETIME, c.getCHANGE_DATETIME().toString());

            String[] whereArgs = new String[]{String.valueOf(c.getSALES_SYS_NO())};
            db.update(TABLE_NAME, values, SALES_SYS_NO + "=?", whereArgs);
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("UpdateSales0", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void DeleteSales(tSalesEntity c) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String[] whereArgs = new String[]{String.valueOf(c.getSALES_SYS_NO())};
            db.delete(TABLE_NAME, SALES_SYS_NO + "=?", whereArgs);
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("DeleteSales", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public Integer GetNextSalesSysNO() {
        Integer nextSysNo = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = " SELECT ifnull(MAX(SALES_SYS_NO),'0') FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            String sSysNo;

            if (cursor.moveToFirst()) {
                do {
                    sSysNo = cursor.getString(0);
                    nextSysNo = Integer.valueOf(sSysNo) + 1;
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("GetNextSalesSysNO", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return nextSysNo;
        }
    }

    public double GetTotalSalesByDate(String companyCode, Date SalesDate, String cashierId, String shiftno) {
        double dTotal = 0;
        Integer nextSysNo = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String sYear = (String) android.text.format.DateFormat.format("yyyy", SalesDate);
            String sMonth = (String) android.text.format.DateFormat.format("MM", SalesDate);
            String sDay = (String) android.text.format.DateFormat.format("dd", SalesDate);

       /* String query = " SELECT SUM(GRAND_TOTAL) FROM " + TABLE_NAME + " WHERE COMPANY_CODE=" + String.valueOf(companyCode) +
                " AND SALES_DATE='" + sYear +"-"+  sMonth +"-" + sDay +"' AND CREATION_USER_ID='" + cashierId +"'";*/

            String query = " SELECT SUM(GRAND_TOTAL) FROM TSALES0 sls " +
                    " LEFT OUTER JOIN TSHIFTLOG shf ON shf.SHIFT_NO = sls.SHIFT_NO and shf.COMPANY_CODE = sls.COMPANY_CODE " +
                    " WHERE shf.COMPANY_CODE = '" + companyCode + "' AND shf.SHIFT_NO = '" + String.valueOf(shiftno) + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("GetNextSalesSysNO", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayNetSalesThisMonth(String CompanyCode, String Month) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Month.length() == 1) {
                Month = "0" + Month;
            }

            String query = " SELECT  ifnull(SUM(S0.GRAND_TOTAL),0) - ifnull(SUM(R0.GRAND_TOTAL),0) FROM tSales0 S0 LEFT JOIN TSALESRETURN0 R0 " +
                    " ON S0.COMPANY_CODE = R0.COMPANY_CODE AND S0.SALES_DOC_NO = R0.SALES_DOC_NO " +
                    " WHERE S0.company_code = '" + CompanyCode + "' and strftime('%m', sales_date) = '" + Month + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("SummayNetSalesThisMonth", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayNetSalesTodayPeriodicly(String CompanyCode, String sToday) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = " SELECT ifnull(SUM(GRAND_TOTAL),0) FROM tsales0 " +
                    " WHERE company_code = '" + CompanyCode + "' and date(SALES_DATE) = '" + sToday + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("SlctSumNetSlsTdayPerdic", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayNetSalesThisWeek(String CompanyCode, String sStartDate, String sEndDate) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = " SELECT  ifnull(SUM(S0.GRAND_TOTAL),0) - ifnull(SUM(R0.GRAND_TOTAL),0) FROM tSales0 S0 LEFT JOIN TSALESRETURN0 R0 " +
                    " ON S0.COMPANY_CODE = R0.COMPANY_CODE AND S0.SALES_DOC_NO = R0.SALES_DOC_NO " +
                    " WHERE S0.company_code = '" + CompanyCode + "' and date(sales_date) between '" + sStartDate + "' and '" + sEndDate + "' ";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumNetSlsTdayPerdic", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayNetSalesThisMonthDaily(String CompanyCode, String Date, String Month) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Date.length() == 1) {
                Date = "0" + Date;
            }

            if (Month.length() == 1) {
                Month = "0" + Month;
            }

            String query = " SELECT  ifnull(SUM(S0.GRAND_TOTAL),0) - ifnull(SUM(R0.GRAND_TOTAL),0) FROM tSales0 S0 LEFT JOIN TSALESRETURN0 R0 " +
                    " ON S0.COMPANY_CODE = R0.COMPANY_CODE AND S0.SALES_DOC_NO = R0.SALES_DOC_NO " +
                    " WHERE S0.company_code = '" + CompanyCode + "' and strftime('%d', sales_date) = '" + Date + "' and strftime('%m', sales_date) = '" + Month + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumNetSlsMonthDaily", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayNetSalesThisMonthDailySplit(String CompanyCode, String Date, String Month, String Filter) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Date.length() == 1) {
                Date = "0" + Date;
            }

            if (Month.length() == 1) {
                Month = "0" + Month;
            }

            String query = " SELECT  ifnull(SUM(S1.TOTAL_NET_PRICE),0) - ifnull(SUM(R0.GRAND_TOTAL),0) FROM tSales0 S0 " +
                    " LEFT JOIN TSALESRETURN0 R0 " +
                    " ON S0.COMPANY_CODE = R0.COMPANY_CODE AND S0.SALES_DOC_NO = R0.SALES_DOC_NO " +
                    " LEFT JOIN TSALES1 S1 " +
                    " ON S1.COMPANY_CODE = S0.COMPANY_CODE AND S1.SALES_DOC_NO = S0.SALES_DOC_NO  " +
                    "  LEFT JOIN MITEM0 I0 " +
                    " ON I0.ITEM_CODE = S1.ITEM_CODE " +
                    " WHERE S0.company_code = '" + CompanyCode + "' and strftime('%d', sales_date) = '" + Date + "' and strftime('%m', sales_date) = '" + Month + "' " + Filter + "";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumNetSlsMonthDaily", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayNetSalesTodayPeriodicly(String CompanyCode, String Date, String sTOday) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Date.length() == 1) {
                Date = "0" + Date;
            }

            String query = " SELECT ifnull(SUM(GRAND_TOTAL),0) FROM tsales0 " +
                    " WHERE company_code = '" + CompanyCode + "' and date(SALES_DATE) = '" + sTOday + "' and strftime('%H', sales_date) = '" + Date + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumNetSlsTodayprdc", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayGrossSalesThisMonth(String CompanyCode, String Month) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Month.length() == 1) {
                Month = "0" + Month;
            }
            String query = "SELECT SUM(grand_total) FROM tsales0 \n" +
                    "WHERE company_code = '" + CompanyCode + "' and strftime('%m', sales_date) = '" + Month + "' ";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumGrssSlsThisMonth", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayGrossSalesTodayPeriodicly(String CompanyCode, String sToday) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = " SELECT ifnull(SUM(GRAND_TOTAL),0) FROM tsales0 " +
                    " WHERE company_code = '" + CompanyCode + "' and date(SALES_DATE) = '" + sToday + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumGrssSlsThisTDY", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayGrossSalesThisWeek(String CompanyCode, String sStartDate, String sEndDate) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = "SELECT SUM(grand_total) FROM tsales0 \n" +
                    "WHERE company_code = '" + CompanyCode + "'  and date(sales_date) between '" + sStartDate + "' and '" + sEndDate + "' ";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumGrssSlsThisWEK", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayReturnSalesTodayPeriodicly(String CompanyCode, String sToday) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = " SELECT ifnull(SUM(GRAND_TOTAL),0) FROM TSALESRETURN0 " +
                    " WHERE company_code = '" + CompanyCode + "' and date(sales_return_date) = '" + sToday + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumRtrnSlsTDY", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayReturnSalesThisMonth(String CompanyCode, String Month) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Month.length() == 1) {
                Month = "0" + Month;
            }
            String query = "SELECT SUM(grand_total) FROM TSALESRETURN0 \n" +
                    "WHERE company_code = '" + CompanyCode + "' and strftime('%m', sales_return_date) = '" + Month + "' ";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumRtrnSlsThisMNTH", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayReturnSalesThisWeek(String CompanyCode, String sStartDate, String sEndDate) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = "SELECT SUM(grand_total) FROM TSALESRETURN0 \n" +
                    "WHERE company_code = '" + CompanyCode + "' and date(sales_return_date) between '" + sStartDate + "' and '" + sEndDate + "' ";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumRtrnSlsThisWek", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayNetSalesThisYearMonthly(String CompanyCode, String Month) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Month.length() == 1) {
                Month = "0" + Month;
            }

            String query = " SELECT  ifnull(SUM(S0.GRAND_TOTAL),0) - ifnull(SUM(R0.GRAND_TOTAL),0) FROM tSales0 S0 LEFT JOIN TSALESRETURN0 R0 " +
                    " ON S0.COMPANY_CODE = R0.COMPANY_CODE AND S0.SALES_DOC_NO = R0.SALES_DOC_NO " +
                    " WHERE S0.company_code = '" + CompanyCode + "' and strftime('%m', sales_date) = '" + Month + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumRtrnSlsThisYR", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayNetSalesThisYear(String CompanyCode, String Year) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Year.length() == 1) {
                Year = "0" + Year;
            }

            String query = " SELECT  ifnull(SUM(S0.GRAND_TOTAL),0) - ifnull(SUM(R0.GRAND_TOTAL),0) FROM tSales0 S0 LEFT JOIN TSALESRETURN0 R0 " +
                    " ON S0.COMPANY_CODE = R0.COMPANY_CODE AND S0.SALES_DOC_NO = R0.SALES_DOC_NO " +
                    " WHERE S0.company_code = '" + CompanyCode + "' and strftime('%Y', sales_date) = '" + Year + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumRtrnSlsThisYR", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayGrossSalesThisYear(String CompanyCode, String Year) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Year.length() == 1) {
                Year = "0" + Year;
            }

            String query = "SELECT SUM(grand_total) FROM tsales0 \n" +
                    "WHERE company_code = '" + CompanyCode + "' and strftime('%Y', sales_date) = '" + Year + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumGrsSlsThisYR", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public double SelectSummayReturnSalesThisYear(String CompanyCode, String Year) {
        double dTotal = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            if (Year.length() == 1) {
                Year = "0" + Year;
            }

            String query = "SELECT SUM(grand_total) FROM TSALESRETURN0 \n" +
                    "WHERE company_code = '" + CompanyCode + "' and  strftime('%Y', sales_return_date) = '" + Year + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    dTotal = cursor.getDouble(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumRtrSlsThisYR", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            return dTotal;
        }
    }

    public boolean IsSalesSysNoAlreadyExist(Integer iSysNo) {
        Integer tmpSysNo = 0;
        Integer nextLineNo = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            boolean isExist = false;

            String query = " SELECT SALES_SYS_NO FROM " + TABLE_NAME + " WHERE SALES_SYS_NO = " + String.valueOf(iSysNo);

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    tmpSysNo = cursor.getInt(0);
                } while (cursor.moveToNext());
            }

            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("SlctSumRtrSlsThisYR", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            if (tmpSysNo.equals(iSysNo)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean IsSalesDocNoAlreadyExist(String sDocNo) {
        String tmpSysNo = "";
        Integer nextLineNo = 0;
        db = this.getReadableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            boolean isExist = false;

            String query = " SELECT SALES_DOC_NO FROM " + TABLE_NAME + " WHERE SALES_DOC_NO = '" + sDocNo + "'";

            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    tmpSysNo = cursor.getString(0);
                } while (cursor.moveToNext());
            }
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("IsSlsDocNoAldyExist", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
            if (tmpSysNo.equals(sDocNo)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void UpdateSalesDisc0Line(tSalesEntity c) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean doAsTransaction = !db.inTransaction();
        if (doAsTransaction)
            db.beginTransaction();
        try {
            String query = " UPDATE tSales0 SET TOTAL = (SELECT SUM(IFNULL(PRICE_LIST,0) * IFNULL(QUANTITY,0)) FROM tSales1 WHERE SALES_SYS_NO = '" + c.getSALES_SYS_NO() + "') "
                    + ", TOTAL_DISC = (SELECT SUM((IFNULL(SELLING_PRICE,0) - IFNULL(NET_PRICE,0)) * IFNULL(QUANTITY,0)) FROM tSales1 WHERE SALES_SYS_NO = '" + c.getSALES_SYS_NO() + "') "
                    + ", GRAND_TOTAL = (SELECT SUM(IFNULL(TOTAL_NET_PRICE,0)) FROM tSales1 WHERE SALES_SYS_NO = '" + c.getSALES_SYS_NO() + "') "
                    + ", DPP = (SELECT SUM(IFNULL(TOTAL_NET_PRICE,0)) / 1.1 FROM tSales1 WHERE SALES_SYS_NO = '" + c.getSALES_SYS_NO() + "') "
                    + ", VAT = (SELECT SUM(IFNULL(TOTAL_NET_PRICE,0)) -  SUM(IFNULL(TOTAL_NET_PRICE,0)) / 1.1 FROM tSales1 WHERE SALES_SYS_NO = '" + c.getSALES_SYS_NO() + "') "
                    + ", CHANGE_USER_ID ='" + c.getCHANGE_USER_ID()
                    + "', CHANGE_DATETIME ='" + String.valueOf(c.getCHANGE_DATETIME())
                    + "' WHERE SALES_SYS_NO =" + String.valueOf(c.getSALES_SYS_NO()) + "";
            db.execSQL(query);
            if (doAsTransaction)
                db.setTransactionSuccessful();
        } catch (
                Exception e) {
            Log.e("UpdateSalesDisc0Line", e.getMessage());
        } finally {
            // ...
            if (doAsTransaction) {
                db.endTransaction();
                db.close();
            }
        }
    }
}