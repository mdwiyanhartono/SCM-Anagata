package dwiyan.com.scm_anagata.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelChat {

    @SerializedName("chatid")
    private String chatid;
    @SerializedName("type")
    private String type;
    @SerializedName("chattext")
    private String chattext;
    @SerializedName("createddate")
    private String createddate;
    @SerializedName("displayname")
    private String displayname;

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChattext() {
        return chattext;
    }

    public void setChattext(String chattext) {
        this.chattext = chattext;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
}
