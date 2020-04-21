package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.tShiftLogEntity;
import id.co.indomobil.test.Helper.TextHelper;

public class tShiftLogHelper  extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "tShiftLog";
    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String SUPERVISOR_ID = "SUPERVISOR_ID";
    public static final String SHIFT_NO = "SHIFT_NO";
    public static final String SHIFT_DATE = "SHIFT_DATE";
    public static final String SHIFT_STATUS = "SHIFT_STATUS";
    public static final String CASHIER_ID = "CASHIER_ID";
    public static final String BEGINNING_AMOUNT = "BEGINNING_AMOUNT";
    public static final String TOTAL_AMOUNT = "TOTAL_AMOUNT";
    public static final String TOTAL_SALES = "TOTAL_SALES";
    public static final String CASHIER_IMG = "CASHIER_IMG";
    public static final String TOTAL_RETUR = "TOTAL_RETUR";
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
            + SUPERVISOR_ID + " VARCHAR(10) null, "
            + SHIFT_NO + " VARCHAR(20) null, "
            + SHIFT_DATE + " DATETIME null, "
            + SHIFT_STATUS + " VARCHAR(2) null, "
            + CASHIER_IMG + " BYTE null , "
            + CASHIER_ID + " VARCHAR(10) null, "
            + BEGINNING_AMOUNT + " NUMERIC(18,4) null, "
            + TOTAL_AMOUNT + " NUMERIC(18,4) null, "
            + TOTAL_SALES + " NUMERIC(18,4) null, "
            + TOTAL_RETUR + " NUMERIC(18,4) null, "
            + CREATION_USER_ID + " VARCHAR(10) null, "
            + CREATION_DATETIME + " DATETIME null,"
            + CHANGE_USER_ID + " VARCHAR(10) null, "
            + CHANGE_DATETIME + " DATETIME null);";
    public tShiftLogHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //test
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

    public ArrayList<tShiftLogEntity> SelectAllShift(String CompanyCode){
        ArrayList<tShiftLogEntity> listItem=new ArrayList<tShiftLogEntity>();
        String query="SELECT * FROM "+TABLE_NAME + " WHERE COMPANY_CODE=" +String.valueOf(CompanyCode);

        query = "SELECT ts.COMPANY_CODE, ts.SUPERVISOR_ID ,ts.SHIFT_NO, ts.SHIFT_DATE, \n"+
                "ts.SHIFT_STATUS, ts.BEGINNING_AMOUNT, ts.TOTAL_AMOUNT, ts.TOTAL_SALES, ts.TOTAL_RETUR \n" +
                ",ts.CREATION_USER_ID, ts.CREATION_DATETIME\n"+
                ",ts.CHANGE_USER_ID, ts.CHANGE_DATETIME , emp.EMPLOYEE_NAME \n" +
                ", CASE WHEN  ts.SHIFT_STATUS='C' THEN 'TUTUP' \n" +
                " ELSE  'AKTIF' \n" +
                "END AS STATUS_DESC " +
                " FROM tShiftLog ts\n" +
                " LEFT OUTER JOIN mEmployee emp ON emp.EMPLOYEE_NO= ts.CASHIER_ID \n" +
                "where ts.COMPANY_CODE='" + CompanyCode +"' order by shift_date desc";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);
        TextHelper textHelper = new TextHelper();

        if(cursor.moveToFirst()){
            do{
                tShiftLogEntity tShift=new tShiftLogEntity();
                tShift.setCOMPANY_CODE(cursor.getString(0));
                tShift.setSUPERVISOR_ID(cursor.getString(1));
                tShift.setSHIFT_NO(cursor.getString(2));
                tShift.setSHIFT_DATE (java.sql.Timestamp.valueOf(cursor.getString(3)));
                tShift.setSHIFT_STATUS(cursor.getString(4));
                tShift.setBEGINNING_AMOUNT(cursor.getDouble(5));
                tShift.setTOTAL_AMOUNT(cursor.getDouble(6));
                tShift.setTOTAL_SALES(cursor.getDouble(7));
                tShift.setTOTAL_RETUR(cursor.getDouble(8));
                tShift.setCREATION_USER_ID(cursor.getString(9));
                tShift.setCREATION_DATETIME (java.sql.Timestamp.valueOf(cursor.getString(10)));
                tShift.setCHANGE_USER_ID(cursor.getString(11));
                tShift.setCHANGE_DATETIME (java.sql.Timestamp.valueOf(cursor.getString(12)));
                tShift.setCASHIER_NAME(cursor.getString(13));
                tShift.setSHIFT_STATUS_DESC(cursor.getString(14));
                listItem.add(tShift);
            }while(cursor.moveToNext());
        }
        db.close();
        return listItem;
    }
    public void InsertShiftLog(tShiftLogEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMPANY_CODE,c.getCOMPANY_CODE());
        values.put(SUPERVISOR_ID,c.getSUPERVISOR_ID());
        values.put(SHIFT_NO,c.getSHIFT_NO());
        values.put(SHIFT_DATE,String.valueOf(c.getSHIFT_DATE()));
        values.put(SHIFT_STATUS,c.getSHIFT_STATUS());
        values.put(CASHIER_ID,c.getCASHIER_ID());
        values.put(BEGINNING_AMOUNT,c.getBEGINNING_AMOUNT());
        values.put(TOTAL_AMOUNT,c.getTOTAL_AMOUNT());
        values.put(TOTAL_SALES,c.getTOTAL_SALES());
        values.put(TOTAL_RETUR,c.getTOTAL_RETUR().toString());
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCREATION_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void UpdateStatus(tShiftLogEntity c){
        SQLiteDatabase db=this.getWritableDatabase();
        String query =" UPDATE " + TABLE_NAME +" SET SHIFT_STATUS = '" + String.valueOf(c.getSHIFT_STATUS()) +"' \n"
                + ", TOTAL_AMOUNT =" + String.valueOf(c.getTOTAL_AMOUNT())
                + ", TOTAL_SALES =" + String.valueOf(c.getTOTAL_SALES())
                + ", CASHIER_IMG=" + String.valueOf(c.getCASHIER_IMG())
                + ", TOTAL_RETUR =" + String.valueOf(c.getTOTAL_RETUR())
                + ", CHANGE_USER_ID ='" + String.valueOf(c.getCHANGE_USER_ID()) +"' \n"
                + ", CHANGE_DATETIME ='" + String.valueOf(c.getCHANGE_DATETIME()) +"' \n"
                + " WHERE COMPANY_CODE ='" +String.valueOf(c.getCOMPANY_CODE())
                + "' AND SHIFT_NO ='" + String.valueOf(c.getSHIFT_NO()) +"'";
        db.execSQL(query);
        db.close();
    }

    public tShiftLogEntity SelectShift (String CompanyCode, String ShiftNo){
        db = this.getReadableDatabase();
        String query = "SELECT \n" +
                " sl.COMPANY_CODE\n" +
                ",SUPERVISOR_ID\n" +
                ",SHIFT_NO\n" +
                ",SHIFT_DATE\n" +
                ",SHIFT_STATUS\n" +
                ",CASHIER_ID\n" +
                ",BEGINNING_AMOUNT\n" +
                ",TOTAL_SALES\n" +
                ",TOTAL_RETUR\n" +
                ",TOTAL_AMOUNT\n" +
                ",EMPLOYEE_NAME\n" +
                " FROM tShiftLog sl" +
                " LEFT JOIN mEmployee em \n" +
                " ON sl.COMPANY_CODE = em.COMPANY_CODE and sl.CASHIER_ID = em.EMPLOYEE_NO\n" +
                " WHERE sl.COMPANY_CODE='" + CompanyCode +"' AND SHIFT_NO='" + ShiftNo +"'" ;

        Cursor cursor = db.rawQuery(query,null);
        tShiftLogEntity tShift =new tShiftLogEntity();

        String a,b;
        b = "data not found";
        TextHelper textHelper = new TextHelper();
        if(cursor.moveToFirst()){
            do {
                tShift = new tShiftLogEntity();
                tShift.setCOMPANY_CODE(cursor.getString(0));
                tShift.setSUPERVISOR_ID(cursor.getString(1));
                tShift.setSHIFT_NO(cursor.getString(2));
                tShift.setSHIFT_DATE (java.sql.Timestamp.valueOf(cursor.getString(3)));
                tShift.setSHIFT_STATUS(cursor.getString(4));
                tShift.setCASHIER_ID(cursor.getString(5));
                tShift.setBEGINNING_AMOUNT(cursor.getDouble(6));
                tShift.setTOTAL_SALES(cursor.getDouble(7));
                tShift.setTOTAL_RETUR(cursor.getDouble(8));
                tShift.setTOTAL_AMOUNT(cursor.getDouble(9));
                tShift.setCASHIER_NAME(cursor.getString(10));
            }while (cursor.moveToNext());
        }
        return tShift ;
    }

    public  boolean IsShiftLogAlreadyExist (String companyCode, String CashierId){
        db = this.getReadableDatabase();
        boolean isExist = false ;

        String query = " SELECT CASHIER_ID FROM "+TABLE_NAME + " WHERE COMPANY_CODE = '" + companyCode + "' AND CASHIER_ID ='" + CashierId +"' AND SHIFT_STATUS='O'";

        Cursor cursor = db.rawQuery(query,null);
        String tmpCashierid ="";
        Integer nextLineNo=0;

        if(cursor.moveToFirst()){
            do {
                tmpCashierid = cursor.getString(0).toString();
            }while (cursor.moveToNext());
        }

        if( tmpCashierid.equals(CashierId)){
            return true;
        } else {
            return false;
        }
    }

    public  boolean IsShiftLogAlreadyExistByCompany (String companyCode){
        db = this.getReadableDatabase();
        boolean isExist = false ;

        String query = " SELECT CASHIER_ID FROM "+TABLE_NAME + " WHERE COMPANY_CODE = '" + companyCode + "' AND SHIFT_STATUS='O'";

        Cursor cursor = db.rawQuery(query,null);
        String tmpCashierid ="";
        Integer nextLineNo=0;

        if(cursor.moveToFirst()){
            do {
                tmpCashierid = cursor.getString(0).toString();
            }while (cursor.moveToNext());
        }

        if( !tmpCashierid.equals("")){
            return true;
        } else {
            return false;
        }
    }

    public  boolean IsShiftLogAlreadyExistSync (String companyCode, String ShiftNo){
        db = this.getReadableDatabase();
        boolean isExist = false ;

        String query = " SELECT SHIFT_NO FROM "+TABLE_NAME + " WHERE COMPANY_CODE = '" + companyCode + "' AND SHIFT_NO ='" + ShiftNo +"'";

        Cursor cursor = db.rawQuery(query,null);
        String tmpCashierid ="";
        Integer nextLineNo=0;

        if(cursor.moveToFirst()){
            do {
                tmpCashierid = cursor.getString(0).toString();
            }while (cursor.moveToNext());
        }

        if( tmpCashierid.equals(ShiftNo)){
            return true;
        } else {
            return false;
        }


    }


    public void UpdateShiftLog(tShiftLogEntity c){
        SQLiteDatabase db=this.getWritableDatabase();
        String query =" UPDATE " + TABLE_NAME +" SET SHIFT_STATUS = '" + String.valueOf(c.getSHIFT_STATUS()) +"' \n"
                + ", TOTAL_AMOUNT =" + String.valueOf(c.getTOTAL_AMOUNT())
                + ", TOTAL_SALES =" + String.valueOf(c.getTOTAL_SALES())
                + ", TOTAL_RETUR =" + String.valueOf(c.getTOTAL_RETUR())
                + ", CHANGE_USER_ID ='" + String.valueOf(c.getCHANGE_USER_ID()) +"' \n"
                + ", CHANGE_DATETIME ='" + String.valueOf(c.getCHANGE_DATETIME()) +"' \n"
                + " WHERE COMPANY_CODE ='" +String.valueOf(c.getCOMPANY_CODE())
                + "' AND SHIFT_NO ='" + String.valueOf(c.getSHIFT_NO()) +"'";
        db.execSQL(query);
        db.close();
    }

    public  String GetShiftNoByCashier (String CompanyCode, String CashierId, Timestamp ShiftDate){
        db = this.getReadableDatabase();
        boolean isExist = false ;

        String sYear=(String) android.text.format.DateFormat.format("yyyy", ShiftDate);
        String sMonth=(String) android.text.format.DateFormat.format("MM", ShiftDate);
        String sDay=(String) android.text.format.DateFormat.format("dd", ShiftDate);

        String query = " SELECT SHIFT_NO FROM "+TABLE_NAME + " WHERE COMPANY_CODE = '" + CompanyCode +
                "' AND CASHIER_ID ='" + CashierId +
                "' AND SHIFT_STATUS='O' AND strftime('%Y-%m-%d', SHIFT_DATE) ='" + sYear +"-"+  sMonth +"-" + sDay +"'";

        Cursor cursor = db.rawQuery(query,null);
        String sShiftNo ="";
        String sCashierID ="";
        Integer nextLineNo=0;

        if(cursor.moveToFirst()){
            do {
                sShiftNo = cursor.getString(0).toString();
            }while (cursor.moveToNext());
        }

        return sShiftNo;
    }

    public  String GetActiveCashier (Date ShiftDate){
        db = this.getReadableDatabase();
        boolean isExist = false ;

        String sYear=(String) android.text.format.DateFormat.format("yyyy", ShiftDate);
        String sMonth=(String) android.text.format.DateFormat.format("MM", ShiftDate);
        String sDay=(String) android.text.format.DateFormat.format("dd", ShiftDate);

        String query = " SELECT EMPLOYEE_NAME FROM tShiftLog sft Left Join mEmployee emp on sft.CASHIER_ID = emp.EMPLOYEE_NO " +
                " WHERE SHIFT_STATUS='O' AND strftime('%Y-%m-%d', SHIFT_DATE) ='" + sYear +"-"+  sMonth +"-" + sDay +"'";

        Cursor cursor = db.rawQuery(query,null);
        String sCashierID ="";

        if(cursor.moveToFirst()){
            do {
                sCashierID = cursor.getString(0).toString();
            }while (cursor.moveToNext());
        }

        return sCashierID;
    }

    public List<tShiftLogEntity> SelectAllData(String criteria) {
        List<tShiftLogEntity> listData = new ArrayList<tShiftLogEntity>();
        if (!criteria.equals(""))
            criteria = " where " + criteria;
        String query = "Select * from tShiftLog" + criteria;;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        //Cursor c = db.q
        TextHelper textHelper = new TextHelper();
        if (cursor.moveToFirst()) {
            do {
                tShiftLogEntity objData = new tShiftLogEntity();

                objData.setCOMPANY_CODE(cursor.getString(0));
                objData.setSUPERVISOR_ID(cursor.getString(1));
                objData.setSHIFT_NO(cursor.getString(2));
                objData.setSHIFT_DATE (java.sql.Timestamp.valueOf(cursor.getString(3)));
                objData.setSHIFT_STATUS(cursor.getString(4));
                objData.setCASHIER_ID(cursor.getString(5));
                objData.setBEGINNING_AMOUNT(cursor.getDouble(6));
                objData.setTOTAL_AMOUNT(cursor.getDouble(7));
                objData.setTOTAL_SALES(cursor.getDouble(8));
                objData.setTOTAL_RETUR(cursor.getDouble(9));
                objData.setCREATION_USER_ID(cursor.getString(10));
                objData.setCREATION_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(11)));
                objData.setCHANGE_USER_ID(cursor.getString(12));
                objData.setCHANGE_DATETIME(java.sql.Timestamp.valueOf(cursor.getString(13)));

                listData.add(objData);
            } while (cursor.moveToNext());
        }
        db.close();
        return listData;
    }
}