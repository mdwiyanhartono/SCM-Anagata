package dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model;

public class ReqBodyIDTransIDUser {
    private String idTrans;
    private String UserID;

    public ReqBodyIDTransIDUser(String idTrans, String userID) {
        this.idTrans = idTrans;
        UserID = userID;
    }

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
