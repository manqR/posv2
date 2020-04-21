package id.co.indomobil.test.DBEntity;

import java.sql.Timestamp;

public class tPO0Entity {
    private String COMPANY_CODE;
    private int PO_SYS_NO;
    private String PO_DOC_NO;
    private Timestamp PO_DATE;
    private String PO_STATUS;
    private String SUPPLIER_CODE;
    private String SUPPLIER_NAME;
    private String SUGGOR_SYS_NO;
    private double TOTAL;
    private double GRAND_TOTAL;
    private double DPP;
    private double VAT;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;

    public tPO0Entity(String po_doc_no, String supplier_code, int po_sys_no, Timestamp po_date, double total) {
        this.PO_DOC_NO = po_doc_no;
        this.SUPPLIER_CODE = supplier_code;
        this.PO_SYS_NO = po_sys_no;
        this.PO_DATE = po_date;
        this.TOTAL = total;
    }
    public tPO0Entity() {

    }

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public int getPO_SYS_NO(){
        return PO_SYS_NO;
    }
    public void setPO_SYS_NO(int PO_SYS_NO){
        this.PO_SYS_NO = PO_SYS_NO;
    }

    public String getPO_DOC_NO(){
        return PO_DOC_NO;
    }
    public void setPO_DOC_NO(String PO_DOC_NO){
        this.PO_DOC_NO = PO_DOC_NO;
    }

    public Timestamp getPO_DATE(){
        return PO_DATE;
    }
    public void setPO_DATE(Timestamp PO_DATE){
        this.PO_DATE = PO_DATE;
    }

    public String getPO_STATUS(){
        return PO_STATUS;
    }
    public void setPO_STATUS(String PO_STATUS){
        this.PO_STATUS = PO_STATUS;
    }

    public String getSUGGOR_SYS_NO(){
        return SUGGOR_SYS_NO;
    }
    public void setSUGGOR_SYS_NO(String SUGGOR_SYS_NO){
        this.SUGGOR_SYS_NO = SUGGOR_SYS_NO;
    }

    public String getSUPPLIER_CODE(){
        return SUPPLIER_CODE;
    }
    public void setSUPPLIER_CODE(String SUPPLIER_CODE){
        this.SUPPLIER_CODE = SUPPLIER_CODE;
    }

    public String getSUPPLIER_NAME(){
        return SUPPLIER_NAME;
    }
    public void setSUPPLIER_NAME(String SUPPLIER_NAME){
        this.SUPPLIER_NAME = SUPPLIER_NAME;
    }

    public double getTOTAL(){
        return TOTAL;
    }
    public void setTOTAL(double TOTAL){
        this.TOTAL = TOTAL;
    }

    public double getGRAND_TOTAL(){
        return GRAND_TOTAL;
    }
    public void setGRAND_TOTAL(double GRAND_TOTAL){
        this.GRAND_TOTAL = GRAND_TOTAL;
    }

    public double getDPP(){
        return DPP;
    }
    public void setDPP(double DPP){
        this.DPP = DPP;
    }

    public double getVAT(){
        return VAT;
    }
    public void setVAT(double VAT){
        this.VAT = VAT;
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