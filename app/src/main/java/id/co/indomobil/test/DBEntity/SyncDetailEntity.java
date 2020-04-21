package id.co.indomobil.test.DBEntity;


public class SyncDetailEntity {
    public String idtrans;
    public String productID;
    public String price;
    public String qty;

    public SyncDetailEntity(String idtrans, String productID, String price, String qty) {
        this.idtrans = idtrans;
        this.productID = productID;
        this.price= price;
        this.qty= qty;
    }
    public SyncDetailEntity() {

    }


    public String getTransID(){ return idtrans; }
    public void setTransID(String idtrans){
        this.idtrans = idtrans;
    }

    public String getProductID(){
        return productID;
    }
    public void setProductID(String productID){
        this.productID = productID;
    }

    public String getPrice(){
        return price;
    }
    public void setPrice(String price){ this.price = price; }

    public String getQty() {return qty;}
    public void setQty(String qty) { this.qty = qty;}

}

