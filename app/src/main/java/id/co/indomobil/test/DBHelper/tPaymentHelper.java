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

import id.co.indomobil.test.DBEntity.tPaymentEntity;

public class tPaymentHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="tPayment";

    public static final String COMPANY_CODE = "COMPANY_CODE";
    public static final String SALES_SYS_NO = "SALES_SYS_NO";
    public static final String PAYMENT_SYS_NO = "PAYMENT_SYS_NO";
    public static final String TOTAL_AMOUNT = "TOTAL_AMOUNT";
    public static final String PAYMENT_AMOUNT = "PAYMENT_AMOUNT";
    //public static final String OUTSTANDING_AMOUNT = "OUTSTANDING_AMOUNT";
    public static final String CHANGE_AMOUNT = "CHANGE_AMOUNT";
    public static final String CASH_AMOUNT = "CASH_AMOUNT";
    public static final String CARD_TYPE = "CARD_TYPE";
    public static final String CARD_BANK = "CARD_BANK";
    public static final String CARD_NO = "CARD_NO";
    public static final String CARD_AMOUNT = "CARD_AMOUNT";
    public static final String VOUCHER_NO = "VOUCHER_NO";
    public static final String VOUCHER_AMOUNT = "VOUCHER_AMOUNT";
    public static final String NO_POLISI = "NO_POLISI";
    public static final String NO_TELP_CUST = "NO_TELP_CUST";
    public static final String CREATION_USER_ID="CREATION_USER_ID";
    public static final String CREATION_DATETIME="CREATION_DATETIME";
    public static final String CHANGE_USER_ID="CHANGE_USER_ID";
    public static final String CHANGE_DATETIME="CHANGE_DATETIME";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;

    public  static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "+ TABLE_NAME + "("
            + COMPANY_CODE + " VARCHAR(30) not null, "
            + SALES_SYS_NO + " INTEGER not null, "
            + PAYMENT_SYS_NO + " INTEGER not null, "
            + TOTAL_AMOUNT + " NUMERIC(18,4) null, "
            + PAYMENT_AMOUNT + " NUMERIC(18,4) null, "
            //+ OUTSTANDING_AMOUNT + " NUMERIC(18,4) null, "
            + CHANGE_AMOUNT + " NUMERIC(18,4) null, "
            + CASH_AMOUNT + " NUMERIC(18,4) null, "
            + CARD_TYPE+ " VARCHAR(10) not null, "
            + CARD_BANK+ " VARCHAR(10) not null, "
            + CARD_NO+ " VARCHAR(10) not null, "
            + CARD_AMOUNT + " NUMERIC(18,4) null,"
            + VOUCHER_NO+ " VARCHAR(10) not null, "
            + VOUCHER_AMOUNT + " NUMERIC(18,4) null,"
            + NO_POLISI+ " VARCHAR(20) not null, "
            + NO_TELP_CUST+ " VARCHAR(20) not null, "
            + CREATION_USER_ID+ " VARCHAR(10) null, "
            + CREATION_DATETIME+ " DATETIME null, "
            + CHANGE_USER_ID+ " VARCHAR(10) null, "
            + CHANGE_DATETIME+ " DATETIME null);";

    public tPaymentHelper(Context context){
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

    public void InsertPayment(tPaymentEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int NewPK = getLatestPK() + 1;
        values.put(COMPANY_CODE, c.CompanyCode);
        values.put(SALES_SYS_NO,c.SOSysNo);
        values.put(PAYMENT_SYS_NO,NewPK);
        values.put(TOTAL_AMOUNT,c.TotalAmount);
        values.put(PAYMENT_AMOUNT,c.PaymentAmount);
        //values.put(OUTSTANDING_AMOUNT,c.Ou);
        values.put(CHANGE_AMOUNT,c.ChangeAmount);
        values.put(CASH_AMOUNT,c.CashAmount);
        values.put(CARD_TYPE,c.CardType);
        values.put(CARD_BANK,c.CardBank);
        values.put(CARD_NO,c.CardNo);
        values.put(CARD_AMOUNT,c.CardAmount);
        values.put(VOUCHER_NO,c.VoucherNo);
        values.put(VOUCHER_AMOUNT,c.VoucherAmount);
        values.put(NO_POLISI,c.NoPolCust);
        values.put(NO_TELP_CUST,c.NoTelpCust);
        values.put(CREATION_USER_ID,c.CreationUserID);
        values.put(CREATION_DATETIME, String.valueOf(c.CreationDatetime));
        values.put(CHANGE_USER_ID,c.ChangeUserID);
        values.put(CHANGE_DATETIME, String.valueOf(c.ChangeDatetime));

        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public int getLatestPK() {
        db = this.getReadableDatabase();
        int latestPK = 0;
        SQLiteStatement stmt = null;
        try {
            stmt = db.compileStatement("SELECT ifnull(MAX(PAYMENT_SYS_NO),0) FROM " + TABLE_NAME + "");
            latestPK = (int) stmt.simpleQueryForLong();
        }catch (Exception err){
            System.out.println(err.toString());
        }
        //db.close();
        return latestPK;
    }

    public int CheckExistDBCategory (){
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        db.close();
        int count = cursor.getCount();
        return count;
    }

    public List<tPaymentEntity> SelectAllPayment(String criteria){
        List<tPaymentEntity> listData=new ArrayList<tPaymentEntity>();
        if(!criteria.equals(""))
            criteria = " where " + criteria;
        String query = "SELECT "
                + COMPANY_CODE + ", "
                + SALES_SYS_NO + ", "
                + PAYMENT_SYS_NO + " , "
                + TOTAL_AMOUNT + ", "
                + PAYMENT_AMOUNT + ", "
                + CHANGE_AMOUNT + ", "
                + CASH_AMOUNT + ", "
                + CARD_TYPE+ ", "
                + CARD_BANK+ ", "
                + CARD_NO+ ", "
                + CARD_AMOUNT + ","
                + VOUCHER_NO+ ", "
                + VOUCHER_AMOUNT + ","
                + NO_POLISI + ","
                + NO_TELP_CUST + ","
                + CREATION_USER_ID + ","
                + CREATION_DATETIME + ","
                + CHANGE_USER_ID + ","
                + CHANGE_DATETIME
                +" FROM " + TABLE_NAME + "" +criteria;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);
        //Cursor c = db.q
        if(cursor.moveToFirst()){
            do{
                tPaymentEntity myPayment=new tPaymentEntity();
                //cat.setCATEGORY_CODE(cursor.getString(0));
                myPayment.CompanyCode = cursor.getString(0);
                myPayment.SOSysNo = cursor.getDouble(1);
                myPayment.PaymentSysNo = cursor.getDouble(2);
                myPayment.TotalAmount = cursor.getDouble(3);
                myPayment.PaymentAmount = cursor.getDouble(4);
                myPayment.ChangeAmount = cursor.getDouble(5);
                myPayment.CashAmount = cursor.getDouble(6);
                myPayment.CardType = cursor.getString(7);
                myPayment.CardBank = cursor.getString(8);
                myPayment.CardNo = cursor.getString(9);
                myPayment.CardAmount = cursor.getDouble(10);
                myPayment.VoucherNo = cursor.getString(11);
                myPayment.VoucherAmount = cursor.getDouble(12);
                myPayment.NoPolCust = cursor.getString(13);
                myPayment.NoTelpCust = cursor.getString(14);
                myPayment.CreationUserID = cursor.getString(15);
                myPayment.CreationDatetime = java.sql.Timestamp.valueOf(cursor.getString(16));
                myPayment.ChangeUserID = cursor.getString(17);
                myPayment.ChangeDatetime = java.sql.Timestamp.valueOf(cursor.getString(18));

                listData.add(myPayment);
            }while(cursor.moveToNext());
        }
        db.close();
        return listData;
    }


    public tPaymentEntity SelectAllPaymentEntity (String criteria) {
        db = this.getReadableDatabase();
        if(!criteria.equals(""))
            criteria = " where " + criteria;
        String query = "SELECT "
                + COMPANY_CODE + ", "
                + SALES_SYS_NO + ", "
                + PAYMENT_SYS_NO + " , "
                + TOTAL_AMOUNT + ", "
                + PAYMENT_AMOUNT + ", "
                + CHANGE_AMOUNT + ", "
                + CASH_AMOUNT + ", "
                + CARD_TYPE+ ", "
                + CARD_BANK+ ", "
                + CARD_NO+ ", "
                + CARD_AMOUNT + ","
                + VOUCHER_NO+ ", "
                + VOUCHER_AMOUNT + ","
                + NO_POLISI + ","
                + NO_TELP_CUST + ","
                + CREATION_USER_ID + ","
                + CREATION_DATETIME + ","
                + CHANGE_USER_ID + ","
                + CHANGE_DATETIME
                +" FROM " + TABLE_NAME + "" +criteria;

        Cursor cursor = db.rawQuery(query, null);
        tPaymentEntity tPayment =new tPaymentEntity();

        if (cursor.moveToFirst()) {
            do {
                tPaymentEntity objData = new tPaymentEntity();
                tPayment.CompanyCode = cursor.getString(0);
                tPayment.SOSysNo = cursor.getDouble(1);
                tPayment.PaymentSysNo = cursor.getDouble(2);
                tPayment.TotalAmount = cursor.getDouble(3);
                tPayment.PaymentAmount = cursor.getDouble(4);
                tPayment.ChangeAmount = cursor.getDouble(5);
                tPayment.CashAmount = cursor.getDouble(6);
                tPayment.CardType = cursor.getString(7);
                tPayment.CardBank = cursor.getString(8);
                tPayment.CardNo = cursor.getString(9);
                tPayment.CardAmount = cursor.getDouble(10);
                tPayment.VoucherNo = cursor.getString(11);
                tPayment.VoucherAmount = cursor.getDouble(12);
                tPayment.NoPolCust = cursor.getString(13);
                tPayment.NoTelpCust = cursor.getString(14);
                tPayment.CreationUserID = cursor.getString(15);
                tPayment.CreationDatetime = java.sql.Timestamp.valueOf(cursor.getString(16));
                tPayment.ChangeUserID = cursor.getString(17);
                tPayment.ChangeDatetime = java.sql.Timestamp.valueOf(cursor.getString(18));
            } while (cursor.moveToNext());
        }
        db.close();
        return tPayment;
    }

    public  boolean IsPaymentSysNoAlreadyExist (Integer iSysNo){
        db = this.getReadableDatabase();
        boolean isExist = false ;

        String query = " SELECT SALES_SYS_NO FROM "+TABLE_NAME + " WHERE SALES_SYS_NO = " + String.valueOf(iSysNo);

        Cursor cursor = db.rawQuery(query,null);
        Integer tmpSysNo =0;
        Integer nextLineNo=0;

        if(cursor.moveToFirst()){
            do {
                tmpSysNo = cursor.getInt(0);
            }while (cursor.moveToNext());
        }

        if( tmpSysNo.equals(iSysNo)){
            return true;
        } else {
            return false;
        }

    }

}
