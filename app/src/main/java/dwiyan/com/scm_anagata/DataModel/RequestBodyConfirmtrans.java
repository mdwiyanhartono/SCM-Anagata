package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyConfirmtrans {
    private String UserId;
    private String MasterTransId;

    public RequestBodyConfirmtrans(String userId, String masterTransId) {
        UserId = userId;
        MasterTransId = masterTransId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getMasterTransId() {
        return MasterTransId;
    }

    public void setMasterTransId(String masterTransId) {
        MasterTransId = masterTransId;
    }
}
