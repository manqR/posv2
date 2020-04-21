package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mComGenVariableEntity;

public class mComGenVariableHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="mComGenVariable";
    public static final String COMPANY_CODE="COMPANY_CODE";
    public static final String VARIABLE="VARIABLE";
    public static final String VALUE="VALUE";
    public static final String DESCRIPTION="DESCRIPTION";

    public static final String ORDER_NO="ORDER_NO";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;


    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + COMPANY_CODE + " VARCHAR(30) not null, "
            + VARIABLE + " VARCHAR(50) null, "
            + VALUE + " VARCHAR(50) null, "
            + DESCRIPTION + " VARCHAR(50) null,"
            + ORDER_NO + " INTEGER null);";

    public mComGenVariableHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        //db.execSQL(tesHelper.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(mComGenVariableHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void InsertValue(mComGenVariableEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMPANY_CODE,c.getCOMPANY_CODE());
        values.put(VALUE,c.getVALUE());
        values.put(VARIABLE,c.getVARIABLE());
        values.put(DESCRIPTION,c.getDESCRIPTION());
        values.put(ORDER_NO,c.getORDER_NO());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void UpdateValue(mComGenVariableEntity c){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMPANY_CODE,c.getCOMPANY_CODE());
        values.put(VALUE,c.getVALUE());
        values.put(VARIABLE,c.getVARIABLE());
        values.put(DESCRIPTION,c.getDESCRIPTION());
        values.put(ORDER_NO,c.getORDER_NO());

        String[] whereArgs = new String[] {String.valueOf(c.getVARIABLE()),String.valueOf(c.getCOMPANY_CODE())};
        db.update(TABLE_NAME, values, VARIABLE + "=? AND " + COMPANY_CODE + "=?", whereArgs);
        db.close();
    }
    public String getVariableValue(String CompanyCode, String VARIABLE){
        db = this.getReadableDatabase();

        String query="SELECT VALUE FROM "+TABLE_NAME +
                " WHERE VARIABLE = '" + VARIABLE + "' " +
                " AND COMPANY_CODE = '" + CompanyCode + "'";

        Cursor cursor = db.rawQuery(query,null);
        String sVariable="";

        if(cursor.moveToFirst()){
            do {
                sVariable = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        return sVariable;
    }

    public List<String> SelectAllReasonName(){
        List<String> listReason=new ArrayList<String>();
        String query="SELECT DESCRIPTION FROM "+ TABLE_NAME +" WHERE VARIABLE LIKE 'ADJ_STK_REASON%' order by order_no";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                listReason.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        return listReason;
    }

    public List<String> SelectFuelCompany(){
        List<String> listFuelComp=new ArrayList<String>();
        String query="SELECT IFNULL(DESCRIPTION,'') FROM "+ TABLE_NAME +" WHERE VARIABLE LIKE 'FUEL_COMPANY%' order by order_no";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                listFuelComp.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        return listFuelComp;
    }

    public List<mComGenVariableEntity> SelectAllReason(){
        List<mComGenVariableEntity> listReason=new ArrayList<mComGenVariableEntity>();
        String query="SELECT VALUE, DESCRIPTION, order_no FROM "+ TABLE_NAME +" WHERE VARIABLE LIKE 'ADJ_STK_REASON%' order by order_no";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mComGenVariableEntity Reason=new mComGenVariableEntity();
                Reason.setVALUE(cursor.getString(0));
                Reason.setDESCRIPTION(cursor.getString(1));
                listReason.add(Reason);
            }while(cursor.moveToNext());
        }
        return listReason;
    }

    public List<mComGenVariableEntity> getDescription(String value){
        List<mComGenVariableEntity> listReason=new ArrayList<mComGenVariableEntity>();
        String query="SELECT DESCRIPTION FROM "+TABLE_NAME + " WHERE VALUE = '" + value + "'";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mComGenVariableEntity Reason=new mComGenVariableEntity();
                Reason.setDESCRIPTION(cursor.getString(0));
                listReason.add(Reason);
            }while(cursor.moveToNext());
        }
        return listReason;
    }

    //Fuel Price
    public List<mComGenVariableEntity> SelectFuelPrice(String CompanyCode){
        List<mComGenVariableEntity> listPriceFuel=new ArrayList<mComGenVariableEntity>();
        String query="SELECT VALUE, DESCRIPTION FROM "+TABLE_NAME +" WHERE VARIABLE LIKE 'SELL_PRICE_FUEL_%' AND COMPANY_CODE = '" + CompanyCode + "' ORDER BY ORDER_NO";

        /*String query = " SELECT VALUE, DESCRIPTION FROM mComgenVariable \n " +
                            " WHERE VARIABLE LIKE 'SELL_PRICE_FUEL_%' \n " +
                            " AND COMPANY_CODE = CASE WHEN " + CompanyCode + " <> 0 THEN " + CompanyCode + " ELSE 0 END \n " +
                            " ORDER BY ORDER_NO ";*/

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mComGenVariableEntity p=new mComGenVariableEntity();
                p.setVALUE(cursor.getString(0));
                p.setDESCRIPTION(cursor.getString(1));
                listPriceFuel.add(p);
            }while(cursor.moveToNext());
        }
        return listPriceFuel;
    }

    //Fuel Amount
    public List<mComGenVariableEntity> SelectFuelAmount(String CompanyCode){
        List<mComGenVariableEntity> listPriceFuel=new ArrayList<mComGenVariableEntity>();
        String query="SELECT VALUE, DESCRIPTION FROM "+TABLE_NAME +" WHERE VARIABLE LIKE 'SELL_AMT_FUEL_%' AND COMPANY_CODE = '" + CompanyCode + "' ORDER BY ORDER_NO";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mComGenVariableEntity p=new mComGenVariableEntity();
                p.setVALUE(cursor.getString(0));
                p.setDESCRIPTION(cursor.getString(1));
                listPriceFuel.add(p);
            }while(cursor.moveToNext());
        }
        return listPriceFuel;
    }

    //Fuel Type
    public List<mComGenVariableEntity> SelectFuelType(String CompanyCode){
        List<mComGenVariableEntity> listPriceFuel=new ArrayList<mComGenVariableEntity>();
        String query="SELECT VALUE, DESCRIPTION FROM "+TABLE_NAME +" WHERE VARIABLE LIKE 'FUEL_TYPE_%' AND COMPANY_CODE = '" + CompanyCode + "' ORDER BY ORDER_NO";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mComGenVariableEntity p=new mComGenVariableEntity();
                p.setVALUE(cursor.getString(0));
                p.setDESCRIPTION(cursor.getString(1));
                listPriceFuel.add(p);
            }while(cursor.moveToNext());
        }
        return listPriceFuel;
    }

    //Period Type
    public List<mComGenVariableEntity> SelectPeriodType(){
        List<mComGenVariableEntity> listPriceFuel=new ArrayList<mComGenVariableEntity>();
        String query="SELECT VALUE, DESCRIPTION FROM "+TABLE_NAME +" WHERE VARIABLE LIKE 'PERIOD_TYPE_%' ORDER BY ORDER_NO";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mComGenVariableEntity p=new mComGenVariableEntity();
                p.setVALUE(cursor.getString(0));
                p.setDESCRIPTION(cursor.getString(1));
                listPriceFuel.add(p);
            }while(cursor.moveToNext());
        }
        return listPriceFuel;
    }

    public boolean isValueExist (String Value, String CompanyCode){
        boolean retValue = false;

        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" where "+VARIABLE+" = '"+Value+"' and "+COMPANY_CODE+" = '"+CompanyCode+"'";
        //(COMPANY_CODE ='"+CompanyCode+"')// OR COMPANY_CODE = '0')" ;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        if(count>0)
            retValue = true;
        return retValue;
    }

}