package dwiyan.com.scm_anagata.DataModel;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponseModelConfirmasiTrans {

    String kode;
    String message;
    String transactioncode;
    String invoicecode;
    String transactionDate;
    String transactionid;
    String transactionDeliveryFee;
    String companyname;
    String companyaddress;
    String companycity;
    String companyprovince;
    String companycodepos;
    String companyemail;
    String companyphone;
    String transactionQnty;
    String transactionStatus;
    String transactionGrandTotal;
    String transactionStatusID;
    String transactionIspaid;
    private List<ModelConfirmTrans> result;


    public String getInvoicecode() {
        return invoicecode;
    }

    public void setInvoicecode(String invoicecode) {
        this.invoicecode = invoicecode;
    }

    public String getTransactionIspaid() {
        return transactionIspaid;
    }

    public void setTransactionIspaid(String transactionIspaid) {
        this.transactionIspaid = transactionIspaid;
    }

    public String getTransactionStatusID() {
        return transactionStatusID;
    }

    public void setTransactionStatusID(String transactionStatusID) {
        this.transactionStatusID = transactionStatusID;
    }

    public String getTransactionGrandTotal() {
        return transactionGrandTotal;
    }

    public void setTransactionGrandTotal(String transactionGrandTotal) {
        this.transactionGrandTotal = transactionGrandTotal;
    }

    public String getTransactionQnty() {
        return transactionQnty;
    }

    public void setTransactionQnty(String transactionQnty) {
        this.transactionQnty = transactionQnty;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactioncode() {
        return transactioncode;
    }

    public void setTransactioncode(String transactioncode) {
        this.transactioncode = transactioncode;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getTransactionDeliveryFee() {
        return transactionDeliveryFee;
    }

    public void setTransactionDeliveryFee(String transactionDeliveryFee) {
        this.transactionDeliveryFee = transactionDeliveryFee;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String getCompanycity() {
        return companycity;
    }

    public void setCompanycity(String companycity) {
        this.companycity = companycity;
    }

    public String getCompanyprovince() {
        return companyprovince;
    }

    public void setCompanyprovince(String companyprovince) {
        this.companyprovince = companyprovince;
    }

    public String getCompanycodepos() {
        return companycodepos;
    }

    public void setCompanycodepos(String companycodepos) {
        this.companycodepos = companycodepos;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getCompanyphone() {
        return companyphone;
    }

    public void setCompanyphone(String companyphone) {
        this.companyphone = companyphone;
    }

    public List<ModelConfirmTrans> getResult() {
        return result;
    }

    public void setResult(List<ModelConfirmTrans> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
