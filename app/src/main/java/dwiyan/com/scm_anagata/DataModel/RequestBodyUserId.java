package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyUserId {
    private String UserId;

    public RequestBodyUserId(String userId) {
        UserId = userId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
