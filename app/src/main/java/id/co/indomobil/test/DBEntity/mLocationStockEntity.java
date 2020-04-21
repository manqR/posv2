package id.co.indomobil.test.DBEntity;


import java.sql.Timestamp;

public class mLocationStockEntity {
    private String COMPANY_CODE;
    private String ITEM_CODE;
    private String ITEM_NAME;
    private double QTY_BEGIN;
    private double QTY_SALES;
    private double QTY_SALES_RETURN;
    private double QTY_PURCHASE;
    private double QTY_ADJUSTMENT;
    private double QTY_ALLOCATED;
    private double QTY_END;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;
    private String CATEGORY_CODE;

    public mLocationStockEntity(String item_code, String company_code) {
        this.ITEM_CODE = item_code;
        this.COMPANY_CODE = company_code;
    }

    public mLocationStockEntity() {

    }

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public String getITEM_CODE(){
        return ITEM_CODE;
    }
    public void setITEM_CODE(String ITEM_CODE){
        this.ITEM_CODE = ITEM_CODE;
    }

    public String getITEM_NAME(){
        return ITEM_NAME;
    }
    public void setITEM_NAME(String ITEM_NAME){
        this.ITEM_NAME = ITEM_NAME;
    }

    public double getQTY_BEGIN(){
        return QTY_BEGIN;
    }
    public void setQTY_BEGIN(double QTY_BEGIN){
        this.QTY_BEGIN = QTY_BEGIN;
    }

    public double getQTY_SALES(){
        return QTY_SALES;
    }
    public void setQTY_SALES(double QTY_SALES){
        this.QTY_SALES = QTY_SALES;
    }

    public double getQTY_SALES_RETURN(){
        return QTY_SALES_RETURN;
    }
    public void setQTY_SALES_RETURN(double QTY_SALES_RETURN){
        this.QTY_SALES_RETURN = QTY_SALES_RETURN;
    }

    public double getQTY_PURCHASE(){
        return QTY_PURCHASE;
    }
    public void setQTY_PURCHASE(double QTY_PURCHASE){
        this.QTY_PURCHASE = QTY_PURCHASE;
    }

    public double getQTY_ADJUSTMENT(){
        return QTY_ADJUSTMENT;
    }
    public void setQTY_ADJUSTMENT(double QTY_ADJUSTMENT){
        this.QTY_ADJUSTMENT = QTY_ADJUSTMENT;
    }

    public double getQTY_ALLOCATED(){
        return QTY_ALLOCATED;
    }
    public void setQTY_ALLOCATED(double QTY_ALLOCATED){
        this.QTY_ALLOCATED = QTY_ALLOCATED;
    }

    public double getQTY_END(){
        return QTY_END;
    }
    public void setQTY_END(double QTY_END){
        this.QTY_END = QTY_END;
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

    public String getCATEGORY_CODE(){
        return CATEGORY_CODE;
    }
    public void setCATEGORY_CODE(String CATEGORY_CODE){
        this.CATEGORY_CODE = CATEGORY_CODE;
    }
}
