package id.co.indomobil.test.DBEntity;


import java.sql.Timestamp;

public class tSalesEntity {
    private String COMPANY_CODE;
    private int SALES_SYS_NO;
    private String SALES_DOC_NO;
    private String SALES_STATUS;
    private Timestamp SALES_DATE;
    private String CUSTOMER_CODE;
    private String CUSTOMER_NAME;
    private String MOBILE_PHONE_NO;
    private String TNKB;
    private Double TOTAL;
    private Double TOTAL_DISC;
    private double GRAND_TOTAL;
    private Double DPP;
    private Double VAT;
    private String SHIFT_NO;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;
    private String STAFF_NAME;
    private double PAYMENT_AMOUNT;
    private double CHANGE_AMOUNT;

/*    public tSalesEntity(int company_code , String sales_doc_no) {
        this.COMPANY_CODE = company_code;
        this.SALES_DOC_NO = sales_doc_no;
    }*/

    public tSalesEntity(String sales_doc_no, String customer_name, double grand_total, Timestamp sales_date) {
        this.SALES_DOC_NO = sales_doc_no;
        this.CUSTOMER_NAME = customer_name;
        this.GRAND_TOTAL = grand_total;
        this.SALES_DATE = sales_date;
    }

    public tSalesEntity() {

    }

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public int getSALES_SYS_NO(){
        return SALES_SYS_NO;
    }
    public void setSALES_SYS_NO(int SALES_SYS_NO){
        this.SALES_SYS_NO = SALES_SYS_NO;
    }

    public String getSALES_DOC_NO(){
        return SALES_DOC_NO;
    }
    public void setSALES_DOC_NO(String SALES_DOC_NO){
        this.SALES_DOC_NO = SALES_DOC_NO;
    }

    public String getSALES_STATUS(){
        return SALES_STATUS;
    }
    public void setSALES_STATUS(String SALES_STATUS){
        this.SALES_STATUS = SALES_STATUS;
    }

    public Timestamp getSALES_DATE(){
        return SALES_DATE;
    }
    public void setSALES_DATE(Timestamp SALES_DATE){
        this.SALES_DATE = SALES_DATE;
    }

    public String getCUSTOMER_CODE(){
        return CUSTOMER_CODE;
    }
    public void setCUSTOMER_CODE(String CUSTOMER_CODE){
        this.CUSTOMER_CODE = CUSTOMER_CODE;
    }

    public String getCUSTOMER_NAME(){
        return CUSTOMER_NAME;
    }
    public void setCUSTOMER_NAME(String CUSTOMER_NAME){
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    public String getMOBILE_PHONE_NO(){
        return MOBILE_PHONE_NO;
    }
    public void setMOBILE_PHONE_NO(String MOBILE_PHONE_NO){
        this.MOBILE_PHONE_NO = MOBILE_PHONE_NO;
    }

    public String getTNKB(){
        return TNKB;
    }
    public void setTNKB(String TNKB){
        this.TNKB = TNKB;
    }

    public Double getTOTAL(){
        return TOTAL;
    }
    public void setTOTAL(Double TOTAL){
        this.TOTAL = TOTAL;
    }

    public Double getTOTAL_DISC(){
        return TOTAL_DISC;
    }
    public void setTOTAL_DISC(Double TOTAL_DISC){
        this.TOTAL_DISC = TOTAL_DISC;
    }

    public Double getGRAND_TOTAL(){
        return GRAND_TOTAL;
    }
    public void setGRAND_TOTAL(Double GRAND_TOTAL){
        this.GRAND_TOTAL = GRAND_TOTAL;
    }

    public Double getDPP(){
        return DPP;
    }
    public void setDPP(Double DPP){
        this.DPP = DPP;
    }

    public Double getVAT(){
        return VAT;
    }
    public void setVAT(Double VAT){
        this.VAT = VAT;
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

    public String getSTAFF_NAME(){
        return STAFF_NAME;
    }
    public void setSTAFF_NAME(String STAFF_NAME){
        this.STAFF_NAME = STAFF_NAME;
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