package dwiyan.com.scm_anagata.History.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelHistory {

    @SerializedName("transactionmasterid")
    private String transactionmasterid;
    @SerializedName("totalprice")
    private String totalprice;
    @SerializedName("ppn")
    private String ppn;
    @SerializedName("paymentmethodid")
    private String paymentmethodid;
    @SerializedName("ispaid")
    private String ispaid;
    @SerializedName("referencecode")
    private String referencecode;
    @SerializedName("isapprove")
    private String isapprove;
    @SerializedName("ppn_value")
    private String ppn_value;
    @SerializedName("createddate")
    private String createddate;

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getTransactionmasterid() {
        return transactionmasterid;
    }

    public void setTransactionmasterid(String transactionmasterid) {
        this.transactionmasterid = transactionmasterid;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getPpn() {
        return ppn;
    }

    public void setPpn(String ppn) {
        this.ppn = ppn;
    }

    public String getPaymentmethodid() {
        return paymentmethodid;
    }

    public void setPaymentmethodid(String paymentmethodid) {
        this.paymentmethodid = paymentmethodid;
    }

    public String getIspaid() {
        return ispaid;
    }

    public void setIspaid(String ispaid) {
        this.ispaid = ispaid;
    }

    public String getReferencecode() {
        return referencecode;
    }

    public void setReferencecode(String referencecode) {
        this.referencecode = referencecode;
    }

    public String getIsapprove() {
        return isapprove;
    }

    public void setIsapprove(String isapprove) {
        this.isapprove = isapprove;
    }

    public String getPpn_value() {
        return ppn_value;
    }

    public void setPpn_value(String ppn_value) {
        this.ppn_value = ppn_value;
    }
}
