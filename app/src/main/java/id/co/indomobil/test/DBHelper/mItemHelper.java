package id.co.indomobil.test.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mItemEntity;
import id.co.indomobil.test.DBEntity.mPriceListEntity;

public class mItemHelper  extends SQLiteOpenHelper {
    public static final String TABLE_NAME="mItem0";
    public static final String RECORD_STATUS="RECORD_STATUS";
    public static final String ITEM_CODE="ITEM_CODE";
    public static final String ITEM_NAME="ITEM_NAME";
    public static final String ITEM_DESCRIPTION="ITEM_DESCRIPTION";
    public static final String CLASS_CODE="CLASS_CODE";
    public static final String CATEGORY_CODE="CATEGORY_CODE";
    public static final String UOM_CODE="UOM_CODE";
    public static final String REMARK ="REMARK";
    public static final String CREATION_USER_ID="CREATION_USER_ID";
    public static final String CREATION_DATETIME="CREATION_DATETIME";
    public static final String CHANGE_USER_ID="CHANGE_USER_ID";
    public static final String CHANGE_DATETIME="CHANGE_DATETIME";

    public static final String DATABASE_NAME="POSv2.db";
    public static final int DATABASE_VERSION=1;
    SQLiteDatabase db;

    public  static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + RECORD_STATUS+ " CHAR(1) null, "
            + ITEM_CODE+ " VARCHAR(30) not null, "
            + ITEM_NAME+ " VARCHAR(100) null, "
            + ITEM_DESCRIPTION+ " VARCHAR(100)  null, "
            + CLASS_CODE+ " VARCHAR(10) null, "
            + CATEGORY_CODE+ " VARCHAR(10)  null, "
            + UOM_CODE+ " VARCHAR(10) null, "
            + REMARK + " VARCHAR(100)  null, "
            + CREATION_USER_ID+ " VARCHAR(10) null, "
            + CREATION_DATETIME+ " DATETIME null, "
            + CHANGE_USER_ID+ " VARCHAR(10) null, "
            + CHANGE_DATETIME+ " DATETIME null);";



    public static final String mPriceList_TABLE="mPriceList";
    public static final String mPriceList_RECORD_STATUS="RECORD_STATUS";
    public static final String mPriceList_ITEM_CODE="ITEM_CODE";
    public static final String mPriceList_EFF_DATE="EFF_DATE";
    public static final String mPriceList_PRICE_AMOUNT="PRICE_AMOUNT";
    public static final String mPriceList_CREATION_USER_ID="CREATION_USER_ID";
    public static final String mPriceList_CREATION_DATETIME="CREATION_DATETIME";
    public static final String mPriceList_CHANGE_USER_ID="CHANGE_USER_ID";
    public static final String mPriceList_CHANGE_DATETIME="CHANGE_DATETIME";
    public static final String mPriceList_DATABASE_NAME="POS_MOBILE.db";

    public  static final String mPriceList_create = "CREATE TABLE "
            + mPriceList_TABLE + "("
            + mPriceList_RECORD_STATUS+ " CHAR(1) null, "
            + mPriceList_ITEM_CODE+ " VARCHAR(30) not null, "
            + mPriceList_EFF_DATE+ " DATETIME null, "
            + mPriceList_PRICE_AMOUNT+ " NUMERIC(18,4) null, "
            + mPriceList_CREATION_USER_ID+ " VARCHAR(10) null, "
            + mPriceList_CREATION_DATETIME+ " DATETIME null, "
            + mPriceList_CHANGE_USER_ID+ " VARCHAR(10) null, "
            + mPriceList_CHANGE_DATETIME+ " DATETIME null);";

    public mItemHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

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

    public void InsertItem(mItemEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(ITEM_CODE,c.getITEM_CODE());
        values.put(ITEM_NAME,c.getITEM_NAME());
        values.put(ITEM_DESCRIPTION,c.getITEM_DESCRIPTION());
        values.put(CLASS_CODE,c.getCLASS_CODE());
        values.put(CATEGORY_CODE,c.getCATEGORY_CODE());
        values.put(UOM_CODE,c.getUOM_CODE());
        values.put(REMARK,c.getREMARKS());
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME, String.valueOf(c.getCHANGE_DATETIME()));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void InsertmPriceList(mPriceListEntity c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ITEM_CODE",c.getITEM_CODE());
        values.put("EFF_DATE",String.valueOf(c.getEFF_DATE()));
        values.put("PRICE_AMOUNT",String.valueOf(c.getPRICE_AMOUNT()));
        values.put(CREATION_USER_ID,c.getCREATION_USER_ID());
        values.put(CREATION_DATETIME, String.valueOf(c.getCREATION_DATETIME()));

        db.insert(mPriceList_TABLE, null, values);
        db.close();
    }

