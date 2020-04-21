package id.co.indomobil.test.DBEntity;


import java.math.BigDecimal;
import java.sql.Timestamp;

public class mPriceListEntity {
    private String RECORD_STATUS;
    private String ITEM_CODE;
    private String COMPANY_CODE;
    private Timestamp EFF_DATE;
    private BigDecimal PRICE_AMOUNT;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;

    public mPriceListEntity(String item_code, String company_code) {
        this.ITEM_CODE = item_code;
        this.COMPANY_CODE = company_code;
    }

    public mPriceListEntity() {

    }

    public String getRECORD_STATUS(){
        return RECORD_STATUS;
    }
    public void setRECORD_STATUS(String RECORD_STATUS){
        this.RECORD_STATUS = RECORD_STATUS;
    }

    public String getITEM_CODE(){
        return ITEM_CODE;
    }
    public void setITEM_CODE(String ITEM_CODE){
        this.ITEM_CODE = ITEM_CODE;
    }

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public Timestamp getEFF_DATE(){
        return EFF_DATE;
    }
    public void setEFF_DATE(Timestamp EFF_DATE){
        this.EFF_DATE = EFF_DATE;
    }

    public BigDecimal getPRICE_AMOUNT(){
        return PRICE_AMOUNT;
    }
    public void setPRICE_AMOUNT(BigDecimal PRICE_AMOUNT){
        this.PRICE_AMOUNT = PRICE_AMOUNT;
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
}
