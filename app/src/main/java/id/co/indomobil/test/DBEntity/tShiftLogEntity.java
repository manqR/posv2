package id.co.indomobil.test.DBEntity;


import java.sql.Timestamp;

public class tShiftLogEntity {
    private String COMPANY_CODE;
    private String SUPERVISOR_ID;
    private String SHIFT_NO;
    private Timestamp SHIFT_DATE;
    private String SHIFT_STATUS;
    private String SHIFT_STATUS_DESC;
    private String CASHIER_ID;
    private String CASHIER_NAME;
    private byte[] CASHIER_IMG;
    private Double BEGINNING_AMOUNT;
    private Double TOTAL_AMOUNT;
    private Double TOTAL_SALES;
    private Double TOTAL_RETUR;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;

    public tShiftLogEntity(String shift_no, String cashier_name, byte cashier_img[], Timestamp shift_date, Double total_amount,Timestamp change_datetime , String SHIFT_STATUS_DESC) {
        this.SHIFT_NO = shift_no;
        this.CASHIER_NAME = cashier_name;
        this.CASHIER_IMG = cashier_img;
        this.SHIFT_DATE = shift_date;
        this.TOTAL_AMOUNT = total_amount;
        this.SHIFT_STATUS_DESC = SHIFT_STATUS_DESC;
        this.CHANGE_DATETIME = change_datetime;
    }

    public tShiftLogEntity() {

    }

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public String getSUPERVISOR_ID(){
        return SUPERVISOR_ID;
    }
    public void setSUPERVISOR_ID(String SUPERVISOR_ID){
        this.SUPERVISOR_ID = SUPERVISOR_ID;
    }

    public String getSHIFT_NO(){
        return SHIFT_NO;
    }
    public void setSHIFT_NO(String SHIFT_NO){
        this.SHIFT_NO = SHIFT_NO;
    }

    public Timestamp getSHIFT_DATE(){
        return SHIFT_DATE;
    }
    public void setSHIFT_DATE(Timestamp SHIFT_DATE){
        this.SHIFT_DATE = SHIFT_DATE;
    }

    public String getSHIFT_STATUS(){
        return SHIFT_STATUS;
    }
    public void setSHIFT_STATUS(String SHIFT_STATUS){
        this.SHIFT_STATUS = SHIFT_STATUS;
    }

    public String getSHIFT_STATUS_DESC(){
        return SHIFT_STATUS_DESC;
    }
    public void setSHIFT_STATUS_DESC(String SHIFT_STATUS_DESC){
        this.SHIFT_STATUS_DESC = SHIFT_STATUS_DESC;
    }

    public String getCASHIER_ID(){
        return CASHIER_ID;
    }
    public void setCASHIER_ID(String CASHIER_ID){
        this.CASHIER_ID = CASHIER_ID;
    }

    public String getCASHIER_NAME(){
        return CASHIER_NAME;
    }
    public void setCASHIER_NAME(String CASHIER_NAME){
        this.CASHIER_NAME = CASHIER_NAME;
    }

    public byte[] getCASHIER_IMG() {return  CASHIER_IMG;}
    public void  setCASHIER_IMG(byte[] CASHIER_IMG){this.CASHIER_IMG = CASHIER_IMG;}

    public Double getBEGINNING_AMOUNT(){
        return BEGINNING_AMOUNT;
    }
    public void setBEGINNING_AMOUNT(Double BEGINNING_AMOUNT){
        this.BEGINNING_AMOUNT = BEGINNING_AMOUNT;
    }

    public Double getTOTAL_AMOUNT(){
        return TOTAL_AMOUNT;
    }
    public void setTOTAL_AMOUNT(Double TOTAL_AMOUNT){
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
    }

    public Double getTOTAL_SALES(){
        return TOTAL_SALES;
    }
    public void setTOTAL_SALES(Double TOTAL_SALES){
        this.TOTAL_SALES = TOTAL_SALES;
    }

    public Double getTOTAL_RETUR(){
        return TOTAL_RETUR;
    }
    public void setTOTAL_RETUR(Double TOTAL_RETUR){
        this.TOTAL_RETUR = TOTAL_RETUR;
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

