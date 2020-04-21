package id.co.indomobil.test.DBHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import id.co.indomobil.test.DBEntity.mCategoryEntity;

public class mCategoryHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="mCategory";
    public static final String RECORD_STATUS="RECORD_STATUS";
    public static final String CATEGORY_CODE="CATEGORY_CODE";
    public static final String CATEGORY_NAME="CATEGORY_NAME";
    public static final String CREATION_USER_ID="CREATION_USER_ID";
    public static final String CREATION_DATETIME="CREATION_DATETIME";
    public static final String CHANGE_USER_ID="CHANGE_USER_ID";
    public static final String CHANGE_DATETIME="CHANGE_DATETIME";


    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;


    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + RECORD_STATUS+ " CHAR(1) not null, "
            + CATEGORY_CODE+ " VARCHAR(10) not null, "
            + CATEGORY_NAME+ " VARCHAR(100) null, "
            + CREATION_USER_ID+ " VARCHAR(10) null, "
            + CREATION_DATETIME+ " DATETIME null, "
            + CHANGE_USER_ID+ " VARCHAR(10) null, "
            + CHANGE_DATETIME+ " DATETIME null);";

    public mCategoryHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(mCategoryHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void InsertCategory(mCategoryEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(CATEGORY_CODE,c.getCATEGORY_CODE());
        values.put(CATEGORY_NAME,c.getCATEGORY_NAME());
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int CheckExistDBCategory (){
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        return count;
    }

    public boolean isCategoryExist (String CategoryCode){
        boolean retValue = false;

        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" where "+CATEGORY_CODE+" = '"+CategoryCode+"' ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        if(count>0)
            retValue = true;
        return retValue;
    }

    public List<mCategoryEntity> SelectAllCategory(String Filter){
        List<mCategoryEntity> listCat=new ArrayList<mCategoryEntity>();
        String query="SELECT * FROM "+TABLE_NAME;

        if (!Filter.equals("")){
            query = query + Filter + " Order by category_code";
        }

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mCategoryEntity cat=new mCategoryEntity();
                cat.setCATEGORY_CODE(cursor.getString(1));
                cat.setCATEGORY_NAME(cursor.getString(2));
                listCat.add(cat);
            }while(cursor.moveToNext());
        }
        return listCat;
    }

    public List<String> SelectAllCategoryName(){
        List<String> listCat=new ArrayList<String>();
        String query="SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                listCat.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        return listCat;
    }

    public void UpdateCategory(mCategoryEntity c){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(CATEGORY_CODE,c.getCATEGORY_CODE());
        values.put(CATEGORY_NAME,c.getCATEGORY_NAME());
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME,String.valueOf(c.getCHANGE_DATETIME()));

        String[] whereArgs = new String[] {String.valueOf(c.getCATEGORY_CODE())};
        db.update(TABLE_NAME, values, CATEGORY_CODE + "=?", whereArgs);
        db.close();
    }

    public void DeleteCategory(mCategoryEntity c){
        ContentValues values = new ContentValues();
        values.put(CATEGORY_CODE,c.getCATEGORY_CODE());

        String[] whereArgs = new String[] {String.valueOf(c.getCATEGORY_CODE())};
        db.delete(TABLE_NAME, CATEGORY_CODE + "=?", whereArgs);
        db.close();
    }

}
