package id.co.indomobil.test.Helper;

import android.content.Context;

import java.sql.Timestamp;
import java.util.ArrayList;

import id.co.indomobil.test.BO.EmployeeBO;
import id.co.indomobil.test.DBEntity.mEmployeeEntity;

public class IndostationMasterData {
    private Context mContext = null;
    public ArrayList<mEmployeeEntity> EmployeeList;

    public IndostationMasterData(Context ctx) {
        this.mContext = ctx;
        EmployeeList = new ArrayList<mEmployeeEntity>();
    }

    //== Employee Data ==//
    //Change Password
    public String ChangePassword(String EmployeeNo, String HashPass) {
        String retvalue = "{}";

        EmployeeBO objData = new EmployeeBO();
        objData.EMPLOYEE_NO = EmployeeNo;
        objData.HASH_PASS = HashPass;
        objData.CHANGE_USERID = EmployeeNo;
        objData.CHANGE_DATETIME = new Timestamp(System.currentTimeMillis());

        retvalue = JsonHelper.serializeObjectToJson(objData);
        return "["+retvalue+"]";
    }
}
