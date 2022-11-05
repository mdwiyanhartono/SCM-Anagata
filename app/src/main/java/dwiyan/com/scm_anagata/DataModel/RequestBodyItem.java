package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyItem {
    private String categid;
    private String UserId;
    private String Text;

    public RequestBodyItem(String categid, String userId, String text) {
        this.categid = categid;
        UserId = userId;
        Text = text;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
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
