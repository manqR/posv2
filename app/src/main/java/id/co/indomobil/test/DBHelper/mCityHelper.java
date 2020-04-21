package id.co.indomobil.test.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mCityEntity;

import static android.content.ContentValues.TAG;

public class mCityHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="mCity";
    public static final String CITY_CODE="CITY_CODE";
    public static final String CITY_NAME="CITY_NAME";
    public static final String CREATION_USER_ID="CREATION_USER_ID";
    public static final String CREATION_DATETIME="CREATION_DATETIME";
    public static final String CHANGE_USER_ID="CHANGE_USER_ID";
    public static final String CHANGE_DATETIME="CHANGE_DATETIME";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;

    public static final String TABLE_CREATE ="CREATE TABLE IF NOT EXISTS  "
            + TABLE_NAME + "("
            + CITY_CODE+ " VARCHAR(10) not null, "
            + CITY_NAME+ " VARCHAR(100) null, "
            + CREATION_USER_ID+ " VARCHAR(10) null, "
            + CREATION_DATETIME+ " DATETIME null, "
            + CHANGE_USER_ID+ " VARCHAR(10) null, "
            + CHANGE_DATETIME+ " DATETIME null);";

    public mCityHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(mCityHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }


    public List<mCityEntity> SelectAllCity(){
        List<mCityEntity> listCity=new ArrayList<mCityEntity>();
        String query="SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mCityEntity city=new mCityEntity();
                city.setCITY_CODE(cursor.getString(0));
                city.setCITY_NAME(cursor.getString(1));
                listCity.add(city);
            }while(cursor.moveToNext());
        }
        return listCity;
    }

    public Cursor GetCityByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        SQLiteDatabase mDb = null;
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(TABLE_NAME, new String[] {CITY_CODE,CITY_CODE},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, TABLE_NAME, new String[] {CITY_CODE,CITY_CODE},
                    CITY_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }



}
