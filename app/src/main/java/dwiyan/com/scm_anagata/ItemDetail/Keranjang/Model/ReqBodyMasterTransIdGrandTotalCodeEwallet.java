package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

public class ReqBodyMasterTransIdGrandTotalCodeEwallet {
    private String MasterTransId;
    private String GrandTotal;
    private String UserID;
    private String CodeEwallet;


    public ReqBodyMasterTransIdGrandTotalCodeEwallet(String masterTransId, String grandTotal, String userID, String codeEwallet) {
        MasterTransId = masterTransId;
        GrandTotal = grandTotal;
        UserID = userID;
        CodeEwallet = codeEwallet;
    }

    public String getCodeEwallet() {
        return CodeEwallet;
    }

    public void setCodeEwallet(String codeEwallet) {
        CodeEwallet = codeEwallet;
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
