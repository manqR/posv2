package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mMixMatchEntity;

public class mMixMatchHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "mMixMatch";
    public static final String DISCOUNT_ID  = "DISCOUNT_ID";
    public static final String DISCOUNT_NAME = "DISCOUNT_NAME";
    public static final String ITEM_CODE = "ITEM_CODE";
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String UNIT = "UNIT";
    public static final String NUMBER_OF_PRODUCT_NEEDED = "NUMBER_OF_PRODUCT_NEEDED";
    public static final String DISCOUNT_TYPE = "DISCOUNT_TYPE";
    public static final String DEAL_PRICE_DISC_PERCENTAGE = "DEAL_PRICE_DISC_PERCENTAGE";
    public static final String LINE_GROUP = "LINE_GROUP";
    public static final String PRICE_GROUP = "PRICE_GROUP";
    public static final String START_DATE = "START_DATE";
    public static final String END_DATE = "END_DATE";
    public static final String CREATION_USER_ID = "CREATION_USER_ID";
    public static final String CREATION_DATETIME = "CREATION_DATETIME";
    public static final String CHANGE_USER_ID = "CHANGE_USER_ID";
    public static final String CHANGE_DATETIME = "CHANGE_DATETIME";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;

    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + DISCOUNT_ID+ " VARCHAR(30) not null, "
            + DISCOUNT_NAME+ " VARCHAR(100) null, "
            + ITEM_CODE+ " VARCHAR(30) null, "
            + ITEM_NAME+ " VARCHAR(100) null, "
            + UNIT+ " VARCHAR(10) null, "
            + NUMBER_OF_PRODUCT_NEEDED+ "  NUMERIC(24,4) null, "
            + DISCOUNT_TYPE+ " VARCHAR(10) null, "
            + DEAL_PRICE_DISC_PERCENTAGE+ " NUMERIC(24,4) null, "
            + LINE_GROUP+ " VARCHAR(10) not null, "
            + PRICE_GROUP+ " VARCHAR(30) null, "
            + START_DATE+ " DATETIME null, "
            + END_DATE+ "  DATETIME null, "
            + CREATION_USER_ID+ " VARCHAR(10) null, "
            + CREATION_DATETIME+ " DATETIME null, "
            + CHANGE_USER_ID+ " VARCHAR(10) null, "
            + CHANGE_DATETIME+ " DATETIME null);";

    public mMixMatchHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(mMixMatchHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void InsertMixMatch(mMixMatchEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DISCOUNT_ID,c.getDISCOUNT_ID());
        values.put(DISCOUNT_NAME,c.getDISCOUNT_NAME());
        values.put(ITEM_CODE,c.getITEM_CODE());
        values.put(ITEM_NAME,c.getITEM_NAME());
        values.put(UNIT,c.getUNIT());
        values.put(NUMBER_OF_PRODUCT_NEEDED,c.getNUMBER_OF_PRODUCT_NEEDED());
        values.put(DISCOUNT_TYPE,c.getDISCOUNT_TYPE());
        values.put(DEAL_PRICE_DISC_PERCENTAGE,c.getDEAL_PRICE_DISC_PERCENTAGE());
        values.put(LINE_GROUP,c.getLINE_GROUP());
        values.put(PRICE_GROUP,c.getPRICE_GROUP());
        values.put(START_DATE,String.valueOf(c.getSTART_DATE()));
        values.put(END_DATE,String.valueOf(c.getEND_DATE()));
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME,String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME,String.valueOf(c.getCHANGE_DATETIME()));
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void UpdateMixMatch(mMixMatchEntity c){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DISCOUNT_NAME,c.getDISCOUNT_NAME());
        values.put(UNIT,c.getUNIT());
        values.put(NUMBER_OF_PRODUCT_NEEDED,c.getNUMBER_OF_PRODUCT_NEEDED());
        values.put(DISCOUNT_TYPE,c.getDISCOUNT_TYPE());
        values.put(DEAL_PRICE_DISC_PERCENTAGE,c.getDEAL_PRICE_DISC_PERCENTAGE());
        values.put(START_DATE,String.valueOf(c.getSTART_DATE()));
        values.put(END_DATE,String.valueOf(c.getEND_DATE()));
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));
        String[] whereArgs = new String[] {String.valueOf(c.getDISCOUNT_ID()), String.valueOf(c.getPRICE_GROUP()), String.valueOf(c.getITEM_CODE()), String.valueOf(c.getLINE_GROUP())};
        db.update(TABLE_NAME, values, DISCOUNT_ID + "=? AND " + PRICE_GROUP + "=? AND " + ITEM_CODE + "=? AND " + LINE_GROUP + "=?",  whereArgs);
        db.close();
    }

    public boolean isDataExist (String DiscountID, String PriceGroup, String ItemCode, String LineGroup){
        boolean retValue = false;
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" where "+DISCOUNT_ID+" = '"+DiscountID+"' and "+PRICE_GROUP+" = '"+PriceGroup+"' and "+ITEM_CODE+" = '"+ItemCode+"' and "+LINE_GROUP+" = '" + LineGroup + "' ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        if(count>0)
            retValue = true;
        return retValue;
    }


    public  boolean IsDiscountIDExists(String itemCode,String companycode) {
        db = this.getReadableDatabase();
        boolean isExist = false;

        String query = "SELECT DISTINCT DISCOUNT_ID FROM mMixMatch \n" +
                " WHERE  ITEM_CODE = '" + itemCode + "' and (PRICE_GROUP= '" + companycode + "' or PRICE_GROUP= 0)\n" +
                " AND (strftime('%Y-%m-%d', datetime('now')) between strftime('%Y-%m-%d', START_DATE) AND strftime('%Y-%m-%d', END_DATE))";

        Cursor cursor = db.rawQuery(query, null);
        String tmpDiscountID = "";

        if (cursor.moveToFirst()) {
            do {
                tmpDiscountID = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        if (tmpDiscountID.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public List<mMixMatchEntity> SelectAllDiscountID(String itemCode, String companycode){
        List<mMixMatchEntity> listMix=new ArrayList<mMixMatchEntity>();


        String query = "SELECT DISTINCT DISCOUNT_ID, DISCOUNT_NAME FROM mMixMatch \n" +
                " WHERE  ITEM_CODE = '" + itemCode + "' and (PRICE_GROUP= '" + companycode + "' or PRICE_GROUP= 0)\n" +
                " AND (strftime('%Y-%m-%d', datetime('now')) between strftime('%Y-%m-%d', START_DATE) AND strftime('%Y-%m-%d', END_DATE)) order by DISCOUNT_ID";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mMixMatchEntity mix=new mMixMatchEntity();
                mix.setDISCOUNT_ID(cursor.getString(0));
                mix.setDISCOUNT_NAME(cursor.getString(1));
                listMix.add(mix);
            }while(cursor.moveToNext());
        }
        return listMix;
    }

    public List<mMixMatchEntity> SelectAllLineGroup(String discounID, String companycode){
        List<mMixMatchEntity> listMix=new ArrayList<mMixMatchEntity>();


        String query = "SELECT DISTINCT LINE_GROUP FROM mMixMatch \n" +
                " WHERE DISCOUNT_ID = '" + discounID + "' and (PRICE_GROUP= '" + companycode + "' or PRICE_GROUP= 0) and DEAL_PRICE_DISC_PERCENTAGE = 0 \n" +
                " AND (strftime('%Y-%m-%d', datetime('now')) between strftime('%Y-%m-%d', START_DATE) AND strftime('%Y-%m-%d', END_DATE)) order by LINE_GROUP";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mMixMatchEntity mix=new mMixMatchEntity();
                mix.setLINE_GROUP(cursor.getString(0));
                listMix.add(mix);
            }while(cursor.moveToNext());
        }
        return listMix;
    }

    public List<mMixMatchEntity> SelectAllLineGroupDetail(String discounID, String itemcode, double qty, String companycode){
        List<mMixMatchEntity> listMix=new ArrayList<mMixMatchEntity>();


        String query = "SELECT DISTINCT LINE_GROUP FROM mMixMatch \n" +
                " WHERE DISCOUNT_ID = '" + discounID + "' and ITEM_CODE = '" + itemcode + "' and (PRICE_GROUP= '" + companycode + "' or PRICE_GROUP= 0) and NUMBER_OF_PRODUCT_NEEDED <= " + qty + " and DEAL_PRICE_DISC_PERCENTAGE = 0  \n" +
                " AND (strftime('%Y-%m-%d', datetime('now')) between strftime('%Y-%m-%d', START_DATE) AND strftime('%Y-%m-%d', END_DATE)) order by LINE_GROUP";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mMixMatchEntity mix=new mMixMatchEntity();
                mix.setLINE_GROUP(cursor.getString(0));
                listMix.add(mix);
            }while(cursor.moveToNext());
        }
        return listMix;
    }

    public List<mMixMatchEntity> SelectMixMatchItem(String discounID, String companycode){
        List<mMixMatchEntity> listMix=new ArrayList<mMixMatchEntity>();


        String query = "SELECT DISTINCT DISCOUNT_ID, DISCOUNT_NAME, mix.ITEM_CODE, mix.ITEM_NAME, DISCOUNT_TYPE, DEAL_PRICE_DISC_PERCENTAGE, START_DATE,END_DATE, CATEGORY_CODE FROM mMixMatch mix \n" +
                " LEFT JOIN mItem0 itm ON itm.ITEM_CODE = mix.ITEM_CODE  WHERE DISCOUNT_ID = '" + discounID + "' and (PRICE_GROUP= '" + companycode + "' or PRICE_GROUP= 0) and DEAL_PRICE_DISC_PERCENTAGE <> 0 \n" +
                " AND (strftime('%Y-%m-%d', datetime('now')) between strftime('%Y-%m-%d', START_DATE) AND strftime('%Y-%m-%d', END_DATE)) order by LINE_GROUP";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mMixMatchEntity mix=new mMixMatchEntity();
                mix.setDISCOUNT_ID(cursor.getString(0));
                mix.setDISCOUNT_NAME(cursor.getString(1));
                mix.setITEM_CODE(cursor.getString(2));
                mix.setITEM_NAME(cursor.getString(3));
                mix.setDISCOUNT_TYPE(cursor.getString(4));
                mix.setDEAL_PRICE_DISC_PERCENTAGE(cursor.getDouble(5));
                mix.setSTART_DATE(java.sql.Timestamp.valueOf(cursor.getString(6)));
                mix.setEND_DATE(java.sql.Timestamp.valueOf(cursor.getString(7)));
                mix.setCATEGORY_CODE(cursor.getString(8));
                listMix.add(mix);
            }while(cursor.moveToNext());
        }
        return listMix;
    }

    public List<mMixMatchEntity> SelectMixMatch(String itemCode,String companycode, String Filter) {
        List<mMixMatchEntity> listPromo = new ArrayList<mMixMatchEntity>();
        String query="SELECT DISTINCT mix.ITEM_CODE,\n" +
                "       mix.DISCOUNT_ID, \n" +
                "       mix.DISCOUNT_NAME, \n" +
                "       mix.DISCOUNT_TYPE, \n" +
                "       IFNULL((SELECT DEAL_PRICE_DISC_PERCENTAGE  \n" +
                "                   FROM mMixMatch mix1 \n" +
                "                       WHERE mix1.DISCOUNT_ID = mix.DISCOUNT_ID AND mix1.ITEM_CODE = mix.ITEM_CODE AND mix1.DEAL_PRICE_DISC_PERCENTAGE <> 0 \n" +
                "                       AND ( mix1.PRICE_GROUP = '" + companycode + "' OR mix1.PRICE_GROUP = 0) \n" +
                "               ORDER BY mix1.PRICE_GROUP DESC LIMIT 1 ),0) DEAL_PRICE_DISC_PERCENTAGE ,\n" +
                "       mix.START_DATE ,\n" +
                "       mix.END_DATE ,\n" +
                "       CATEGORY_CODE , \n" +
                "       mix.ITEM_NAME \n" +
                "   FROM mMixMatch mix \n" +
                " LEFT JOIN mItem0 itm ON itm.ITEM_CODE = mix.ITEM_CODE \n" +
                " WHERE (PRICE_GROUP= '" + companycode + "' or PRICE_GROUP= 0)\n" +
                " AND DEAL_PRICE_DISC_PERCENTAGE <> 0 \n" +
                " AND (strftime('%Y-%m-%d', datetime('now')) between strftime('%Y-%m-%d', START_DATE) AND strftime('%Y-%m-%d', END_DATE))";

        if (!Filter.equals("")){
            query = query + " " + Filter;
        }

        query = query + " " + " ORDER BY mix.DISCOUNT_ID";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                mMixMatchEntity objData = new mMixMatchEntity();

                objData.setITEM_CODE(cursor.getString(0));
                objData.setDISCOUNT_ID(cursor.getString(1));
                objData.setDISCOUNT_NAME(cursor.getString(2));
                objData.setDISCOUNT_TYPE(cursor.getString(3));
                objData.setDEAL_PRICE_DISC_PERCENTAGE(cursor.getDouble(4));
                objData.setSTART_DATE (java.sql.Timestamp.valueOf(cursor.getString(5)));
                objData.setEND_DATE (java.sql.Timestamp.valueOf(cursor.getString(6)));
                objData.setCATEGORY_CODE(cursor.getString(7));
                objData.setITEM_NAME(cursor.getString(8));
                listPromo.add(objData);
            } while (cursor.moveToNext());
        }
        db.close();
        return listPromo;
    }

}
