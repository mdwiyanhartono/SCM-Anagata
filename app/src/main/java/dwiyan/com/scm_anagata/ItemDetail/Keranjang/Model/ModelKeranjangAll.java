package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelKeranjangAll {

    @SerializedName("transactioncartid")
    private String transactioncartid;
    @SerializedName("itemid")
    private String itemid;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("price")
    private String price;
    @SerializedName("totalprice")
    private String totalprice;
    @SerializedName("itemname")
    private String itemname;
    @SerializedName("description")
    private String description;
    @SerializedName("uomname")
    private String uomname;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUomname() {
        return uomname;
    }

    public void setUomname(String uomname) {
        this.uomname = uomname;
    }

    public String getTransactioncartid() {
        return transactioncartid;
    }

    public void setTransactioncartid(String transactioncartid) {
        this.transactioncartid = transactioncartid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
