package dwiyan.com.scm_anagata.ItemDetail.Model;

public class ReqBodyUpdateKeranjang {
    private String userid;
    private String idtranschart;
    private String itemid;
    private String count;

    public ReqBodyUpdateKeranjang(String userid, String idtranschart, String itemid, String count) {
        this.userid = userid;
        this.idtranschart = idtranschart;
        this.itemid = itemid;
        this.count = count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
