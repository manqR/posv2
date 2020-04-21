package id.co.indomobil.test.DBEntity;


import java.sql.Timestamp;

public class tSales1Entity {
    private String COMPANY_CODE;
    private String SALES_DOC_NO;
    private int SALES_SYS_NO;
    private int SALES_LINE_NO;
    private String ITEM_CODE;
    private String ITEM_NAME;
    private Double PRICE_LIST;
    private Double SELLING_PRICE;
    private Double DISCOUNT_PERCENTAGE;
    private Double QUANTITY;
    private Double DISCOUNT_NOMINAL;
    private Double NET_PRICE;
    private Double TOTAL_NET_PRICE;
    private String UOM_CODE;
    private String CATEGORY_CODE;
    private String DRAWER_CODE;
    private Double COGS;
    private String SHIFT_NO;
    private String PROMO_NO;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;
    private Double PAYMENT_AMOUNT;
    private Double CHANGE_AMOUNT;

    public tSales1Entity(String itemcode , double quantity, double sellingprice  , double linetotal ) {
        this.ITEM_CODE = itemcode;
        this.QUANTITY = quantity;
        this.SELLING_PRICE = sellingprice;
        this.TOTAL_NET_PRICE = linetotal;
    }

    public tSales1Entity() {

    }

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public String getSALES_DOC_NO(){
        return SALES_DOC_NO;
    }
    public void setSALES_DOC_NO(String SALES_DOC_NO){
        this.SALES_DOC_NO = SALES_DOC_NO;
    }

    public int getSALES_SYS_NO(){
        return SALES_SYS_NO;
    }
    public void setSALES_SYS_NO(int SALES_SYS_NO){
        this.SALES_SYS_NO = SALES_SYS_NO;
    }

    public int getSALES_LINE_NO(){
        return SALES_LINE_NO;
    }
    public void setSALES_LINE_NO(int SALES_LINE_NO){
        this.SALES_LINE_NO = SALES_LINE_NO;
    }

    public String getITEM_NAME(){
        return ITEM_NAME;
    }
    public void setITEM_NAME(String ITEM_NAME){
        this.ITEM_NAME = ITEM_NAME;
    }

    public String getITEM_CODE(){
        return ITEM_CODE;
    }
    public void setITEM_CODE(String ITEM_CODE){
        this.ITEM_CODE = ITEM_CODE;
    }

    public Double getPRICE_LIST(){
        return PRICE_LIST;
    }
    public void setPRICE_LIST(Double PRICE_LIST){
        this.PRICE_LIST = PRICE_LIST;
    }

    public Double getSELLING_PRICE(){
        return SELLING_PRICE;
    }
    public void setSELLING_PRICE(Double SELLING_PRICE){
        this.SELLING_PRICE = SELLING_PRICE;
    }

    public Double getDISCOUNT_PERCENTAGE(){
        return DISCOUNT_PERCENTAGE;
    }
    public void setDISCOUNT_PERCENTAGE(Double DISCOUNT_PERCENTAGE){
        this.DISCOUNT_PERCENTAGE = DISCOUNT_PERCENTAGE;
    }

    public Double getQUANTITY(){
        return QUANTITY;
    }
    public void setQUANTITY(Double QUANTITY){
        this.QUANTITY = QUANTITY;
    }

    public Double getDISCOUNT_NOMINAL(){
        return DISCOUNT_NOMINAL;
    }
    public void setDISCOUNT_NOMINAL(Double DISCOUNT_NOMINAL){
        this.DISCOUNT_NOMINAL = DISCOUNT_NOMINAL;
    }

    public Double getNET_PRICE(){
        return NET_PRICE;
    }
    public void setNET_PRICE(Double NET_PRICE){
        this.NET_PRICE = NET_PRICE;
    }

    public Double getTOTAL_NET_PRICE(){
        return TOTAL_NET_PRICE;
    }
    public void setTOTAL_NET_PRICE(Double TOTAL_NET_PRICE){
        this.TOTAL_NET_PRICE = TOTAL_NET_PRICE;
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

    public String getDRAWER_CODE(){
        return DRAWER_CODE;
    }
    public void setDRAWER_CODE(String DRAWER_CODE){
        this.DRAWER_CODE = DRAWER_CODE;
    }

    public Double getCOGS(){
        return COGS;
    }
    public void setCOGS(Double COGS){
        this.COGS = COGS;
    }

    public String getSHIFT_NO(){
        return SHIFT_NO;
    }
    public void setSHIFT_NO(String SHIFT_NO){
        this.SHIFT_NO = SHIFT_NO;
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

    public String getPROMO_NO(){
        return PROMO_NO;
    }
    public void setPROMO_NO(String PROMO_NO){
        this.PROMO_NO = PROMO_NO;
    }

    public Double getPAYMENT_AMOUNT(){
        return PAYMENT_AMOUNT;
    }
    public void setPAYMENT_AMOUNT(Double PAYMENT_AMOUNT){
        this.PAYMENT_AMOUNT = PAYMENT_AMOUNT;
    }

    public Double getCHANGE_AMOUNT(){
        return CHANGE_AMOUNT;
    }
    public void setCHANGE_AMOUNT(Double CHANGE_AMOUNT){
        this.CHANGE_AMOUNT = CHANGE_AMOUNT;
    }

}