    public void UpdateItem(mItemEntity c){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(RECORD_STATUS,c.getRECORD_STATUS());
        values.put(ITEM_NAME,c.getITEM_NAME());
        values.put(ITEM_DESCRIPTION,c.getITEM_DESCRIPTION());
        values.put(CLASS_CODE,c.getCLASS_CODE());
        values.put(CATEGORY_CODE,c.getCATEGORY_CODE());
        values.put(UOM_CODE,c.getUOM_CODE());
        values.put(REMARK,c.getREMARKS());
        values.put(CATEGORY_CODE,c.getCATEGORY_CODE());
        values.put(CHANGE_USER_ID,c.getCHANGE_USER_ID());
        values.put(CHANGE_DATETIME,String.valueOf(c.getCHANGE_DATETIME()));

        String[] whereArgs = new String[] {String.valueOf(c.getITEM_CODE())};
        db.update(TABLE_NAME, values, ITEM_CODE + "=?", whereArgs);
        db.close();
    }

    public void DeleteItem(mItemEntity c){
        ContentValues values = new ContentValues();
        values.put(ITEM_CODE,c.getITEM_CODE());

        String[] whereArgs = new String[] {String.valueOf(c.getITEM_CODE())};
        db.delete(TABLE_NAME, ITEM_CODE + "=?", whereArgs);
        db.close();
    }

    public  String SelectItemName (String itemCode){
        db = this.getReadableDatabase();
        String query = "select ITEM_CODE,ITEM_NAME FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b = "data not found";
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString(0);
                if (a.equals(itemCode)){
                    b = cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }
        return b;
    }

    public  mItemEntity SelectItemByCode (String itemCode){
        db = this.getReadableDatabase();
        String query = "SELECT \n" +
                "ITEM_CODE\n" +
                ",ITEM_NAME\n" +
                ",ITEM_DESCRIPTION\n" +
                ",CLASS_CODE\n" +
                ",CATEGORY_CODE\n" +
                ",UOM_CODE\n" +
                ",REMARK\n" +
                " FROM mItem0 WHERE ITEM_CODE='" +itemCode +"'";

        Cursor cursor = db.rawQuery(query,null);
        mItemEntity mItem =new mItemEntity();

        String a,b;
        b = "data not found";
        if(cursor.moveToFirst()){
            do {
                mItem = new mItemEntity();
                mItem.setITEM_CODE(cursor.getString(0));
                mItem.setITEM_NAME(cursor.getString(1));
                mItem.setITEM_DESCRIPTION(cursor.getString(2));
                mItem.setCLASS_CODE(cursor.getString(3));
                mItem.setCATEGORY_CODE(cursor.getString(4));
                mItem.setUOM_CODE(cursor.getString(5));
                mItem.setREMARKS(cursor.getString(6));
            }while (cursor.moveToNext());
        }
        return mItem ;
    }

    public  double GetItemPriceSales (String itemCode, String companyCode, String SlsDocNo){
        db = this.getReadableDatabase();

        String query = "select ITEM_CODE,PRICE_LIST FROM tSales1 \n" +
                " WHERE COMPANY_CODE = '" + companyCode + "' AND SALES_DOC_NO = '" + SlsDocNo + "' AND ITEM_CODE = '" + itemCode + "'  \n" +
                " LIMIT 1";
        Cursor cursor = db.rawQuery(query,null);
        double SellingPrice = 0 ;

        if(cursor.moveToFirst()){
            do {
                SellingPrice = cursor.getDouble(1);
                break;

            }while (cursor.moveToNext());
        }

        /*if (SellingPrice==0){
            query = "select ITEM_CODE,COMPANY_CODE,PRICE_AMOUNT FROM \n"+
                    mPriceList_TABLE + " WHERE COMPANY_CODE = 0 \n" +
                    " AND ITEM_CODE='" + itemCode + "' ";
             cursor = db.rawQuery(query,null);
            if(cursor.moveToFirst()){
                do {
                    SellingPrice = cursor.getDouble(2);
                    break;

                }while (cursor.moveToNext());
            }


        }*/
        return SellingPrice;
    }

    public  double GetItemPrice (String itemCode, String companyCode){
        db = this.getReadableDatabase();
        String query = "select ITEM_CODE,PRICE_AMOUNT FROM \n"+
                mPriceList_TABLE + " WHERE \n" +
                " (COMPANY_CODE = '" + companyCode + "' OR COMPANY_CODE = 0) and ITEM_CODE='" + itemCode + "' \n" +
                " AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                "ORDER BY COMPANY_CODE DESC, EFF_DATE DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query,null);
        double SellingPrice = 0 ;

        if(cursor.moveToFirst()){
            do {
                SellingPrice = cursor.getDouble(1);
                break;

            }while (cursor.moveToNext());
        }

