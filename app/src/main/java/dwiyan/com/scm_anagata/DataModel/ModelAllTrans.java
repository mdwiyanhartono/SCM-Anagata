package dwiyan.com.scm_anagata.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelAllTrans {

    @SerializedName("itemname")
    private String itemname;
    @SerializedName("description")
    private String description;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("totalprice")
    private String totalprice;
    @SerializedName("uomname")
    private String uomname;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getUomname() {
        return uomname;
    }

    public void setUomname(String uomname) {
        this.uomname = uomname;
    }
}
