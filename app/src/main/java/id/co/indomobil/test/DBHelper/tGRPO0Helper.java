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

import id.co.indomobil.test.DBEntity.tGRPO0Entity;

public class tGRPO0Helper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="tGRPO0";

    public static final String COMPANY_CODE	= "COMPANY_CODE";
    public static final String GRPO_SYS_NO	= "GRPO_SYS_NO";
    public static final String GRPO_DOC_NO	= "GRPO_DOC_NO";
    public static final String GRPO_DATE	= "GRPO_DATE";
    public static final String PO_SYS_NO	= "PO_SYS_NO";
    public static final String PO_DOC_NO	= "PO_DOC_NO";
    public static final String PO_DATE	    = "PO_DATE";
    public static final String SUPPLIER_CODE	= "SUPPLIER_CODE";
    public static final String SUPPLIER_DO_SYS_NO	= "SUPPLIER_DO_SYS_NO";
    public static final String SUPPLIER_DO_DOC_NO	= "SUPPLIER_DO_DOC_NO";
    public static final String TOTAL = "TOTAL";
    public static final String GRAND_TOTAL	= "GRAND_TOTAL";
    public static final String DPP	= "DPP";
    public static final String VAT	= "VAT";
    public static final String CREATION_USER_ID	= "CREATION_USER_ID";
    public static final String CREATION_DATETIME	= "CREATION_DATETIME";
    public static final String CHANGE_USER_ID	= "CHANGE_USER_ID";
    public static final String CHANGE_DATETIME	= "CHANGE_DATETIME";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;

    public  static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "+ TABLE_NAME + " ("
            + COMPANY_CODE + "	varchar(30) null, "
            + GRPO_SYS_NO + "	int not null, "
            + GRPO_DOC_NO + "	varchar(20), "
            + GRPO_DATE + "	datetime, "
            + PO_SYS_NO + "	int not null, "
            + PO_DOC_NO + "	varchar(20), "
            + PO_DATE + "	datetime, "
            + SUPPLIER_CODE + "	varchar(20), "
            + SUPPLIER_DO_SYS_NO + "	int not null, "
            + SUPPLIER_DO_DOC_NO + "	varchar(20), "
            + TOTAL + "	numeric(18,4) null, "
            + GRAND_TOTAL + "	numeric(18,4) null, "
            + DPP + "	numeric(18,4) null, "
            + VAT + "	numeric(18,4) null, "
            + CREATION_USER_ID + "	varchar(20), "
            + CREATION_DATETIME + "	datetime, "
            + CHANGE_USER_ID + "	varchar(20), "
            + CHANGE_DATETIME + "	datetime "
            +");";


    public tGRPO0Helper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        //db.execSQL(tesHelper.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(tPaymentHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public Integer GetNextPODocNO() {
        db = this.getReadableDatabase();
        String query = " SELECT IFNULL(MAX(substr(GRPO_DOC_NO,6)),'0'),* FROM " + TABLE_NAME;
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

    public void InsertGRPO0(tGRPO0Entity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMPANY_CODE,c.getCOMPANY_CODE());
        values.put(GRPO_SYS_NO,c.getGRPO_SYS_NO());
        values.put(GRPO_DOC_NO,c.getGRPO_DOC_NO());
        values.put(GRPO_DATE,String.valueOf(c.getGRPO_DATE()));
        values.put(PO_SYS_NO,c.getPO_SYS_NO());
        values.put(PO_DOC_NO,c.getPO_DOC_NO());
        values.put(PO_DATE,String.valueOf(c.getPO_DATE()));
        values.put(SUPPLIER_CODE,c.getSUPPLIER_CODE());
        values.put(SUPPLIER_DO_SYS_NO   ,c.getSUPPLIER_DO_SYS_NO());
        values.put(SUPPLIER_DO_DOC_NO   ,c.getSUPPLIER_DO_DOC_NO());
        values.put(TOTAL,c.getTOTAL());
        values.put(GRAND_TOTAL,c.getGRAND_TOTAL());
        values.put(DPP,c.getDPP());
        values.put(VAT,c.getVAT());
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCREATION_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCREATION_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void InsertGR0(tGRPO0Entity c, String EmpNo){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        java.sql.Timestamp createdDateTime = new java.sql.Timestamp(System.currentTimeMillis());

        String query = " INSERT INTO tGRPO0 " +
                " SELECT OUTLET_COMPANY_CODE " +
                ", " + c.getGRPO_SYS_NO() + " " +
                ", '" + c.getGRPO_DOC_NO() + "' " +
                ", '" +  String.valueOf(createdDateTime) + "' " +
                ", " + c.getPO_SYS_NO() + " " +
                ", '" + c.getPO_DOC_NO() + "' " +
                ", PO_DATE " +
                ", SUPPLIER_COMPANY_CODE " +
                ", " + c.getSUPPLIER_DO_SYS_NO() + " " +
                ", SUPPLIER_DO_DOC_NO " +
                ", " + c.getTOTAL() + " " +
                ", " + c.getGRAND_TOTAL() + " " +
                ", " + c.getDPP() + " " +
                ", " + c.getVAT() + " " +
                ",'" + EmpNo + "' " +
                ",'" +  String.valueOf(createdDateTime) + "' " +
                ",'" + EmpNo + "' " +
                ",'" +  String.valueOf(createdDateTime) + "' " +
                " FROM tSupplierDO0 do0 " +
                "    LEFT JOIN tPurchaseOrder0 po0 " +
                "       ON do0.OUTLET_COMPANY_CODE = po0.COMPANY_CODE AND do0.OUTLET_PO_SYS_NO = po0.PO_SYS_NO " +
                " WHERE do0.OUTLET_COMPANY_CODE = '" + c.getCOMPANY_CODE() +
                "' AND do0.SUPPLIER_DO_SYS_NO = " + c.getSUPPLIER_DO_SYS_NO() +
                " AND do0.OUTLET_PO_SYS_NO = " + c.getPO_SYS_NO() + "" ;

        db.execSQL(query);
        db.close();
    }

    public boolean IsGRPOSysNoAlreadyExist(Integer iSysNo) {
        db = this.getReadableDatabase();
        boolean isExist = false;

        String query = " SELECT GRPO_SYS_NO FROM " + TABLE_NAME + " WHERE GRPO_SYS_NO = " + String.valueOf(iSysNo);

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

    public Integer GetNextGRPOSysNO() {
        db = this.getReadableDatabase();
        String query = " SELECT IFNULL(MAX(GRPO_SYS_NO),'0') FROM " + TABLE_NAME;
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

    public void InsertData(tGRPO0Entity objData){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int NewPK = getLatestPK() + 1;

        values.put(COMPANY_CODE,objData.COMPANY_CODE);
        values.put(GRPO_SYS_NO,objData.GRPO_SYS_NO);
        values.put(GRPO_DOC_NO,objData.GRPO_DOC_NO);
        values.put(GRPO_DATE,String.valueOf(objData.GRPO_DATE));
        values.put(PO_SYS_NO,objData.PO_SYS_NO);
        values.put(PO_DOC_NO,objData.PO_DOC_NO);
        values.put(PO_DATE,String.valueOf(objData.PO_DATE));
        values.put(SUPPLIER_CODE,objData.SUPPLIER_CODE);
        values.put(SUPPLIER_DO_SYS_NO,objData.SUPPLIER_DO_SYS_NO);
        values.put(SUPPLIER_DO_DOC_NO,objData.SUPPLIER_DO_DOC_NO);
        values.put(TOTAL,objData.TOTAL);
        values.put(GRAND_TOTAL,objData.GRAND_TOTAL);
        values.put(DPP,objData.DPP);
        values.put(VAT,objData.VAT);
        values.put(CREATION_USER_ID,objData.CREATION_USER_ID);
        values.put(CREATION_DATETIME,String.valueOf(objData.CREATION_DATETIME));
        values.put(CHANGE_USER_ID,objData.CHANGE_USER_ID);
        values.put(CHANGE_DATETIME,String.valueOf(objData.CHANGE_DATETIME));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int getLatestPK() {
        db = this.getReadableDatabase();
        SQLiteStatement stmt = db.compileStatement("SELECT ifnull(MAX(GRPO_SYS_NO),0) FROM "+TABLE_NAME+"");
        db.close();
        return (int) stmt.simpleQueryForLong();
    }

    public int CheckExistDBCategory (){
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        db.close();
        int count = cursor.getCount();
        return count;
    }

    public tGRPO0Entity SelectDatabySysNo(String criteria) {
        db = this.getReadableDatabase();
        if(!criteria.equals(""))
            criteria = " where " + criteria;
        String query = "select "
                +COMPANY_CODE  + ", "
                +GRPO_SYS_NO + ", "
                +GRPO_DOC_NO + ", "
                +GRPO_DATE + ", "
                +PO_SYS_NO + ", "
                +PO_DOC_NO + ", "
                +PO_DATE + ", "
                +SUPPLIER_CODE + ", "
                +SUPPLIER_DO_SYS_NO + ", "
                +SUPPLIER_DO_DOC_NO + ", "
                +TOTAL + ", "
                +GRAND_TOTAL + ", "
                +DPP + ", "
                +VAT + ", "
                +CREATION_USER_ID + ", "
                +CREATION_DATETIME + ", "
                +CHANGE_USER_ID + ", "
                +CHANGE_DATETIME + " " +" from " + TABLE_NAME + " " +criteria;

        Cursor cursor = db.rawQuery(query, null);
        tGRPO0Entity objData = new tGRPO0Entity();
        if (cursor.moveToFirst()) {
            do {
                objData.COMPANY_CODE = cursor.getString(0);
                objData.GRPO_SYS_NO = cursor.getInt(1);
                objData.GRPO_DOC_NO = cursor.getString(2);
                objData.GRPO_DATE = java.sql.Timestamp.valueOf(cursor.getString(3));
                objData.PO_SYS_NO = cursor.getInt(4);
                objData.PO_DOC_NO = cursor.getString(5);
                objData.PO_DATE = java.sql.Timestamp.valueOf(cursor.getString(6));
                objData.SUPPLIER_CODE = cursor.getString(7);
                objData.SUPPLIER_DO_SYS_NO = cursor.getInt(8);
                objData.SUPPLIER_DO_DOC_NO = cursor.getString(9);
                objData.TOTAL = cursor.getDouble(10);
                objData.GRAND_TOTAL = cursor.getDouble(11);
                objData.DPP = cursor.getDouble(12);
                objData.VAT = cursor.getDouble(13);
                objData.CREATION_USER_ID = cursor.getString(14);
                objData.CREATION_DATETIME = java.sql.Timestamp.valueOf(cursor.getString(15));
                objData.CHANGE_USER_ID = cursor.getString(16);
                objData.CHANGE_DATETIME = java.sql.Timestamp.valueOf(cursor.getString(17));
            } while (cursor.moveToNext());
        }
        db.close();
        return objData;
    }

    public List<tGRPO0Entity> SelectAllData(String criteria){
        List<tGRPO0Entity> listData=new ArrayList<tGRPO0Entity>();
        if(!criteria.equals(""))
            criteria = " where " + criteria;
        String query = "select "
                +COMPANY_CODE  + ", "
                +GRPO_SYS_NO + ", "
                +GRPO_DOC_NO + ", "
                +GRPO_DATE + ", "
                +PO_SYS_NO + ", "
                +PO_DOC_NO + ", "
                +PO_DATE + ", "
                +SUPPLIER_CODE + ", "
                +SUPPLIER_DO_SYS_NO + ", "
                +SUPPLIER_DO_DOC_NO + ", "
                +TOTAL + ", "
                +GRAND_TOTAL + ", "
                +DPP + ", "
                +VAT + ", "
                +CREATION_USER_ID + ", "
                +CREATION_DATETIME + ", "
                +CHANGE_USER_ID + ", "
                +CHANGE_DATETIME + " " +" from " + TABLE_NAME + " " +criteria + " order by GRPO_DATE desc";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);
        //Cursor c = db.q
        if(cursor.moveToFirst()){
            do{
                tGRPO0Entity objData = new tGRPO0Entity();

                objData.COMPANY_CODE = cursor.getString(0);
                objData.GRPO_SYS_NO = cursor.getInt(1);
                objData.GRPO_DOC_NO = cursor.getString(2);
                objData.GRPO_DATE = java.sql.Timestamp.valueOf(cursor.getString(3));
                objData.PO_SYS_NO = cursor.getInt(4);
                objData.PO_DOC_NO = cursor.getString(5);
                objData.PO_DATE = java.sql.Timestamp.valueOf(cursor.getString(6));
                objData.SUPPLIER_CODE = cursor.getString(7);
                objData.SUPPLIER_DO_SYS_NO = cursor.getInt(8);
                objData.SUPPLIER_DO_DOC_NO = cursor.getString(9);
                objData.TOTAL = cursor.getDouble(10);
                objData.GRAND_TOTAL = cursor.getDouble(11);
                objData.DPP = cursor.getDouble(12);
                objData.VAT = cursor.getDouble(13);
                objData.CREATION_USER_ID = cursor.getString(14);
                objData.CREATION_DATETIME = java.sql.Timestamp.valueOf(cursor.getString(15));
                objData.CHANGE_USER_ID = cursor.getString(16);
                objData.CHANGE_DATETIME = java.sql.Timestamp.valueOf(cursor.getString(17));

                listData.add(objData);
            }while(cursor.moveToNext());
        }
        db.close();
        return listData;
    }
}
