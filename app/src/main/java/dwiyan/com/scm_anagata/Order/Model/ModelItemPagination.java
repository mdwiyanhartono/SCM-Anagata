package dwiyan.com.scm_anagata.Order.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelItemPagination {

    @SerializedName("itemid")
    private String itemid;
    @SerializedName("countChart")
    private String countChart;
    @SerializedName("itemname")
    private String itemname;
    @SerializedName("itemuom")
    private String itemuom;
    @SerializedName("itemcode")
    private String itemcode;
    @SerializedName("itemdesc")
    private String itemdesc;
    @SerializedName("itemprice")
    private String itemprice;
    @SerializedName("itempriceview")
    private String itempriceview;
    @SerializedName("ImageFileContent")
    private String ImageFileContent;

    public ModelItemPagination(String itemid, String countChart, String itemname, String itemuom, String itemcode, String itemdesc, String itemprice, String itempriceview, String imageFileContent, String fileContent) {
        this.itemid = itemid;
        this.countChart = countChart;
        this.itemname = itemname;
        this.itemuom = itemuom;
        this.itemcode = itemcode;
        this.itemdesc = itemdesc;
        this.itemprice = itemprice;
        this.itempriceview = itempriceview;
        ImageFileContent = imageFileContent;
    }

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

    public String getCountChart() {
        return countChart;
    }

    public void setCountChart(String countChart) {
        this.countChart = countChart;
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

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getImageFileContent() {
        return ImageFileContent;
    }

    public void setImageFileContent(String imageFileContent) {
        ImageFileContent = imageFileContent;
    }
}
