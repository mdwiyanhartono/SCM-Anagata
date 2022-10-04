package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelRequestItem {

    @SerializedName("ItemName")
    private String ItemName;
    @SerializedName("VisitDate")
    private String VisitDate;
    @SerializedName("TimeRequest")
    private String TimeRequest;
    @SerializedName("status")
    private String status;

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getVisitDate() {
        return VisitDate;
    }

    public void setVisitDate(String visitDate) {
        VisitDate = visitDate;
    }

    public String getTimeRequest() {
        return TimeRequest;
    }

    public void setTimeRequest(String timeRequest) {
        TimeRequest = timeRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
