package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyItemPagination {
    private String categid;
    private String UserId;
    private String Text;
    private String Start;
    private String Limit;

    public RequestBodyItemPagination(String categid, String userId, String text, String start, String limit) {
        this.categid = categid;
        UserId = userId;
        Text = text;
        Start = start;
        Limit = limit;
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
