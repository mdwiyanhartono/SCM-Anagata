package dwiyan.com.scm_anagata.ItemDetail.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelItemDetail {

    @SerializedName("itemid")
    private String itemid;
    @SerializedName("countkeranjang")
    private String countkeranjang;
    @SerializedName("itemname")
    private String itemname;
    @SerializedName("itemuom")
    private String itemuom;
    @SerializedName("itemprice")
    private String itemprice;
    @SerializedName("itempriceview")
    private String itempriceview;
    @SerializedName("itemdesc")
    private String itemdesc;
    @SerializedName("total")
    private String total;
    @SerializedName("idtranschart")
    private String idtranschart;
    @SerializedName("count")
    private String count;
    @SerializedName("imagefile")
    private String imagefile;

    public String getItemuom() {
        return itemuom;
    }

    public void setItemuom(String itemuom) {
        this.itemuom = itemuom;
    }

    public String getItempriceview() {
        return itempriceview;
    }

    public void setItempriceview(String itempriceview) {
        this.itempriceview = itempriceview;
    }

    public String getIdtranschart() {
        return idtranschart;
    }

    public void setIdtranschart(String idtranschart) {
        this.idtranschart = idtranschart;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCountkeranjang() {
        return countkeranjang;
    }

    public void setCountkeranjang(String countkeranjang) {
        this.countkeranjang = countkeranjang;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getImagefile() {
        return imagefile;
    }

    public void setImagefile(String imagefile) {
        this.imagefile = imagefile;
    }
}
