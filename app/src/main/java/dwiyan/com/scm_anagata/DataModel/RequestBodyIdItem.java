package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyIdItem {
    private String ItemId;
    private String UserId;

    public RequestBodyIdItem(String itemId, String userId) {
        ItemId = itemId;
        UserId = userId;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
