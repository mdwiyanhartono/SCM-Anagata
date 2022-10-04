package dwiyan.com.scm_anagata.DataModel;

public class RequestBodyChat {
    private String userId;
    private String Pesan;
    private String To;

    public RequestBodyChat(String userId, String pesan, String to) {
        this.userId = userId;
        Pesan = pesan;
        To = to;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPesan() {
        return Pesan;
    }

    public void setPesan(String pesan) {
        Pesan = pesan;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }
}
