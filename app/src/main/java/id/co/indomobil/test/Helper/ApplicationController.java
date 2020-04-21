package id.co.indomobil.test.Helper;

import android.app.Application;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Vector;

public class ApplicationController extends Application {
    public String EmpNo = "";
    public String EmpName = "";
    public String JobPosition = "";
    public String CompanyCode = "";
    public String CompanyName = "";
    public String CompanyAddress1 = "";
    public String CompanyAddress2 = "";
    public String CompanyTelp = "";
    public Boolean IsNew = true;
    public Boolean IsDBUploaded = true;

    public Bundle BundleObjectCollection = new Bundle();
    public Vector VariousObjectCollection = new Vector();
    public Vector SalesObjectCollection = new Vector();
    public Vector MasterObjectCollection = new Vector();
    public Vector ReceivingObjectCollection = new Vector();
    public HashMap KeyValuePairCollection = new HashMap();

    private static ApplicationController singleton;

    public ApplicationController getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
