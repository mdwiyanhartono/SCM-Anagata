package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

public class ReqBodyMasterTransIdGrandTotal {
    private String MasterTransId;
    private String GrandTotal;
    private String UserID;
    private String BankKode;

    public ReqBodyMasterTransIdGrandTotal(String masterTransId, String grandTotal, String userID, String bankKode) {
        MasterTransId = masterTransId;
        GrandTotal = grandTotal;
        UserID = userID;
        BankKode = bankKode;
    }

    public String getBankKode() {
        return BankKode;
    }

    public void setBankKode(String bankKode) {
        BankKode = bankKode;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getMasterTransId() {
        return MasterTransId;
    }

    public void setMasterTransId(String masterTransId) {
        MasterTransId = masterTransId;
    }

    public String getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        GrandTotal = grandTotal;
    }
}
