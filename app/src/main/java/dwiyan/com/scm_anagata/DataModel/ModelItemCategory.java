package dwiyan.com.scm_anagata.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelItemCategory {

    @SerializedName("categoryid")
    private String categoryid;
    @SerializedName("categoryname")
    private String categoryname;
    @SerializedName("categorycode")
    private String categorycode;
    @SerializedName("categorydesc")
    private String categorydesc;
    @SerializedName("ImageFileContent")
    private String ImageFileContent;
    @SerializedName("imagepath")
    private String imagepath;

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }

    public String getCategorydesc() {
        return categorydesc;
    }

    public void setCategorydesc(String categorydesc) {
        this.categorydesc = categorydesc;
    }

    public String getImageFileContent() {
        return ImageFileContent;
    }

    public void setImageFileContent(String imageFileContent) {
        ImageFileContent = imageFileContent;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
