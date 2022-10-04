package dwiyan.com.scm_anagata.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelConfirmTrans {

    @SerializedName("itemname")
    private String itemname;
    @SerializedName("description")
    private String description;
    @SerializedName("quantityapproved")
    private String quantityapproved;
    @SerializedName("totalpriceapproved")
    private String totalpriceapproved;
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

    public String getQuantityapproved() {
        return quantityapproved;
    }

    public void setQuantityapproved(String quantityapproved) {
        this.quantityapproved = quantityapproved;
    }

    public String getTotalpriceapproved() {
        return totalpriceapproved;
    }

    public void setTotalpriceapproved(String totalpriceapproved) {
        this.totalpriceapproved = totalpriceapproved;
    }

    public String getUomname() {
        return uomname;
    }

    public void setUomname(String uomname) {
        this.uomname = uomname;
    }
}
