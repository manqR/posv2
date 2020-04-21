package id.co.indomobil.test.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mEmployeeEntity;

public class mEmployeeHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME="mEmployee";
    public static final String RECORD_STATUS="RECORD_STATUS";
    public static final String EMPLOYEE_NO="EMPLOYEE_NO";
    public static final String EMPLOYEE_NAME="EMPLOYEE_NAME";
    public static final String EMPLOYEE_ADDRESS="EMPLOYEE_ADDRESS";
    public static final String CITY_CODE="CITY_CODE";
    public static final String COMPANY_CODE="COMPANY_CODE";
    public static final String ACCESS_CODE="ACCESS_CODE";
    public static final String HASH_PASS="HASH_PASS";
    public static final String KEY_PASS="KEY_PASS";
    public static final String PHONE_NO="PHONE_NO";
    public static final String JOB_POSITION="JOB_POSITION";
    public static final String CREATION_USER_ID="CREATION_USER_ID";
    public static final String CREATION_DATETIME="CREATION_DATETIME";
    public static final String CHANGE_USER_ID="CHANGE_USER_ID";
    public static final String CHANGE_DATETIME="CHANGE_DATETIME";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;

    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + RECORD_STATUS+ " CHAR(1) null, "
            + EMPLOYEE_NO+ " VARCHAR(10) not null, "
            + EMPLOYEE_NAME+ " VARCHAR(200) null, "
            + EMPLOYEE_ADDRESS+ " VARCHAR(200) null, "
            + CITY_CODE+ " VARCHAR(10) null, "
            + COMPANY_CODE+ " VARCHAR(30) not null, "
            + ACCESS_CODE+ " VARCHAR(10) null, "
            + HASH_PASS+ " VARCHAR(250) null, "
            + KEY_PASS+ " VARCHAR(250) null, "
            + PHONE_NO+ " VARCHAR(50) null, "
            + JOB_POSITION+ " VARCHAR(10) null, "
            + CREATION_USER_ID+ " VARCHAR(10) null, "
            + CREATION_DATETIME+ " DATETIME null, "
            + CHANGE_USER_ID+ " VARCHAR(10) null, "
            + CHANGE_DATETIME+ " DATETIME null);";

    public mEmployeeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public mEmployeeHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public mEmployeeEntity SelectEmployee (String employeeCode){
        db = this.getReadableDatabase();
        /*String query = "SELECT * FROM mEmployee";*/
        String query = "SELECT EMPLOYEE_NO ," +
                " EMPLOYEE_NAME ," +
                " EMPLOYEE_ADDRESS ," +
                " CITY_CODE ," +
                " COMPANY_CODE ," +
                " ACCESS_CODE ," +
                " HASH_PASS ," +
                " KEY_PASS ," +
                " PHONE_NO ," +
                " JOB_POSITION FROM mEmployee " +
                " WHERE EMPLOYEE_NO = '"+ employeeCode +"'";
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        mEmployeeEntity  m = new mEmployeeEntity();
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString(0);
                if (a.equals(employeeCode)){
                    m = new mEmployeeEntity();
                    m.setEMPLOYEE_NO(cursor.getString(0));
                    m.setEMPLOYEE_NAME(cursor.getString(1));
                    m.setEMPLOYEE_ADDRESS(cursor.getString(2));
                    m.setCITY_CODE(cursor.getString(3));
                    m.setCOMPANY_CODE(cursor.getString(4));
                    m.setACCESS_CODE(cursor.getString(5));
                    m.setHASH_PASS(cursor.getString(6));
                    m.setKEY_PASS(cursor.getString(7));
                    m.setPHONE_NO(cursor.getString(8));
                    m.setJOB_POSITION(cursor.getString(9));
                    break;
                }
            }while (cursor.moveToNext());
        }
        return m;
    }


    public void InsertEmployee(mEmployeeEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(EMPLOYEE_NO,c.getEMPLOYEE_NO());
        values.put(EMPLOYEE_NAME,c.getEMPLOYEE_NAME());
        values.put(EMPLOYEE_ADDRESS,c.getEMPLOYEE_ADDRESS());
        values.put(CITY_CODE,c.getCITY_CODE());
        values.put(COMPANY_CODE,c.getCOMPANY_CODE());
        values.put(ACCESS_CODE,c.getACCESS_CODE());
        values.put(HASH_PASS,c.getHASH_PASS());
        values.put(KEY_PASS,c.getKEY_PASS());
        values.put(PHONE_NO,c.getPHONE_NO());
        values.put(JOB_POSITION, c.getJOB_POSITION());
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void UpdatePassword(mEmployeeEntity e){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMPLOYEE_NO,e.getEMPLOYEE_NO());
        values.put(HASH_PASS,e.getHASH_PASS());

        String[] whereArgs = new String[] {String.valueOf(e.getEMPLOYEE_NO())};
        db.update(TABLE_NAME, values, EMPLOYEE_NO + "=?", whereArgs);
        db.close();
    }

    public void UpdateEmployee(mEmployeeEntity c){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMPLOYEE_NO,c.getEMPLOYEE_NO());

        String[] whereArgs = new String[] {String.valueOf(c.getEMPLOYEE_NO())};
        db.update(TABLE_NAME, values, EMPLOYEE_NO + "=?", whereArgs);
        db.close();
    }

    public List<mEmployeeEntity> SelectEmpByJob(String companycode, String jobPost){
        List<mEmployeeEntity> listEmp=new ArrayList<mEmployeeEntity>();
        String query = "SELECT EMPLOYEE_NO ,\n" +
                " EMPLOYEE_NAME ," +
                " EMPLOYEE_ADDRESS ," +
                " CITY_CODE ," +
                " COMPANY_CODE ," +
                " ACCESS_CODE ," +
                " HASH_PASS ," +
                " KEY_PASS ," +
                " PHONE_NO ," +
                " JOB_POSITION FROM mEmployee " +
                " WHERE COMPANY_CODE = '"+ companycode +"' AND JOB_POSITION='" + jobPost +"'";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mEmployeeEntity mEmp=new mEmployeeEntity();
                mEmp.setEMPLOYEE_NO(cursor.getString(0));
                mEmp.setEMPLOYEE_NAME(cursor.getString(1));
                mEmp.setEMPLOYEE_ADDRESS(cursor.getString(2));
                mEmp.setCITY_CODE(cursor.getString(3));
                mEmp.setCOMPANY_CODE(cursor.getString(4));
                mEmp.setACCESS_CODE(cursor.getString(5));
                mEmp.setHASH_PASS(cursor.getString(6));
                mEmp.setKEY_PASS(cursor.getString(7));
                mEmp.setPHONE_NO(cursor.getString(8));
                mEmp.setJOB_POSITION(cursor.getString(9));

                listEmp.add(mEmp);
            }while(cursor.moveToNext());
        }
        return listEmp;
    }

    public boolean isEmployeeExist (String EmployeeNo){
        boolean retValue = false;

        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" where "+EMPLOYEE_NO+" = '"+EmployeeNo+"' ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        if(count>0)
            retValue = true;
        return retValue;
    }
}
