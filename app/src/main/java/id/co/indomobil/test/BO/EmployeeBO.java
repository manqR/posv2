package id.co.indomobil.test.BO;

import java.sql.Timestamp;

public class EmployeeBO {
    public String RECORD_STATUS;
    public String EMPLOYEE_NO;
    public String EMPLOYEE_NAME;
    public String EMPLOYEE_ADDRESS;
    public String CITY_CODE;
    public String COMPANY_CODE;
    public String ACCESS_CODE;
    public String HASH_PASS;
    public String KEY_PASS;
    public String PHONE_NO;
    public String JOB_POSITION;
    public String CREATION_USERID;
    public Timestamp CREATION_DATETIME;
    public String CHANGE_USERID;
    public Timestamp CHANGE_DATETIME;

    public EmployeeBO(){
        RECORD_STATUS = "";
        EMPLOYEE_NO = "";
        EMPLOYEE_NAME = "";
        EMPLOYEE_ADDRESS ="";
        CITY_CODE = "";
        COMPANY_CODE ="";
        ACCESS_CODE = "";
        HASH_PASS="";
        KEY_PASS ="";
        PHONE_NO="";
        JOB_POSITION ="";
        CREATION_USERID = "";
        CREATION_DATETIME = null;
        CHANGE_USERID = "";
        CHANGE_DATETIME = null;
    }
}
