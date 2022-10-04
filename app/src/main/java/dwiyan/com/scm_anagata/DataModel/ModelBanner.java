package dwiyan.com.scm_anagata.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelBanner {


    @SerializedName("BannerId")
    private String BannerId;
    @SerializedName("BannerLabel")
    private String BannerLabel;
    @SerializedName("BannerDescription")
    private String BannerDescription;
    @SerializedName("ImageFileContent")
    private String ImageFileContent;

    public String getImageFileContent() {
        return ImageFileContent;
    }

    public String getBannerId() {
        return BannerId;
    }

    public void setBannerId(String bannerId) {
        BannerId = bannerId;
    }

    public String getBannerLabel() {
        return BannerLabel;
    }

    public void setBannerLabel(String bannerLabel) {
        BannerLabel = bannerLabel;
    }

    public String getBannerDescription() {
        return BannerDescription;
    }

    public void setBannerDescription(String bannerDescription) {
        BannerDescription = bannerDescription;
    }

    public void setImageFileContent(String imageFileContent) {
        ImageFileContent = imageFileContent;
    }
}
