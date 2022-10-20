package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyUserIdToken {
    private String UserId;
    private String Token;

    public RequestBodyUserIdToken(String userId, String token) {
        UserId = userId;
        Token = token;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
