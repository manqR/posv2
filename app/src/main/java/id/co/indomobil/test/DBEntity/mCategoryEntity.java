package id.co.indomobil.test.DBEntity;


import java.util.Date;

public class mCategoryEntity {

    // Labels Table Columns names
    public static final String COLUMN_CATEGORY_CODE = "CATEGORY_CODE";
    public static final String COLUMN_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String COLUMN_CREATION_USER_ID = "CREATION_USER_ID";
    public static final String COLUMN_CREATION_DATETIME = "CREATION_DATETIME";
    public static final String COLUMN_CHANGE_USER_ID = "CHANGE_USER_ID";
    public static final String COLUMN_CHANGE_DATETIME = "CHANGE_DATETIME";

    private String RECORD_STATUS;
    private String CATEGORY_CODE;
    private String CATEGORY_NAME;
    private String CREATION_USER_ID;
    private Date CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Date CHANGE_DATETIME;


    public mCategoryEntity(String category_name, String category_code) {
        this.CATEGORY_CODE = category_code;
        this.CATEGORY_NAME = category_name;
    }

    public mCategoryEntity() {

    }

    public String getRECORD_STATUS(){
        return RECORD_STATUS;
    }

    public void setRECORD_STATUS(String RECORD_STATUS){
        this.RECORD_STATUS = RECORD_STATUS;
    }

    public String getCATEGORY_CODE(){
        return CATEGORY_CODE;
    }
    public void setCATEGORY_CODE(String CATEGORY_CODE){
        this.CATEGORY_CODE = CATEGORY_CODE;
    }

    public String getCATEGORY_NAME(){
        return CATEGORY_NAME;
    }
    public void setCATEGORY_NAME(String CATEGORY_NAME){
        this.CATEGORY_NAME = CATEGORY_NAME;
    }

    public String getCREATION_USER_ID(){
        return CREATION_USER_ID;
    }
    public void setCREATION_USER_ID(String CREATION_USER_ID){this.CREATION_USER_ID = CREATION_USER_ID; }

    public Date getCREATION_DATETIME(){
        return CREATION_DATETIME;
    }
    public void setCREATION_DATETIME(Date CREATION_DATETIME){this.CREATION_DATETIME = CREATION_DATETIME; }

    public String getCHANGE_USER_ID(){
        return CHANGE_USER_ID;
    }
    public void setCHANGE_USER_ID(String CHANGE_USER_ID){
        this.CHANGE_USER_ID = CHANGE_USER_ID;
    }

    public Date getCHANGE_DATETIME(){
        return CHANGE_DATETIME;
    }
    public void setCHANGE_DATETIME(Date CHANGE_DATETIME){
        this.CHANGE_DATETIME = CHANGE_DATETIME;
    }

}
