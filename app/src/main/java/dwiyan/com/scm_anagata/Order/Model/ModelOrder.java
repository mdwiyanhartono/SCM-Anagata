package dwiyan.com.scm_anagata.Order.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelOrder {

    @SerializedName("transactionmasterid")
    private String transactionmasterid;
    @SerializedName("transactionmastercode")
    private String transactionmastercode;
    @SerializedName("invoicenumber")
    private String invoicenumber;
    @SerializedName("totalprice")
    private String invoicedate;
    @SerializedName("invoicedate")
    private String totalprice;
    @SerializedName("duedate")
    private String duedate;
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


    public String getTransactionmastercode() {
        return transactionmastercode;
    }

    public void setTransactionmastercode(String transactionmastercode) {
        this.transactionmastercode = transactionmastercode;
    }

    public String getCreateddate() {
        return createddate;
    }

    public String getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public String getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
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
