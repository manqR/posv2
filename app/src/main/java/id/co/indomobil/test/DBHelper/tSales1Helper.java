package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.tSales1Entity;
import id.co.indomobil.test.Helper.TextHelper;

public class tSales1Helper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "tSales1";
    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String SALES_DOC_NO = "SALES_DOC_NO";
    public static final String SALES_SYS_NO = "SALES_SYS_NO";
    public static final String SALES_LINE_NO = "SALES_LINE_NO";
    public static final String ITEM_CODE = "ITEM_CODE";
    public static final String PRICE_LIST = "PRICE_LIST";
    public static final String SELLING_PRICE = "SELLING_PRICE";
    public static final String DISCOUNT_PERCENTAGE = "DISCOUNT_PERCENTAGE";
    public static final String QUANTITY = "QUANTITY";
    public static final String DISCOUNT_NOMINAL = "DISCOUNT_NOMINAL";
    public static final String NET_PRICE = "NET_PRICE";
    public static final String TOTAL_NET_PRICE = "TOTAL_NET_PRICE";
    public static final String UOM_CODE = "UOM_CODE";
    public static final String DRAWER_CODE = "DRAWER_CODE";
    public static final String COGS = "COGS";
    public static final String CREATION_USER_ID = "CREATION_USER_ID";
    public static final String CREATION_DATETIME = "CREATION_DATETIME";
    public static final String CHANGE_USER_ID = "CHANGE_USER_ID";
    public static final String CHANGE_DATETIME = "CHANGE_DATETIME";
    public static final String SHIFT_NO = "SHIFT_NO";
    public static final String PROMO_NO = "PROMO_NO";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;


    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + COMPANY_CODE + " VARCHAR(30) not null, "
            + SALES_DOC_NO + " VARCHAR(20) null, "
            + SALES_SYS_NO + " INTEGER not null, "
            + SALES_LINE_NO + " INTEGER null, "
            + ITEM_CODE + " VARCHAR(30) null, "
            + PRICE_LIST + " NUMERIC(18,4) null, "
            + SELLING_PRICE + " NUMERIC(18,4) null, "
            + DISCOUNT_PERCENTAGE + " NUMERIC(18,4) null, "
            + QUANTITY + " NUMERIC(18,4) null, "
            + DISCOUNT_NOMINAL + " NUMERIC(18,4) null null, "
            + NET_PRICE + " NUMERIC(18,4) null, "
            + TOTAL_NET_PRICE + " NUMERIC(18,4) null, "
            + UOM_CODE + " VARCHAR(10) null, "
            + DRAWER_CODE + " VARCHAR(10) null, "
            + COGS + " NUMERIC(18,4) null, "
            + SHIFT_NO + " VARCHAR(15) null, "
            + PROMO_NO + " VARCHAR(20) null, "
            + CREATION_USER_ID + " VARCHAR(10) null, "
            + CREATION_DATETIME + " DATETIME null,"
            + CHANGE_USER_ID + " VARCHAR(10) null, "
            + CHANGE_DATETIME + " DATETIME null);";


    public tSales1Helper(Context context) {
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

    public void InsertSales1(tSales1Entity c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMPANY_CODE, c.getCOMPANY_CODE());
        values.put(SALES_SYS_NO, c.getSALES_SYS_NO());
        values.put(SALES_LINE_NO, c.getSALES_LINE_NO());
        values.put(ITEM_CODE, c.getITEM_CODE());
        values.put(PRICE_LIST, c.getPRICE_LIST());
        values.put(SELLING_PRICE, c.getSELLING_PRICE());
        values.put(DISCOUNT_PERCENTAGE, c.getDISCOUNT_PERCENTAGE());
        values.put(QUANTITY, c.getQUANTITY().toString());
        values.put(DISCOUNT_NOMINAL, c.getDISCOUNT_NOMINAL().toString());
        values.put(NET_PRICE, c.getNET_PRICE().toString());
        values.put(TOTAL_NET_PRICE, c.getTOTAL_NET_PRICE().toString());
        values.put(UOM_CODE, c.getUOM_CODE());
        values.put(SHIFT_NO, c.getSHIFT_NO());
        values.put(DRAWER_CODE, c.getDRAWER_CODE());
        values.put(PROMO_NO, c.getPROMO_NO());
        values.put(CREATION_USER_ID, c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID, c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void InsertSales1WithDocNo(tSales1Entity c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMPANY_CODE, c.getCOMPANY_CODE());
        values.put(SALES_DOC_NO, c.getSALES_DOC_NO());
        values.put(SALES_SYS_NO, c.getSALES_SYS_NO());
        values.put(SALES_LINE_NO, c.getSALES_LINE_NO());
        values.put(ITEM_CODE, c.getITEM_CODE());
        values.put(PRICE_LIST, c.getPRICE_LIST());
        values.put(SELLING_PRICE, c.getSELLING_PRICE());
        values.put(DISCOUNT_PERCENTAGE, c.getDISCOUNT_PERCENTAGE());
        values.put(QUANTITY, c.getQUANTITY().toString());
        values.put(DISCOUNT_NOMINAL, c.getDISCOUNT_NOMINAL().toString());
        values.put(NET_PRICE, c.getNET_PRICE().toString());
        values.put(TOTAL_NET_PRICE, c.getTOTAL_NET_PRICE().toString());
        values.put(UOM_CODE, c.getUOM_CODE());
        values.put(DRAWER_CODE, c.getDRAWER_CODE());
        values.put(COGS, c.getCOGS());
        values.put(SHIFT_NO, c.getSHIFT_NO());
        values.put(PROMO_NO, c.getPROMO_NO());
        values.put(CREATION_USER_ID, c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID, c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void UpdateSales1(tSales1Entity c) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_NAME + " SET QUANTITY = QUANTITY + 1 "
                + ", TOTAL_NET_PRICE = ( QUANTITY + 1) * NET_PRICE "
                + ", CHANGE_USER_ID ='" + c.getCREATION_USER_ID()
                + "', CHANGE_DATETIME ='" + String.valueOf(c.getCHANGE_DATETIME())
                + "' WHERE SALES_SYS_NO =" + String.valueOf(c.getSALES_SYS_NO())
                + " AND ITEM_CODE ='" + String.valueOf(c.getITEM_CODE()) + "'";
        db.execSQL(query);
        db.close();
    }

    public void UpdateSales1BBM(tSales1Entity c) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_NAME + " SET QUANTITY = QUANTITY + 1 "
                + ", TOTAL_NET_PRICE ='" + c.getTOTAL_NET_PRICE()
                + ", CHANGE_USER_ID ='" + c.getCREATION_USER_ID()
                + "', CHANGE_DATETIME ='" + String.valueOf(c.getCHANGE_DATETIME())
                + "' WHERE SALES_SYS_NO =" + String.valueOf(c.getSALES_SYS_NO())
                + " AND ITEM_CODE ='" + String.valueOf(c.getITEM_CODE()) + "'";
        db.execSQL(query);
        db.close();
    }

    public void UpdateSales1Line(tSales1Entity c) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        //values.put(SALES_SYS_NO,c.getCATEGORY_CODE());
        //values.put(SALES_LINE_NO,c.getCATEGORY_NAME());
        //values.put(ITEM_CODE,c.getITEM_CODE());
        //values.put(PRICE_LIST,c.getPRICE_LIST());
        //values.put(SELLING_PRICE,c.getSELLING_PRICE());
        //values.put(DISCOUNT_PERCENTAGE,c.getDISCOUNT_PERCENTAGE());
        values.put(QUANTITY, c.getQUANTITY());
        //values.put(DISCOUNT_NOMINAL,c.getDISCOUNT_NOMINAL());
        //values.put(NET_PRICE,c.getNET_PRICE());
        values.put(TOTAL_NET_PRICE, c.getTOTAL_NET_PRICE());
        //values.put(UOM_CODE,c.getUOM_CODE());
        //values.put(DRAWER_CODE,c.getDRAWER_CODE());
        //values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        //values.put(CHANGE_DATETIME,c.getCHANGE_DATETIME().toString());

        String[] whereArgs = new String[]{String.valueOf(c.getSALES_SYS_NO()), String.valueOf(c.getSALES_LINE_NO())};
        db.update(TABLE_NAME, values, SALES_SYS_NO + "=? and " + SALES_LINE_NO + "=?", whereArgs);
        db.close();
    }

    public void UpdateSalesDocNo(tSales1Entity c) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(SALES_DOC_NO, c.getSALES_DOC_NO());

        String[] whereArgs = new String[]{String.valueOf(c.getSALES_SYS_NO())};
        db.update(TABLE_NAME, values, SALES_SYS_NO + "=?", whereArgs);
        db.close();
    }

    public void UpdateSalesDisc1Line(tSales1Entity c) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        //values.put(SALES_SYS_NO,c.getCATEGORY_CODE());
        //values.put(SALES_LINE_NO,c.getCATEGORY_NAME());
        //values.put(ITEM_CODE,c.getITEM_CODE());
        //values.put(PRICE_LIST,c.getPRICE_LIST());
        //values.put(SELLING_PRICE,c.getSELLING_PRICE());
        values.put(DISCOUNT_PERCENTAGE, c.getDISCOUNT_PERCENTAGE());
        //values.put(QUANTITY,c.getQUANTITY());
        values.put(DISCOUNT_NOMINAL, c.getDISCOUNT_NOMINAL());
        values.put(NET_PRICE, c.getNET_PRICE());
        values.put(TOTAL_NET_PRICE, c.getTOTAL_NET_PRICE());
        //values.put(UOM_CODE,c.getUOM_CODE());
        //values.put(DRAWER_CODE,c.getDRAWER_CODE());
        values.put(CHANGE_USER_ID, c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, c.getCHANGE_DATETIME().toString());

        String[] whereArgs = new String[]{String.valueOf(c.getSALES_SYS_NO()), String.valueOf(c.getSALES_LINE_NO())};
        db.update(TABLE_NAME, values, SALES_SYS_NO + "=? and " + SALES_LINE_NO + "=?", whereArgs);
        db.close();
    }

    public void DeleteSales1(tSales1Entity c) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] whereArgs = new String[]{String.valueOf(c.getSALES_SYS_NO()), String.valueOf(c.getSALES_LINE_NO())};
        db.delete(TABLE_NAME, SALES_SYS_NO + "=? AND " + SALES_LINE_NO + "=?", whereArgs);
        db.close();
    }

    public void DeleteSales1BySysNo(Integer SysNo) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] whereArgs = new String[]{String.valueOf(SysNo)};
        db.delete(TABLE_NAME, SALES_SYS_NO + "=?", whereArgs);
        db.close();
    }


    public List<tSales1Entity> SelectSalseItemList(Integer SysNo) {
        List<tSales1Entity> listItem = new ArrayList<tSales1Entity>();
        String query = "SELECT  "
                + " SALES_SYS_NO , SALES_LINE_NO , ITEM_CODE, ifnull(PRICE_LIST,0) , ifnull(SELLING_PRICE,0) "
                + " , ifnull(DISCOUNT_PERCENTAGE,0) , ifnull(QUANTITY,0) , ifnull(DISCOUNT_NOMINAL,0) , ifnull(NET_PRICE,0)"
                + " , ifnull(TOTAL_NET_PRICE,0) , UOM_CODE , DRAWER_CODE ,SHIFT_NO"
                + " FROM " + TABLE_NAME + " WHERE SALES_SYS_NO = " + SysNo.toString();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                tSales1Entity item = new tSales1Entity("", 0, 0, 0);
                item.setSALES_SYS_NO(cursor.getInt(0));
                item.setSALES_LINE_NO(cursor.getInt(1));
                item.setITEM_CODE(cursor.getString(2));
                item.setPRICE_LIST(cursor.getDouble(3));
                item.setSELLING_PRICE(cursor.getDouble(4));
                item.setDISCOUNT_PERCENTAGE(cursor.getDouble(5));
                item.setQUANTITY(cursor.getDouble(6));
                item.setDISCOUNT_NOMINAL(cursor.getDouble(7));
                item.setNET_PRICE(cursor.getDouble(8));
                item.setTOTAL_NET_PRICE(cursor.getDouble(9));
                item.setUOM_CODE(cursor.getString(10));
                item.setDRAWER_CODE(cursor.getString(11));
                item.setSHIFT_NO(cursor.getString(12));
                listItem.add(item);
            } while (cursor.moveToNext());
        }
        return listItem;
    }

    public Integer GetNextSalesLineNO(Integer iSysNo) {
        db = this.getReadableDatabase();

        String query = " SELECT ifnull(MAX(SALES_LINE_NO),'0') FROM " + TABLE_NAME + " WHERE " + SALES_SYS_NO + "=? ";

        String[] whereArgs = new String[]{String.valueOf(iSysNo)};

        query = " SELECT ifnull(MAX(SALES_LINE_NO),'0') FROM " + TABLE_NAME + " WHERE " + SALES_SYS_NO + "= " + String.valueOf(iSysNo);

        Cursor cursor = db.rawQuery(query, null);
        String sSysNo;
        Integer nextLineNo = 0;

        if (cursor.moveToFirst()) {
            do {
                sSysNo = cursor.getString(0);
                nextLineNo = Integer.valueOf(sSysNo) + 1;
            } while (cursor.moveToNext());
        }

        if (nextLineNo == 0) {
            nextLineNo = 1;
        }
        return nextLineNo;
    }

    public boolean IsItemAlreadyExist(Integer iSysNo, String itemCode) {
        db = this.getReadableDatabase();
        boolean isExist = false;

        String query = " SELECT ITEM_CODE FROM " + TABLE_NAME + " WHERE SALES_SYS_NO = " + String.valueOf(iSysNo)
                + " AND ITEM_CODE ='" + itemCode + "'";

        Cursor cursor = db.rawQuery(query, null);
        String tmpItemCode = "";
        Integer nextLineNo = 0;

        if (cursor.moveToFirst()) {
            do {
                tmpItemCode = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        if (tmpItemCode.equals(itemCode)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean IsStockExist(Integer iSysNo, String itemCode, String companycode, String categorycode) {
        db = this.getReadableDatabase();
        boolean isExist = false;
        String query;
        /*String query = " SELECT ts1.ITEM_CODE,ts1.QUANTITY, ts1.QUANTITY +1 ,stk.QTY_END FROM tSales1 ts1\n" +
                " LEFT OUTER JOIN tSales0 ts0 ON ts0.SALES_SYS_NO = ts1.SALES_SYS_NO\n" +
                " LEFT OUTER JOIN mLocationStock stk ON stk .COMPANY_CODE = ts0.COMPANY_CODE AND stk .ITEM_CODE = ts1.ITEM_CODE \n" +
                " WHERE ts1.SALES_SYS_NO= "+ String.valueOf(iSysNo) + " AND ts1.ITEM_CODE='" + itemCode +"' \n" +
                " AND ( (ts1.QUANTITY+1) <= stk.QTY_END)";*/

        /*query = " SELECT stk.ITEM_CODE,ts1.QUANTITY, ts1.QUANTITY +1 ,stk.QTY_END " +
                "FROM mLocationStock stk \n" +
                "LEFT JOIN tSales1 ts1 ON ts1.SALES_SYS_NO="+String.valueOf(iSysNo)+" AND stk .COMPANY_CODE = ts0.COMPANY_CODE AND stk .ITEM_CODE = ts1.ITEM_CODE \n" +
                "LEFT JOIN tSales0 ts0 ON ts0.SALES_SYS_NO = ts1.SALES_SYS_NO\n" +
                "WHERE stk.ITEM_CODE='" + itemCode +"' AND stk.COMPANY_CODE='"+companycode+"' \n" +
                "AND ( (IFNULL(ts1.QUANTITY,0)+1) <= stk.QTY_END) ";*/

        query = " SELECT stk.ITEM_CODE \n" +//,IFNULL(ts1.QUANTITY,0), IFNULL(ts1.QUANTITY,0) +1 ,stk.QTY_END " +
                "FROM mLocationStock stk \n" +
                "LEFT JOIN tSales1 ts1 ON ts1.ITEM_CODE = stk.ITEM_CODE AND ts1.SALES_SYS_NO =" + String.valueOf(iSysNo) + "\n" +
                "LEFT JOIN tSales0 ts0 ON ts0.COMPANY_CODE = stk.COMPANY_CODE AND ts0.SALES_SYS_NO = ts1.SALES_SYS_NO \n" +
                "WHERE stk.COMPANY_CODE='" + companycode + "'\n" +
                "AND stk.ITEM_CODE='" + itemCode + "'\n" +
                "AND ( (IFNULL(ts1.QUANTITY,0)+1) <= IFNULL(stk.QTY_END,0)) ";

        Cursor cursor = db.rawQuery(query, null);
        String tmpItemCode = "";
        Integer nextLineNo = 0;

        if (cursor.moveToFirst()) {
            do {
                tmpItemCode = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        if (tmpItemCode.equals("")) {
            if (!categorycode.equals("")) {
                if (categorycode.toUpperCase().substring(0, 2).equals("JS")) {
                    tmpItemCode = "JS";
                }
            }
        }

        if (tmpItemCode.equals("")) {
            return false;
        } else {
            return true;
        }


    }

    public Integer getStockQty(Integer iSysNo, String itemCode, String companycode) {
        db = this.getReadableDatabase();

        String query = " SELECT stk.QTY_END FROM mLocationStock stk \n" +
                "LEFT OUTER JOIN  tSales1 ts1 ON ts1.SALES_SYS_NO=" + String.valueOf(iSysNo) + " AND stk .ITEM_CODE = ts1.ITEM_CODE \n" +
                "LEFT OUTER JOIN tSales0 ts0 ON stk .COMPANY_CODE = ts0.COMPANY_CODE AND ts0.SALES_SYS_NO = ts1.SALES_SYS_NO\n" +
                "WHERE stk.ITEM_CODE='" + itemCode + "' AND stk.COMPANY_CODE='" + companycode + "' \n";

        Cursor cursor = db.rawQuery(query, null);
        Integer iStockQty = 0;

        if (cursor.moveToFirst()) {
            do {
                iStockQty = cursor.getInt(0);
            } while (cursor.moveToNext());
        }

        return iStockQty;
    }

    public ArrayList<tSales1Entity> SelectAllItem(int HeaderSysNo) {
        ArrayList<tSales1Entity> listItem = new ArrayList<tSales1Entity>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE SALES_SYS_NO=" + String.valueOf(HeaderSysNo);

        query = "SELECT ts.SALES_SYS_NO, ts.SALES_LINE_NO ,ts.Item_code, ts.price_list, ts.Selling_price, \n" +
                "ts.Discount_Percentage, ts.Quantity, ts.Discount_Nominal, ts.Net_Price, ts.TOTAL_NET_PRICE\n" +
                ",ts.UOM_CODE, ts.DRAWER_CODE,ts.CREATION_USER_ID, ts.CREATION_DATETIME\n" +
                ",ts.CHANGE_USER_ID, ts.CHANGE_DATETIME ,mi.Item_name ,mi.Category_Code \n" +
                " FROM Tsales1 ts\n" +
                "left outer join mitem0 mi on mi.item_code=ts.item_code\n" +
                "where ts.SALES_SYS_NO=" + String.valueOf(HeaderSysNo) + " order by mi.item_name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                tSales1Entity item = new tSales1Entity("", 0, 0, 0);
                item.setSALES_SYS_NO(cursor.getInt(0));
                item.setSALES_LINE_NO(cursor.getInt(1));
                item.setITEM_CODE(cursor.getString(2));
                item.setPRICE_LIST(cursor.getDouble(3));
                item.setSELLING_PRICE(cursor.getDouble(4));
                item.setDISCOUNT_PERCENTAGE(cursor.getDouble(5));
                item.setQUANTITY(cursor.getDouble(6));
                item.setDISCOUNT_NOMINAL(cursor.getDouble(7));
                item.setNET_PRICE(cursor.getDouble(8));
                item.setTOTAL_NET_PRICE(cursor.getDouble(9));
                item.setUOM_CODE(cursor.getString(10));
                item.setDRAWER_CODE(cursor.getString(11));
                item.setCREATION_USER_ID(cursor.getString(12));
                //item.setCREATION_DATETIME(new Date(cursor.getLong(13)));
                item.setCREATION_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(13)));
                item.setCHANGE_USER_ID(cursor.getString(14));
                //item.setCHANGE_DATETIME(new Date(cursor.getLong(15)));
                item.setCHANGE_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(15)));
                item.setITEM_NAME(cursor.getString(16));
                item.setCATEGORY_CODE(cursor.getString(17));
                listItem.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return listItem;
    }

    public ArrayList<tSales1Entity> SelectAllItem2(int HeaderSysNo, String CompanyCode) {
        ArrayList<tSales1Entity> listItem = new ArrayList<tSales1Entity>();
        String query = "SELECT ts1.SALES_SYS_NO, ts1.SALES_LINE_NO ,ts1.Item_code, ts1.price_list, ts1.Selling_price, \n" +
                " ts1.Discount_Percentage, ts1.Quantity, ts1.Discount_Nominal, ts1.Net_Price, ts1.TOTAL_NET_PRICE\n" +
                ",ts1.UOM_CODE, ts1.DRAWER_CODE,ts1.CREATION_USER_ID, ts1.CREATION_DATETIME\n" +
                ",ts1.CHANGE_USER_ID, ts1.CHANGE_DATETIME ,mi.Item_name, ts1.SALES_DOC_NO, ts0.COMPANY_CODE, mi.UOM_CODE, mi.CATEGORY_CODE \n" +
                ",p.PAYMENT_AMOUNT, p.CHANGE_AMOUNT \n" +
                " FROM tsales1 ts1\n" +
                " left outer join tsales0 ts0 on ts0.SALES_SYS_NO = ts1.SALES_SYS_NO AND ts0.COMPANY_CODE= '" + CompanyCode + "' \n" +
                " left outer join mitem0 mi on mi.item_code=ts1.item_code\n" +
                " left outer join tpayment p on p.sales_sys_no = ts0.sales_sys_no \n" +
                " where ts1.SALES_SYS_NO=" + String.valueOf(HeaderSysNo) + " order by ts1.SALES_LINE_NO";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                tSales1Entity item = new tSales1Entity("", 0, 0, 0);
                item.setSALES_SYS_NO(cursor.getInt(0));
                item.setSALES_LINE_NO(cursor.getInt(1));
                item.setITEM_CODE(cursor.getString(2));
                item.setPRICE_LIST(cursor.getDouble(3));
                item.setSELLING_PRICE(cursor.getDouble(4));
                item.setDISCOUNT_PERCENTAGE(cursor.getDouble(5));
                item.setQUANTITY(cursor.getDouble(6));
                item.setDISCOUNT_NOMINAL(cursor.getDouble(7));
                item.setNET_PRICE(cursor.getDouble(8));
                item.setTOTAL_NET_PRICE(cursor.getDouble(9));
                item.setUOM_CODE(cursor.getString(10));
                item.setDRAWER_CODE(cursor.getString(11));
                item.setCREATION_USER_ID(cursor.getString(12));
                //item.setCREATION_DATETIME(new Date(cursor.getLong(13)));
                item.setCREATION_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(13)));
                item.setCHANGE_USER_ID(cursor.getString(14));
                //item.setCHANGE_DATETIME(new Date(cursor.getLong(15)));
                item.setCHANGE_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(15)));
                item.setITEM_NAME(cursor.getString(16) + " ( " + cursor.getString(2) + " )");
                item.setSALES_DOC_NO(cursor.getString(17));
                item.setCOMPANY_CODE(CompanyCode);
                item.setUOM_CODE(cursor.getString(19));
                item.setCATEGORY_CODE(cursor.getString(20));
                item.setPAYMENT_AMOUNT(cursor.getDouble(21));
                item.setCHANGE_AMOUNT(cursor.getDouble(22));
                listItem.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return listItem;
    }

    public ArrayList<tSales1Entity> SelectAllItemPrintPreview(int HeaderSysNo, String CompanyCode) {
        ArrayList<tSales1Entity> listItem = new ArrayList<tSales1Entity>();
        String query = "SELECT ts1.SALES_SYS_NO, ts1.SALES_LINE_NO ,ts1.Item_code, ts1.price_list, ts1.Selling_price, \n" +
                " ts1.Discount_Percentage, ts1.Quantity, ts1.Discount_Nominal, ts1.Net_Price, ts1.TOTAL_NET_PRICE\n" +
                ",ts1.UOM_CODE, ts1.DRAWER_CODE,ts1.CREATION_USER_ID, ts1.CREATION_DATETIME\n" +
                ",ts1.CHANGE_USER_ID, ts1.CHANGE_DATETIME ,mi.Item_name, ts1.SALES_DOC_NO, ts0.COMPANY_CODE, mi.UOM_CODE, mi.CATEGORY_CODE, p.PAYMENT_AMOUNT, p.CHANGE_AMOUNT \n" +
                " FROM tsales1 ts1\n" +
                " left outer join tsales0 ts0 on ts0.SALES_SYS_NO = ts1.SALES_SYS_NO AND ts0.COMPANY_CODE= '" + CompanyCode + "' \n" +
                " left outer join mitem0 mi on mi.item_code=ts1.item_code\n" +
                " left outer join tPayment p on p.SALES_SYS_NO = ts1.SALES_SYS_NO\n" +
                " where ts1.SALES_SYS_NO=" + String.valueOf(HeaderSysNo) + " order by ts1.SALES_LINE_NO";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                tSales1Entity item = new tSales1Entity("", 0, 0, 0);
                item.setSALES_SYS_NO(cursor.getInt(0));
                item.setSALES_LINE_NO(cursor.getInt(1));
                item.setITEM_CODE(cursor.getString(2));
                item.setPRICE_LIST(cursor.getDouble(3));
                item.setSELLING_PRICE(cursor.getDouble(4));
                item.setDISCOUNT_PERCENTAGE(cursor.getDouble(5));
                item.setQUANTITY(cursor.getDouble(6));
                item.setDISCOUNT_NOMINAL(cursor.getDouble(7));
                item.setNET_PRICE(cursor.getDouble(8));
                item.setTOTAL_NET_PRICE(cursor.getDouble(9));
                item.setUOM_CODE(cursor.getString(10));
                item.setDRAWER_CODE(cursor.getString(11));
                item.setCREATION_USER_ID(cursor.getString(12));
                //item.setCREATION_DATETIME(new Date(cursor.getLong(13)));
                item.setCREATION_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(13)));
                item.setCHANGE_USER_ID(cursor.getString(14));
                //item.setCHANGE_DATETIME(new Date(cursor.getLong(15)));
                item.setCHANGE_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(15)));
                item.setITEM_NAME(cursor.getString(16) + " ( " + cursor.getString(2) + " )");
                item.setSALES_DOC_NO(cursor.getString(17));
                item.setCOMPANY_CODE(CompanyCode);
                item.setUOM_CODE(cursor.getString(19));
                item.setCATEGORY_CODE(cursor.getString(20));
                item.setPAYMENT_AMOUNT(cursor.getDouble(21));
                item.setCHANGE_AMOUNT(cursor.getDouble(22));
                listItem.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return listItem;
    }

    public double getTotal1(int HeaderSysNo) {
        double retValue = 0;
        db = this.getReadableDatabase();
        SQLiteStatement stmt = db.compileStatement("SELECT ifnull(sum(" + SELLING_PRICE + "*" + QUANTITY + "),0) FROM " + TABLE_NAME + " WHERE SALES_SYS_NO=" + String.valueOf(HeaderSysNo));
        retValue = (double) stmt.simpleQueryForLong();
        db.close();
        return retValue;
    }

    public double getTotal(int HeaderSysNo) {
        double retValue = 0;
        db = this.getReadableDatabase();
        String query = "SELECT ifnull(sum(TOTAL_NET_PRICE),0) FROM " + TABLE_NAME + " WHERE SALES_SYS_NO=" + String.valueOf(HeaderSysNo);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                retValue = cursor.getDouble(0);
            } while (cursor.moveToNext());
        }
        db.close();
        return retValue;
    }

    public double[] getTotalAndQty(int HeaderSysNo) {
        double[] rst = new double[2];
        double rstTotal = 0;
        db = this.getReadableDatabase();
        String query = "SELECT ifnull(sum(" + NET_PRICE + "*" + QUANTITY + "),0),ifnull(sum("
                + QUANTITY + "),0) FROM " + TABLE_NAME + " WHERE SALES_SYS_NO=" + String.valueOf(HeaderSysNo);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                rst[0] = cursor.getDouble(0);
                rst[1] = cursor.getDouble(1);
            } while (cursor.moveToNext());
        }
        db.close();

        return rst;
    }

    public double getTotalDiscAmount(int HeaderSysNo) {
        double retValue = 0;
        db = this.getReadableDatabase();
        SQLiteStatement stmt = db.compileStatement("SELECT ifnull(sum(" + DISCOUNT_NOMINAL + "*" + QUANTITY + "),0) FROM " + TABLE_NAME + " WHERE SALES_SYS_NO=" + String.valueOf(HeaderSysNo));
        retValue = (double) stmt.simpleQueryForLong();
        db.close();
        return retValue;
    }
    /*public double getGrandTotal(int HeaderSysNo){
        double retValue = 0;
        db = this.getReadableDatabase();
        SQLiteStatement stmt = db.compileStatement("SELECT ifnull(sum(PAYMENT_SYS_NO),0) FROM "+TABLE_NAME+ " WHERE SALES_SYS_NO=" +String.valueOf(HeaderSysNo));
        retValue = (double) stmt.simpleQueryForLong();
        db.close();
        return retValue;
    }
    public double getTotalDP(int HeaderSysNo){
        double retValue = 0;
        db = this.getReadableDatabase();
        SQLiteStatement stmt = db.compileStatement("SELECT ifnull(sum(PAYMENT_SYS_NO),0) FROM "+TABLE_NAME+ " WHERE SALES_SYS_NO=" +String.valueOf(HeaderSysNo));
        retValue = (double) stmt.simpleQueryForLong();
        db.close();
        return retValue;
    }*/

    public List<tSales1Entity> SelectSales1Data(String criteria) {
        List<tSales1Entity> listData = new ArrayList<tSales1Entity>();
        if (!criteria.equals(""))
            criteria = " where " + criteria;
        String query = "SELECT COMPANY_CODE,\n " +
                "       SALES_DOC_NO,\n " +
                "       SALES_SYS_NO,\n " +
                "       SALES_LINE_NO,\n " +
                "       ITEM_CODE,\n " +
                "       PRICE_LIST,\n " +
                "       SELLING_PRICE,\n" +
                "       DISCOUNT_PERCENTAGE,\n" +
                "       QUANTITY,\n " +
                "       DISCOUNT_NOMINAL,\n " +
                "       NET_PRICE,\n " +
                "       TOTAL_NET_PRICE,\n " +
                "       UOM_CODE,\n " +
                "       DRAWER_CODE,\n " +
                "       COGS,\n " +
                "       SHIFT_NO,\n " +
                "       PROMO_NO,\n " +
                "       CREATION_USER_ID,\n " +
                "       CREATION_DATETIME,\n " +
                "       CHANGE_USER_ID,\n " +
                "       CHANGE_DATETIME\n " +
                "  FROM tsales1 " + criteria;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //Cursor c = db.q
        TextHelper textHelper = new TextHelper();
        if (cursor.moveToFirst()) {
            do {
                tSales1Entity objData = new tSales1Entity();
                objData.setCOMPANY_CODE(cursor.getString(0));
                objData.setSALES_DOC_NO(cursor.getString(1));
                objData.setSALES_SYS_NO(cursor.getInt(2));
                objData.setSALES_LINE_NO(cursor.getInt(3));
                objData.setITEM_CODE(cursor.getString(4));
                objData.setPRICE_LIST(cursor.getDouble(5));
                objData.setSELLING_PRICE(cursor.getDouble(6));
                objData.setDISCOUNT_PERCENTAGE(cursor.getDouble(7));
                objData.setQUANTITY(cursor.getDouble(8));
                objData.setDISCOUNT_NOMINAL(cursor.getDouble(9));
                objData.setNET_PRICE(cursor.getDouble(10));
                objData.setTOTAL_NET_PRICE(cursor.getDouble(11));
                objData.setUOM_CODE(cursor.getString(12));
                objData.setDRAWER_CODE(cursor.getString(13));
                objData.setCOGS(cursor.getDouble(14));
                objData.setSHIFT_NO(cursor.getString(15));
                objData.setPROMO_NO(cursor.getString(16));
                objData.setCREATION_USER_ID(cursor.getString(17));
                objData.setCREATION_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(18)));
                objData.setCHANGE_USER_ID(cursor.getString(19));
                objData.setCHANGE_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(20)));
                listData.add(objData);
            } while (cursor.moveToNext());
        }
        db.close();
        return listData;
    }

}
