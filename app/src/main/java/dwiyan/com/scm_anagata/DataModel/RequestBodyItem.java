package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyItem {
    private String categid;
    private String UserId;

    public RequestBodyItem(String categid, String userId) {
        this.categid = categid;
        UserId = userId;
    }

    public String getCategid() {
        return categid;
    }

    public void setCategid(String categid) {
        this.categid = categid;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
