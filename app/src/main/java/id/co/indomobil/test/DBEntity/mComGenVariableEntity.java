package id.co.indomobil.test.DBEntity;


public class mComGenVariableEntity {
    public String COMPANY_CODE;
    public String VARIABLE;
    public String VALUE;
    public String DESCRIPTION;
    public int ORDER_NO;

    public String getCOMPANY_CODE(){
        return COMPANY_CODE;
    }
    public void setCOMPANY_CODE(String COMPANY_CODE){
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public String getVARIABLE(){
        return VARIABLE;
    }
    public void setVARIABLE(String VARIABLE){ this.VARIABLE = VARIABLE; }

    public String getVALUE(){
        return VALUE;
    }
    public void setVALUE(String VALUE){ this.VALUE = VALUE; }

    public String getDESCRIPTION(){
        return DESCRIPTION;
    }
    public void setDESCRIPTION(String DESCRIPTION){ this.DESCRIPTION = DESCRIPTION; }

    public int getORDER_NO(){
        return ORDER_NO;
    }
    public void setORDER_NO(int ORDER_NO){
        this.ORDER_NO = ORDER_NO;
    }
}
