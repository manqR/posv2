package id.co.indomobil.test.DBEntity;


import java.sql.Timestamp;

public class mMixMatchEntity {
    private String DISCOUNT_ID;
    private String DISCOUNT_NAME;
    private String ITEM_CODE;
    private String ITEM_NAME;
    private String UNIT;
    private Double NUMBER_OF_PRODUCT_NEEDED;
    private String DISCOUNT_TYPE;
    private Double DEAL_PRICE_DISC_PERCENTAGE;
    private String LINE_GROUP;
    private String PRICE_GROUP;
    private Timestamp START_DATE;
    private Timestamp END_DATE;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;
    private String CATEGORY_CODE;

    public mMixMatchEntity() {

    }

    public String getDISCOUNT_ID() { return DISCOUNT_ID; }
    public void setDISCOUNT_ID(String DISCOUNT_ID){
        this.DISCOUNT_ID = DISCOUNT_ID;
    }

    public String getDISCOUNT_NAME() { return DISCOUNT_NAME; }
    public void setDISCOUNT_NAME(String DISCOUNT_NAME){
        this.DISCOUNT_NAME = DISCOUNT_NAME;
    }

    public String getITEM_CODE() { return ITEM_CODE; }
    public void setITEM_CODE(String ITEM_CODE){
        this.ITEM_CODE = ITEM_CODE;
    }

    public String getITEM_NAME() { return ITEM_NAME; }
    public void setITEM_NAME(String ITEM_NAME){
        this.ITEM_NAME = ITEM_NAME;
    }

    public String getUNIT() { return UNIT; }
    public void setUNIT(String UNIT){
        this.UNIT = UNIT;
    }

    public Double getNUMBER_OF_PRODUCT_NEEDED(){
        return NUMBER_OF_PRODUCT_NEEDED;
    }
    public void setNUMBER_OF_PRODUCT_NEEDED(Double NUMBER_OF_PRODUCT_NEEDED){
        this.NUMBER_OF_PRODUCT_NEEDED = NUMBER_OF_PRODUCT_NEEDED;
    }

    public String getLINE_GROUP() { return LINE_GROUP; }
    public void setLINE_GROUP(String LINE_GROUP){
        this.LINE_GROUP = LINE_GROUP;
    }

    public Double getDEAL_PRICE_DISC_PERCENTAGE(){
        return DEAL_PRICE_DISC_PERCENTAGE;
    }
    public void setDEAL_PRICE_DISC_PERCENTAGE(Double DEAL_PRICE_DISC_PERCENTAGE){
        this.DEAL_PRICE_DISC_PERCENTAGE = DEAL_PRICE_DISC_PERCENTAGE;
    }

    public String getDISCOUNT_TYPE() { return DISCOUNT_TYPE; }
    public void setDISCOUNT_TYPE(String DISCOUNT_TYPE){
        this.DISCOUNT_TYPE = DISCOUNT_TYPE;
    }

    public String getPRICE_GROUP() { return PRICE_GROUP; }
    public void setPRICE_GROUP(String PRICE_GROUP){
        this.PRICE_GROUP = PRICE_GROUP;
    }

    public Timestamp getSTART_DATE(){
        return START_DATE;
    }
    public void setSTART_DATE(Timestamp START_DATE){
        this.START_DATE = START_DATE;
    }

    public Timestamp getEND_DATE(){
        return END_DATE;
    }
    public void setEND_DATE(Timestamp END_DATE){
        this.END_DATE = END_DATE;
    }

    public String getCREATION_USER_ID() { return CREATION_USER_ID; }
    public void setCREATION_USER_ID(String CREATION_USER_ID){
        this.CREATION_USER_ID = CREATION_USER_ID;
    }

    public Timestamp getCREATION_DATETIME(){
        return CREATION_DATETIME;
    }
    public void setCREATION_DATETIME(Timestamp CREATION_DATETIME){
        this.CREATION_DATETIME = CREATION_DATETIME;
    }

    public String getCHANGE_USER_ID() { return CHANGE_USER_ID; }
    public void setCHANGE_USER_ID(String CHANGE_USER_ID){
        this.CHANGE_USER_ID = CHANGE_USER_ID;
    }

    public Timestamp getCHANGE_DATETIME(){
        return CHANGE_DATETIME;
    }
    public void setCHANGE_DATETIME(Timestamp CHANGE_DATETIME){
        this.CHANGE_DATETIME = CHANGE_DATETIME;
    }

    public String getCATEGORY_CODE() { return CATEGORY_CODE; }
    public void setCATEGORY_CODE(String CATEGORY_CODE){
        this.CATEGORY_CODE = CATEGORY_CODE;
    }
}
