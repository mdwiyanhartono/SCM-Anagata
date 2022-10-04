package dwiyan.com.scm_anagata.ItemDetail.Model;

public class ReqBodyRemoveKeranjang {
    private String userid;
    private String idtranschart;
    private String itemid;

    public ReqBodyRemoveKeranjang(String userid, String idtranschart, String itemid) {
        this.userid = userid;
        this.idtranschart = idtranschart;
        this.itemid = itemid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getIdtranschart() {
        return idtranschart;
    }

    public void setIdtranschart(String idtranschart) {
        this.idtranschart = idtranschart;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

}