        /*if (SellingPrice==0){
            query = "select ITEM_CODE,COMPANY_CODE,PRICE_AMOUNT FROM \n"+
                    mPriceList_TABLE + " WHERE COMPANY_CODE = 0 \n" +
                    " AND ITEM_CODE='" + itemCode + "' ";
             cursor = db.rawQuery(query,null);
            if(cursor.moveToFirst()){
                do {
                    SellingPrice = cursor.getDouble(2);
                    break;

                }while (cursor.moveToNext());
            }


        }*/
        return SellingPrice;
    }

    public  Integer GetItemPriceByName (String itemcode, String companyCode){
        db = this.getReadableDatabase();
        String query = "select p.ITEM_CODE, p.COMPANY_CODE,p.PRICE_AMOUNT FROM mItem0 i \n" +
                " Left Join mPriceList p ON i.item_code = p.item_code \n" +
                " WHERE (p.COMPANY_CODE='" + companyCode +"'  or p.COMPANY_CODE = 0)\n" +
                " AND lower(i.ITEM_CODE)='" + itemcode + "' \n" +
                " AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                " ORDER BY COMPANY_CODE DESC, EFF_DATE DESC limit 1\n";


        Cursor cursor = db.rawQuery(query,null);
        Integer SellingPrice = 0 ;

        if(cursor.moveToFirst()){
            do {
                SellingPrice = cursor.getInt(2);
                break;

            }while (cursor.moveToNext());
        }

        /*if (SellingPrice==0){
            query = "select ITEM_CODE,COMPANY_CODE,PRICE_AMOUNT FROM \n"+
                    mPriceList_TABLE + " WHERE COMPANY_CODE = 0 \n" +
                    " AND ITEM_CODE='" + itemCode + "' ";
             cursor = db.rawQuery(query,null);
            if(cursor.moveToFirst()){
                do {
                    SellingPrice = cursor.getDouble(2);
                    break;

                }while (cursor.moveToNext());
            }
        }*/
        return SellingPrice;
    }

    public int CheckExistDBItem (){
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        return count;
    }
    public boolean isItemCodeExist (String ItemCode){
        boolean retValue = false;
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" where "+ITEM_CODE+" = '"+ItemCode+"' ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        if(count>0)
            retValue = true;
        return retValue;
    }

    public List<mItemEntity> SelectAllItem(String companycode, String Filter){
        List<mItemEntity> listItem=new ArrayList<mItemEntity>();
        String query = "SELECT \n" +
                "           mi0.ITEM_CODE\n" +
                "           ,mi0.ITEM_NAME\n" +
                "           ,mi0.ITEM_DESCRIPTION\n" +
                "           ,mi0.CLASS_CODE\n" +
                "           ,mi0.CATEGORY_CODE\n" +
                "           ,mi0.UOM_CODE\n" +
                "           ,mi0.REMARK\n" +
                "           ,lcs.QTY_END\n" +
                ",          IFNULL((SELECT PRICE_AMOUNT \n" +
                "               FROM mPriceList \n" +
                "                   WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE \n" +
                "                       AND mPriceList.RECORD_STATUS = 'A' \n" +
                "                       AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "                       AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                "               ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) PRICE_AMOUNT\n" +
                "       FROM mItem0 mi0\n" +
                "           LEFT JOIN mLocationStock lcs ON \n" +
                "               lcs.ITEM_CODE = mi0.ITEM_CODE \n" +
                "   WHERE IFNULL((SELECT PRICE_AMOUNT  \n" +
                "      FROM mPriceList  \n" +
                "      WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE  \n" +
                "      AND mPriceList.RECORD_STATUS = 'A'  \n" +
                "      AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "     ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) > 0 \n" +
                "     AND mi0.RECORD_STATUS = 'A' \n" +
                " " + Filter + " \n" +
                " UNION \n" +
                " SELECT \n" +
                "           mi0.ITEM_CODE\n" +
                "           ,mi0.ITEM_NAME\n" +
                "           ,mi0.ITEM_DESCRIPTION\n" +
                "           ,mi0.CLASS_CODE\n" +
                "           ,mi0.CATEGORY_CODE\n" +
                "           ,mi0.UOM_CODE\n" +
                "           ,mi0.REMARK\n" +
                "           ,IFNULL(lcs.QTY_END,0)\n" +
                ",          IFNULL((SELECT PRICE_AMOUNT \n" +
                "               FROM mPriceList \n" +
                "                   WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE \n" +
                "                       AND mPriceList.RECORD_STATUS = 'A' \n" +
                "                       AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "                       AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                "               ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) PRICE_AMOUNT\n" +
                "       FROM mItem0 mi0\n" +
                "           LEFT JOIN mLocationStock lcs ON \n" +
                "               lcs.ITEM_CODE = mi0.ITEM_CODE \n" +
                "   WHERE IFNULL((SELECT PRICE_AMOUNT  \n" +
                "      FROM mPriceList  \n" +
                "      WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE  \n" +
                "      AND mPriceList.RECORD_STATUS = 'A'  \n" +
                "      AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "     ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) > 0 \n" +
                "     AND mi0.RECORD_STATUS = 'A' \n" +
                " and mi0.CATEGORY_CODE LIKE 'JS%' \n" +
                " ORDER BY QTY_END DESC, mi0.CATEGORY_CODE ASC, mi0.ITEM_CODE ASC ";

      /*  if (!Filter.equals("")){
            query = query + " " + Filter;
        }*/

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mItemEntity item=new mItemEntity();
                item.setITEM_CODE(cursor.getString(0));
                item.setITEM_NAME(cursor.getString(1));
                item.setITEM_DESCRIPTION(cursor.getString(2));
                item.setCLASS_CODE(cursor.getString(3));
                item.setCATEGORY_CODE(cursor.getString(4));
                item.setUOM_CODE(cursor.getString(5));
                item.setREMARKS(cursor.getString(6));
                item.setQTY_END(cursor.getDouble(7));
                item.setPRICE_AMOUNT(cursor.getDouble(8));
                listItem.add(item);
            }while(cursor.moveToNext());
        }
        return listItem;
    }

    public List<mItemEntity> SelectAllItemMaster( String companycode, String Filter){
        List<mItemEntity> listItem=new ArrayList<mItemEntity>();
        String query = "SELECT \n" +
                "           mi0.ITEM_CODE\n" +
                "           ,mi0.ITEM_NAME\n" +
                "           ,mi0.ITEM_DESCRIPTION\n" +
                "           ,mi0.CLASS_CODE\n" +
                "           ,mi0.CATEGORY_CODE\n" +
                "           ,mi0.UOM_CODE\n" +
                "           ,mi0.REMARK\n" +
                "           ,lcs.QTY_END\n" +
                ",          IFNULL((SELECT PRICE_AMOUNT \n" +
                "               FROM mPriceList \n" +
                "                   WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE \n" +
                "                       AND mPriceList.RECORD_STATUS = 'A' \n" +
                "                       AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "                       AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                "               ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) PRICE_AMOUNT\n" +
                "       FROM mItem0 mi0\n" +
                "           LEFT JOIN mLocationStock lcs ON \n" +
                "               lcs.ITEM_CODE = mi0.ITEM_CODE \n" +
                "   WHERE IFNULL((SELECT PRICE_AMOUNT  \n" +
                "      FROM mPriceList  \n" +
                "      WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE  \n" +
                "      AND mPriceList.RECORD_STATUS = 'A'  \n" +
                "      AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "     ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) > 0 \n" +
                "     AND mi0.RECORD_STATUS = 'A' \n" +
                " " + Filter + " \n" +
                " UNION \n" +
                " SELECT mi0.ITEM_CODE \n" +
                " ,mi0.ITEM_NAME \n" +
                " ,mi0.ITEM_DESCRIPTION \n" +
                " ,mi0.CLASS_CODE \n" +
                " ,mi0.CATEGORY_CODE \n" +
                " ,mi0.UOM_CODE \n" +
                " ,mi0.REMARK \n" +
                " ,IFNULL(lcs.QTY_END,0) \n" +
                " ,0 \n " +
                " FROM  mItem0 mi0 " +
                " LEFT JOIN mLocationStock lcs ON \n " +
                " lcs.ITEM_CODE = mi0.ITEM_CODE \n " +
                " WHERE CATEGORY_CODE = 'SV' \n " +
                " AND mi0.RECORD_STATUS = 'A' \n " +
                " ORDER BY QTY_END DESC, mi0.CATEGORY_CODE ASC, mi0.ITEM_CODE ASC ";

      /*  if (!Filter.equals("")){
            query = query + " " + Filter;
        }*/

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mItemEntity item=new mItemEntity();
                item.setITEM_CODE(cursor.getString(0));
                item.setITEM_NAME(cursor.getString(1));
                item.setITEM_DESCRIPTION(cursor.getString(2));
                item.setCLASS_CODE(cursor.getString(3));
                item.setCATEGORY_CODE(cursor.getString(4));
                item.setUOM_CODE(cursor.getString(5));
                item.setREMARKS(cursor.getString(6));
                item.setQTY_END(cursor.getDouble(7));
                item.setPRICE_AMOUNT(cursor.getDouble(8));
                listItem.add(item);
            }while(cursor.moveToNext());
        }
        return listItem;
    }

    public List<mItemEntity> SelectAllItemAdjustment( String companycode, String Filter){
        List<mItemEntity> listItem=new ArrayList<mItemEntity>();
        String query = "SELECT \n" +
                "           mi0.ITEM_CODE\n" +
                "           ,mi0.ITEM_NAME\n" +
                "           ,mi0.ITEM_DESCRIPTION\n" +
                "           ,mi0.CLASS_CODE\n" +
                "           ,mi0.CATEGORY_CODE\n" +
                "           ,mi0.UOM_CODE\n" +
                "           ,mi0.REMARK\n" +
                "           ,lcs.QTY_END\n" +
                ",          IFNULL((SELECT PRICE_AMOUNT \n" +
                "               FROM mPriceList \n" +
                "                   WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE \n" +
                "                       AND mPriceList.RECORD_STATUS = 'A' \n" +
                "                       AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "                       AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                "               ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) PRICE_AMOUNT\n" +
                "       FROM mItem0 mi0\n" +
                "           LEFT JOIN mLocationStock lcs ON \n" +
                "               lcs.ITEM_CODE = mi0.ITEM_CODE \n" +
                "   WHERE IFNULL((SELECT PRICE_AMOUNT  \n" +
                "      FROM mPriceList  \n" +
                "      WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE  \n" +
                "      AND mPriceList.RECORD_STATUS = 'A'  \n" +
                "      AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "     ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) > 0 \n" +
                " " + Filter + " \n" +
                " UNION " +
                " SELECT mi0.ITEM_CODE \n" +
                " ,mi0.ITEM_NAME \n" +
                " ,mi0.ITEM_DESCRIPTION \n" +
                " ,mi0.CLASS_CODE \n" +
                " ,mi0.CATEGORY_CODE \n" +
                " ,mi0.UOM_CODE \n" +
                " ,mi0.REMARK \n" +
                " ,IFNULL(lcs.QTY_END,0) \n" +
                " ,0 \n " +
                " FROM  mItem0 mi0 " +
                " LEFT JOIN mLocationStock lcs ON \n " +
                " lcs.ITEM_CODE = mi0.ITEM_CODE \n " +
                " WHERE CATEGORY_CODE = 'SV' \n " +
                " AND mi0.RECORD_STATUS = 'A' \n " +
                " ORDER BY QTY_END DESC, mi0.CATEGORY_CODE ASC, mi0.ITEM_CODE ASC " ;

      /*  if (!Filter.equals("")){
            query = query + " " + Filter;
        }*/

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mItemEntity item=new mItemEntity();
                item.setITEM_CODE(cursor.getString(0));
                item.setITEM_NAME(cursor.getString(1));
                item.setITEM_DESCRIPTION(cursor.getString(2));
                item.setCLASS_CODE(cursor.getString(3));
                item.setCATEGORY_CODE(cursor.getString(4));
                item.setUOM_CODE(cursor.getString(5));
                item.setREMARKS(cursor.getString(6));
                item.setQTY_END(cursor.getDouble(7));
                item.setPRICE_AMOUNT(cursor.getDouble(8));
                listItem.add(item);
            }while(cursor.moveToNext());
        }
        return listItem;
    }


    public List<mItemEntity> SelectAllItemReturn( String companycode, Integer sysNo){
        List<mItemEntity> listItem=new ArrayList<mItemEntity>();

       /* String query = " SELECT s1.ITEM_CODE\n" +
                " , mi0.ITEM_NAME\n" +
                " , mi0.ITEM_DESCRIPTION\n" +
                " , mi0.CLASS_CODE\n" +
                " , mi0.CATEGORY_CODE\n" +
                " , mi0.UOM_CODE\n" +
                " , mi0.REMARK\n" +
                " , s1.QUANTITY\n" +
                " , (SELECT PRICE_AMOUNT " +
        "       FROM mPriceList " +
                "           WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE " +
                "                   AND (COMPANY_CODE = " + String.valueOf(companycode) + " OR COMPANY_CODE = 0) \n" +
                "                       AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                "   ORDER BY COMPANY_CODE DESC, EFF_DATE DESC LIMIT 1) PRICE_AMOUNT \n" +
                " , ab.QTY_TO_RETURN QTY_TO_RETURN\n" +
                "   FROM tSales1 s1\n" +
                        " LEFT JOIN mitem0 mi0 ON s1.ITEM_CODE = mi0.ITEM_CODE\n" +
                        " LEFT JOIN (SELECT s1.ITEM_CODE, IFNULL(s1.QUANTITY,0) - IFNULL (SUM(sr1.QUANTITY_RETURN),0) QTY_TO_RETURN\n" +
                "                       FROM tSales1 s1\n" +
                "                           LEFT JOIN tSalesReturn1 sr1 \n" +
                "                               ON sr1.COMPANY_CODE = s1.COMPANY_CODE \n" +
                "                                   AND sr1.SALES_SYS_NO = s1.SALES_SYS_NO \n" +
                "                                   AND sr1.ITEM_CODE = s1.ITEM_CODE\n" +
                "                       WHERE s1.SALES_SYS_NO = " + sysNo + " \n" +
                "                               AND s1.COMPANY_CODE = " + String.valueOf(companycode) + " \n" +
                "                               AND SALES_DOC_NO <> '' \n" +
                "                       GROUP BY s1.ITEM_CODE, s1.QUANTITY" +
                "                                    ) AB " +
                "           ON AB.ITEM_CODE = s1.ITEM_CODE \n" +
                " WHERE s1.COMPANY_CODE = " + String.valueOf(companycode) + " AND SALES_SYS_NO = " + sysNo + " AND SALES_DOC_NO <> ''\n";
                */
        String query = " SELECT s1.ITEM_CODE\n" +
                " , mi0.ITEM_NAME\n" +
                " , mi0.ITEM_DESCRIPTION\n" +
                " , mi0.CLASS_CODE\n" +
                " , mi0.CATEGORY_CODE\n" +
                " , mi0.UOM_CODE\n" +
                " , mi0.REMARK\n" +
                " , s1.QUANTITY\n" +
                " , net_price PRICE_AMOUNT \n" +
                " , ab.QTY_TO_RETURN QTY_TO_RETURN\n" +
                "   FROM tSales1 s1\n" +
                " LEFT JOIN mitem0 mi0 ON s1.ITEM_CODE = mi0.ITEM_CODE\n" +
                " LEFT JOIN (SELECT s1.ITEM_CODE, IFNULL(s1.QUANTITY,0) - IFNULL (SUM(sr1.QUANTITY_RETURN),0) QTY_TO_RETURN\n" +
                "                       FROM tSales1 s1\n" +
                "                           LEFT JOIN tSalesReturn1 sr1 \n" +
                "                               ON sr1.COMPANY_CODE = s1.COMPANY_CODE \n" +
                "                                   AND sr1.SALES_SYS_NO = s1.SALES_SYS_NO \n" +
                "                                   AND sr1.ITEM_CODE = s1.ITEM_CODE\n" +
                "                       WHERE s1.SALES_SYS_NO = " + sysNo + " \n" +
                "                               AND s1.COMPANY_CODE = '" + companycode + "' \n" +
                "                               AND SALES_DOC_NO <> '' \n" +
                "                       GROUP BY s1.ITEM_CODE, s1.QUANTITY" +
                "                                    ) AB " +
                "           ON AB.ITEM_CODE = s1.ITEM_CODE \n" +
                " WHERE s1.COMPANY_CODE = '" + companycode + "' AND SALES_SYS_NO = " + sysNo + " AND SALES_DOC_NO <> ''\n";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mItemEntity item=new mItemEntity();
                item.setITEM_CODE(cursor.getString(0));
                item.setITEM_NAME(cursor.getString(1));
                item.setITEM_DESCRIPTION(cursor.getString(2));
                item.setCLASS_CODE(cursor.getString(3));
                item.setCATEGORY_CODE(cursor.getString(4));
                item.setUOM_CODE(cursor.getString(5));
                item.setREMARKS(cursor.getString(6));
                item.setQTY_END(cursor.getDouble(7));
                item.setPRICE_AMOUNT(cursor.getDouble(8));
                item.setQTY_TO_RETURN(cursor.getDouble(9));
                item.setisReturn(true);
                listItem.add(item);
            }while(cursor.moveToNext());
        }
        return listItem;
    }

    public List<mItemEntity> SelectAllItemForGRPO( String companycode, String catCode ){
        List<mItemEntity> listItem=new ArrayList<mItemEntity>();
        if(!catCode.equals(""))
            catCode = " WHERE mi0.CATEGORY_CODE ='"+catCode+"'" ;
        String query = "SELECT \n" +
                "mi0.ITEM_CODE\n" +
                ",mi0.ITEM_NAME\n" +
                ",mi0.ITEM_DESCRIPTION\n" +
                ",mi0.CLASS_CODE\n" +
                ",mi0.CATEGORY_CODE\n" +
                ",mi0.UOM_CODE\n" +
                ",mi0.REMARK\n" +
                ",lcs.QTY_END\n" +
                ",ifnull(mi1.LAST_PURCHASE_PRICE,0) \n" +
                " FROM mItem0 mi0\n" +
                " LEFT OUTER JOIN mItem1 mi1 ON  mi1.ITEM_CODE = mi0.ITEM_CODE AND mi1.COMPANY_CODE ='" + companycode +"' \n" +
                " LEFT OUTER JOIN mLocationStock lcs ON  lcs.ITEM_CODE = mi0.ITEM_CODE AND lcs.COMPANY_CODE ='" + companycode +"'\n";
        query = query + catCode;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mItemEntity item=new mItemEntity();
                item.setITEM_CODE(cursor.getString(0));
                item.setITEM_NAME(cursor.getString(1));
                item.setITEM_DESCRIPTION(cursor.getString(2));
                item.setCLASS_CODE(cursor.getString(3));
                item.setCATEGORY_CODE(cursor.getString(4));
                item.setUOM_CODE(cursor.getString(5));
                item.setREMARKS(cursor.getString(6));
                item.setQTY_END(cursor.getDouble(7));
                item.setPRICE_AMOUNT(cursor.getDouble(8));
                listItem.add(item);
            }while(cursor.moveToNext());
        }
        return listItem;
    }

    public List<mItemEntity> SelectItemByCategory(String companycode ,String catCode){
        List<mItemEntity> listItem=new ArrayList<mItemEntity>();

        String Filter = "";

        String SubsitemCode=catCode.substring(0,2);

        if (SubsitemCode.equals("JS")){
            Filter =  "WHERE mi0.CATEGORY_CODE = '"+catCode+"'";
        } else{
            Filter =  "WHERE mi0.CATEGORY_CODE ='"+catCode+"' AND lcs.QTY_END <> 0 ";
        }

        String query= " SELECT \n" +
                "mi0.ITEM_CODE\n" +
                ",mi0.ITEM_NAME\n" +
                ",mi0.ITEM_DESCRIPTION\n" +
                ",mi0.CLASS_CODE\n" +
                ",mi0.CATEGORY_CODE\n" +
                ",mi0.UOM_CODE\n" +
                ",mi0.REMARK\n" +
                ",lcs.QTY_END\n" +
                ", (SELECT PRICE_AMOUNT \n" +
                "       FROM mPriceList \n" +
                "       WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE \n" +
                "       AND mPriceList.RECORD_STATUS = 'A' \n" +
                "       AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0) \n" +
                "       AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                "   ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1) PRICE_AMOUNT \n" +
                " FROM mItem0 mi0 \n" +
                " LEFT OUTER JOIN mLocationStock lcs ON  lcs.ITEM_CODE = mi0.ITEM_CODE AND  lcs.COMPANY_CODE = '"+ companycode +"'\n"+
                " " + Filter +  " \n" +
                "  and IFNULL((SELECT PRICE_AMOUNT \n" +
                "                    FROM mPriceList \n" +
                "                    WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE \n" +
                "                    AND mPriceList.RECORD_STATUS = 'A'  \n" +
                "                    AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "              ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) > 0 ";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mItemEntity item=new mItemEntity();
                item.setITEM_CODE(cursor.getString(0));
                item.setITEM_NAME(cursor.getString(1));
                item.setITEM_DESCRIPTION(cursor.getString(2));
                item.setCLASS_CODE(cursor.getString(3));
                item.setCATEGORY_CODE(cursor.getString(4));
                item.setUOM_CODE(cursor.getString(5));
                item.setREMARKS(cursor.getString(6));
                item.setQTY_END(cursor.getDouble(7));
                item.setPRICE_AMOUNT(cursor.getDouble(8));
                listItem.add(item);
            }while(cursor.moveToNext());
        }
        return listItem;
    }

    public List<mItemEntity> SelectItemMasterByCategory(String companycode ,String catCode){
        List<mItemEntity> listItem=new ArrayList<mItemEntity>();

        String Filter = "";

        Filter =  "WHERE mi0.CATEGORY_CODE ='"+catCode+"' ";

        String query= " SELECT \n" +
                "mi0.ITEM_CODE\n" +
                ",mi0.ITEM_NAME\n" +
                ",mi0.ITEM_DESCRIPTION\n" +
                ",mi0.CLASS_CODE\n" +
                ",mi0.CATEGORY_CODE\n" +
                ",mi0.UOM_CODE\n" +
                ",mi0.REMARK\n" +
                ",lcs.QTY_END\n" +
                ", (SELECT PRICE_AMOUNT \n" +
                "       FROM mPriceList \n" +
                "       WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE \n" +
                "       AND mPriceList.RECORD_STATUS = 'A' \n" +
                "       AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0) \n" +
                "       AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                "   ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1) PRICE_AMOUNT \n" +
                " FROM mItem0 mi0 \n" +
                " LEFT OUTER JOIN mLocationStock lcs ON  lcs.ITEM_CODE = mi0.ITEM_CODE AND  lcs.COMPANY_CODE = '"+ companycode +"'\n"+
                " " + Filter +  " \n" ;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mItemEntity item=new mItemEntity();
                item.setITEM_CODE(cursor.getString(0));
                item.setITEM_NAME(cursor.getString(1));
                item.setITEM_DESCRIPTION(cursor.getString(2));
                item.setCLASS_CODE(cursor.getString(3));
                item.setCATEGORY_CODE(cursor.getString(4));
                item.setUOM_CODE(cursor.getString(5));
                item.setREMARKS(cursor.getString(6));
                item.setQTY_END(cursor.getDouble(7));
                item.setPRICE_AMOUNT(cursor.getDouble(8));
                listItem.add(item);
            }while(cursor.moveToNext());
        }
        return listItem;
    }

    public List<mItemEntity> SelectItemByCategoryAdjustment(String companycode ,String catCode){
        List<mItemEntity> listItem=new ArrayList<mItemEntity>();
        String Union ="";
        if (catCode.equals("SV")){
            Union = " UNION " +
                    " SELECT mi0.ITEM_CODE \n" +
                    " ,mi0.ITEM_NAME \n" +
                    " ,mi0.ITEM_DESCRIPTION \n" +
                    " ,mi0.CLASS_CODE \n" +
                    " ,mi0.CATEGORY_CODE \n" +
                    " ,mi0.UOM_CODE \n" +
                    " ,mi0.REMARK \n" +
                    " ,IFNULL(lcs.QTY_END,0) \n" +
                    " ,0 \n " +
                    " FROM  mItem0 mi0 " +
                    " LEFT JOIN mLocationStock lcs ON \n " +
                    " lcs.ITEM_CODE = mi0.ITEM_CODE \n " +
                    " WHERE CATEGORY_CODE = 'SV' \n " +
                    " AND mi0.RECORD_STATUS = 'A' \n " ;
        }

        String query= " SELECT \n" +
                "mi0.ITEM_CODE\n" +
                ",mi0.ITEM_NAME\n" +
                ",mi0.ITEM_DESCRIPTION\n" +
                ",mi0.CLASS_CODE\n" +
                ",mi0.CATEGORY_CODE\n" +
                ",mi0.UOM_CODE\n" +
                ",mi0.REMARK\n" +
                ",lcs.QTY_END\n" +
                ", (SELECT PRICE_AMOUNT \n" +
                "       FROM mPriceList \n" +
                "       WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE \n" +
                "       AND mPriceList.RECORD_STATUS = 'A' \n" +
                "       AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0) \n" +
                "       AND strftime('%Y-%m-%d', EFF_DATE) <= date('now') \n" +
                "   ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1) PRICE_AMOUNT \n" +
                " FROM mItem0 mi0 \n" +
                " LEFT OUTER JOIN mLocationStock lcs ON  lcs.ITEM_CODE = mi0.ITEM_CODE AND  lcs.COMPANY_CODE = '"+ companycode +"'\n"+
                " WHERE mi0.CATEGORY_CODE ='"+catCode+"' \n" +
                "  and IFNULL((SELECT PRICE_AMOUNT \n" +
                "                    FROM mPriceList \n" +
                "                    WHERE mPriceList.ITEM_CODE = mi0.ITEM_CODE \n" +
                "                    AND mPriceList.RECORD_STATUS = 'A'  \n" +
                "                    AND ( mPriceList.COMPANY_CODE = '" + companycode + "' OR mPriceList.COMPANY_CODE = 0)  \n" +
                "              ORDER BY mPriceList.COMPANY_CODE DESC , EFF_DATE DESC LIMIT 1),0) > 0 \n " +
                " " + Union +  " \n" +
                " ORDER BY QTY_END DESC, mi0.CATEGORY_CODE ASC, mi0.ITEM_CODE ASC " ;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mItemEntity item=new mItemEntity();
                item.setITEM_CODE(cursor.getString(0));
                item.setITEM_NAME(cursor.getString(1));
                item.setITEM_DESCRIPTION(cursor.getString(2));
                item.setCLASS_CODE(cursor.getString(3));
                item.setCATEGORY_CODE(cursor.getString(4));
                item.setUOM_CODE(cursor.getString(5));
                item.setREMARKS(cursor.getString(6));
                item.setQTY_END(cursor.getDouble(7));
                item.setPRICE_AMOUNT(cursor.getDouble(8));
                listItem.add(item);
            }while(cursor.moveToNext());
        }
        return listItem;
    }

    public List<mItemEntity> SelectReturnItemByCategory(String companycode ,String catCode, Integer sysNo){
        List<mItemEntity> listItem=new ArrayList<mItemEntity>();

      /*  query= " SELECT \n" +
                "mi0.ITEM_CODE\n" +
                ",mi0.ITEM_NAME\n" +
                ",mi0.ITEM_DESCRIPTION\n" +
                ",mi0.CLASS_CODE\n" +
                ",mi0.CATEGORY_CODE\n" +
                ",mi0.UOM_CODE\n" +
                ",mi0.REMARK\n" +
                ",S1.QUANTITY\n" +
                ", (SELECT PRICE_AMOUNT FROM mPriceList where mPriceList.ITEM_CODE=mi0.ITEM_CODE AND ( COMPANY_CODE = 0 OR COMPANY_CODE=" + String.valueOf(companycode) +" ) ORDER BY  COMPANY_CODE DESC LIMIT 1) PRICE_AMOUNT\n" +
                " FROM tsales1 s1\n" +
                " LEFT OUTER JOIN mLocationStock lcs ON  lcs.ITEM_CODE = s1.ITEM_CODE" +
                " LEFT OUTER JOIN mItem0 mi0 ON s1.item_code = mi0.item_code " +
                "       WHERE sales_sys_no = " + sysNo + " AND  lcs.COMPANY_CODE = "+ String.valueOf(companycode) +"\n"+
                " and mi0.CATEGORY_CODE ='"+catCode+"'";*/

        String query = " SELECT s1.ITEM_CODE\n" +
                " , mi0.ITEM_NAME\n" +
                " , mi0.ITEM_DESCRIPTION\n" +
                " , mi0.CLASS_CODE\n" +
                " , mi0.CATEGORY_CODE\n" +
                " , mi0.UOM_CODE\n" +
                " , mi0.REMARK\n" +
                " , s1.QUANTITY\n" +
                " , net_price PRICE_AMOUNT \n" +
                " , ab.QTY_TO_RETURN QTY_TO_RETURN\n" +
                "   FROM tSales1 s1\n" +
                " LEFT JOIN mitem0 mi0 ON s1.ITEM_CODE = mi0.ITEM_CODE\n" +
                " LEFT JOIN (SELECT s1.ITEM_CODE, IFNULL(s1.QUANTITY,0) - IFNULL (SUM(sr1.QUANTITY_RETURN),0) QTY_TO_RETURN\n" +
                "                       FROM tSales1 s1\n" +
                "                           LEFT JOIN tSalesReturn1 sr1 \n" +
                "                               ON sr1.COMPANY_CODE = s1.COMPANY_CODE \n" +
                "                                   AND sr1.SALES_SYS_NO = s1.SALES_SYS_NO \n" +
                "                                   AND sr1.ITEM_CODE = s1.ITEM_CODE\n" +
                "                       WHERE s1.SALES_SYS_NO = " + sysNo + " \n" +
                "                               AND s1.COMPANY_CODE = '" + companycode + "' \n" +
                "                               AND SALES_DOC_NO <> '' \n" +
                "                       GROUP BY s1.ITEM_CODE, s1.QUANTITY" +
                "                                    ) AB " +
                "           ON AB.ITEM_CODE = s1.ITEM_CODE \n" +
                " WHERE s1.COMPANY_CODE = '" + companycode + "' AND SALES_SYS_NO = " + sysNo + " AND SALES_DOC_NO <> ''\n" +
                " and mi0.CATEGORY_CODE ='"+catCode+"'";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                mItemEntity item=new mItemEntity();
                item.setITEM_CODE(cursor.getString(0));
                item.setITEM_NAME(cursor.getString(1));
                item.setITEM_DESCRIPTION(cursor.getString(2));
                item.setCLASS_CODE(cursor.getString(3));
                item.setCATEGORY_CODE(cursor.getString(4));
                item.setUOM_CODE(cursor.getString(5));
                item.setREMARKS(cursor.getString(6));
                item.setQTY_END(cursor.getDouble(7));
                item.setPRICE_AMOUNT(cursor.getDouble(8));
                item.setQTY_TO_RETURN(cursor.getDouble(9));
                item.setisReturn(true);
                listItem.add(item);
            }while(cursor.moveToNext());
        }
        return listItem;
    }

}
