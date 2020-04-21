package id.co.indomobil.test.DBEntity;


import java.sql.Timestamp;

public class mItemEntity {
    private String RECORD_STATUS;
    private String ITEM_CODE;
    private String ITEM_NAME;
    private String ITEM_DESCRIPTION;
    private String CLASS_CODE;
    private String CATEGORY_CODE;
    private String UOM_CODE;
    private String REMARKS;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;
    private Boolean isReturn = false;

    private Double QTY_END;
    private Double QTY_TO_RETURN;
    private double PRICE_AMOUNT;

    public mItemEntity(String item_code, String item_name, Double qty_end , Double qty_to_return, double price_amount, String category_code) {
        this.ITEM_CODE = item_code;
        this.ITEM_NAME = item_name;
        this.QTY_END= qty_end;
        this.QTY_TO_RETURN= qty_to_return;
        this.PRICE_AMOUNT = price_amount;
        this.CATEGORY_CODE = category_code;
    }

    public mItemEntity() {

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

    public String getITEM_NAME(){
        return ITEM_NAME;
    }
    public void setITEM_NAME(String ITEM_NAME){
        this.ITEM_NAME = ITEM_NAME;
    }

    public String getITEM_DESCRIPTION(){
        return ITEM_DESCRIPTION;
    }
    public void setITEM_DESCRIPTION(String ITEM_DESCRIPTION){
        this.ITEM_DESCRIPTION = ITEM_DESCRIPTION;
    }

    public String getCLASS_CODE(){
        return CLASS_CODE;
    }
    public void setCLASS_CODE(String CLASS_CODE){
        this.CLASS_CODE = CLASS_CODE;
    }

    public String getCATEGORY_CODE(){
        return CATEGORY_CODE;
    }
    public void setCATEGORY_CODE(String CATEGORY_CODE){
        this.CATEGORY_CODE = CATEGORY_CODE;
    }

    public String getUOM_CODE(){
        return UOM_CODE;
    }
    public void setUOM_CODE(String UOM_CODE){
        this.UOM_CODE = UOM_CODE;
    }

    public String getREMARKS(){
        return REMARKS;
    }
    public void setREMARKS(String REMARKS){
        this.REMARKS = REMARKS;
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

    public Double getQTY_END(){
        return QTY_END;
    }
    public void setQTY_END(Double QTY_END){
        this.QTY_END = QTY_END;
    }

    public Double getQTY_TO_RETURN(){
        return QTY_TO_RETURN;
    }
    public void setQTY_TO_RETURN(Double QTY_TO_RETURN){
        this.QTY_TO_RETURN = QTY_TO_RETURN;
    }

    public double getPRICE_AMOUNT(){
        return PRICE_AMOUNT;
    }
    public void setPRICE_AMOUNT(double PRICE_AMOUNT){
        this.PRICE_AMOUNT = PRICE_AMOUNT;
    }

    public boolean getisReturn(){
        return isReturn;
    }
    public void setisReturn(boolean isReturn){
        this.isReturn = isReturn;
    }
}
