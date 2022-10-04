package dwiyan.com.scm_anagata.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ModelProduct {

    @SerializedName("ProductId")
    private String ProductId;
    @SerializedName("ProductName")
    private String ProductName;
    @SerializedName("ProductDesc")
    private String ProductDesc;
    @SerializedName("ProductPrice")
    private String ProductPrice;
    @SerializedName("ImageFileContent")
    private String ImageFileContent;
    @SerializedName("ImageFileType")
    private String ImageFileType;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getImageFileContent() {
        return ImageFileContent;
    }

    public void setImageFileContent(String imageFileContent) {
        ImageFileContent = imageFileContent;
    }

    public String getImageFileType() {
        return ImageFileType;
    }

    public void setImageFileType(String imageFileType) {
        ImageFileType = imageFileType;
    }
}
