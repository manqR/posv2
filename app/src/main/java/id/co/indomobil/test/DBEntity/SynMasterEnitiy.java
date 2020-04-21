package id.co.indomobil.test.DBEntity;

public class SynMasterEnitiy {
    public String idtrans;
    public String total_price;
    public String tanggal;
    public String is_sync;



    public String getTransID(){ return idtrans; }
    public void setTransID(String idtrans){
        this.idtrans = idtrans;
    }

    public String getTotalPric(){
        return total_price;
    }
    public void setTotalPrice(String total_price){
        this.total_price = total_price;
    }

    public String getTanggal(){
        return tanggal;
    }
    public void setTanggal(String tanggal){ this.tanggal = tanggal; }

    public String getIsSync() {return is_sync;}
    public void setIsSync(String is_sync) { this.is_sync = is_sync;}


}

