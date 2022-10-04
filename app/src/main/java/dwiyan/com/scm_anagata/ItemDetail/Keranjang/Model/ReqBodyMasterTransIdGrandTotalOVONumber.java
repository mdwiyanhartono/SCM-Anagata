package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

public class ReqBodyMasterTransIdGrandTotalOVONumber {
    private String MasterTransId;
    private String GrandTotal;
    private String UserID;
    private String MobileNumber;

    public ReqBodyMasterTransIdGrandTotalOVONumber(String masterTransId, String grandTotal, String userID, String mobileNumber) {
        MasterTransId = masterTransId;
        GrandTotal = grandTotal;
        UserID = userID;
        MobileNumber = mobileNumber;
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

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }
}
