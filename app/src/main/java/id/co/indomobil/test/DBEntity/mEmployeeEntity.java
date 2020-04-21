package id.co.indomobil.test.DBEntity;

import java.sql.Timestamp;

public class mEmployeeEntity {
    private String RECORD_STATUS;
    private String EMPLOYEE_NO;
    private String EMPLOYEE_NAME;
    private String EMPLOYEE_ADDRESS;
    private String CITY_CODE;
    private String COMPANY_CODE;
    private String ACCESS_CODE;
    private String HASH_PASS;
    private String KEY_PASS;
    private String PHONE_NO;
    private String JOB_POSITION;
    private String CREATION_USER_ID;
    private Timestamp CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Timestamp CHANGE_DATETIME;

    public String getRECORD_STATUS(){
        return RECORD_STATUS;
    }
    public void setRECORD_STATUS(String RECORD_STATUS){
        this.EMPLOYEE_NO = RECORD_STATUS;
    }
    public String getEMPLOYEE_NO(){
        return EMPLOYEE_NO;
    }
    public void setEMPLOYEE_NO(String EMPLOYEE_NO){
        this.EMPLOYEE_NO = EMPLOYEE_NO;
    }
    public String getEMPLOYEE_NAME(){
        return EMPLOYEE_NAME;
    }
    public void setEMPLOYEE_NAME(String EMPLOYEE_NAME){ this.EMPLOYEE_NAME = EMPLOYEE_NAME; }
    public String getEMPLOYEE_ADDRESS() {return EMPLOYEE_ADDRESS;}
    public void setEMPLOYEE_ADDRESS(String EMPLOYEE_ADDRESS) { this.EMPLOYEE_ADDRESS = EMPLOYEE_ADDRESS;}
    public String getCITY_CODE(){
        return CITY_CODE;
    }
    public void setCITY_CODE(String CITY_CODE){
        this.CITY_CODE = CITY_CODE;
    }
    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){ this.COMPANY_CODE = COMPANY_CODE; }
    public String getACCESS_CODE() {return ACCESS_CODE;}
    public void setACCESS_CODE(String ACCESS_CODE) { this.ACCESS_CODE = ACCESS_CODE;}
    public String getHASH_PASS() {return HASH_PASS;}
    public void setHASH_PASS(String HASH_PASS) { this.HASH_PASS = HASH_PASS;}
    public String getKEY_PASS(){return KEY_PASS;}
    public void setKEY_PASS(String KEY_PASS){
        this.KEY_PASS = KEY_PASS;
    }
    public String getPHONE_NO(){ return PHONE_NO; }
    public void setPHONE_NO(String PHONE_NO){ this.PHONE_NO = PHONE_NO; }
    public String getJOB_POSITION(){ return JOB_POSITION; }
    public void setJOB_POSITION(String JOB_POSITION){ this.JOB_POSITION = JOB_POSITION; }
    public String getCREATION_USER_ID() {return CREATION_USER_ID;}
    public void setCREATION_USER_ID(String CREATION_USER_ID) { this.CREATION_USER_ID = CREATION_USER_ID;}
    public Timestamp getCREATION_DATETIME(){
        return CREATION_DATETIME;
    }
    public void setCREATION_DATETIME(Timestamp CREATION_DATETIME){this.CREATION_DATETIME = CREATION_DATETIME;}
    public String getCHANGE_USER_ID(){
        return CHANGE_USER_ID;
    }
    public void setCHANGE_USER_ID(String CHANGE_USER_ID){this.CHANGE_USER_ID = CHANGE_USER_ID;}
    public Timestamp getCHANGE_DATETIME(){
        return CHANGE_DATETIME;
    }
    public void setCHANGE_DATETIME(Timestamp CHANGE_DATETIME){this.CHANGE_DATETIME = CHANGE_DATETIME;}
}
