package id.co.indomobil.test.DBEntity;


import java.sql.Timestamp;

public class tGRPO0Entity {
    public String COMPANY_CODE;
    public int GRPO_SYS_NO;
    public String GRPO_DOC_NO;
    public Timestamp GRPO_DATE;
    public int PO_SYS_NO;
    public String PO_DOC_NO;
    public Timestamp PO_DATE;
    public String SUPPLIER_CODE;
    public int SUPPLIER_DO_SYS_NO;
    public String SUPPLIER_DO_DOC_NO;
    public double TOTAL;
    public double GRAND_TOTAL;
    public double DPP;
    public double VAT;
    public String CREATION_USER_ID;
    public Timestamp CREATION_DATETIME;
    public String CHANGE_USER_ID;
    public Timestamp CHANGE_DATETIME;
    public String STAFF_NAME;

    public tGRPO0Entity(String grpo_doc_no, String po_doc_no, Timestamp grpo_date, Timestamp po_date) {
        this.GRPO_DOC_NO = grpo_doc_no;
        this.PO_DOC_NO = po_doc_no;
        this.GRPO_DATE = grpo_date;
        this.PO_DATE = po_date;
    }

    public tGRPO0Entity() {

    }

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public int getGRPO_SYS_NO(){
        return GRPO_SYS_NO;
    }
    public void setGRPO_SYS_NO(int GRPO_SYS_NO){
        this.GRPO_SYS_NO = GRPO_SYS_NO;
    }

    public String getGRPO_DOC_NO(){
        return GRPO_DOC_NO;
    }
    public void setGRPO_DOC_NO(String GRPO_DOC_NO){
        this.GRPO_DOC_NO = GRPO_DOC_NO;
    }

    public Timestamp getGRPO_DATE(){
        return GRPO_DATE;
    }
    public void setGRPO_DATE(Timestamp GRPO_DATE){
        this.GRPO_DATE = GRPO_DATE;
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

    public String getSUPPLIER_CODE(){
        return SUPPLIER_CODE;
    }
    public void setSUPPLIER_CODE(String SUPPLIER_CODE){
        this.SUPPLIER_CODE = SUPPLIER_CODE;
    }

    public int getSUPPLIER_DO_SYS_NO(){
        return SUPPLIER_DO_SYS_NO;
    }
    public void setSUPPLIER_DO_SYS_NO(int SUPPLIER_DO_SYS_NO){
        this.SUPPLIER_DO_SYS_NO = SUPPLIER_DO_SYS_NO;
    }

    public String getSUPPLIER_DO_DOC_NO(){
        return SUPPLIER_DO_DOC_NO;
    }
    public void setSUPPLIER_DO_DOC_NO(String SUPPLIER_DO_DOC_NO){
        this.SUPPLIER_DO_DOC_NO = SUPPLIER_DO_DOC_NO;
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

    public String getSTAFF_NAME(){
        return STAFF_NAME;
    }
    public void setSTAFF_NAME(String STAFF_NAME){
        this.STAFF_NAME = STAFF_NAME;
    }

}
