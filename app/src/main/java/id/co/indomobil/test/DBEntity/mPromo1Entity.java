package id.co.indomobil.test.DBEntity;


import java.sql.Timestamp;

public class mPromo1Entity {
    private String RECORD_STATUS;
    private String COMPANY_CODE;
    private String PROMO_NO;
    private String PROMO_NAME;
    private String ITEM_CODE;
    private String DISC_METHOD;
    private int DISC_PERCENT;
    private Double DISC_AMOUNT;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;

    private Timestamp PERIOD_FROM;
    private Timestamp PERIOD_TO;

    public mPromo1Entity() {

    }

    public String getRECORD_STATUS(){
        return RECORD_STATUS;
    }
    public void setRECORD_STATUS(String RECORD_STATUS){
        this.RECORD_STATUS = RECORD_STATUS;
    }

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public String getPROMO_NO(){return PROMO_NO; }
    public void setPROMO_NO(String PROMO_NO){ this.PROMO_NO = PROMO_NO; }

    public String getPROMO_NAME(){return PROMO_NAME; }
    public void setPROMO_NAME(String PROMO_NAME){ this.PROMO_NAME = PROMO_NAME; }

    public String getITEM_CODE(){
        return ITEM_CODE;
    }
    public void setITEM_CODE(String ITEM_CODE){
        this.ITEM_CODE = ITEM_CODE;
    }

    public String getDISC_METHOD(){
        return DISC_METHOD;
    }
    public void setDISC_METHOD(String DISC_METHOD){
        this.DISC_METHOD = DISC_METHOD;
    }

    public int getDISC_PERCENT(){
        return DISC_PERCENT;
    }
    public void setDISC_PERCENT(int DISC_PERCENT){
        this.DISC_PERCENT = DISC_PERCENT;
    }

    public Double getDISC_AMOUNT(){
        return DISC_AMOUNT;
    }
    public void setDISC_AMOUNT(Double DISC_AMOUNT){
        this.DISC_AMOUNT = DISC_AMOUNT;
    }

    public String getCREATION_USER_ID(){
        return CREATION_USER_ID;
    }
    public void setCREATION_USER_ID(String CREATION_USER_ID){
        this.CREATION_USER_ID = CREATION_USER_ID;
    }

    public Timestamp getCREATION_DATETIME(){
        return CREATION_DATETIME;
    }
    public void setCREATION_DATETIME(Timestamp CREATION_DATETIME){
        this.CREATION_DATETIME = CREATION_DATETIME;
    }

    public String getCHANGE_USER_ID(){
        return CHANGE_USER_ID;
    }
    public void setCHANGE_USER_ID(String CHANGE_USER_ID){
        this.CHANGE_USER_ID = CHANGE_USER_ID;
    }

    public Timestamp getCHANGE_DATETIME(){
        return CHANGE_DATETIME;
    }
    public void setCHANGE_DATETIME(Timestamp CHANGE_DATETIME){
        this.CHANGE_DATETIME = CHANGE_DATETIME;
    }

    public Timestamp getPERIOD_FROM(){
        return PERIOD_FROM;
    }
    public void setPERIOD_FROM(Timestamp PERIOD_FROM){
        this.PERIOD_FROM = PERIOD_FROM;
    }

    public Timestamp getPERIOD_TO(){
        return PERIOD_TO;
    }
    public void setPERIOD_TO(Timestamp PERIOD_TO){
        this.PERIOD_TO = PERIOD_TO;
    }

}
