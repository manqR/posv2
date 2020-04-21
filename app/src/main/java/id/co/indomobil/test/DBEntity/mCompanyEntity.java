package id.co.indomobil.test.DBEntity;

import java.sql.Timestamp;

public class mCompanyEntity {
    public String RECORD_STATUS;
    public String COMPANY_CODE;
    public String COMPANY_NAME;
    public String COMPANY_ADDRESS;
    public String CITY_CODE;
    public String PHONE_NO;
    public String EMAIL_ADDRESS;
    public String COMPANY_NPWP;
    public String PIC_NAME;
    public String MAC_ADDRESS;
    public Timestamp LAST_DATA_CHANGED;
    public Timestamp LAST_SYNC;
    public String CREATION_USERID;
    public Timestamp CREATION_DATETIME;
    public String CHANGE_USERID;
    public Timestamp CHANGE_DATETIME;

    public String getRECORD_STATUS(){ return RECORD_STATUS; }
    public void setRECORD_STATUS(String RECORD_STATUS){
        this.RECORD_STATUS = RECORD_STATUS;
    }

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public String getCOMPANY_NAME(){
        return COMPANY_NAME;
    }
    public void setCOMPANY_NAME(String COMPANY_NAME){ this.COMPANY_NAME = COMPANY_NAME; }

    public String getCOMPANY_ADDRESS() {return COMPANY_ADDRESS;}
    public void setCOMPANY_ADDRESS(String COMPANY_ADDRESS) { this.COMPANY_ADDRESS = COMPANY_ADDRESS;}

    public String getCITY_CODE(){
        return CITY_CODE;
    }
    public void setCITY_CODE(String CITY_CODE){
        this.CITY_CODE = CITY_CODE;
    }

    public String getPHONE_NO(){
        return PHONE_NO;
    }
    public void setPHONE_NO(String PHONE_NO){ this.PHONE_NO = PHONE_NO; }

    public String getEMAIL_ADDRESS() {return EMAIL_ADDRESS;}
    public void setEMAIL_ADDRESS(String EMAIL_ADDRESS) { this.EMAIL_ADDRESS = EMAIL_ADDRESS;}

    public String getCOMPANY_NPWP() {return COMPANY_NPWP;}
    public void setCOMPANY_NPWP(String COMPANY_NPWP) { this.COMPANY_NPWP = COMPANY_NPWP;}

    public String getPIC_NAME(){return PIC_NAME;}
    public void setPIC_NAME(String PIC_NAME){
        this.PIC_NAME = PIC_NAME;
    }

    public String getMAC_ADDRESS(){return MAC_ADDRESS;}
    public void setMAC_ADDRESS(String MAC_ADDRESS){
        this.MAC_ADDRESS = MAC_ADDRESS;
    }

    public Timestamp getLAST_DATA_CHANGED(){
        return LAST_DATA_CHANGED;
    }
    public void setLAST_DATA_CHANGED(Timestamp LAST_DATA_CHANGED){this.LAST_DATA_CHANGED = LAST_DATA_CHANGED;}

    public Timestamp getLAST_SYNC(){
        return LAST_SYNC;
    }
    public void setLAST_SYNC(Timestamp LAST_SYNC){this.LAST_SYNC = LAST_SYNC;}

    public Timestamp getCREATION_DATETIME(){
        return CREATION_DATETIME;
    }
    public void setCREATION_DATETIME(Timestamp CREATION_DATETIME){this.CREATION_DATETIME = CREATION_DATETIME;}

    public String getCREATION_USER_ID() {return CREATION_USERID;}
    public void setCREATION_USER_ID(String CREATION_USERID) { this.CREATION_USERID = CREATION_USERID;}

    public String getCHANGE_USER_ID(){
        return CHANGE_USERID;
    }
    public void setCHANGE_USER_ID(String CHANGE_USERID){this.CHANGE_USERID = CHANGE_USERID;}

    public Timestamp getCHANGE_DATETIME(){
        return CHANGE_DATETIME;
    }
    public void setCHANGE_DATETIME(Timestamp CHANGE_DATETIME){this.CHANGE_DATETIME = CHANGE_DATETIME;}
}
